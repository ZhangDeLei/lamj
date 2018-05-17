package com.coderfamily.lamj.common.data;

/**
 * @author ZhangDL
 * @date 2018/1/25 16:51
 */
public enum ResponseCode {
    success(100, "操作成功"),
    error(200, "服务器错误"),
    user_already_exists(201, "用户已存在"),
    unknown_account(201, "账户不存在"),
    password_incorrect(202, "密码错误"),
    unauthenticated(203, "未登录"),
    result_null(204, "查询结果为空"),
    expired(205, "有效期已过，请续费"),
    user_stop(206, "用户已停用");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
