package com.dll.common.exception;

import com.dll.common.model.ReturnMsg;

/**
 * 业务异常
 */
public class BusinessException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -4368873598973127606L;
    private final String code;
    private final Object data;


    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }

    public BusinessException(String code, String message, Object data, Exception ex) {
        super(message, ex);
        this.code = code;
        this.data = data;
    }

    public BusinessException(String code, String message, Exception ex) {

        super(message, ex);
        this.code = code;
        this.data = null;
    }

    public Throwable getInnerException() {
        return super.getCause();
    }

    public Object getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public static final BusinessException CommonException = new BusinessException(ReturnMsg.ERROR.getCode(), ReturnMsg.ERROR.getMsg());
    public static final BusinessException CommonAuthException = new BusinessException(ReturnMsg.AUTH.getCode(), ReturnMsg.AUTH.getMsg());
}
