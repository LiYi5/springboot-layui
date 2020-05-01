package com.xiyouedu;

import com.alibaba.fastjson.JSONObject;
import com.xiyouedu.bean.Power;
import com.xiyouedu.bean.Role;
import com.xiyouedu.bean.User;
import com.xiyouedu.mapper.RoleMapper;
import com.xiyouedu.mapper.RolePowerMapper;
import com.xiyouedu.mapper.UserMapper;
import com.xiyouedu.mapper.UserRoleMapper;
import com.xiyouedu.service.UserService;
import com.xiyouedu.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisApplicationTests {
	//记录器
	Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	UserMapper userMapper;
	@Test
	public void contextLoads() {

	}
}
