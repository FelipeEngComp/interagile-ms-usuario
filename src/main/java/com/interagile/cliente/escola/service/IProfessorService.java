package com.interagile.cliente.escola.service;

import java.util.List;

import com.interagile.cliente.escola.model.ProfessorCadastro;
import com.interagile.cliente.escola.model.ProfessorDB;

public interface IProfessorService {

	List<ProfessorDB> listar();
	
	ProfessorDB consultar(final String matricula);

	Boolean cadastrar(ProfessorCadastro professor);

}
