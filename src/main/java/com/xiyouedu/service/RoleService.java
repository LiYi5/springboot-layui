package com.xiyouedu.service;

import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.RoleP;
import com.xiyouedu.bean.RolePermissionVo;

import java.util.List;

public interface RoleService {
    List<RolePermissionVo> findAll(String role);

    List<String> deleteRole(Integer rid);

    List<Power> Powerlist();

    String addRole(String role, String pers, Integer roleid);

    RoleP getRoleAndPowers(Integer pid);
}
