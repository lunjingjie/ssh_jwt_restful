package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.PageResourceDao;
import com.ssh.jwt.model.Resource;
import com.ssh.jwt.vo.ResourceTreeVo;
import core.dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PageResourceDaoImpl extends BaseDao<Resource> implements PageResourceDao {

    public PageResourceDaoImpl() {
        super(Resource.class);
    }

    /**
     * 根据父id获取子节点
     *
     * @param roleId
     * @param pid
     * @return
     */
    @Override
    public List<ResourceTreeVo> getChildrenTreeByPid(int roleId, int pid) {
        String sql = "SELECT t3.resource_id as id, t3.resource_name as resourceName, t3.resource_path as resourcePath, t3.pid as pid " +
                "FROM role t1 " +
                "INNER JOIN role_resource t2 ON t1.role_id = t2.role_id " +
                "INNER JOIN resource t3 ON t3.resource_id = t2.resource_id " +
                "WHERE t3.pid = ? AND t1.role_id = ? AND t2.is_deleted = 'N'";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql)
                .setParameter(0, pid)
                .setParameter(1, roleId)
                .setResultTransformer(Transformers.aliasToBean(ResourceTreeVo.class));
        sq.addScalar("id", StandardBasicTypes.INTEGER);
        sq.addScalar("resourceName", StandardBasicTypes.STRING);
        sq.addScalar("resourcePath", StandardBasicTypes.STRING);
        sq.addScalar("pid", StandardBasicTypes.INTEGER);
        return sq.list();
    }

    /**
     * 根据子节点获取父节点
     *
     * @param roleId
     * @param cid
     * @return
     */
    @Override
    public ResourceTreeVo getParentTreeByChildId(int roleId, int cid) {
        String sql = "SELECT t3.resource_id as id, t3.resource_name as resourceName, t3.resource_path as resourcePath, t3.pid as pid " +
                "FROM role t1 " +
                "INNER JOIN role_resource t2 ON t1.role_id = t2.role_id " +
                "INNER JOIN resource t3 ON t3.resource_id = t2.resource_id " +
                "WHERE t1.role_id = ? AND t3.resource_id = ? AND t2.is_deleted = 'N'";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql)
                .setParameter(0, roleId)
                .setParameter(1, cid)
                .setResultTransformer(Transformers.aliasToBean(ResourceTreeVo.class));
        sq.addScalar("id", StandardBasicTypes.INTEGER);
        sq.addScalar("resourceName", StandardBasicTypes.STRING);
        sq.addScalar("resourcePath", StandardBasicTypes.STRING);
        sq.addScalar("pid", StandardBasicTypes.INTEGER);
        return (ResourceTreeVo) sq.uniqueResult();
    }

    @Override
    public List<ResourceTreeVo> getChildrenTreeByPid(int pid) {
        String sql = "SELECT resource_id as id, resource_name as resourceName, resource_path as resourcePath, pid as pid " +
                "FROM resource WHERE pid = ?";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql).setParameter(0, pid)
                .setResultTransformer(Transformers.aliasToBean(ResourceTreeVo.class));
        sq.addScalar("id", StandardBasicTypes.INTEGER);
        sq.addScalar("resourceName", StandardBasicTypes.STRING);
        sq.addScalar("resourcePath", StandardBasicTypes.STRING);
        sq.addScalar("pid", StandardBasicTypes.INTEGER);
        return sq.list();
    }

    @Override
    public ResourceTreeVo getParentTreeByChildId(int cid) {
        String sql = "SELECT resource_id as id, resource_name as resourceName, resource_path as resourcePath, pid as pid " +
                "FROM resource WHERE resource_id = ?";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql).setParameter(0, cid)
                .setResultTransformer(Transformers.aliasToBean(ResourceTreeVo.class));
        sq.addScalar("id", StandardBasicTypes.INTEGER);
        sq.addScalar("resourceName", StandardBasicTypes.STRING);
        sq.addScalar("resourcePath", StandardBasicTypes.STRING);
        sq.addScalar("pid", StandardBasicTypes.INTEGER);
        return (ResourceTreeVo) sq.uniqueResult();
    }
}
