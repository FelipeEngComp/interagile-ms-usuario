package com.interagile.cliente.escola.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.dao.RegistroDAO;
import com.interagile.cliente.escola.repository.IRegistroRepository;

import reactor.core.publisher.Mono;

@Service
public class UsuarioMatriculaService implements IUsuarioMatriculaService {

	private IRegistroRepository registroRepository;
	private static final int ALUNO = 1;

	@Autowired
	public UsuarioMatriculaService(IRegistroRepository registroRepository) {
		this.registroRepository = registroRepository;
	}

	@Override
	public Mono<Boolean> matricularAluno(String cpf) {

		try {
			// TODO:implementar algoritmo de validacao do cpf

			// TODO:implementar algoritmo de geração de matrícula
			Random random = new Random();
			String matricula = String.format("%06d", random.nextInt(9999999));

			RegistroDAO registro = new RegistroDAO();

			registro.setCpf(cpf);
			registro.setMatricula("MAT"+matricula);
			registro.setTipoUsuario(ALUNO);

			registroRepository.save(registro);

			return Mono.just(true);
		} catch (Exception e) {
			return Mono.error(e);
		}
	}

}
