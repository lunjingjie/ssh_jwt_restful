package com.ssh.jwt.exception;

import core.util.Code;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -6112780192479692859L;

    /**
     * 状态码
     */
    private Code code;

    public CustomException(Code code) {
        super();
        this.code = code;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}

