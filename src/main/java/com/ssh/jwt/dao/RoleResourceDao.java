package com.ssh.jwt.dao;

import com.ssh.jwt.model.Resource;
import com.ssh.jwt.model.RoleResource;
import core.dao.Dao;

import java.util.List;

public interface RoleResourceDao extends Dao<RoleResource> {

    List<Resource> getRoleResourceIds(int roleId);
}
