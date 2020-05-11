package com.interagile.cliente.escola.service;

import java.util.List;

import com.interagile.cliente.escola.dto.UsuarioDTO;

public interface IUsuarioService {

	List<String> consultaListaDeAlunos();

	Boolean cadastroAluno(UsuarioDTO usuario);

	Boolean cadastroProfessor(UsuarioDTO usuario);

}
