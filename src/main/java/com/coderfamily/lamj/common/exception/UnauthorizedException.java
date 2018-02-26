package com.coderfamily.lamj.common.exception;

/**
 * @author ZhangDL
 * @date 2018/1/26 13:36
 */
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
