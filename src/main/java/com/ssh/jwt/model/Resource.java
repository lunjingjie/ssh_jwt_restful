package com.ssh.jwt.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

@Entity
public class Resource {
    private int resourceId;
    private String resourceName;
    private String resourcePath;
    private String resourceIcon;
    private Integer pid;
    private Timestamp insertTime;
    private Timestamp updateTime;
    private String isDeleted;
    private Set<RoleResource> roleResourcesByResourceId;

    @Id
    @Column(name = "resource_id", nullable = false)
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Basic
    @Column(name = "resource_name", nullable = true, length = 255)
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    @Basic
    @Column(name = "resource_path", nullable = true, length = 255)
    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    @Basic
    @Column(name = "resource_icon", nullable = true, length = 255)
    public String getResourceIcon() {
        return resourceIcon;
    }

    public void setResourceIcon(String resourceIcon) {
        this.resourceIcon = resourceIcon;
    }

    @Basic
    @Column(name = "pid", nullable = true)
    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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
    @Column(name = "is_deleted", nullable = true, length = 255)
    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    @OneToMany(mappedBy = "resourceByResourceId")
    public Set<RoleResource> getRoleResourcesByResourceId() {
        return roleResourcesByResourceId;
    }

    public void setRoleResourcesByResourceId(Set<RoleResource> roleResourcesByResourceId) {
        this.roleResourcesByResourceId = roleResourcesByResourceId;
    }
}
