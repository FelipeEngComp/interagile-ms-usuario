package com.interagile.cliente.escola.model;

import lombok.Value;

@Value
public class AlunoCadastro {
	
	private String cpf;
	
	private String email;
	
	private Long telefone;
	
	private String nome;
	
	private Long idCurso;
	
}
