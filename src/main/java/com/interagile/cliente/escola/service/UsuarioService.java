package com.interagile.cliente.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.dto.UsuarioDTO;
import com.interagile.cliente.escola.model.AlunoDB;
import com.interagile.cliente.escola.repository.IAlunoRepository;

@Service
public class UsuarioService implements IUsuarioService {

	IAlunoRepository alunoRepository;

	@Autowired
	public UsuarioService(IAlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@Override
	public List<AlunoDB> consultaListaDeAlunos() {
		try {
			List<AlunoDB> usuarioDBO = this.alunoRepository.findAll();

			return usuarioDBO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean cadastroAluno(UsuarioDTO usuario) {
		return true;
	}

	@Override
	public Boolean cadastroProfessor(UsuarioDTO usuario) {
		return true;
	}

}
