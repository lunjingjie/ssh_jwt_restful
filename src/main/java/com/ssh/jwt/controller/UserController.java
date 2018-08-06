package com.ssh.jwt.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.ssh.jwt.model.Role;
import com.ssh.jwt.model.User;
import com.ssh.jwt.service.UserService;
import com.ssh.jwt.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("web/user")
@Api(tags = "用户信息")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ApiOperation(httpMethod = "POST", value = "创建单个用户信息", produces = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(UserVo userVo) {
        User user = new User();
        user.setUserName(userVo.getUserName());
        user.setUserPassword(userVo.getUserPassword());
        Role role = new Role(userVo.getRoleId());
        user.setRoleByRoleId(role);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setInsertTime(now);
        user.setIsDeleted("N");
        userService.persist(user);
    }

    @GetMapping("/list")
    @ApiOperation(httpMethod = "GET", value = "查看所有用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserVo.UserQueryView.class)
    public List<UserVo> getAllUser() {
        return userService.getAllUser();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(httpMethod = "DELETE", value = "删除用户", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@PathVariable(value = "id") int userId) {
        userService.deleteUser(userId);
    }

    @PutMapping
    @ApiOperation(httpMethod = "PUT", value = "更新用户", produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(UserVo userVo) {
        String username = userVo.getUserName();
        int userId = userVo.getUserId();
        int roleId = userVo.getRoleId();
        String password = userVo.getUserPassword();
        User user = userService.get(userId);
        user.setUserPassword(password);
        if (username != "") {
            user.setUserName(username);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        user.setUpdateTime(now);
        if (roleId != 0) {
            Role role = new Role(roleId);
            user.setRoleByRoleId(role);
        }
        userService.update(user);
    }
}
