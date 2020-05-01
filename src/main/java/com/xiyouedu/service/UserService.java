package com.xiyouedu.service;

import com.xiyouedu.bean.*;


import java.util.List;

public interface UserService {
    //添加用户
    boolean addUser(User user,String rid);

    //分页查找用户
    List<User> findUsers(int page, int rows);

    //删除用户
    boolean deleteUser(Integer id);

    //根据id查询用户
    User getUserByid(Integer id);

    boolean login(User user);

    List<UserRoleVo> findAll(String username);


    boolean update(String user,String roleids,Integer uid);

    User findByUsername(String username);

    //查询用户拥有角色
    List<Role> selectByUsername(String username);

    List<Power> fidByRoleId(String rolename);

    List<String> findRolenameByUsername(String username);

    List<Role> Rolelist();

    UserR getUserAndRoles(Integer id);

    List<Power> findPowerByUsername(String username);
}
