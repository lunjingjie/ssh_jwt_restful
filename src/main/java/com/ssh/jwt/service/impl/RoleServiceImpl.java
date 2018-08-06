package com.ssh.jwt.service.impl;

import com.ssh.jwt.dao.RoleDao;
import com.ssh.jwt.model.Resource;
import com.ssh.jwt.model.Role;
import com.ssh.jwt.model.RoleResource;
import com.ssh.jwt.service.PageResourceService;
import com.ssh.jwt.service.RoleResourceService;
import com.ssh.jwt.service.RoleService;
import com.ssh.jwt.vo.SaveRoleResourceVo;
import core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private final RoleDao roleDao;
    private final PageResourceService pageResourceService;
    private final RoleResourceService roleResourceService;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao, PageResourceService pageResourceService, RoleResourceService roleResourceService) {
        this.dao = roleDao;
        this.roleDao = roleDao;
        this.pageResourceService = pageResourceService;
        this.roleResourceService = roleResourceService;
    }

    @Override
    public void deleteRoleResource(Role role) {
        // TODO 有bug，待查实
        roleResourceService.deleteAudit("role.roleId", role.getRoleId());
    }

    @Override
    public void saveRoleResource(Role role, SaveRoleResourceVo svo) {
        String[] pageResourceIds = svo.getPageResourceIds().split("&");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (String id : pageResourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setInsertTime(timestamp);
            roleResource.setIsDeleted("N");
            roleResource.setRoleByRoleId(role);
            Resource resource = pageResourceService.get(Integer.valueOf(id));
            roleResource.setResourceByResourceId(resource);
            roleResourceService.persist(roleResource);
        }
    }
}
