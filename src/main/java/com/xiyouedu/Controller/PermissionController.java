package com.xiyouedu.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiyouedu.bean.Power;
import com.xiyouedu.service.PermissionService;
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
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionService;

    /**
     * 返回权限表格
     * @return
     */
    @RequestMapping("toshowpermission")
    public String toshowp(){
        return "/permission/showpermission.html";
    }

    /**
     * 返回权限信息
     * @param permissionname
     * @return
     */
    @RequestMapping("showpermission")
    @ResponseBody
    public LayuiResult<Power> showp(String permissionname){

        LayuiResult<Power> result=new LayuiResult<>();
        List<Power> list=permissionService.findAll(permissionname);
        logger.debug("查询到的权限列表为，list："+list);
        result.setData(list);
        return result;
    }





    /**
     *删除
     * @param pid
     * @return
     */
    @RequestMapping("permission/delete")
    @ResponseBody
    public Map<String,Object> delete(Integer pid){
        logger.debug("删除的权限ID："+pid);
        Map<String,Object> map=new HashMap<>();
        List<String> list=permissionService.deletePermission(pid);
        if(null==list){
            map.put("msg","ok");
            logger.info("ID为："+pid+"的权限信息删除成功！");
        }else {
            map.put("rolename",list);
            map.put("msg","error");
            logger.error("ID为："+pid+"的权限删除失败，拥有该权限的角色为："+list);
        }
        return map;
    }

    /**
     * 添加和修改权限
     * @param permission
     * @return
     */
    @RequestMapping("addpermission")
    @ResponseBody
    public String addp(String permission,Integer pid){

        if(pid==null){
            logger.info("添加权限操作");
        }else{
            logger.info("更新权限操作");
        }
        String s = permissionService.addP(permission, pid);

        return s;

    }

    /**
     * 回显权限信息
     * @param pid
     * @return
     */
    @RequestMapping(value = "/getpermission",method = RequestMethod.GET)
    @ResponseBody
    public Power getpermission(@RequestParam("id") Integer pid){
        logger.debug("需要回显的权限ID为："+pid);
        Power power = permissionService.getPower(pid);
        logger.debug("权限ID为："+pid+"的信息为："+power);
        return  power;
    }

}
