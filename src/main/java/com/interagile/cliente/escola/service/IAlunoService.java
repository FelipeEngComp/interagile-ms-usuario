package com.interagile.cliente.escola.service;

import java.util.List;

import com.interagile.cliente.escola.model.AlunoCadastro;
import com.interagile.cliente.escola.model.AlunoDB;

public interface IAlunoService {

	List<AlunoDB> listar();
	
	AlunoDB consultar(final String matricula);

	Boolean cadastrar(final AlunoCadastro usuario);

}
