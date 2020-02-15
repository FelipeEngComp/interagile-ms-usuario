package com.interagile.cliente.usuario.model.dbo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NonNull;

@Entity
@Data
@Table(name ="tb_usuario_faculdade")
public class UsuarioDBO implements Serializable{
	
	private static final long serialVersionUID = 5402066257568990643L;
	
	public UsuarioDBO() {
	}
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_usr")
	private long id;
	
	@Column(name = "cpf")
	@NonNull
	private String cpf;
	
	@Column(name = "email")
	@NonNull
	private String email;
	
	@Column(name = "telefone")
	private Long telefone;
	
	@Column(name = "nome")
	@NonNull
	private String nome;
	
	@Column(name = "matricula")
	@NonNull
	private String matricula;
	
	@Column(name = "tipo_usuario")
	@NonNull
	private String tipoUsuario;
	
	@Column(name = "semestre")
	private int semestre;
	
	@JoinColumn(name = "id_curso")
	@ManyToOne
	private CursoDBO curso;
	
}
