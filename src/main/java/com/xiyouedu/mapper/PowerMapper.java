package com.xiyouedu.mapper;

import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.PowerExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PowerMapper {
    int countByExample(PowerExample example);

    int deleteByExample(PowerExample example);

    int deleteByPrimaryKey(Integer powerId);

    int insert(Power record);

    int insertSelective(Power record);

    List<Power> selectByExample(PowerExample example);

    Power selectByPrimaryKey(Integer powerId);

    int updateByExampleSelective(@Param("record") Power record, @Param("example") PowerExample example);

    int updateByExample(@Param("record") Power record, @Param("example") PowerExample example);

    int updateByPrimaryKeySelective(Power record);

    int updateByPrimaryKey(Power record);

    List<Power> getpermissionByRolename(String rolename);
    //查询所有权限
    List<Power> findAll();
    //模糊查询
    List<Power> findLike(@Param("powername") String permissionname);
    //根据权限名查找
    Power findByPname(String powername);
    //根据权限URL查询
    Power findByPUrl(String powerurl);

}