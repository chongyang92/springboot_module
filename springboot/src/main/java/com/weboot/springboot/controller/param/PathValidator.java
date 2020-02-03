package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.Path;
import com.weboot.springboot.utils.BeanCopierUtils;

public class PathValidator {
    private String pathId;
    private String httpPath;
    private String httpMethodType;
    private String type;
    private String description;

    public String getPathId() {
        return pathId;
    }

    public void setPathId(String pathId) {
        this.pathId = pathId;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }

    public String getHttpMethodType() {
        return httpMethodType;
    }

    public void setHttpMethodType(String httpMethodType) {
        this.httpMethodType = httpMethodType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Path genPath(){
        Path path = new Path();
        BeanCopierUtils.copyProperties(this,path);
        return path;
    }
}
