package com.interagile.cliente.escola.exception;

public class ContaCadastradaException extends RuntimeException{
	
	private static final long serialVersionUID = 7542763902797881436L;
	private int httpStatus;
	
	public ContaCadastradaException(final String msg, final int httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}
	
}
