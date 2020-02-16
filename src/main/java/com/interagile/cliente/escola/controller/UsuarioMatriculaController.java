package com.interagile.cliente.escola.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interagile.cliente.escola.response.Response;
import com.interagile.cliente.escola.response.Response.ResponseBuilder;
import com.interagile.cliente.escola.service.IUsuarioMatriculaService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioMatriculaController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UsuarioMatriculaController.class);
	
	@Autowired
	private IUsuarioMatriculaService usuarioMatriculaService;
	
	@PostMapping("/matricula/aluno/{cpf}")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 400, message = "Erro na requisição") })
	public ResponseEntity<Response<Boolean>> matriculaAluno(@PathVariable String cpf) {
		LOG.debug("Iniciando a controller do usuario");
		ResponseBuilder<Boolean> responseBuilder = Response.builder();
		
		try {
		Boolean usuarioMatriculado = usuarioMatriculaService.matricularAluno(cpf);
		
			responseBuilder.data(usuarioMatriculado);
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
			
		}catch (Exception e) {
			// TODO: handle exception
			responseBuilder.data(false);
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
		}
		
		
	}
	
}
