package com.interagile.cliente.escola.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.dao.UsuarioDAO;
import com.interagile.cliente.escola.dto.UsuarioDTO;
import com.interagile.cliente.escola.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	IUsuarioRepository usuarioRepository;

	@Autowired
	public UsuarioService(IUsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public List<String> consultaListaDeAlunos() {
		try {
			List<UsuarioDAO> usuarioDBO = this.usuarioRepository.findAll();

			List<String> listaDeAlunos = new ArrayList<String>();

			for (UsuarioDAO usr : usuarioDBO) {
				if (usr.getTipoUsuario() == 0) {
					listaDeAlunos.add(usr.getNome());
				}
			}

			return listaDeAlunos;
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
