package com.weboot.springboot.controller.param.wf;

import com.weboot.springboot.model.wf.TaskCompleteModel;
import com.weboot.springboot.type.WfResultType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 完成任务校验器
 * @author yxkj-liuchao
 *
 */
public class CompleteTaskValidator {

	/**
	 * 任务ID
	 */
	@NotNull(message = "{completeTaskValidator.taskId.notNull}")
    @Length(min = 1, max = 64, message = "{completeTaskValidator.taskId.length}")
	private String taskId;
	
	/**
	 * 处理结果
	 */
	/*@NotNull(message = "{completeTaskValidator.wfResult.notNull}")
	@Pattern(regexp = "(PASS|BACK)", message = "{completeTaskValidator.wfResult.pattern}")*/
	private String wfResult;
	
	/**
	 * 批注
	 */
	private String comment;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getWfResult() {
		return wfResult;
	}

	public void setWfResult(String wfResult) {
		this.wfResult = wfResult;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "CompleteTaskValidator [taskId=" + taskId + ", wfResult=" + wfResult + ", comment=" + comment + "]";
	}
	
	/**
	 * 类型转换
	 * @return
	 */
	public TaskCompleteModel genTaskCompleteModel() {
		TaskCompleteModel taskCompleteModel = new TaskCompleteModel();
		taskCompleteModel.setTaskId(taskId);
		taskCompleteModel.setWfResult(WfResultType.valueOf(wfResult));
		taskCompleteModel.setComment(comment);
		return taskCompleteModel;
	}
}
