package com.interagile.cliente.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interagile.cliente.escola.model.ProfessorDB;

@Repository
public interface IProfessorRepository extends JpaRepository<ProfessorDB, Long> {

	@Query("select p from ProfessorDB p where p.matricula =: matricula")
	public ProfessorDB findProfessorByMatricula(@Param("matricula") String matricula);

	@Query("select MAX(p.idProfessor) from ProfessorDB p")
	public Long findLastProfessor();

	@Query("select p from ProfessorDB p where p.cpf = :cpf or p.email = :email or p.telefone = :telefone")
	public ProfessorDB findProfessorCadastrado(@Param("cpf") String cpf, @Param("email") String email,
			@Param("telefone") Long telefone);

}
