package com.ssh.jwt.vo;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiModelProperty;

public class UserVo {

    public interface UserQueryView {};

    @ApiModelProperty("用户ID")
    private int userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("用户密码")
    private String userPassword;
    @ApiModelProperty("权限角色ID")
    private int roleId;

    public UserVo() {
    }

    public UserVo(int userId, String userName, String userPassword, int roleId) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.roleId = roleId;
    }

    @JsonView(UserQueryView.class)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @JsonView(UserQueryView.class)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonView(UserQueryView.class)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
