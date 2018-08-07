package com.ssh.jwt.service;

import com.ssh.jwt.model.Resource;
import com.ssh.jwt.model.RoleResource;
import core.service.Service;

import java.util.List;

public interface RoleResourceService extends Service<RoleResource> {

    List<Resource> getRoleResourceIds(int roleId);
}
