package com.ssh.jwt.service;

import com.ssh.jwt.model.Resource;
import com.ssh.jwt.vo.ResourceTreeVo;
import core.service.Service;

public interface PageResourceService extends Service<Resource>{

    ResourceTreeVo getResourceTreeByRoleId(int roleId);

    ResourceTreeVo loadAllResourceTree();
}
