package com.ssh.jwt.controller;

import com.google.common.collect.Maps;
import com.ssh.jwt.dao.UserDao;
import com.ssh.jwt.model.User;
import com.ssh.jwt.service.UserService;
import core.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userSerivce;

    @Autowired
    public LoginController(UserService userSerivce) {
        this.userSerivce = userSerivce;
    }

    @GetMapping("/applyToken")
    public Map<String, Object> applyToken(String userName, String userPassword) throws Exception {
        String jwt = TokenUtil.createToken("web", userName);
        String[] propName = {"userName", "userPassword"};
        String[] propValue = {userName, userPassword};
        User user = userSerivce.getByProerties(propName, propValue);
        Map<String, Object> map = Maps.newHashMap();
        map.put("token", jwt);
        map.put("用户名", userName);
        return map;
    }
}
