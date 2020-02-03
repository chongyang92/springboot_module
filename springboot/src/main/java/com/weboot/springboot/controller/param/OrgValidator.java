package com.weboot.springboot.controller.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weboot.springboot.domain.Org;
import com.weboot.springboot.utils.BeanCopierUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.cglib.beans.BeanCopier;

import javax.validation.constraints.NotBlank;
import java.util.Calendar;
import java.util.Date;

public class OrgValidator {

    private String orgId;

    @NotBlank(message = "{orgValidator.parentOrgId.notBlank}")
    @Length(min = 0, max = 50, message = "{orgValidator.parentOrgId.length}")
    @JsonProperty
    private String parentOrgId;

    @NotBlank(message = "{orgValidator.realOrgId.notBlank}")
    @Length(min = 0, max = 50, message = "{orgValidator.realOrgId.length}")
    private String realOrgId;

    @NotBlank(message = "{orgValidator.name.notBlank}")
    @Length(min = 0, max = 150, message = "{orgValidator.name.length}")
    private String orgName;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getRealOrgId() {
        return realOrgId;
    }

    public void setRealOrgId(String realOrgId) {
        this.realOrgId = realOrgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Org genOrg(){
        Org org = new Org();
        BeanCopierUtils.copyProperties(this,org);
        org.setStatus("NORMAL");
        return org;
    }
}
