package com.weboot.springboot.service;

import com.weboot.springboot.model.wf.PendingTaskModel;
import com.weboot.springboot.model.wf.ProcdefModel;
import com.weboot.springboot.model.wf.TaskCompleteModel;
import com.weboot.springboot.model.wf.WfTranInfo;
import org.activiti.engine.history.HistoricTaskInstance;

import java.io.InputStream;
import java.util.List;

public interface WfService {
    /**
     * 流程部署
     * @param zipIs 流程定义打包压缩文件
     * @return 流程部署ID
     */
    String deployAuthWorkflow(InputStream zipIs);

    /**
     * 流程部署(在类路径下加载流程定义文件)
     * @return 流程部署ID
     */
    String deployAuthWorkflow();

    /**
     * 查询审批流程定义列表
     * @param
     * @return
     */
    List<ProcdefModel> listProcdef();

    /**
     * 查询审批流程定义列表
     * @param
     * @return
     */
    InputStream getProcdefDiagram(String deploymentId,String diagramResourceName);

    /**
     * 开启流程
     * @param
     * @return 流程实例ID
     */
    String startAuthWorkflow(String businessKey);

    /**
     * 根据用户查询任务列表
     * @param
     * @return 流程实例ID
     */
    List<PendingTaskModel> listPendingTask(String userName,String processInstanceId);

    /**
     * 完成任务
     * @param taskCompleteModel
     */
    void completeTask(TaskCompleteModel taskCompleteModel);

    /**
     * 查询流程实例状态，当前执行到哪一步了
     * @param proinsatanceId
     * @return
     */
    String queryProinsatanceState(String proinsatanceId);

    /**
     * 根据用户查询历史任务列表
     * @param
     * @return 流程实例ID
     */
    List<HistoricTaskInstance> listHistoryTask(String userName, String processInstanceId);

    /**
     * 删除已部署的流程定义
     * @param deployMentId
     */
    void deleteDeployment(String deployMentId);

    /**
     * 挂起或激活流程定义相关的实例
     * @param processDefinitionKey
     */
    void suspendOrActivateProcessInstance(String processDefinitionKey);

    /**
     * 根据业务ID激活或挂起某个流程实例
     * @param businessKey
     */
    void suspendOrActivateSingleProcessInstance(String businessKey);

    /**
     * 传入对象开启流程实例
     * @param wfTranInfo
     * @return
     */
    String startAuthWorkflow_Object(WfTranInfo wfTranInfo);

    /**
     * 处理任务时(完成任务前)设置变量
     * @param taskCompleteModel
     */
    void completeTaskGlobalVar(TaskCompleteModel taskCompleteModel);
}
