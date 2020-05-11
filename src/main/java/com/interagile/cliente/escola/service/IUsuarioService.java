package com.interagile.cliente.escola.service;

import java.util.List;

import com.interagile.cliente.escola.dto.UsuarioDTO;
import com.interagile.cliente.escola.model.AlunoDB;

public interface IUsuarioService {

	List<AlunoDB> consultaListaDeAlunos();

	Boolean cadastroAluno(UsuarioDTO usuario);

	Boolean cadastroProfessor(UsuarioDTO usuario);

}
