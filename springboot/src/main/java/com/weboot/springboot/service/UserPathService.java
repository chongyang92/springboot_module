package com.weboot.springboot.service;

import com.weboot.springboot.domain.Path;
import com.weboot.springboot.domain.UserPathKey;

import java.util.List;

public interface UserPathService {
    String insertUserPath(UserPathKey userPath);
    int deleteUserPath(UserPathKey userPath);
    List<Path> listPathByUserId(String userId);
}
