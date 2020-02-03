package com.weboot.springboot.service.impl;

        import com.baidu.fsg.uid.UidGenerator;
        import com.weboot.springboot.domain.User;
        import com.weboot.springboot.domain.UserExample;
        import com.weboot.springboot.exception.ServiceException;
        import com.weboot.springboot.mapper.UserMapper;
        import com.weboot.springboot.service.UserService;
        import org.apache.commons.lang3.StringUtils;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.stereotype.Service;

        import javax.annotation.Resource;
        import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<User> getUserlist(User user) {
        UserExample userExample = new UserExample();
        UserExample.Criteria cc = userExample.createCriteria();
        if (StringUtils.isNotBlank(user.getUserId())) {
            cc.andUserIdEqualTo(user.getUserId());
        }
        if (StringUtils.isNotBlank(user.getUserName())) {
            cc.andUserNameLike("%" + user.getUserName() + "%");
        }
        if (StringUtils.isNotBlank(user.getOrgId())) {
            cc.andOrgIdEqualTo(user.getOrgId());
        }

        return userMapper.selectByExample(userExample);
    }

    @Override
    public String insertUser(User user) {
        String userId = String.valueOf(uidGenerator.getUID());
        user.setUserId(userId);
        userMapper.insert(user);
        return userId;
    }

    @Override
    public String editUser(User user) {
        userMapper.updateByPrimaryKeySelective(user);
        return user.getUserName();
    }

    @Override
    public String deleteUser(String userId) {
        if (1 != userMapper.deleteByPrimaryKey(userId)) {
            throw new ServiceException("删除用户失败");
        }
        return userId;
    }
}

