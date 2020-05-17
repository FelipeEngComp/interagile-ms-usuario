package com.interagile.cliente.escola.controller;

import java.util.Arrays;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interagile.cliente.escola.exception.UsuarioException;
import com.interagile.cliente.escola.model.ProfessorCadastro;
import com.interagile.cliente.escola.model.ProfessorDB;
import com.interagile.cliente.escola.response.Response;
import com.interagile.cliente.escola.response.Response.ResponseBuilder;
import com.interagile.cliente.escola.service.IProfessorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/professores")
@Api(value = "Professor")
@CrossOrigin(origins = "*")
public class ProfessorController {

	private static final Logger LOG = LoggerFactory.getLogger(ProfessorController.class);

	@Autowired
	private IProfessorService professorService;

	@GetMapping("/")
	@ApiOperation(value = "Listar alunos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 404, message = "Erro na requisição") })
	public ResponseEntity<Response<List<ProfessorDB>>> listar() {
		LOG.debug("Iniciando a controller de listar Alunos");
		ResponseBuilder<List<ProfessorDB>> responseBuilder = Response.builder();
		try {
			List<ProfessorDB> listaProfessores = this.professorService.listar();
			responseBuilder.data(listaProfessores);
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		} catch (Exception e) {
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		}

	}
	
	@GetMapping("/{matricula}")
	@ApiOperation(value = "Consultar Aluno")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 404, message = "Erro na requisição") })
	public ResponseEntity<Response<ProfessorDB>> consultar(@PathVariable String matricula) {
		LOG.debug("Iniciando a controller de consultar aluno");
		ResponseBuilder<ProfessorDB> responseBuilder = Response.builder();
		try {
			ProfessorDB professor = this.professorService.consultar(matricula);
			responseBuilder.data(professor);
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		} catch (Exception e) {
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		}

	}
	
	@PostMapping("/")
	@ApiOperation(value = "Cadastrar professor")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 404, message = "Erro na requisição") })
	public ResponseEntity<Response<Boolean>> cadastrar(@RequestBody ProfessorCadastro professor) {
		LOG.debug("Iniciando a controller de consultar professor");
		ResponseBuilder<Boolean> responseBuilder = Response.builder(); 
		try {
			responseBuilder.data(this.professorService.cadastrar(professor));
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		} catch (UsuarioException erroUsuario) {
			responseBuilder.erros(Arrays.asList(erroUsuario.getMessage()));
			responseBuilder.status(erroUsuario.getHttpStatusCode());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		} catch (Exception e) {
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		}

	}

}
