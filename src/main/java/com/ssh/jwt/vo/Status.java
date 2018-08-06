package com.ssh.jwt.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用于返回给客户端消息的封装，包括状态码、状态信息
 * @author Administrator
 */
public class Status {

    @ApiModelProperty("状态码")
    private String statusCode;
    @ApiModelProperty("状态信息")
    private String statusMsg;

    public Status(String statusCode, String statusMsg) {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
