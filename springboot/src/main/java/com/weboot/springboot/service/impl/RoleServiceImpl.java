package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.domain.Role;
import com.weboot.springboot.domain.RoleExample;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.RoleMapper;
import com.weboot.springboot.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<Role> getRolelist(Role role) {

        RoleExample roleExample = new RoleExample();
        RoleExample.Criteria cc = roleExample.createCriteria();
        if (StringUtils.isNotBlank(role.getRoleId())){
            cc.andRoleIdEqualTo(role.getRoleId());
        }
        if(StringUtils.isNotBlank(role.getRoleName())){
            cc.andRoleNameLike("%"+role.getRoleName() +"%");
        }

        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public String insertRole(Role role) {//方法名后用throws 抛出多个异常
        if("中信银行".equals(role.getRoleName())){
            //方法体内用throw,因为这里只有一个异常
            throw new ServiceException("新增角色失败");
        }
        String roleId = String.valueOf(uidGenerator.getUID());
        role.setRoleId(roleId);
        roleMapper.insert(role);
        return roleId;
    }

    @Override
    public String editRole(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
        return role.getRoleName();
    }

    @Override
    public String deleteRole(String roleId) {
        if(1 != roleMapper.deleteByPrimaryKey(roleId)){
            throw new ServiceException("删除角色失败");
        }
        return roleId;
    }

    @Override
    public Role listRoleByRoleId(String roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }
}
