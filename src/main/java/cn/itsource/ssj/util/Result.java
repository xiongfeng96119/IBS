package cn.itsource.ssj.util;

/**
 * 专门用来封装增删改的返回消息
 */
public class Result {

    private Integer status;

    private String msg;

    public Result(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
