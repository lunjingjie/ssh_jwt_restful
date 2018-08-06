package com.ssh.jwt.service;

import com.ssh.jwt.model.User;
import com.ssh.jwt.vo.UserVo;
import core.service.Service;

import java.util.List;

public interface UserService extends Service<User> {

    List<UserVo> getAllUser();

    void deleteUser(int userId);
}
