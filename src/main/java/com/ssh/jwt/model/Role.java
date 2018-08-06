package com.ssh.jwt.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Role {
    private int roleId;
    private String roleName;
    private String roleDesc;
    private Timestamp insertTime;
    private Timestamp updateTime;
    private String isDeleted;
    private Set<User> usersByRoleId;
    private Set<RoleResource> roleResourcesByRoleId;

    public Role() {
    }

    public Role(int roleId) {
        this.roleId = roleId;
    }

    public Role(int roleId, String roleName, String roleDesc, Timestamp insertTime, Timestamp updateTime, String isDeleted, Set<User> usersByRoleId) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
        this.usersByRoleId = usersByRoleId;
    }

    @Id
    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_desc", nullable = true, length = 255)
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "insert_time", nullable = true)
    public Timestamp getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Timestamp insertTime) {
        this.insertTime = insertTime;
    }

    @Basic
    @Column(name = "update_time", nullable = true)
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "is_deleted", nullable = true, length = 1)
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Set<User> getUsersByRoleId() {
        return usersByRoleId;
    }

    public void setUsersByRoleId(Set<User> usersByRoleId) {
        this.usersByRoleId = usersByRoleId;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Set<RoleResource> getRoleResourcesByRoleId() {
        return roleResourcesByRoleId;
    }

    public void setRoleResourcesByRoleId(Set<RoleResource> roleResourcesByRoleId) {
        this.roleResourcesByRoleId = roleResourcesByRoleId;
    }
}
