package com.interagile.cliente.escola.exception;

import lombok.Getter;

@Getter
public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = -9163368491361476150L;
	private final int httpStatusCode;

	public UsuarioException(final String mensagem, final int httpStatusCode) {
		super(mensagem);
		this.httpStatusCode = httpStatusCode;
	}
}
