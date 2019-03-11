package org.akj.springboot.authorization.exception;

import java.io.Serializable;

public class GenericException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private String msg;

	private String code;

	public GenericException(String msg) {
	        super(msg);
	        this.msg = msg;
	    }

	public GenericException(String code, String message) {
	        super(message);
	        this.code = code;
	    }

	public GenericException(String msg, Throwable e) {
	        super(msg, e);
	        this.msg = msg;
	    }


	public GenericException(String msg, String code, Throwable e) {
	        super(msg, e);
	        this.msg = msg;
	        this.code = code;
	    }
}