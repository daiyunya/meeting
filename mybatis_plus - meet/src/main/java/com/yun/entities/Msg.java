package com.yun.entities;

import java.util.Map;

public class Msg {
    private int code;
    private String message;
    private Map<String,Object> extend;


    public com.yun.entities.Msg add(Map<String ,Object> extend){
        this.extend = extend;
        return this;
    }
    public static com.yun.entities.Msg success(){
        com.yun.entities.Msg msg = new com.yun.entities.Msg();
        msg.setCode(100);
        msg.setMessage("操作成功");
        return msg;
    }

    public com.yun.entities.Msg setError(String message){
        setMessage(message);
        return this;
    }

    public static com.yun.entities.Msg fault(){
        com.yun.entities.Msg msg = new com.yun.entities.Msg();
        msg.setCode(200);
        msg.setMessage("操作失败");
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }
}
