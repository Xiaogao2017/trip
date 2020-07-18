package com.trip.domain;

import java.io.Serializable;

/**
 * @author Mr. Li xiaogao 2020-06-04 18:18
 */
public class ResultInfo implements Serializable {
    //后端返回结果正常为true，发生异常返回false
    private boolean flag;
    //后端返回结果数据对象
    private Object data;
    //发生异常的错误消息
    private String errorMsg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
