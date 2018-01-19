package com.apps.mapper;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DataNotSufficientException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	public DataNotSufficientException() {
		super();
	}
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
	public DataNotSufficientException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
}
