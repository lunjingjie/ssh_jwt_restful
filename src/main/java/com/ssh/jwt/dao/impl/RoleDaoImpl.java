package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.RoleDao;
import com.ssh.jwt.model.Role;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {

    public RoleDaoImpl() {
        super(Role.class);
    }
}
