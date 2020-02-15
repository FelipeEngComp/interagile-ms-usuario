package com.interagile.cliente.usuario.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interagile.cliente.usuario.response.Response;
import com.interagile.cliente.usuario.response.Response.ResponseBuilder;
import com.interagile.cliente.usuario.service.IUsuarioService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@GetMapping("/consultar/alunos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso na requisição"),
			@ApiResponse(code = 400, message = "Erro na requisição") })
	public Mono<ResponseEntity<Response<List<String>>>> consultaAlunos() {
		LOG.debug("Iniciando a controller do usuario");
		ResponseBuilder<List<String>> responseBuilder = Response.builder();
		
		usuarioService.consultaListaDeAlunos().map(listaUsuario->{
			responseBuilder.data(listaUsuario);
			responseBuilder.status(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build());
			
		}).onErrorResume(e->{
			responseBuilder.erros(Arrays.asList(e.getMessage()));
			responseBuilder.status(HttpStatus.OK.value());
			return Mono.just(ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build()));
		}).subscribe();
		
		return Mono.just(ResponseEntity.status(HttpStatus.OK).body(responseBuilder.build()));
	}
}
