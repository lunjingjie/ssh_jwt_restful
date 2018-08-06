package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.UserDao;
import com.ssh.jwt.model.User;
import com.ssh.jwt.vo.UserVo;
import core.dao.BaseDao;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public List<User> testConnection() {
        String sql = "SELECT user_password as userPassword FROM user WHERE user_name = ?";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql)
                .setParameter(0, "lunjingjie")
                .setResultTransformer(Transformers.aliasToBean(User.class));
        sq.addScalar("userPassword", StandardBasicTypes.STRING);
        return sq.list();
    }

    @Override
    public List<UserVo> getAllUser() {
        String sql = "SELECT user_id as userId, user_name as userName, user_password as userPassword, role_id as roleId" +
                " FROM user WHERE is_deleted = 'N'";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(UserVo.class));
        sq.addScalar("userId", StandardBasicTypes.INTEGER);
        sq.addScalar("userName", StandardBasicTypes.STRING);
        sq.addScalar("userPassword", StandardBasicTypes.STRING);
        sq.addScalar("roleId", StandardBasicTypes.INTEGER);
        return sq.list();
    }

    /**
     * 软删除用户
     */
    @Override
    public void softDeleteUser(int userId) {
        String sql = "UPDATE user SET is_deleted = 'Y' WHERE user_id = ?";
        SQLQuery sq = (SQLQuery) getSession().createSQLQuery(sql)
                .setParameter(0, userId);
        sq.executeUpdate();
    }
}
