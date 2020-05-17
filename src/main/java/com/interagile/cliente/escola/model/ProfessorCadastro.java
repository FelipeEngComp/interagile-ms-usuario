package com.interagile.cliente.escola.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
public class ProfessorCadastro {
	
	@CPF
	private String cpf;
	
	@javax.validation.constraints.Email
	private String email;
	
	private Long telefone;
	
	@NotEmpty
	private String nome;
	
	private List<String> listIdMateria;

}
