package com.xiyouedu.shiro;

import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.User;
import com.xiyouedu.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 执行授权逻辑
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("执行授权逻辑");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //添加资源授权字符串
//        info.addStringPermission("user:add");
        //数据库查询当前登录用户的授权字符串
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Power> power = null;

        try {
             power = userServiceimpl.findPowerByUsername(user.getUsername());

        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> permissions=new ArrayList<>();
        if(power!=null){
            for(Power p:power){
                permissions.add(p.getPowerurl());
            }
        }
        logger.debug("权限信息为："+permissions);
        info.addStringPermissions(permissions);
        return info;
    }

    @Autowired
    private UserServiceImpl userServiceimpl;

    /**
     * 执行认证逻辑
     *
     * @param arg0
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
            logger.info("执行认证逻辑");
//        String username="eric";
//        String password="123456";

        //编写shiro判断逻辑，判断用户名和密码
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;

        logger.info("用户登录认证：验证当前Subject时获取到token为：" + token);
        User user = userServiceimpl.findByUsername(token.getUsername());
        if (user == null) {
            //用户名不存在
            return null;//shiro底层会抛出UnknownAccountException
        }

        //2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
