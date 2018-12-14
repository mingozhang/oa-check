package com.mingo.oacheck.domain;

import java.io.Serializable;

/**
 * @author mingo
 * @create 2018-11-29 17:06
 * @desc
 **/
public class ResponseBody<T> implements Serializable {

    private static final long serialVersionUID = 1284048332352225994L;
    private String code;

    private String msg;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseBody(){

    }

    public ResponseBody(T data){
        this.code = "200";
        this.data = data;
    }

    public ResponseBody(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
