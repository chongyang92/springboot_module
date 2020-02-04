package com.weboot.springboot.service.impl;

import com.weboot.springboot.domain.Path;
import com.weboot.springboot.domain.UserPathExample;
import com.weboot.springboot.domain.UserPathKey;
import com.weboot.springboot.mapper.PathMapper;
import com.weboot.springboot.mapper.UserPathMapper;
import com.weboot.springboot.service.UserPathService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserPathServiceImpl implements UserPathService {
    private static final Logger logger = LoggerFactory.getLogger(UserPathServiceImpl.class);

    @Resource
    private UserPathMapper userPathMapper;
    @Resource
    private PathMapper pathMapper;

    @Override
    public String insertUserPath(UserPathKey userPath) {
        userPathMapper.insert(userPath);
        return userPath.getPathId().toString() + userPath.getUserId().toString();
    }

    @Override
    public int deleteUserPath(UserPathKey userPath) {
        return userPathMapper.deleteByPrimaryKey(userPath);
    }

    @Override
    public List<Path> listPathByUserId(String userId) {
        List<Path> pathList = new ArrayList<>();
        UserPathExample userPathExample = new UserPathExample();
        UserPathExample.Criteria cc = userPathExample.createCriteria();
        cc.andUserIdEqualTo(userId);
        List<UserPathKey> userPathKeyList = userPathMapper.selectByExample(userPathExample);
        for(UserPathKey userPathKey : userPathKeyList){
            Path path = pathMapper.selectByPrimaryKey(userPathKey.getPathId());
            pathList.add(path);
        }
        return pathList;
    }
}
