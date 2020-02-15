package com.interagile.cliente.usuario.model.dto;

import java.io.Serializable;

import com.interagile.cliente.usuario.model.dbo.CursoDBO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable{
	
	private static final long serialVersionUID = 1304506754609858280L;
	
	private long id;
	
	private String cpf;
	
	private String email;
	
	private Long telefone;
	
	private String nome;
	
	private String matricula;
	
	private String tipoUsuario;
	
	private int semestre;
	
	private CursoDBO curso;
	
}
