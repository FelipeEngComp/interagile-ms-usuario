package com.interagile.cliente.escola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.interagile.cliente.escola.model.AlunoDB;

@Repository
public interface IAlunoRepository extends JpaRepository<AlunoDB, Long>{
	
	@Query("select a from AlunoDB a where a.matricula =: matricula")
	public AlunoDB findAlunoByMatricula(@Param("matricula")String matricula);

}
