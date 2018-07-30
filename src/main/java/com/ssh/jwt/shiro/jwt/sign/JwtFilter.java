package com.ssh.jwt.shiro.jwt.sign;

import com.ssh.jwt.vo.Status;
import core.util.Code;
import core.util.TokenUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对除登录外的请求进行过滤
 * 过滤规则:
 * 1.验证request中的authorization(token)是否存在与服务器缓存中
 * 2.存在：放行,继续执行过滤器链
 * 3.不存在：返回false，验证失败，返回401状态码以及状态信息
 * @author Administrator
 */
public class JwtFilter extends AccessControlFilter {

    /**
     * shiro Subject对象验证通过，放行; 失败则返回false
     * @param servletRequest ServletRequest对象
     * @param servletResponse ServletResponse对象
     * @param o
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return getSubject(servletRequest, servletResponse) != null && getSubject(servletRequest, servletResponse).isAuthenticated();
    }

    /**
     * 认证请求header中authorization(token)是否存在
     * 存在：放行;
     * 不存在：返回错误状态吗
     * @param servletRequest ServletRequest对象
     * @param servletResponse ServletResponse对象
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String token = request.getHeader("authorization");
        boolean isExist = TokenUtil.verifyIsContains(token);
        if(!isExist){
            setResponseParam((HttpServletResponse) servletResponse, Code.UNAUTHORIZED);
        }
        return isExist;
    }

    private void setResponseParam(HttpServletResponse response, Code code) throws IOException {
        Status status = new Status(code.getStatusCode(), code.getStatusMsg());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(status);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonStr);
    }
}
