package com.common.system.util;

import java.io.Serializable;

/**
 * Created by Mr.Yangxiufeng on 2017/6/23.
 * Time:9:16
 * ProjectName:Common-admin
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -2342172746518830094L;
    /**
     * 状态，默认是失败
     */
    private boolean status = false;

    /**
     * 状态码，默认是失败
     */
    private int code = MsgCode.FAILED;

    /**
     * 信息
     */
    private String msg = "操作失败";

    /**
     * 返回结果实体
     */
    private T data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
