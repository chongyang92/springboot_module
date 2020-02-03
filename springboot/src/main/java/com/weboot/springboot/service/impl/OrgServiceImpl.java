package com.weboot.springboot.service.impl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.execption.ServiceException;
import com.weboot.springboot.mapper.OrgMapper;
import com.weboot.springboot.domain.Org;
import com.weboot.springboot.domain.OrgExample;
import com.weboot.springboot.service.OrgService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrgMapper orgMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<Org> getOrglist(Org org) {

        OrgExample orgExample = new OrgExample();
        OrgExample.Criteria cc = orgExample.createCriteria();
        if (StringUtils.isNotBlank(org.getOrgId())){
            cc.andOrgIdEqualTo(org.getOrgId());
        }
        if(StringUtils.isNotBlank(org.getOrgName())){
            cc.andOrgNameLike("%"+org.getOrgName() +"%");
        }
        if (StringUtils.isNotBlank(org.getParentOrgId())){
            cc.andParentOrgIdEqualTo(org.getParentOrgId());
        }
        if (StringUtils.isNotBlank(org.getRealOrgId())){
            cc.andRealOrgIdLike("%" + org.getRealOrgId() + "%");
        }

        return orgMapper.selectByExample(orgExample);
    }

    @Override
    public String insertOrg(Org org) {//方法名后用throws 抛出多个异常
        if("中信银行".equals(org.getOrgName())){
            //方法体内用throw,因为这里只有一个异常
            throw new ServiceException("新增机构失败");
        }
        String orgId = String.valueOf(uidGenerator.getUID());
        org.setOrgId(orgId);
        orgMapper.insert(org);
        return orgId;
    }

    @Override
    public String editOrg(Org org) {
        orgMapper.updateByPrimaryKeySelective(org);
        return org.getOrgName();
    }

    @Override
    public String deleteOrg(String orgId) {
        if(1 != orgMapper.deleteByPrimaryKey(orgId)){
            throw new ServiceException("删除机构失败");
        }
        return orgId;
    }
}
