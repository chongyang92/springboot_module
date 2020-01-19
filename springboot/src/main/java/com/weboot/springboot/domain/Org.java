package com.weboot.springboot.domain;

import java.util.Date;

public class Org {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private String orgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.PARENT_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private String parentOrgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.ORG_NAME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private String orgName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.REAL_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private String realOrgId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.STATUS
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.CREATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column org.UPDATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.ORG_ID
     *
     * @return the value of org.ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.ORG_ID
     *
     * @param orgId the value for org.ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.PARENT_ORG_ID
     *
     * @return the value of org.PARENT_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public String getParentOrgId() {
        return parentOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.PARENT_ORG_ID
     *
     * @param parentOrgId the value for org.PARENT_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId == null ? null : parentOrgId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.ORG_NAME
     *
     * @return the value of org.ORG_NAME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.ORG_NAME
     *
     * @param orgName the value for org.ORG_NAME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.REAL_ORG_ID
     *
     * @return the value of org.REAL_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public String getRealOrgId() {
        return realOrgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.REAL_ORG_ID
     *
     * @param realOrgId the value for org.REAL_ORG_ID
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setRealOrgId(String realOrgId) {
        this.realOrgId = realOrgId == null ? null : realOrgId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.STATUS
     *
     * @return the value of org.STATUS
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.STATUS
     *
     * @param status the value for org.STATUS
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.CREATE_TIME
     *
     * @return the value of org.CREATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.CREATE_TIME
     *
     * @param createTime the value for org.CREATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column org.UPDATE_TIME
     *
     * @return the value of org.UPDATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column org.UPDATE_TIME
     *
     * @param updateTime the value for org.UPDATE_TIME
     *
     * @mbg.generated Sun Jan 19 22:38:47 CST 2020
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}