package com.xiyouedu.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.xiyouedu.bean.*;
import com.xiyouedu.mapper.RoleMapper;
import com.xiyouedu.mapper.UserMapper;
import com.xiyouedu.mapper.UserRoleMapper;
import com.xiyouedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public boolean addUser(User user,String roleids) {

        User user1 = userMapper.findByUsername(user.getUsername());

        if (user1 == null) {
            //用户表加用户
            userMapper.insertUser(user);
            //用户角色关联用户和角色
            List<Integer> rIds = JSON.parseArray(roleids, Integer.class);
            User u=userMapper.findByUsername(user.getUsername());
            for(int i=0;i<rIds.size();i++){
                UserRole userRole=new UserRole();
                userRole.setIdUser(u.getUserId());
                userRole.setIdRole(rIds.get(i));
                userRoleMapper.insert(userRole);
            }

            return true;
        } else {
            return false;
        }

    }

    //分页查找
    @Override
    public List<User> findUsers(int page, int rows) {
        UserExample example = new UserExample();
        List<User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public boolean deleteUser(Integer id) {
                userRoleMapper.deleteByUid(id);
        int i = userMapper.deleteByPrimaryKey(id);
        return i == 0 ? false : true;
    }

    @Override
    public User getUserByid(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }

    @Override
    public boolean login(User user) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        List<User> list = userMapper.selectByExample(example);
        return (list.size() == 0) ? false : true;
    }

    @Override
    public List<UserRoleVo> findAll(String username) {
        if (username == null) {
            List<UserRoleVo> urlist1 = userMapper.selectAll();
            if (urlist1 != null && urlist1.size() > 0) {
                for (UserRoleVo ur1 : urlist1) {
                    List<Role> roles1 = roleMapper.getRoleByUsername(ur1.getUsername());
                    if (null != roles1 && roles1.size() > 0) {
                        StringBuilder sb1 = new StringBuilder();
                        for (int i = 0; i < roles1.size(); i++) {
                            Role r1 = roles1.get(i);
                            sb1.append(r1.getRolename());
                            if (i != (roles1.size() - 1)) {
                                sb1.append("，");
                            }
                        }
                        ur1.setRolename(sb1.toString());
                    }
                }
            }
            return urlist1;
        } else {
            if (username == "") {
                List<UserRoleVo> urlist2 = userMapper.selectAll();
                if (urlist2 != null && urlist2.size() > 0) {
                    for (UserRoleVo ur2 : urlist2) {
                        List<Role> roles2 = roleMapper.getRoleByUsername(ur2.getUsername());
                        if (null != roles2 && roles2.size() > 0) {
                            StringBuilder sb2 = new StringBuilder();
                            for (int j = 0; j < roles2.size(); j++) {
                                Role r2 = roles2.get(j);
                                sb2.append(r2.getRolename());
                                if (j != (roles2.size() - 1)) {
                                    sb2.append("，");
                                }
                            }
                            ur2.setRolename(sb2.toString());
                        }
                    }
                }
                return urlist2;
            } else {
                List<UserRoleVo> urlist3 = userMapper.findByusernameLike(username);
                if (urlist3 != null && urlist3.size() > 0) {
                    for (UserRoleVo ur3 : urlist3) {
                        List<Role> roles3 = roleMapper.getRoleByUsername(ur3.getUsername());
                        if (null != roles3 && roles3.size() > 0) {
                            StringBuilder sb3 = new StringBuilder();
                            for (int k = 0; k < roles3.size(); k++) {
                                Role r3 = roles3.get(k);
                                sb3.append(r3.getRolename());
                                if (k != (roles3.size() - 1)) {
                                    sb3.append("，");
                                }
                            }
                            ur3.setRolename(sb3.toString());
                        }
                    }
                }
                return urlist3;
            }
        }

    }


    @Override
    public boolean update(String u,String roleids,Integer uid) {
        UserRoleVo userrolevo = JSON.parseObject(u, new TypeReference<UserRoleVo>() {
        });
        User user=new User();
        user.setUserId(uid);
        user.setUsername(userrolevo.getUsername());
        user.setPassword(userrolevo.getPassword());
        List<Integer> rIds = JSON.parseArray(roleids, Integer.class);
        User user1 = userMapper.findByUsername(user.getUsername());
        if (user1 == null) {
            userMapper.updateByPrimaryKey(user);
            userRoleMapper.deleteByUid(user.getUserId());
            for(int i=0;i<rIds.size();i++){
                UserRole userRole=new UserRole();
                userRole.setIdUser(user.getUserId());
                userRole.setIdRole(rIds.get(i));
                userRoleMapper.insert(userRole);
            }
            return true;
        } else {
            String username = userMapper.selectByPrimaryKey(user.getUserId()).getUsername();
            if (username.equals(user.getUsername())) {
                userMapper.updateByPrimaryKey(user);
                userRoleMapper.deleteByUid(user.getUserId());
                for(int i=0;i<rIds.size();i++){
                    UserRole userRole=new UserRole();
                    userRole.setIdUser(user.getUserId());
                    userRole.setIdRole(rIds.get(i));
                    userRoleMapper.insert(userRole);
                }
                return true;
            } else
                return false;

        }
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);

    }

    @Override
    public List<Role> selectByUsername(String username) {
        return roleMapper.getRoleByUsername(username);
    }

    public List<Power> fidByRoleId(String rolename) {
//        Role role = userMapper.finfByUsername(username);
//        System.out.println(role.getRolename());
        List<Power> powerList = roleMapper.findByRoleid(rolename);

        return powerList;
    }

    @Override
    public List<String> findRolenameByUsername(String username) {
        List<String> Rname = roleMapper.getRnameByUname(username);
        return Rname;

    }

    //查询角色列表
    @Override
    public List<Role> Rolelist() {
        return roleMapper.selectAll();
    }

    //查询用户信息及关联角色id
    @Override
    public UserR getUserAndRoles(Integer id) {
        return userMapper.getUserAndRoles(id);
    }

    @Override
    public List<Power> findPowerByUsername(String username) {
        return userMapper.findPowerByUsername(username);
    }


}
