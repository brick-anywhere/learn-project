package com.dll.common.model;


import com.alibaba.fastjson.annotation.JSONType;
import com.dll.common.lock.ReturnMsg;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liuji
 * @Description: 统一返回类
 * @date 2019/5/23 22:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JSONType(orders = {"code", "message", "data"})
public class ResultData<T> implements Serializable {


    private String code;
    private String msg;
    private T data;

    public static <T> ResultData<T> ok(String msg) {
        return succeedWith(null, ReturnMsg.SUCCESS.getCode(), msg);
    }

    public static <T> ResultData<T> ok(T model, String msg) {
        return succeedWith(model, ReturnMsg.SUCCESS.getCode(), msg);
    }

    public static <T> ResultData<T> ok(T model) {
        return succeedWith(model, ReturnMsg.SUCCESS.getCode(), "");
    }

    public static <T> ResultData<T> succeedWith(T datas, String code, String msg) {
        return new ResultData<>(code, msg, datas);
    }

    public static <T> ResultData<T> failed(String code, String msg) {
        return failedWith(null, code, msg);
    }

    public static <T> ResultData<T> failed(String msg) {
        return failedWith(null, ReturnMsg.ERROR.getCode(), msg);
    }

    public static <T> ResultData<T> failed(T model, String msg) {
        return failedWith(model, ReturnMsg.ERROR.getCode(), msg);
    }

    public static <T> ResultData<T> failedWith(T data, String code, String msg) {
        return new ResultData<>(code, msg, data);
    }

    //----------------------------------封装2 --> 懒人使用 如下: -----------------------------------------------------------------------

    //---------------------------成功的方法如下:------------------------------------

    public static <T> ResultData<T> success() {
        return succeedWith(null, ReturnMsg.SUCCESS.getCode(), ReturnMsg.SUCCESS.getMsg());
    }

    public static <T> ResultData<T> success(ReturnMsg returnMsg) {
        return succeedWith(null, returnMsg.getCode(), returnMsg.getMsg());
    }

    public static <T> ResultData<T> success(T model) {
        return ResultData.ok(model);
    }

    //---------------------------失败的方法如下:------------------------------------

    public static <T> ResultData<T> error() {
        return failedWith(null, ReturnMsg.BUSINESS_ERROR.getCode(), ReturnMsg.BUSINESS_ERROR.getMsg());
    }

    public static <T> ResultData<T> error(ReturnMsg returnMsg) {
        return failedWith(null, returnMsg.getCode(), returnMsg.getMsg());
    }

    public static <T> ResultData<T> error(String msg) {
        return failedWith(null, ReturnMsg.BUSINESS_ERROR.getCode(), msg);
    }

    //----------------------------------封装2 --> 懒人使用 如上: 推荐使用,简单粗暴-----------------------------------------------------------------------
}
