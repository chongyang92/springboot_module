package com.weboot.springboot.serviceImpl;

import com.weboot.springboot.mapper.OrgMapper;
import com.weboot.springboot.model.Org;
import com.weboot.springboot.model.OrgExample;
import com.weboot.springboot.service.OrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Resource
    private OrgMapper orgMapper;
    @Override
    public List<Org> getOrglist() {

        OrgExample orgExample = new OrgExample();
        OrgExample.Criteria cc = orgExample.createCriteria();
        cc.andOrgIdIsNotNull();
        return orgMapper.selectByExample(orgExample);
    }
}
