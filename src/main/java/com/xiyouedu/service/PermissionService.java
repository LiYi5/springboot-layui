package com.xiyouedu.service;

import com.xiyouedu.bean.Power;

import java.util.List;

public interface PermissionService {
    List<Power> findAll(String permissionname);

    List<String> deletePermission(Integer pid);

    String addP(String p,Integer pid);

    Power getPower(Integer pid);

//    String update(String permission, Integer pid);
}
