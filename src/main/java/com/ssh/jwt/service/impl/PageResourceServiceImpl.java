package com.ssh.jwt.service.impl;

import com.ssh.jwt.dao.PageResourceDao;
import com.ssh.jwt.model.Resource;
import com.ssh.jwt.service.PageResourceService;
import com.ssh.jwt.vo.ResourceTreeVo;
import core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageResourceServiceImpl extends BaseService<Resource> implements PageResourceService {

    private final PageResourceDao pageResourceDao;

    @Autowired
    public PageResourceServiceImpl(PageResourceDao pageResourceDao) {
        this.pageResourceDao = pageResourceDao;
        this.dao = pageResourceDao;
    }

    @Override
    public ResourceTreeVo getResourceTreeByRoleId(int roleId) {
        ResourceTreeVo resourceTreeVo = createChildNodes(roleId, 1);
        return resourceTreeVo;
    }

    public ResourceTreeVo createChildNodes(int roleId, int pid) {
        ResourceTreeVo node = pageResourceDao.getParentTreeByChildId(roleId, pid);
        List<ResourceTreeVo> allResource = pageResourceDao.getChildrenTreeByPid(roleId, pid);
        for (ResourceTreeVo resource : allResource) {
            ResourceTreeVo n = createChildNodes(roleId, resource.getId());
            node.getChildren().add(n);
        }
        return node;
    }

    @Override
    public ResourceTreeVo loadAllResourceTree() {
        ResourceTreeVo resourceTreeVo = createChildNodes(1);
        return resourceTreeVo;
    }

    public ResourceTreeVo createChildNodes(int pid) {
        ResourceTreeVo node = pageResourceDao.getParentTreeByChildId(pid);
        List<ResourceTreeVo> allResource = pageResourceDao.getChildrenTreeByPid(pid);
        for (ResourceTreeVo resource : allResource) {
            ResourceTreeVo n = createChildNodes(resource.getId());
            node.getChildren().add(n);
        }
        return node;
    }
}
