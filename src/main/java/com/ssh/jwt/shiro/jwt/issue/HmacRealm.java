package com.ssh.jwt.shiro.jwt.issue;

import com.ssh.jwt.model.User;
import com.ssh.jwt.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class HmacRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    public HmacRealm(UserService userService) {
        super();
        setAuthenticationTokenClass(UsernamePasswordToken.class);
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // TODO 认证成功后，授权如页面资源信息等，(核实是否存入session当中)
        String userName = (String) principalCollection.fromRealm(getName()).iterator().next();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    /**
     * 认证登录请求
     * 通过HmacFilter的onAccessDenied存入的UsernamePasswordToken对象，
     * 获取UsernamePasswordToken对象的name、password信息进行数据库验证
     * 成功返回AuthenticationInfo对象
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String userPassword = String.valueOf(usernamePasswordToken.getPassword());
        String[] propName = {"userName", "userPassword"};
        Object[] propValue = {username, userPassword};
        User user = userService.getByProerties(propName, propValue);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserName(), user.getUserPassword(), getName());
        return authenticationInfo;
    }
}
