package com.interagile.cliente.usuario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interagile.cliente.usuario.model.dbo.UsuarioDBO;
import com.interagile.cliente.usuario.model.dto.UsuarioDTO;
import com.interagile.cliente.usuario.repository.IUsuarioRepository;

import reactor.core.publisher.Mono;

@Service
public class UsuarioService implements IUsuarioService {

	IUsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Mono<List<String>> consultaListaDeAlunos() {
		try {
			List<UsuarioDBO> usuarioDBO = this.usuarioRepository.findAll();

			List<String> listaDeAlunos = new ArrayList<String>();

			for (UsuarioDBO usr : usuarioDBO) {
				if (usr.getTipoUsuario().equals("aluno")) {
					listaDeAlunos.add(usr.getNome());
				}
			}

			return Mono.just(listaDeAlunos);
		} catch (Exception e) {
			return Mono.error(e);
		}
	}
	
	@Override
	public Mono<Boolean> cadastroAluno(UsuarioDTO usuario){
		return Mono.just(true);
	}
	
	@Override
	public Mono<Boolean> cadastroProfessor(UsuarioDTO usuario){
		return Mono.just(true);
	}

}
