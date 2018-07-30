package com.ssh.jwt.shiro.jwt.issue;

import com.ssh.jwt.vo.Status;
import core.util.Code;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 定义登录过滤器，由shiro进行验证
 * 验证成功: 放行
 * 验证失败: 返回错误状态码以及状态信息
 * @author Administrator
 */
public class HmacFilter extends AccessControlFilter{

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        try {
            Subject subject = getSubject(servletRequest, servletResponse);
            String userName = servletRequest.getParameter("userName");
            String userPassword = servletRequest.getParameter("userPassword");
            UsernamePasswordToken token = new UsernamePasswordToken(userName, userPassword);
            // 执行HmacRealm对象的doGetAuthenticationInfo方法进行验证
            subject.login(token);
            return true;
        } catch (AuthenticationException e) {
            setResponsePram((HttpServletResponse) servletResponse, Code.VALIDATE_ERROR);
            return false;
        }
    }

    /**
     * 验证异常时，设置response返回内容
     * @param response 返回客户端对象
     * @param code 自定义返回状态码
     */
    private void setResponsePram(HttpServletResponse response, Code code) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Status status = new Status(code.getStatusCode(), code.getStatusMsg());
        // 把对象转化为json字符串
        ObjectMapper mapper  = new ObjectMapper();
        String json = mapper.writeValueAsString(status);
        // 把json内容放进response， 设置编码格式、指定返回json格式
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        response.getWriter().print(json);
    }
}
