package com.xiyouedu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiyouedu.bean.*;
import com.xiyouedu.mapper.PowerMapper;
import com.xiyouedu.mapper.RoleMapper;
import com.xiyouedu.mapper.RolePowerMapper;
import com.xiyouedu.mapper.UserRoleMapper;
import com.xiyouedu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePowerMapper rolePowerMapper;

    @Override
    public List<RolePermissionVo> findAll(String rolename) {
        if (rolename == null) {
            List<RolePermissionVo> rplist1 = roleMapper.findAll();
            if (null != rplist1 && rplist1.size() > 0) {
                for (RolePermissionVo rp1 : rplist1) {
                    List<Power> permisssions1 = powerMapper.getpermissionByRolename(rp1.getRolename());
                    if (null != permisssions1 && permisssions1.size() > 0) {
                        StringBuilder sb1 = new StringBuilder();
                        for (int i = 0; i < permisssions1.size(); i++) {
                            Power p1 = permisssions1.get(i);
                            sb1.append(p1.getPowername());
                            if (i != (permisssions1.size() - 1)) {
                                sb1.append("，");
                            }
                        }
                        rp1.setPowername(sb1.toString());
                    }
                }
            }
            return rplist1;
        }
        else {
            if(rolename==""){
                List<RolePermissionVo> rplist2 = roleMapper.findAll();
                if (null != rplist2&& rplist2.size() > 0) {
                    for (RolePermissionVo rp2 : rplist2) {
                        List<Power> permisssions2 = powerMapper.getpermissionByRolename(rp2.getRolename());
                        if (null != permisssions2 && permisssions2.size() > 0) {
                            StringBuilder sb2 = new StringBuilder();
                            for (int i = 0; i < permisssions2.size(); i++) {
                                Power p2 = permisssions2.get(i);
                                sb2.append(p2.getPowername());
                                if (i != (permisssions2.size() - 1)) {
                                    sb2.append("，");
                                }
                            }
                            rp2.setPowername(sb2.toString());
                        }
                    }
                }
                return rplist2;

            }
            else{
                List<RolePermissionVo> rplist3 = roleMapper.findByRolenameLike(rolename);
                if (null != rplist3 && rplist3.size() > 0) {
                    for (RolePermissionVo rp3 : rplist3) {
                        List<Power> permisssions3 = powerMapper.getpermissionByRolename(rp3.getRolename());
                        if (null != permisssions3 && permisssions3.size() > 0) {
                            StringBuilder sb3 = new StringBuilder();
                            for (int i = 0; i < permisssions3.size(); i++) {
                                Power p1 = permisssions3.get(i);
                                sb3.append(p1.getPowername());
                                if (i != (permisssions3.size() - 1)) {
                                    sb3.append("，");
                                }
                            }
                            rp3.setPowername(sb3.toString());
                        }
                    }
                }
                return rplist3;
            }

        }

    }

    @Override
    public List<String> deleteRole(Integer rid) {
        List<User> users = userRoleMapper.finUserByRid(rid);
        if(users.size()==0){
            roleMapper.deleteByPrimaryKey(rid);
            rolePowerMapper.deleteByPrimaryKey(rid);
            return null;
        }else{
            List<String> list = new ArrayList<>();
            for(User u:users){
                list.add(u.getUsername());
            }
            return list;
        }
    }

    /**
     * 回显权限列表
     * @return
     */
    @Override
    public List<Power> Powerlist() {
        return powerMapper.findAll();
    }

    @Override
    public String addRole(String r, String pers, Integer roleid) {
        Role role = JSON.parseObject(r, new TypeReference<Role>() {
        });
        List<Integer> pids = JSON.parseArray(pers, Integer.class);
        role.setRoleId(roleid);
        //更新
        if(roleid!=null){
               Role role1=  roleMapper.findByRname(role.getRolename());
                if(role1==null){
                    //可以修改
                    roleMapper.updateByPrimaryKey(role);
                    rolePowerMapper.deleteByPrimaryKey(roleid);
                    for(int i=0;i<pids.size();i++){
                        RolePower rolePower=new RolePower();
                        rolePower.setIdPower(pids.get(i));
                        rolePower.setIdRole(roleid);
                        rolePowerMapper.insert(rolePower);
                    }
                    return "upok";
                }
                else{
                    String rolename = roleMapper.selectByPrimaryKey(roleid).getRolename();
                    if(rolename.equals(role.getRolename())){
                        roleMapper.updateByPrimaryKey(role);
                        rolePowerMapper.deleteByPrimaryKey(roleid);
                        for(int i=0;i<pids.size();i++){
                            RolePower rolePower=new RolePower();
                            rolePower.setIdPower(pids.get(i));
                            rolePower.setIdRole(roleid);
                            rolePowerMapper.insert(rolePower);
                        }
                        return "upok";
                    }
                //新的角色名重复
                    return "uperror";
            }
        } else {
            //添加(角色名不能一样)
            Role r1 = roleMapper.findByRname(role.getRolename());
            if (r1 == null) {
                //添加角色
               roleMapper.insertto(role.getRolename());
                //关联权限
                Role r2 = roleMapper.findByRname(role.getRolename());
                for(int i=0;i<pids.size();i++){
                    RolePower rolePower2=new RolePower();
                    rolePower2.setIdPower(pids.get(i));
                    rolePower2.setIdRole(r2.getRoleId());
                    rolePowerMapper.insert(rolePower2);
                }
                return "addok";
            }else{
                return "adderror";
            }
        }
    }

    @Override
    public RoleP getRoleAndPowers(Integer rid) {
        return roleMapper.getRoleAndPowers(rid);
    }

}
