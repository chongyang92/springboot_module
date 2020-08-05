package com.weboot.springboot.type;

/**
 * 审批流程处理结果类型
 * @author yxkj-liuchao
 *
 */
public enum WfResultType {

	PASS("通过"), BACK("退回");
	
	private String desc;

	public String getDesc() {
		return desc;
	}

	private WfResultType(String desc) {
		this.desc = desc;
	}
	
}
