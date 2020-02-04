package com.weboot.springboot.controller.param;

import com.weboot.springboot.domain.UserPathKey;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserPathValidator {
    private String userId;
    private List<String> pathIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getPathIds() {
        return pathIds;
    }

    public void setPathIds(List<String> pathIds) {
        this.pathIds = pathIds;
    }

    public List<UserPathKey> genUserPathKey() {
        List<UserPathKey> userPathKeyList = new ArrayList<>();
        if (StringUtils.isNoneBlank(userId)) {
            userId = StringUtils.trim(userId);
            if (pathIds != null && !pathIds.isEmpty()) {
                for (String pathId : pathIds) {
                    UserPathKey userPathKey = new UserPathKey();
                    userPathKey.setPathId(pathId);
                    userPathKey.setUserId(userId);
                    userPathKeyList.add(userPathKey);
                }
            }
        }
        return userPathKeyList;
    }
}

