package com.ssh.jwt.dao.impl;

import com.ssh.jwt.dao.UserDao;
import com.ssh.jwt.model.User;
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
}
