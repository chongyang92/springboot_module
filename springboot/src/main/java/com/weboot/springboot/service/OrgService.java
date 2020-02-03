package com.weboot.springboot.service;

import com.weboot.springboot.domain.Org;

import java.util.List;

public interface OrgService {
    List<Org> getOrglist(Org org);
    String insertOrg(Org org);
    String editOrg(Org org);
    String deleteOrg(String orgId);
}
