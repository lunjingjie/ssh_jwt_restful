package com.ssh.jwt.service.impl;

import com.ssh.jwt.dao.RoleResourceDao;
import com.ssh.jwt.model.Resource;
import com.ssh.jwt.model.RoleResource;
import com.ssh.jwt.service.RoleResourceService;
import core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleResourceServiceImpl extends BaseService<RoleResource> implements RoleResourceService {

    private final RoleResourceDao roleResourceDao;

    @Autowired
    public RoleResourceServiceImpl(RoleResourceDao roleResourceDao) {
        this.dao = roleResourceDao;
        this.roleResourceDao = roleResourceDao;
    }

    @Override
    public List<Resource> getRoleResourceIds(int roleId) {
        return roleResourceDao.getRoleResourceIds(roleId);
    }
}
