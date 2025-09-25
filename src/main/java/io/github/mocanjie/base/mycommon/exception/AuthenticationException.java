package io.github.mocanjie.base.mycommon.exception;

public class AuthenticationException extends BaseException {

	private static final long serialVersionUID = 1L;

	protected static int code = 401;

	public AuthenticationException(String message) {
		super(code, message);
	}

}