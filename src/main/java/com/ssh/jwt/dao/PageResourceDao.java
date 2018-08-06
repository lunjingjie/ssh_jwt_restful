package com.ssh.jwt.dao;

import com.ssh.jwt.model.Resource;
import com.ssh.jwt.vo.ResourceTreeVo;
import core.dao.Dao;

import java.util.List;

public interface PageResourceDao extends Dao<Resource>{

    /**
     * 根据父id获取子节点
     * @param pid
     * @return
     */
    List<ResourceTreeVo> getChildrenTreeByPid(int roleId, int pid);

    /**
     * 根据子节点获取父节点
     * @param cid
     * @return
     */
    ResourceTreeVo getParentTreeByChildId(int roleId, int cid);

    /**
     * 根据父id获取子节点
     * @param pid
     * @return
     */
    List<ResourceTreeVo> getChildrenTreeByPid(int pid);

    /**
     * 根据子节点获取父节点
     * @param cid
     * @return
     */
    ResourceTreeVo getParentTreeByChildId(int cid);
}
