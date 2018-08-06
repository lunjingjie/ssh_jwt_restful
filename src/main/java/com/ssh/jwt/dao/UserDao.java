package com.ssh.jwt.dao;

import com.ssh.jwt.model.User;
import com.ssh.jwt.vo.UserVo;
import core.dao.Dao;

import java.util.List;

public interface UserDao extends Dao<User>{

    List<User> testConnection();

    /**
     * 获取所有用户列表
     * @return
     */
    List<UserVo> getAllUser();

    /**
     * 软删除用户
     */
    void softDeleteUser(int userId);
}
