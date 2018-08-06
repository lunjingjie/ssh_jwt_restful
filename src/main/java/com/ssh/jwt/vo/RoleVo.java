package com.ssh.jwt.vo;

import io.swagger.annotations.ApiModelProperty;

public class RoleVo {

    @ApiModelProperty("权限角色ID")
    private int roleId;
    @ApiModelProperty("权限角色名称")
    private String roleName;

    public RoleVo() {
    }

    public RoleVo(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
