package com.ssh.jwt.service;

import com.ssh.jwt.model.Role;
import com.ssh.jwt.vo.SaveRoleResourceVo;
import core.service.Service;

public interface RoleService extends Service<Role>{

    void deleteRoleResource(Role role);

    void saveRoleResource(Role role, SaveRoleResourceVo svo);
}
