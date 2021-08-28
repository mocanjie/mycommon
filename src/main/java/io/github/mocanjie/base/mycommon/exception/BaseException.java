package io.github.mocanjie.base.mycommon.exception;

public class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	protected int code;
	
	protected String message;

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

	public BaseException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public BaseException() {
		super();
	}

	
	

}
