package com.weboot.springboot.model.wf;

import com.weboot.springboot.type.WfResultType;

public class TaskCompleteModel {

	/**
	 * 任务ID
	 */
	private String taskId;
	
	/**
	 * 完成用户
	 */
	private String assignee;
	
	/**
	 * 处理结果
	 */
	private WfResultType wfResult;
	
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

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public WfResultType getWfResult() {
		return wfResult;
	}

	public void setWfResult(WfResultType wfResult) {
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
		return "TaskCompleteModel [taskId=" + taskId + ", assignee=" + assignee + ", wfResult=" + wfResult
				+ ", comment=" + comment + "]";
	}

}
