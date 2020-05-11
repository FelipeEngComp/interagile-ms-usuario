package com.interagile.cliente.escola.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.dao.RegistroDAO;
import com.interagile.cliente.escola.exception.CadastradoException;
import com.interagile.cliente.escola.repository.IRegistroRepository;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

@Service
public class UsuarioMatriculaService implements IUsuarioMatriculaService {

	private IRegistroRepository registroRepository;
	private static final int ALUNO = 1;
	private static final int PROFESSOR = 2;

	@Autowired
	public UsuarioMatriculaService(IRegistroRepository registroRepository) {
		this.registroRepository = registroRepository;
	}

	@Override
	public Boolean matricularAluno(String cpf) throws Exception {

		try {
			
			CPFValidator cpfValidator = new CPFValidator();
			
			cpfValidator.assertValid(cpf);
			
			if (registroRepository.findRegistroByCpf(cpf)!=null) {
				throw new CadastradoException("Usuario já matriculado",HttpStatus.BAD_REQUEST.value());
			}
			
			Random random = new Random();
			String matricula = String.format("%06d", random.nextInt(9999999));

			RegistroDAO registro = new RegistroDAO();

			registro.setCpf(cpf);
			registro.setMatricula("MAT"+matricula);
			registro.setTipoUsuario(ALUNO);

			registroRepository.save(registro);

			return true;
		} catch (InvalidStateException e) {
			throw new InvalidStateException(e.getInvalidMessages());
		}catch (CadastradoException contaCadastradaException) {
			throw  contaCadastradaException;
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}

}
