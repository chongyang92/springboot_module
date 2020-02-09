package com.weboot.springboot.service;

import com.weboot.springboot.domain.Perm;

import java.util.List;

public interface PermService {
    List<Perm> getPermlist(Perm perm);
    String insertPerm(Perm perm);
    String editPerm(Perm perm);
    String deletePerm(String permId);
    Perm listPermByPermId(String permId);
}
