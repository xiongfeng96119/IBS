package cn.itsource.utils;

public class ResultJson {
	
	private Integer status;
	private String msg;
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
	public ResultJson(Integer status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}
	public ResultJson() {
		super();
	}

}
