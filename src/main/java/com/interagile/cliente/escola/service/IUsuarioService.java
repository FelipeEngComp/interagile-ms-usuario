package com.interagile.cliente.escola.service;

import java.util.List;

import com.interagile.cliente.escola.dto.UsuarioDTO;

import reactor.core.publisher.Mono;

public interface IUsuarioService {

	Mono<List<String>> consultaListaDeAlunos();

	Mono<Boolean> cadastroAluno(UsuarioDTO usuario);

	Mono<Boolean> cadastroProfessor(UsuarioDTO usuario);

}
