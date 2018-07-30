package com.ssh.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("web")
public class TestController {

    @GetMapping
    @RequestMapping("/test")
    public Map<String, String> testControl() {
        Map<String, String> map = new HashMap<>();
        map.put("msg", "success");
        return map;
    }
}
