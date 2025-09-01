package com.dll.common.lock;

/**
 * @author
 * @Description:
 * @date
 * 给前台呈现的时候，常规的是如下：
 * SUCCESS("0","成功") //操作成功时的提示，成功时可以不提示msg出来
 * ERROR("1","错误")  //操作失败时的提示，失败时需要指定错误内容，不建议出现新的编码
 * BUSINESS_ERROR("2", "业务异常") //出现异常的提示，需要指定错误内容
 */
public enum ReturnMsg {
    //成功
    SUCCESS("0","success"),
    //失败
    ERROR("1","fail"),
    //业务异常
    BUSINESS_ERROR("2", "business exception"),
    //参数错误
    PARAM_ERROR("3","param error"),
    //帐号锁定
    DISABLED("5","disabled"),
    //无权限访问
    AUTH("6","no authority"),

    //踢出
    KICKOUT("7","kick out");


    private String code;
    private String msg;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    ReturnMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}