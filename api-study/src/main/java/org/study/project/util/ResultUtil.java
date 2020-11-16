package org.study.project.util;

import java.io.Serializable;

/**
 * @author chenyao
 * @Description:
 * @date 2018/7/1/13:01
 */
public class ResultUtil<T> implements Serializable{

    private String message;
    private boolean success;
    private T data;



    public static<T> ResultUtil<T> success(T t){
        ResultUtil<T> t1 = new ResultUtil<>();
        t1.setSuccess(true);
        t1.setMessage("success");
        t1.setData(t);

        return t1;
    }
    public static<T> ResultUtil<T> success(T t,String message,boolean success){
        ResultUtil<T> t1 = new ResultUtil<>();
        t1.setSuccess(success);
        t1.setMessage(message);
        t1.setData(t);

        return t1;
    }
    public static ResultUtil fail(String message){
        ResultUtil t = new ResultUtil<>();
        t.setSuccess(false);
        t.setMessage(message);
        return t;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
