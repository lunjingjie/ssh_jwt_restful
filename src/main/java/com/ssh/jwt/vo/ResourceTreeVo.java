package com.ssh.jwt.vo;

import java.util.ArrayList;
import java.util.List;

public class ResourceTreeVo {

    private int id;
    private String resourceName;
    private String resourcePath;
    private int pid;
    private List<ResourceTreeVo> children = new ArrayList<>();

    public ResourceTreeVo() {
    }

    public ResourceTreeVo(int id, String resourceName, String resourcePath, int pid, List<ResourceTreeVo> children) {
        this.id = id;
        this.resourceName = resourceName;
        this.resourcePath = resourcePath;
        this.pid = pid;
        this.children = children;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public List<ResourceTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceTreeVo> children) {
        this.children = children;
    }
}
