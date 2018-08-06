package com.ssh.jwt.controller;

import com.ssh.jwt.exception.CustomException;
import com.ssh.jwt.vo.Status;
import core.util.Code;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalExceptionResolver extends DefaultHandlerExceptionResolver {

    public static Logger logger = Logger.getLogger("AllError");

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView defaultErrorHandler(HttpServletRequest request, HttpServletResponse response,
                                            Object handler, Exception ex) throws IOException {
        String url = request.getServletPath();
        logger.error("请求接口路径：" + url);
        logger.error(ex);
        if (url.startsWith("/web")) {
            if (ex instanceof CustomException) {
                setResponseParam(response, ((CustomException) ex).getCode());
                return null;
            } else if (ex instanceof NullPointerException) {
                setResponseParam(response, Code.NULL_ERROR);
                return null;
            } else if (ex instanceof AuthenticationException) {
                setResponseParam(response, Code.VALIDATE_ERROR);
                return null;
            }
        }
        setResponseParam(response, Code.SYSTEM_ERROR);
        return null;
    }

    private void setResponseParam(HttpServletResponse response, Code code) throws IOException {
        Status status = new Status(code.getStatusCode(), code.getStatusMsg());
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(jsonStr);
    }
}

