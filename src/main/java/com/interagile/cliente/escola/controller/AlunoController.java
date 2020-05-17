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
import com.interagile.cliente.escola.model.AlunoCadastro;
import com.interagile.cliente.escola.model.AlunoDB;
import com.interagile.cliente.escola.response.Response;
import com.interagile.cliente.escola.response.Response.ResponseBuilder;
import com.interagile.cliente.escola.service.IAlunoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/aluno")
@Api(value = "Aluno")
@CrossOrigin(origins = "*")
public class AlunoController {

	private static final Logger LOG = LoggerFactory.getLogger(AlunoController.class);

	@Autowired
	private IAlunoService alunoService;

	@GetMapping("/")
	@ApiOperation(value = "Listar alunos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 404, message = "Erro na requisição") })
	public ResponseEntity<Response<List<AlunoDB>>> listar() {
		LOG.debug("Iniciando a controller de listar Alunos");
		ResponseBuilder<List<AlunoDB>> responseBuilder = Response.builder();
		try {
			List<AlunoDB> listaAlunos = alunoService.listar();
			responseBuilder.data(listaAlunos);
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
	public ResponseEntity<Response<AlunoDB>> consultar(@PathVariable String matricula) {
		LOG.debug("Iniciando a controller de consultar aluno");
		ResponseBuilder<AlunoDB> responseBuilder = Response.builder();
		try {
			AlunoDB aluno = alunoService.consultar(matricula);
			responseBuilder.data(aluno);
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		} catch (Exception e) {
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		}

	}
	
	@PostMapping("/")
	@ApiOperation(value = "Cadastrar aluno")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 404, message = "Erro na requisição") })
	public ResponseEntity<Response<Boolean>> cadastrar(@RequestBody AlunoCadastro aluno) {
		LOG.debug("Iniciando a controller de consultar aluno");
		ResponseBuilder<Boolean> responseBuilder = Response.builder();
		try {
			responseBuilder.data(alunoService.cadastrar(aluno));
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
