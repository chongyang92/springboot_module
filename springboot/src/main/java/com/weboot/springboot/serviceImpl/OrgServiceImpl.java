package com.weboot.springboot.serviceImpl;

import com.baidu.fsg.uid.UidGenerator;
import com.weboot.springboot.mapper.OrgMapper;
import com.weboot.springboot.domain.Org;
import com.weboot.springboot.domain.OrgExample;
import com.weboot.springboot.service.OrgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrgMapper orgMapper;
    @Resource
    private UidGenerator uidGenerator;

    @Override
    public List<Org> getOrglist() {

        OrgExample orgExample = new OrgExample();
        OrgExample.Criteria cc = orgExample.createCriteria();
        cc.andOrgIdIsNotNull();
        return orgMapper.selectByExample(orgExample);
    }

    @Override
    public String insertOrg(Org org) {
        String orgId = String.valueOf(uidGenerator.getUID());
        org.setOrgId(orgId);
        orgMapper.insert(org);
        return orgId;
    }

    @Override
    public String editOrg(Org org) {
        orgMapper.updateByPrimaryKeySelective(org);
        return null;
    }
}
