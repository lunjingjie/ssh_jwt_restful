package com.ssh.jwt.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role_resource", schema = "ssh_jwt_restful")
public class RoleResource {
    private Integer id;
    private Timestamp insertTime;
    private Timestamp updateTime;
    private String isDeleted;
    private Role roleByRoleId;
    private Resource resourceByResourceId;

    public RoleResource() {
    }

    public RoleResource(Integer id) {
        this.id = id;
    }

    public RoleResource(Integer id, Timestamp insertTime, Timestamp updateTime, String isDeleted, Role roleByRoleId, Resource resourceByResourceId) {
        this.id = id;
        this.insertTime = insertTime;
        this.updateTime = updateTime;
        this.isDeleted = isDeleted;
        this.roleByRoleId = roleByRoleId;
        this.resourceByResourceId = resourceByResourceId;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id")
    public Resource getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(Resource resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }
}
