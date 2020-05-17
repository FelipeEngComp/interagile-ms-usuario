package com.interagile.cliente.escola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.exception.UsuarioException;
import com.interagile.cliente.escola.model.AlunoCadastro;
import com.interagile.cliente.escola.model.AlunoDB;
import com.interagile.cliente.escola.repository.IAlunoRepository;

@Service
public class AlunoService implements IAlunoService {
	
	private static final Logger LOG = LoggerFactory.getLogger(AlunoService.class);
	
	private IAlunoRepository alunoRepository;

	@Autowired
	public AlunoService(IAlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}

	@Override
	public List<AlunoDB> listar() {
		try {
			List<AlunoDB> usuarioDBO = this.alunoRepository.findAll();

			return usuarioDBO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean cadastrar(AlunoCadastro aluno) {
		try {
			
			
			
			 	
			 AlunoDB alunoDB = new AlunoDB();
			
			 this.alunoRepository.save(alunoDB);			
			 return true;
		}catch (Exception e) {
			throw e;
		}
	}

	@Override
	public AlunoDB consultar(String matricula) {
		try {
			
			AlunoDB aluno = this.alunoRepository.findAlunoByMatricula(matricula);
			
			if(aluno!=null)
				return aluno;
					
			throw new UsuarioException("Usuario não encontrado", HttpStatus.NOT_FOUND.value());
			
		}catch (UsuarioException u) {
			LOG.error("Erro ao consultar aluno {}",u);
			throw  u;
		}catch (Exception e) {
			throw e;
		}
	}

}
