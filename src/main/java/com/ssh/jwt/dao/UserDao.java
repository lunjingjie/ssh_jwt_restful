package com.ssh.jwt.dao;

import com.ssh.jwt.model.User;
import core.dao.Dao;

import java.util.List;

public interface UserDao extends Dao<User>{

    List<User> testConnection();
}
