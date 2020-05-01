package com.xiyouedu.mapper;

import com.xiyouedu.bean.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    // 根据角色名查询权限
    List<Power> findByRoleid(String rolename);

    //根据用户名查询角色列表
    List<Role> getRoleByUsername(@Param("username") String username);

    List<String> getRnameByUname(@Param("username") String username);

    List<Role> selectAll();

    List<RolePermissionVo> findAll();

    List<RolePermissionVo> findByRolenameLike(@Param("rolename") String rolename);

    Role findByRname(String rolename);


    int insertto(String rolename);

    RoleP getRoleAndPowers(@Param("roleid") Integer rid);

}