package com.ssh.jwt.service.impl;

import com.ssh.jwt.dao.UserDao;
import com.ssh.jwt.model.User;
import com.ssh.jwt.service.UserService;
import com.ssh.jwt.vo.UserVo;
import core.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        this.dao = userDao;
    }

    @Override
    public List<UserVo> getAllUser() {
        return userDao.getAllUser();
    }

    @Override
    public void deleteUser(int userId) {
        userDao.softDeleteUser(userId);
    }
}
