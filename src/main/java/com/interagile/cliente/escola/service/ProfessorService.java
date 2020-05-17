package com.interagile.cliente.escola.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.interagile.cliente.escola.Util.MatriculaUtil;
import com.interagile.cliente.escola.constante.TipoUsuario;
import com.interagile.cliente.escola.exception.UsuarioException;
import com.interagile.cliente.escola.model.MateriaDB;
import com.interagile.cliente.escola.model.ProfessorCadastro;
import com.interagile.cliente.escola.model.ProfessorDB;
import com.interagile.cliente.escola.repository.IMateriaRepository;
import com.interagile.cliente.escola.repository.IProfessorRepository;

@Service
public class ProfessorService implements IProfessorService {

	private static final Logger LOG = LoggerFactory.getLogger(ProfessorService.class);

	private IProfessorRepository professorRepository;
	private IMateriaRepository materiaRepository;

	@Autowired
	public ProfessorService(IProfessorRepository professorRepository, IMateriaRepository materiaRepository) {
		this.professorRepository = professorRepository;
		this.materiaRepository = materiaRepository;
	}

	@Override
	public List<ProfessorDB> listar() {
		try {
			List<ProfessorDB> professorDBO = this.professorRepository.findAll();

			return professorDBO;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Boolean cadastrar(ProfessorCadastro professor) {
		try {
			if (this.professorRepository.findProfessorCadastrado(professor.getCpf(), professor.getEmail(),
					professor.getTelefone()) != null) {
				throw new UsuarioException("Professor já está cadastrado.", HttpStatus.BAD_REQUEST.value());
			}

			ProfessorDB professorDb = new ProfessorDB();

			Long ultimoProfessorId = this.professorRepository.findLastProfessor();
			ProfessorDB ultimoProfessor = null;

			if (ultimoProfessorId != null)
				ultimoProfessor = this.professorRepository.findById(ultimoProfessorId).get();

			String matriculaUltimoprofessor = null;

			if (ultimoProfessor != null) {
				matriculaUltimoprofessor = ultimoProfessor.getMatricula();
			}

			List<MateriaDB> listMateriaDB = this.materiaRepository
					.findMateriaByListCodigo(professor.getListIdMateria());

			professorDb.setNome(professor.getNome());
			professorDb.setEmail(professor.getEmail());
			professorDb.setCpf(professor.getCpf());
			professorDb.setMateriasCursada(listMateriaDB);
			professorDb.setTelefone(professor.getTelefone());
			professorDb.setMatricula(MatriculaUtil.criaMatricula(TipoUsuario.PROFESSOR, matriculaUltimoprofessor));

			this.professorRepository.save(professorDb);

			return true;
		} catch (UsuarioException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public ProfessorDB consultar(String matricula) {
		try {

			ProfessorDB professor = this.professorRepository.findProfessorByMatricula(matricula);

			if (professor != null)
				return professor;

			throw new UsuarioException("Usuario não encontrado", HttpStatus.NOT_FOUND.value());

		} catch (UsuarioException u) {
			LOG.error("Erro ao consultar aluno {}", u);
			throw u;
		} catch (Exception e) {
			throw e;
		}
	}

}
