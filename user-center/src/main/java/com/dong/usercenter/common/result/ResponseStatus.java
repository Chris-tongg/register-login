package com.dong.usercenter.common.result;

/**
 * 自己根据公司的业务场景定制,
 * 参照 https://docs.open.alipay.com/common/105806/
 *
 * @author zhangwei
 */

public enum ResponseStatus {
    /* 成功状态码 10000 */
    SUCCESS(200, "成功"),
    /* 账号错误*/

    /*用户未登录*/
    ACCOUNT_NOT_LOGIN(1001, "user no login"),
    /*账号不存在或密码错误*/
    ACCOUNT_LOGIN_ERROR(10002, "user login error"),
    /*账号已存在*/
    ACCOUNT_IS_EXISTENT(10003, "account is existent"),
    /*账号不存在*/
    ACCOUNT_NOT_EXIST(10004, "account not exist"),
    /*账号已禁止  请与管理员联系*/
    ACCOUNT_LOCKED(10005, "user account locked"),
    /* 提示 : 账号注册失败 */
    ACCOUNT_REGISTER_ERROR(10006, "user register fail"),
    /*账号未激活*/
    ACCOUNT_NOT_ACTIVE(10007,"user account not active"),

    /* 参数错误*/
    /*参数不为空*/
    PARAMS_NOT_IS_BLANK(20001, "params not is blank"),
    /*参数无效*/
    PARAMS_IS_INVALID(20002, "params is invalid"),
    /*参数类型错误*/
    PARAM_TYPE_ERROR(20003, "param type error"),
    /*参数缺失*/
    PARAM_IS_DEFICIENCY(20004, "param is deficiency"),
    /*暂无权限*/
    PERMISSION_NO_ACCESS(20006, "no permissions access"),
    AUTH_ERROR(20007, "auth error"),
    /* 业务错误 */
    /* 业务繁忙 请稍后在试 */
    BUSINESS_UNKNOW_ERROR(30001, " busy with business"),

    /*内部调用服务不可用*/
    GW_UNKNOW_ERROR(30002, "unknow error"),

    /* ======系统错误：40001-49999===== */
    /* 提示语 "系统繁忙，请稍后重试"*/
    SYSTEM_INNER_ERROR(40001, "system error"),
    /*未知错误 请稍后在试*/
    SYSTEM_UNKNOW_ERROR(40002, "system unknow error"),
    /*内部系统接口调用异常*/
    INNER_INVOKE_ERROR(50001, "inner invoke error"),
    /*外部系统接口调用异常*/
    OUTER_INVOKE_ERROR(50002, "outer invoke error"),
    /*该接口禁止访问*/
    NO_ACCESS_FORBIDDEN(50003, "no access forbidden"),
    /*接口地址无效*/
    NO_FOUND_ERROR(50004, "no found error"),
    /* 数据错误 */
    DATA_IS_WRONG(60001, "DATA_IS_WRONG");
//        自定义


    private Integer status;

    private String msg;

    ResponseStatus(Integer status, String message) {
        this.status = status;
        this.msg = message;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
