package com.weboot.springboot.model.wf;

import java.io.Serializable;

public class WfTranInfo implements Serializable {
    private static final long serialVersionUID = 5147816173831702324L;
    private String bussinessKey;
    private boolean check;
    private boolean authoriation;

    public String getBussinessKey() {
        return bussinessKey;
    }

    public void setBussinessKey(String bussinessKey) {
        this.bussinessKey = bussinessKey;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isAuthoriation() {
        return authoriation;
    }

    public void setAuthoriation(boolean authoriation) {
        this.authoriation = authoriation;
    }
}
