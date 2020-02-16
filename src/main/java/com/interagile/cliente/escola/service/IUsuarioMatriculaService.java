package com.interagile.cliente.escola.service;

import reactor.core.publisher.Mono;

public interface IUsuarioMatriculaService {
	
	Mono<Boolean> matricularAluno(final String cpf);
	
}
