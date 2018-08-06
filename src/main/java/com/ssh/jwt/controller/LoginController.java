package com.ssh.jwt.controller;

import com.google.common.collect.Maps;
import com.ssh.jwt.dao.UserDao;
import com.ssh.jwt.model.User;
import com.ssh.jwt.service.UserService;
import core.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/login")
@Api(tags="登录")
public class LoginController {

    private final UserService userSerivce;

    @Autowired
    public LoginController(UserService userSerivce) {
        this.userSerivce = userSerivce;
    }

    @GetMapping("/applyToken")
    @ApiOperation(httpMethod = "GET", value = "请求登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> applyToken(@ApiParam("用户名") @RequestParam String userName,
                                          @ApiParam("用户密码") @RequestParam String userPassword) throws Exception {
        String jwt = TokenUtil.createToken("web", userName);
        String[] propName = {"userName", "userPassword"};
        String[] propValue = {userName, userPassword};
        User user = userSerivce.getByProerties(propName, propValue);
        int roleId = user.getRoleByRoleId().getRoleId();
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", jwt);
        map.put("roleId", roleId);
        return map;
    }
}
