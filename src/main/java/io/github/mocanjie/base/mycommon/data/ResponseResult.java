package io.github.mocanjie.base.mycommon.data;

/**
 * 输出类
 * @author mo
 *
 */
public class ResponseResult {
	
	protected int code = 200;
	protected String msg = "";
	
	public ResponseResult(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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
	public ResponseResult() {
		super();
	}

}
