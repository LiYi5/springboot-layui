package com.xiyouedu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.Role;
import com.xiyouedu.bean.User;
import com.xiyouedu.mapper.PowerMapper;
import com.xiyouedu.mapper.RolePowerMapper;
import com.xiyouedu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private RolePowerMapper rolePowerMapper;
    @Override
    public List<Power> findAll(String permissionname) {
        if(permissionname==null){
              List<Power> list1= powerMapper.findAll();
              return list1;
        }else{
            if(permissionname==""){
               List<Power> list2= powerMapper.findAll();
               return list2;
            }else{
                List<Power> list3=powerMapper.findLike(permissionname);
                return list3;
            }
        }
    }

    @Override
    public List<String> deletePermission(Integer pid) {
        List<Role> roles=rolePowerMapper.findRoleBypid(pid);
        if(roles.size()==0){
            powerMapper.deleteByPrimaryKey(pid);
            return null;
        }
        else {
            List<String> list = new ArrayList<>();
            for(Role r:roles){
                list.add(r.getRolename());
            }
            return list;
        }

    }

    @Override
    public String addP(String permission,Integer pid) {
        Power power = JSON.parseObject(permission, new TypeReference<Power>() {
        });

        Power p1 = powerMapper.findByPname(power.getPowername());
        Power p2 = powerMapper.findByPUrl(power.getPowerurl());
        if(p1!=null){
            return "name";
        }else if(p2!=null){

            return "url";
        }else{
            if(pid!=null){
                power.setPowerId(pid);
                int i = powerMapper.updateByPrimaryKey(power);
                if(i==1){
                    return "ok";
                }else {
                    return "error";
                }
            }
            else{
                int i = powerMapper.insert(power);
                if(i==1){
                    return "ok";
                }else{
                    return "error";
                }
            }
        }

    }

    @Override
    public Power getPower(Integer pid) {
       return powerMapper.selectByPrimaryKey(pid);
    }

}
