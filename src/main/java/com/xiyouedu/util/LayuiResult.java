package com.xiyouedu.util;

import java.util.List;

public class LayuiResult<T> {

    private int code;
    private String msg;
    private int count = 10;
    private List<T> data;

    @Override
    public String toString() {
        return "LayuiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public List<T> getData() {
        return data;
    }
}
