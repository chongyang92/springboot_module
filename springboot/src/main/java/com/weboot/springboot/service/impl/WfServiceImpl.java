package com.weboot.springboot.service.impl;

import com.weboot.springboot.model.wf.PendingTaskModel;
import com.weboot.springboot.model.wf.ProcdefModel;
import com.weboot.springboot.model.wf.TaskCompleteModel;
import com.weboot.springboot.model.wf.WfTranInfo;
import com.weboot.springboot.service.WfService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Service
public class WfServiceImpl implements WfService {

    /**
     * Activiti 的表都以 ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的 API 对应。
     *
     * ① ACT_RE_*: 'RE'表示 repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
     * ② ACT_RU_*: 'RU'表示 runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。
     *    Activiti 只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
     * ③ ACT_HI_*: 'HI'表示 history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
     * ④ ACT_GE_*: GE 表示 general。通用数据， 用于不同场景下。
     */

    public static final Logger logger = LoggerFactory.getLogger(WfServiceImpl.class);
    /**
     * 流程定义bpmn文件classpath
     */
    @Value(value = "${workflow.deploy.bpmn.classpath}")
    private String bpmnClasspath;

    /**
     * 流程定义png文件classpath
     */
    @Value(value = "${workflow.deploy.png.classpath}")
    private String pngClasspath;

    //流程部署createDeployment()、流程部署查询createDeploymentQuery()、流程部署删除deleteDeployment(deploymentId)、设置类别setDeploymentCategory(deploymentId,category)、设置部署KEY setDeploymentKey(deploymentId,key)
    //流程定义查询 createProcessDefinitionQuery()、流程定义激活activateProcessDefinitionByKey(key)、流程定义挂起suspendProcessDefinitionByKey(key)、获取流程定义实例getProcessDefinition(processDefinitionId)
    //获取流程定义图 getResourceAsStream(deploymentId,diagramResourceName)
    /**
     * 是 activiti 的资源管理类，提供了管理和控制流程发布包和流程定义的操作。使用工作流建模工
     * 具设计的业务流程图需要使用此 service 将流程定义文件的内容部署到计算机。
     * 除了部署流程定义以外还可以：
     * 查询引擎中的发布包和流程定义。
     * 暂停或激活发布包，对应全部和特定流程定义。 暂停意味着它们不能再执行任何操作了，激活
     * 是对应的反向操作。
     * 获得多种资源，像是包含在发布包里的文件， 或引擎自动生成的流程图。
     * 获得流程定义的 pojo 版本， 可以用来通过 java 解析流程，而不必通过 xml。
     */
    @Resource
    private RepositoryService repositoryService;

    //创建并启动一个流程实例 startProcessInstanceByKey(key,var)、流程实例查询createProcessInstanceQuery()
    //变量信息获取getVariableInstance(executionId,variableName)、设置全局流程变量setVariables(executionId, Map<String, ? extends Object> variables)
    //监听信息addEventListener(activitiEventListener)、删除监听removeEventListener(activitiEventListener)
    /**
     * 它是 activiti 的流程运行管理类。可以从这个服务类中获取很多关于流程执行相关的信息
     */
    @Resource
    private RuntimeService runtimeService;


    //任务信息查询createTaskQuery()：条件包括候选人、
    // 完成任务complete(taskId)、完成任务complete(taskId，varibales)、删除任务deleteTask(taskId)
    //获取任务处理人getIdentityLinksForTask(taskId)
    //获取变量getVariableInstance(taskId, variableName)、hasVariable(String taskId, String variableName)、removeVariable(taskId, variableName)
    //getTaskEvents(taskId)
    //设置全局流程变量setVariables(String taskId, Map<String, ? extends Object> variables)
    //候选人用户拾取任务claim(String taskId, String userId)这里的userId就是用户名lisi，改方法完成后，候选用户变为任务处理人assignee
    //设置任务处理人或拾取任务后不处理任务setAssignee(taskId, userId) userId为lisi或null
    /**
     * 是 activiti 的任务管理类。可以从这个类中获取任务的信息。
     */
    @Resource
    private TaskService taskService;

    //历史任务查询createHistoricTaskInstanceQuery()、删除历史任务进程实例deleteHistoricProcessInstance(processInstanceId)
    //获取历史任务处理人getHistoricIdentityLinksForTask(taskId)
    //获取流程定义相关处理人 getHistoricIdentityLinksForProcessInstance(processInstanceId)
    /**
     * 是 activiti 的历史管理类，可以查询历史信息，执行流程时，引擎会保存很多数据（根据配置），比
     * 如流程实例启动时间，任务的参与者， 完成任务的时间，每个流程实例的执行路径，等等。 这个
     * 服务主要通过查询功能来获得这些数据。
     */
    @Resource
    private HistoryService historyService;

    /**
     * 将bpmn和png文件压缩成.zip包，然后会自动解压，然后放到deployAuthWorkflow方法涉及的三张表，结果一致(除了act_re_procdef中bpmn和路径)
     *
     * @param zipIs
     * @return
     */
    @Override
    public String deployAuthWorkflow(InputStream zipIs) {

        ZipInputStream zipInputStream = new ZipInputStream(zipIs);//默认utf-8
        Deployment deployment = repositoryService.createDeployment()
                .category("部署类别zip")
                .name("部署名称zip")
                .addZipInputStream(zipInputStream)
                .deploy();
        return deployment.getId();
    }

    /**
     * act_re_deploment 流程部署信息  部署的流程名称、类别、时间、版本(自动更新)
     * act_re_procdef   流程定义信息  bpmn文件、图的名称和路径、部署ID、版本信息
     * act_ge_bytearray 流程定义的bpmn文件和图的字节流、部署ID
     * @return
     */
    @Override
    public String deployAuthWorkflow() {
        Deployment deployment = repositoryService.createDeployment()
                .category("部署类别test")
                .name("部署名称test")
                .addClasspathResource(bpmnClasspath)  //加载资源文件，一次只能加载一个
                .addClasspathResource(pngClasspath)
                .deploy();  //完成部署
        return deployment.getId();
    }


    /**
     * 查询流程定义信息
     * act_re_prodef 流程定义信息 流程定义名称、KEY、版本、部署ID、bpmn和png名称
     * @return
     */
    @Override
    public List<ProcdefModel> listProcdef() {
        logger.info("listProcdefParams is null when listProcdef");

        List<ProcdefModel> procdefModelList = new ArrayList<ProcdefModel>();
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                 .orderByProcessDefinitionKey()
                .asc()
                .orderByProcessDefinitionVersion()
                .desc()
                .list();
        if(processDefinitionList != null && !processDefinitionList.isEmpty()){
            Deployment deployment;
            for(ProcessDefinition processDefinition : processDefinitionList){
                ProcdefModel procdefModel = new ProcdefModel();
                procdefModel.setCategory(processDefinition.getCategory());
                procdefModel.setDeploymentId(processDefinition.getDeploymentId());
                procdefModel.setDescription(processDefinition.getDescription());
                procdefModel.setDiagramResourceName(processDefinition.getDiagramResourceName());
                procdefModel.setKey(processDefinition.getKey());
                procdefModel.setName(processDefinition.getName());
                procdefModel.setProcdefId(processDefinition.getId());
                procdefModel.setVersion(processDefinition.getVersion());
                deployment = repositoryService.createDeploymentQuery().
                        deploymentId(processDefinition.getDeploymentId()).
                        singleResult();
                procdefModel.setCategory(deployment.getCategory());
                procdefModel.setCreateTime(deployment.getDeploymentTime());
                procdefModelList.add(procdefModel);
            }
        }

        return procdefModelList;
    }

    /**
     * 获取流程定义图
     * diagramResourceName png图片资源名称字段 可以通过repositoryService.getProcessDefinition().getDiagramResourceName()获取
     * resourceName bpmn资源名称字段 可以通过repositoryService.getProcessDefinition().getResourceName()获取
     * @param deploymentId
     * @param diagramResourceName
     * @return
     */
    @Override
    public InputStream getProcdefDiagram(String deploymentId,String diagramResourceName) {
        if (StringUtils.isBlank(deploymentId) || StringUtils.isBlank(diagramResourceName)) {
            logger.info("processDefinitionId is blank when getDiagramResource");
        }
        return repositoryService.getResourceAsStream(deploymentId,diagramResourceName);
    }

    /**
     * 启动流程实例
     * act_hi_actinst 流程中节点实例历史信息，每个动作act节点的历史信息，和当前正在执行的节点，其endtime为空
     * act_hi_identitylink 参与者历史信息，每个节点的参与者，历史参与着和当前正在执行节点的参与者，当前节点未执行完成
     * act_hi_procinst 流程实例信息，启动了多少流程实例，有流程实例ID(processInstanceId)、开始时间、act动作开始ID，如果流程未执行完成，结束时间和act动作结束ID为空
     * act_ru_execution 应该是和act_hi_actinst对应正在执行的act动作，该动作未完成，有流程实例ID、当前act动作ID(流转到本处理人，但本处理人未完成)
     * act_ru_identitylink 当前流程实例参与者信息，该流程实例未完成，参与者是未完成实例的所有参与者
     * act_ru_task 未完成的流程实例中处理人和当前需要处理的动作ID信息，
     * @return
     */
    @Override
    public String startAuthWorkflow(String businessKey) {
        //根据流程定义的key（就是bpmn文件的Id myProcess_helloworld）来启动一个实例，activiti找该key下版本最高的流程定义
        //这个key就是act_re_procdef中的KEY_ 字段
        //一般情况下为了方便开发使用该方法启动一个流程实例

        //将信息加入map,以便传入流程中，参数信息
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "lchy");
        variables.put("day",10);
        //这里可以把业务ID businessKey传入runtimeService.startProcessInstanceByKey("myProcess_helloworld",businessKey,variables);
        //businessKey这个字段会保存在act_ru_execution里
        //通过流程实例可以获取String businessKey = processInstance.getBusinessKey();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_helloworld","businessKey",variables);
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_helloworld",variables);
        String processInstanceId = processInstance.getId();

        System.out.println(processInstanceId);
        System.out.println("正在活动的activiti ID"+processInstance.getActivityId());//null


        //根据流程定义的id来启动一个实例，这种方法一般不用
        //runtimeService.startProcessInstanceById(processDefinitionId);

        return processInstanceId;
    }

    /**
     * 查询某用户待处理任务列表
     * act_hi_taskinst userName用户需要处理的任务有哪些，taskId来自这里
     * @param userName
     * @param processInstanceId
     * @return
     */
    @Override
    public List<PendingTaskModel> listPendingTask(String userName,String processInstanceId) {
        List<PendingTaskModel> pendingTaskModels = new ArrayList<>();
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(userName)
                .processInstanceId(processInstanceId)
                .list();
        for(Task task : taskList){
            PendingTaskModel pendingTaskModel = new PendingTaskModel();
            pendingTaskModel.setTaskId(task.getId());
            pendingTaskModel.setTaskName(task.getName());
            pendingTaskModels.add(pendingTaskModel);
        }
        return pendingTaskModels;
    }

    /**
     * 根据任务ID完成任务
     * act_hi_actinst 当前用户的任务记录endTime本来为空，现在填充上时间了，新增一条记录为下一个任务处理人的记录
     * act_hi_identitylink 新增一条流程实例相关的参与者
     * act_hi_procinst 流程实例表无变化
     * act_hi_taskinst 新增一条记录，是表示任务流转到了下一个角色，有开始时间无结束时间。原taskId记录新增结束时间
     * act_ru_execution 应该是和act_hi_actinst对应正在执行的act动作，该动作未完成，有流程实例ID、当前act动作ID(流转到本处理人，但本处理人未完成)
     * 已经完成的流程实例不在act_ru_execution显示
     * act_ru_identitylink 当前流程实例参与者信息，该流程实例未完成，参与者是未完成实例的所有参与者，将下一个角色用户加进来
     * act_ru_task 未完成的流程实例当前走到了哪个act,该act由哪个角色的用户去处理，当前流程实例待处理任务的列表。之前的被删掉了，每走到下一个act之前的记录都会被删掉
     *              从流程定义图中摘取act节点的信息然后把上一条信息删除，只保留正在执行(未完成)的流程实例，正在执行的act节点和处理人
     * @param taskCompleteModel
     */
    @Override
    public void completeTask(TaskCompleteModel taskCompleteModel) {
        taskService.complete(taskCompleteModel.getTaskId());//传入act_hi_taskinst的 taskId
    }

    @Override
    public String queryProinsatanceState(String proinsatanceId) {
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId(proinsatanceId)
                .parentId(proinsatanceId)
                .singleResult();
        System.out.println(execution.getActivityId());
        return execution.getActivityId();
    }

    @Override
    public List<HistoricTaskInstance> listHistoryTask(String userName, String processInstanceId) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userName)
                .processInstanceId(processInstanceId)
                .list();
        return list;
    }

    /**
     * 删除已部署的流程定义
     * @param deployMentId
     */
    @Override
    public void deleteDeployment(String deployMentId){
        /**只删除部署相关的三张表中信息
         * act_re_deploment 流程部署信息  部署的流程名称、类别、时间、版本(自动更新)
         * * act_re_procdef   流程定义信息  bpmn文件、图的名称和路径、部署ID、版本信息
         * * act_ge_bytearray 流程定义的bpmn文件和图的字节流、部署ID
         */
        /**
         * 1) 使用 repositoryService 删除流程定义
         * 2) 如果该流程定义下没有正在运行的流程，则可以用普通删除。
         */
        //repositoryService.deleteDeployment(deployMentId);

        /**
         * 3) 如果该流程定义下存在已经运行的流程，使用普通删除报错，可用级联删除方法将流程及相关
         * 记录全部删除。项目开发中使用级联删除的情况比较多，删除操作一般只开放给超级管理员使
         * 用。
         */
        //将未完成的流程实例(ru表)及历史任务相关信息(his表)一并删除
        repositoryService.deleteDeployment(deployMentId,true);
    }

    /**
     * 挂起或激活流程定义相关的实例
     * 操作流程定义为挂起状态，该流程定义下边所有的流程实例全部暂停：
     * 流程定义为挂起状态该流程定义将不允许启动新的流程实例，同时该流程定义下所有的流程实例将
     * 全部挂起暂停执行。
     * 挂起场景，当请假人数过多，挂起流程，下月激活流程
     * @param processDefinitionKey
     */
    @Override
    public void suspendOrActivateProcessInstance(String processDefinitionKey){
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(processDefinitionKey)
                .latestVersion()
                .singleResult();
        boolean suspended = processDefinition.isSuspended();
        if(suspended) {
            repositoryService.activateProcessDefinitionById(processDefinition.getId(),true,null);
            System.out.println("流程定义"+processDefinitionKey+"相关实例已激活");
        }else {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId(), true, null);
            System.out.println("流程定义"+processDefinitionKey+"相关实例已挂起");
        }
    }

    @Override
    public void suspendOrActivateSingleProcessInstance(String businessKey){
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        //System.out.println("ActivityId:"+processInstance.getActivityId());
        boolean suspended = processInstance.isSuspended();
        if(suspended) {
            runtimeService.activateProcessInstanceById(processInstance.getId());
            System.out.println("流程实例"+businessKey+"已激活");
        }else {
            runtimeService.suspendProcessInstanceById(processInstance.getId());
            System.out.println("流程实例"+businessKey+"已挂起");
        }
    }

    /* UEL表达式设置Assignee*/
    /**
     * 启动流程实例
     * act_hi_actinst 流程中节点实例历史信息，每个动作act节点的历史信息，和当前正在执行的节点，其endtime为空
     * act_hi_identitylink 参与者历史信息，每个节点的参与者，历史参与着和当前正在执行节点的参与者，当前节点未执行完成
     * act_hi_procinst 流程实例信息，启动了多少流程实例，有流程实例ID(processInstanceId)、开始时间、act动作开始ID，如果流程未执行完成，结束时间和act动作结束ID为空
     * act_ru_execution 应该是和act_hi_actinst对应正在执行的act动作，该动作未完成，有流程实例ID、当前act动作ID(流转到本处理人，但本处理人未完成)
     * act_ru_identitylink 当前流程实例参与者信息，该流程实例未完成，参与者是未完成实例的所有参与者
     * act_ru_task 未完成的流程实例中处理人和当前需要处理的动作ID信息，
     * @return
     */
    /**
     * act_ru_variable 新增一条记录，记录流程实例ID、对象名称、对象类型为serializable、其内容保存在act_ge_bytearry、还保存了bytearry_id
     * act_ge_bytearry 新增多条记录，以二进制序列化形式保存对象内容
     * @param wfTranInfo
     * @return
     */
    @Override
    public String startAuthWorkflow_Object(WfTranInfo wfTranInfo) {
        //根据流程定义的key（就是bpmn文件的Id myProcess_helloworld）来启动一个实例，activiti找该key下版本最高的流程定义
        //这个key就是act_re_procdef中的KEY_ 字段
        //一般情况下为了方便开发使用该方法启动一个流程实例

        //将信息加入map,以便传入流程中，参数信息
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("wfTranInfo", wfTranInfo);
        //这里可以把业务ID businessKey传入runtimeService.startProcessInstanceByKey("myProcess_helloworld",businessKey,variables);
        //businessKey这个字段会保存在act_ru_execution和act_ru_task里
        //通过流程实例可以获取String businessKey = processInstance.getBusinessKey();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_helloworld",wfTranInfo.getBussinessKey(),variables);
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess_helloworld",variables);
        String processInstanceId = processInstance.getId();

        System.out.println(processInstanceId);
        System.out.println("正在活动的activiti ID"+processInstance.getActivityId());//null


        //根据流程定义的id来启动一个实例，这种方法一般不用
        //runtimeService.startProcessInstanceById(processDefinitionId);

        return processInstanceId;
    }

    /**
     * 根据任务ID完成任务——办理任务时，设置global全局变量
     * act_hi_actinst 当前用户的任务记录endTime本来为空，现在填充上时间了，新增一条记录为下一个任务处理人的记录
     * act_hi_identitylink 新增一条流程实例相关的参与者
     * act_hi_procinst 流程实例表无变化
     * act_hi_taskinst 新增一条记录，是表示任务流转到了下一个角色，有开始时间无结束时间。原taskId记录新增结束时间
     * act_ru_execution 应该是和act_hi_actinst对应正在执行的act动作，该动作未完成，有流程实例ID、当前act动作ID(流转到本处理人，但本处理人未完成)
     * 已经完成的流程实例不在act_ru_execution显示
     * act_ru_identitylink 当前流程实例参与者信息，该流程实例未完成，参与者是未完成实例的所有参与者，将下一个角色用户加进来
     * act_ru_task 未完成的流程实例当前走到了哪个act,该act由哪个角色的用户去处理，当前流程实例待处理任务的列表。之前的被删掉了，每走到下一个act之前的记录都会被删掉
     *              从流程定义图中摘取act节点的信息然后把上一条信息删除，只保留正在执行(未完成)的流程实例，正在执行的act节点和处理人
     * @param taskCompleteModel
     */
    /**
     * 在完成任务时设置流程变量，该流程变量只有在该任务完成后其它结点才可使用该变量，它的作用
     * 域是整个流程实例，如果设置的流程变量的 key 在流程实例中已存在相同的名字则后设置的变量替
     * 换前边设置的变量。
     * @param taskCompleteModel
     */
    @Override
    public void completeTaskGlobalVar(TaskCompleteModel taskCompleteModel) {
        Map<String,Object> map = new HashMap<>();
        map.put("var1","var10");
        taskService.complete(taskCompleteModel.getTaskId(),map);//传入act_ru_task的 taskId（其实和act_hi_taskinst的taskId是一样的）
    }
}