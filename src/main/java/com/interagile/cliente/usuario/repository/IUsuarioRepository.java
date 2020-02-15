package com.interagile.cliente.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.interagile.cliente.usuario.model.dbo.UsuarioDBO;

public interface IUsuarioRepository extends JpaRepository<UsuarioDBO, Long>{

}
