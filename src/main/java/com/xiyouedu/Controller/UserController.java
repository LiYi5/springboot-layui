package com.xiyouedu.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiyouedu.bean.Role;
import com.xiyouedu.bean.User;
import com.xiyouedu.bean.UserR;
import com.xiyouedu.bean.UserRoleVo;
import com.xiyouedu.service.UserService;
import com.xiyouedu.util.LayuiResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

        private static final Logger logger = LoggerFactory.getLogger(UserController.class);

        @Autowired
        private UserService userService;


        //首页面
        @RequestMapping("/")
        public String index() {
            return "login.html";
        }


        //登陆页面
        @RequestMapping("/login.action")
        public String login(User user, Model model) {

            logger.debug("登陆用户为:"+user);
            /**
             * 使用shiro编写认证操作
             */
            //1.获取Subject
            Subject subject = SecurityUtils.getSubject();
            //2.封装用户数据
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            //执行登陆操作
            try {
                subject.login(token);
                logger.info("用户为"+user+"登陆成功！");
                List<Role> roles = userService.selectByUsername(user.getUsername());

                logger.debug("登陆用户的角色为："+roles);

                model.addAttribute("name", user.getUsername());
    //            System.out.println(role.getRolename());
                if (roles.size()!=0) {
                    model.addAttribute("msg", roles.get(0).getRolename());
    //                model.addAttribute("powerlist",powerList);
                } else {
                    model.addAttribute("msg", "你没有任何角色");
                }
                return "user/index.html";
                //登陆成功
            } catch (UnknownAccountException e) {
    //                e.printStackTrace();
                //登陆失败:用户名不存在
                logger.error("用户为："+user+"用户名不存在");
                model.addAttribute("msg", "用户名不存在");
                return "login.html";
            } catch (IncorrectCredentialsException e) {
    //                e.printStackTrace();
                logger.error("用户为："+user+"密码错误");
                //登陆失败:密码错误
                model.addAttribute("msg", "密码错误");
                return "login.html";
            }
        }


        /**
         * 添加用户操作
         *
         * @param user 前端页面返回的json格式的user数据
         * @return true/false：返回到前端页面，判断添加用户操作是否成功
         */
        @RequestMapping("adduser")
        @ResponseBody
        @RequiresPermissions("user:add")
        public String add(String user,String roleids) {
            UserRoleVo userrolevo = JSON.parseObject(user, new TypeReference<UserRoleVo>() {
            });
            User user1=new User();
            user1.setUsername(userrolevo.getUsername());
            user1.setPassword(userrolevo.getPassword());
            logger.debug("添加的用户信息,user:"+user1+"roleids:"+roleids);
            boolean b = userService.addUser(user1, roleids);
            if(b){
                logger.info("用户为："+user1+"添加成功");
                return "ok";
            }else{
                logger.error("用户为："+user1+"添加失败！用户名重复");
                return "error";
            }

        }

        /**
         * 返回用户列表页面
         *
         * @return
         */
        @RequestMapping("toshowuser")
        @RequiresPermissions("user:select")
        public String toshowu() {
            return "/user/showuser.html";
        }

        /**
         * 用户数据列表展示
         * @return 符合layui中tab要求的json格式数据{code：0,msg:"",count:100,data[userlist]}
         */
        @RequestMapping("showuser")
        @ResponseBody
        @RequiresPermissions("user:select")
        public LayuiResult<UserRoleVo> show(String user) {
            LayuiResult<UserRoleVo> result = new LayuiResult<UserRoleVo>();
            List<UserRoleVo> list = userService.findAll(user);
            result.setData(list);
            logger.debug("用户列表查询=list:" + list);
            return result;

        }

        /**
         * 删除用户
         *
         * @param uid 前端页面传递的userid；
         * @return true/false 让前端页面判断删除是否成功
         */
        @RequestMapping("delete")
        @ResponseBody
        @RequiresPermissions("user:delete")
        public String delete(Integer uid) {
                logger.debug("删除用户的ID为："+uid);
            boolean b = userService.deleteUser(uid);
            if(b){
                logger.info("用户ID为："+uid+"删除成功");
                return "ok";
            }else{
                logger.error("用户ID为："+uid+"删除失败");
                return "error";
            }

        }

    /**
     * 返回用户信息：user信息，拥有的角色信息
     * @param uid
     * @return
     */
        @RequestMapping(value = "/getUserAndRole" ,method = RequestMethod.GET)
        @ResponseBody
        @RequiresPermissions("user:update")
        public Map<String,Object> getUserAndRole(@RequestParam("id") Integer uid){
            logger.debug("编辑的用户ID为："+uid);
            Map<String,Object> map = new HashMap<>();
            //查询用户
            UserR ur = userService.getUserAndRoles(uid);
            if(null!=ur){
                map.put("user",ur);
                logger.debug("用户ID为"+uid+"的信息为，user"+ur);
                List<Role> roles = userService.Rolelist();

                if(null!=roles&&roles.size()>0){
                    map.put("roles",roles);
                    logger.info("用户ID为"+uid+"的角色为："+roles);
                }
                else{
                    logger.warn("用户ID为"+uid+"没有角色！");
                }
                map.put("msg","ok");
            }
            return map;
        }

    /**
     * 获取页面返回的更新用户信息
     * @param user
     * @param roleids
     * @param uid
     * @return
     */
        @RequestMapping("updateu")
        @ResponseBody
        @RequiresPermissions("user:update")
        public boolean updateuser(String user,String roleids,Integer uid) {
            logger.debug("用户ID为："+uid+"更新的信息为，user："+user+"角色关联为："+roleids);
            boolean update = userService.update(user, roleids, uid);
            if(update){
                logger.debug("用户ID为："+uid+"更新成功!");
            }else{
                logger.error("用户ID为："+uid+"更新失败!");
            }
            return  update;
        }

        //注销
        @RequestMapping("goout")
        public String goout() {
            return "login.html";
        }

    /**
     * 添加用户表单回显数据库角色
     * @return 角色列表
     */
        @RequestMapping("selectRoleList")
        @ResponseBody
        @RequiresPermissions("user:add")
        public  List<Role> selectRoleList(){
            List<Role> result = userService.Rolelist();
            logger.debug("数据库中的角色为："+result);
            return result;

        }


        @RequestMapping("welcome")
        public String welcome(){
            return "/user/welcome.html";
        }

}
