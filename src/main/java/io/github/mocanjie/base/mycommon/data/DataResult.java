package io.github.mocanjie.base.mycommon.data;

public class DataResult extends ResponseResult {
	
	protected Object data;
	
	public DataResult(Object data) {
		this.data = data;
	}
	public DataResult(int code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public DataResult(String msg, Object data) {
		this.msg = msg;
		this.data = data;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
