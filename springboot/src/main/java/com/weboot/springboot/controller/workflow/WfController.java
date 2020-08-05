package com.weboot.springboot.controller.workflow;

import com.weboot.springboot.controller.param.wf.CompleteTaskValidator;
import com.weboot.springboot.core.Result;
import com.weboot.springboot.core.ResultBuilder;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.model.wf.PendingTaskModel;
import com.weboot.springboot.model.wf.ProcdefModel;
import com.weboot.springboot.model.wf.TaskCompleteModel;
import com.weboot.springboot.model.wf.WfTranInfo;
import com.weboot.springboot.service.WfService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@Validated
@RequestMapping("/authFlow")
public class WfController {
    private static final Logger logger = LoggerFactory.getLogger(WfController.class);

    @Resource
    private WfService wfService;

    //部署流程定义，zip包形式
   @RequestMapping(value = "/deployWithZip", method = RequestMethod.POST)
    public Result deployWithZip(@RequestParam("zipFile") MultipartFile zipFile) throws IOException {
        logger.info("deploy forfaiting auth workflow with zipFile");
        if (zipFile == null) {
            logger.info("zipFile is null when deploy auth workflow with zipFile");
            throw new ServiceException("部署的ZIP文件不能为空");
        }
        System.out.println(zipFile.getName());
        System.out.println(zipFile.getOriginalFilename());
        System.out.println(zipFile.getContentType());
        String fileName = zipFile.getOriginalFilename();
        if(StringUtils.endsWithIgnoreCase(fileName,".zip")){
            logger.info("zipFile not zip file when deploy auth workflow with zipFile [originalFilename={}]", fileName);
            throw new ServiceException("部署的文件必须是ZIP文件");
        }
        InputStream inputStream = zipFile.getInputStream();
        String deploymentId = wfService.deployAuthWorkflow(inputStream);
        return ResultBuilder.genSuccessResult(deploymentId);
    }

    //项目源码resource下
    @RequestMapping(value = "/deploy", method = RequestMethod.POST)
    public Result deployWithZip() throws IOException {
        logger.info("deploy forfaiting auth workflow");

        String deployId = wfService.deployAuthWorkflow();
        return ResultBuilder.genSuccessResult(deployId);
    }

    //查询流程定义信息
    @RequestMapping(value = "/procdef/list", method = RequestMethod.GET)
    public Result listProcdef() throws IOException {
        logger.info("list procdef : ");

        List<ProcdefModel> procdefModels = wfService.listProcdef();
        return ResultBuilder.genSuccessResult(procdefModels);
    }

    //查看流程定义图
    @RequestMapping(value = "/procdef/diagram", method = RequestMethod.GET)
    public void getProcdefDiagram(@RequestParam(name = "deploymentId", required = true) String deploymentId,
                                  @RequestParam(name = "diagramResourceName", required = true) String diagramResourceName,
                                  HttpServletResponse response) throws IOException {
        logger.info("get Procdef Diagram : ");

        InputStream inputStream = wfService.getProcdefDiagram(deploymentId,diagramResourceName);
        response.setContentType("image/png");
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    //开启一个流程实例(流水线)
    @RequestMapping(value = "/procdef/startAuthWorkflow", method = RequestMethod.GET)
    public Result getProcdefDiagram(@RequestParam(name = "businessKey",required = true)String businessKey) throws IOException {
        logger.info("startAuthWorkflow : ");

        String processInstanceId = wfService.startAuthWorkflow(businessKey);
        return ResultBuilder.genSuccessResult(processInstanceId);
    }

    //查看任务列表
    @RequestMapping(value = "/procdef/pendingTask/list", method = RequestMethod.GET)
    public Result getPendingTaskList(@RequestParam(name = "userName",required = true)String userName
                                ,@RequestParam(name = "processInstanceId",required = true)String processInstanceId) throws IOException {
        logger.info("getPendingTaskList : ");

        List<PendingTaskModel> pendingTaskModels = wfService.listPendingTask(userName,processInstanceId);
        return ResultBuilder.genSuccessResult(pendingTaskModels);
    }

    //流程中某个角色完成自己的任务，任务会自动流转到下一个角色中
    @RequestMapping(value = "/procdef/completeTask", method = RequestMethod.POST)
    public Result completeTask(@RequestBody CompleteTaskValidator completeTaskValidator) throws IOException {
        logger.info("completeTask : ");

        TaskCompleteModel taskCompleteModel = completeTaskValidator.genTaskCompleteModel();
        wfService.completeTask(taskCompleteModel);
        return ResultBuilder.genSuccessResult();
    }

    //查询流程实例状态
    @RequestMapping(value = "/procdef/queryProinsatanceState", method = RequestMethod.GET)
    public Result queryProinsatanceState(@RequestParam(name = "proinsatanceId",required = true)String proinsatanceId) throws IOException {
        logger.info("queryProinsatanceState : ");

        String activiId = wfService.queryProinsatanceState(proinsatanceId);
        return ResultBuilder.genSuccessResult(activiId);
    }

    //查看历史任务列表
    @RequestMapping(value = "/procdef/historyTask/list", method = RequestMethod.GET)
    public Result getHistoryTaskList(@RequestParam(name = "userName",required = true)String userName
            ,@RequestParam(name = "processInstanceId",required = true)String processInstanceId) throws IOException {
        logger.info("getHistoryTaskList : ");

        List<HistoricTaskInstance> taskModels = wfService.listHistoryTask(userName,processInstanceId);
        return ResultBuilder.genSuccessResult(taskModels);
    }

    //删除已部署的流程定义
    @RequestMapping(value = "/deleteDeployment", method = RequestMethod.GET)
    public Result deleteDeployment(@RequestParam(name = "deployMentId",required = true)String deployMentId) throws IOException {
        logger.info("deleteDeployment : ");

        wfService.deleteDeployment(deployMentId);
        return ResultBuilder.genSuccessResult();
    }

    //激活与挂起流程定义相关的实例
    @RequestMapping(value = "/suspendOrActivateProcessInstance", method = RequestMethod.GET)
    public Result suspendOrActivateProcessInstance(@RequestParam(name = "processDefinitionKey",required = true)String processDefinitionKey) throws IOException {
        logger.info("suspendOrActivateProcessInstance : ");

        wfService.suspendOrActivateProcessInstance(processDefinitionKey);
        return ResultBuilder.genSuccessResult();
    }

    //激活与挂起某个流程实例
    @RequestMapping(value = "/suspendOrActivateSingleProcessInstance", method = RequestMethod.GET)
    public Result suspendOrActivateSingleProcessInstance(@RequestParam(name = "businessKey",required = true)String businessKey) throws IOException {
        logger.info("suspendOrActivateSingleProcessInstance : ");

        wfService.suspendOrActivateSingleProcessInstance(businessKey);
        return ResultBuilder.genSuccessResult();
    }

    //个人任务——分配任务负责人——表达式分配


    //传入对象方式开启流程实例
    @RequestMapping(value = "/procdef/startAuthWorkflow_Object", method = RequestMethod.POST)
    public Result startAuthWorkflow_Object(@RequestBody WfTranInfo wfTranInfo) throws IOException {
        logger.info("startAuthWorkflow_Object : ");

        String processInstanceId = wfService.startAuthWorkflow_Object(wfTranInfo);
        return ResultBuilder.genSuccessResult(processInstanceId);
    }

    //处理任务时设置global全局变量
    @RequestMapping(value = "/procdef/completeTaskGlobalVar", method = RequestMethod.POST)
    public Result completeTaskGlobalVar(@RequestBody TaskCompleteModel taskCompleteModel) throws IOException {
        logger.info("completeTaskGlobalVar : ");

        wfService.completeTaskGlobalVar(taskCompleteModel);
        return ResultBuilder.genSuccessResult();
    }
}
