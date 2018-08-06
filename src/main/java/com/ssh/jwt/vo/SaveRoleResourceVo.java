package com.ssh.jwt.vo;

import io.swagger.annotations.ApiModelProperty;

public class SaveRoleResourceVo {

    @ApiModelProperty("权限角色ID")
    private int roleId;
    @ApiModelProperty("页面资源id集合，用'&'作为分隔符")
    private String pageResourceIds;

    public SaveRoleResourceVo() {
    }

    public SaveRoleResourceVo(int roleId, String pageResourceIds) {
        this.roleId = roleId;
        this.pageResourceIds = pageResourceIds;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPageResourceIds() {
        return pageResourceIds;
    }

    public void setPageResourceIds(String pageResourceIds) {
        this.pageResourceIds = pageResourceIds;
    }
}
