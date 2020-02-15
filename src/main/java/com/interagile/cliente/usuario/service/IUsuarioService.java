package com.interagile.cliente.usuario.service;

import java.util.List;

import com.interagile.cliente.usuario.model.dto.UsuarioDTO;

import reactor.core.publisher.Mono;

public interface IUsuarioService {

	Mono<List<String>> consultaListaDeAlunos();

	Mono<Boolean> cadastroAluno(UsuarioDTO usuario);

	Mono<Boolean> cadastroProfessor(UsuarioDTO usuario);

}
