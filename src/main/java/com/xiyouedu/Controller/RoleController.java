package com.xiyouedu.Controller;

import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.Role;
import com.xiyouedu.bean.RoleP;
import com.xiyouedu.bean.RolePermissionVo;
import com.xiyouedu.service.RoleService;
import com.xiyouedu.util.LayuiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);


    @Autowired
    private RoleService roleService;

    /**
     * 返回角色表格
     * @return
     */
    @RequestMapping("toshowrole")
    public String toshowr(){
        return "/role/showrole.html";
    }

    /**
     * 返回角色信息及拥有权限
     * @param role
     * @return
     */
    @RequestMapping("showrole")
    @ResponseBody
    public LayuiResult<RolePermissionVo> show(String role){
        LayuiResult<RolePermissionVo>  result=new LayuiResult<>();
        List<RolePermissionVo> list=roleService.findAll(role);
        result.setData(list);
        logger.debug("查询到的角色列表，list："+list);
        return result;

    }

    /**
     * 删除角色
     * @param rid
     * @return
     */
    @RequestMapping("role/delete")
    @ResponseBody
    public Map<String,Object> delete(Integer rid) {
        logger.debug("删除的角色ID为："+rid);
            Map<String,Object> map=new HashMap<>();
            List<String> list = roleService.deleteRole(rid);
            if(null==list){
                map.put("msg","ok");
                logger.info("角色ID为："+rid+"的角色删除成功");
            }else {

                map.put("username",list);
                map.put("msg","error");
                logger.error("角色ID为："+rid+"的角色删除失败，拥有该角色的用户名为："+list);
            }
        return map;
    }

    /**
     * 添加用户表单回显数据库角色
     * @return 角色列表
     */
    @RequestMapping("selectPermiassionList")
    @ResponseBody
    public  List<Power> selectRoleList(){
        List<Power> result = roleService.Powerlist();
        logger.info("数据库拥有的权限列表为:"+result);
        return result;

    }

    /**
     * 添加和编辑
     * @param role
     * @param pers
     * @param roleid
     * @return
     */
    @RequestMapping("addRole")
    @ResponseBody
    public String addrole(String role,String pers,Integer roleid)
    {
        if(roleid==null){
            logger.info("添加角色操作");
        }else {
            logger.info("编辑角色操作！");
        }
        String s = roleService.addRole(role, pers, roleid);
        if(s.equals("upok")){
            logger.info("更新角色成功");
        }else if(s.equals("uperror")){
            logger.error("更新角色失败");
        }else if(s.equals("addok")){
            logger.info("添加角色成功");

        }else {
            logger.error("添加角色失败");
        }
        return s;
    }

    /**
     * 编辑角色信息回显
     * @param pid
     * @return
     */
    @RequestMapping(value = "getRoleAndpermissions",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getRoleAndpermissions(@RequestParam("id") Integer pid){
        logger.debug("编辑的角色id为："+pid);
        Map<String,Object> map = new HashMap<>();
         RoleP rp=roleService.getRoleAndPowers(pid);
        if(null!=rp){
            map.put("role",rp);
            logger.info("角色id为："+pid+"的信息为："+rp);
            List<Power> powers=roleService.Powerlist();
            if(null!=powers&&powers.size()>0){
                map.put("powers",powers);
                logger.info("角色id为："+pid+"的权限信息为："+rp);

            }else{
                logger.error("ID为："+pid+"的信息回显错误！");
            }
            map.put("msg","ok");
        }

         return map;
    }

}
