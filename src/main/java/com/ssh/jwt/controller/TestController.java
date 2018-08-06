package com.ssh.jwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("web")
@Api(tags="测试")
public class TestController {

    @GetMapping
    @RequestMapping("/test")
    @ApiOperation(httpMethod = "GET", value = "请求登录", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> testControl() {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "success");
        return map;
    }
}
