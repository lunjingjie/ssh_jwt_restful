package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.RoleResourceDao;
import com.ssh.jwt.model.Resource;
import com.ssh.jwt.model.RoleResource;
import core.dao.BaseDao;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleResourceDaoImpl extends BaseDao<RoleResource> implements RoleResourceDao {

    public RoleResourceDaoImpl() {
        super(RoleResource.class);
    }

    @Override
    public List<Resource> getRoleResourceIds(int roleId) {
        String sql = "SELECT t2.resource_id as resourceId FROM resource t1 " +
                "INNER JOIN role_resource t2 ON t1.resource_id = t2.resource_id " +
                "WHERE t2.role_id = ? AND t2.is_deleted = 'N'";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql)
                .setParameter(0, roleId)
                .setResultTransformer(Transformers.aliasToBean(Resource.class));

        sq.addScalar("resourceId", StandardBasicTypes.INTEGER);
        return sq.list();
    }
}
