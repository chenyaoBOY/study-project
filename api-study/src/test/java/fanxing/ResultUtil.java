package fanxing;

import java.io.Serializable;

/**
 * @author chenyao
 * @Description: 结果
 * @date 2018/6/29/17:34
 */
public class ResultUtil<T> implements Serializable,Result{

    private String message;
    private boolean success;
    private int status;
    private T data;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
