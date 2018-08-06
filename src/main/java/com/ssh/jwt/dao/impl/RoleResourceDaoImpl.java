package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.RoleResourceDao;
import com.ssh.jwt.model.RoleResource;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleResourceDaoImpl extends BaseDao<RoleResource> implements RoleResourceDao {

    public RoleResourceDaoImpl() {
        super(RoleResource.class);
    }
}
