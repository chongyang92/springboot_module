package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.domain.Perm;
import com.weboot.springboot.domain.PermExample;
import com.weboot.springboot.exception.ServiceException;
import com.weboot.springboot.mapper.PermMapper;
import com.weboot.springboot.service.PermService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jnlp.PersistenceService;
import java.util.List;

@Service
public class PermServiceImpl implements PermService {
    @Resource
    private PermMapper permMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<Perm> getPermlist(Perm perm) {

        PermExample permExample = new PermExample();
        PermExample.Criteria cc = permExample.createCriteria();
        if (StringUtils.isNotBlank(perm.getPermId())){
            cc.andPermIdEqualTo(perm.getPermId());
        }
        if(StringUtils.isNotBlank(perm.getPermName())){
            cc.andPermNameLike("%"+perm.getPermName() +"%");
        }
        if (StringUtils.isNotBlank(perm.getParentPermId())){
            cc.andParentPermIdEqualTo(perm.getParentPermId());
        }

        return permMapper.selectByExample(permExample);
    }

    @Override
    public String insertPerm(Perm perm) {//方法名后用throws 抛出多个异常
        if("中信银行".equals(perm.getPermName())){
            //方法体内用throw,因为这里只有一个异常
            throw new ServiceException("新增权限失败");
        }
        String permId = String.valueOf(uidGenerator.getUID());
        perm.setPermId(permId);
        permMapper.insert(perm);
        return permId;
    }

    @Override
    public String editPerm(Perm perm) {
        permMapper.updateByPrimaryKeySelective(perm);
        return perm.getPermName();
    }

    @Override
    public String deletePerm(String permId) {
        if(1 != permMapper.deleteByPrimaryKey(permId)){
            throw new ServiceException("删除权限失败");
        }
        return permId;
    }

    @Override
    public Perm listPermByPermId(String permId) {
        return permMapper.selectByPrimaryKey(permId);
    }
}
