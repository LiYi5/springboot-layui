package com.xiyouedu.mapper;

import com.xiyouedu.bean.Role;
import com.xiyouedu.bean.RolePower;
import com.xiyouedu.bean.RolePowerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RolePowerMapper {
    int countByExample(RolePowerExample example);

    int deleteByExample(RolePowerExample example);

    int deleteByPrimaryKey(Integer idRole);

    int insert(RolePower record);

    int insertSelective(RolePower record);

    List<RolePower> selectByExample(RolePowerExample example);

    RolePower selectByPrimaryKey(Integer idRole);

    int updateByExampleSelective(@Param("record") RolePower record, @Param("example") RolePowerExample example);

    int updateByExample(@Param("record") RolePower record, @Param("example") RolePowerExample example);

    int updateByPrimaryKeySelective(RolePower record);

    int updateByPrimaryKey(RolePower record);

    List<Role> findRoleBypid(Integer pid);
}