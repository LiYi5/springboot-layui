package com.xiyouedu.mapper;

import com.xiyouedu.bean.User;
import com.xiyouedu.bean.UserRole;
import com.xiyouedu.bean.UserRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);

     int deleteByUid(Integer uid);

     List<User> finUserByRid(Integer rid);
}