package io.github.mocanjie.base.mycommon.exception;

public class ValidateException extends BaseException {
	
private static final long serialVersionUID = 1L;
	
	protected static int code = 400;
	
	public ValidateException(String message) {
		super(code, message);
	}
}
