package com.interagile.cliente.escola.Util;

import com.interagile.cliente.escola.constante.TipoUsuario;

public class MatriculaUtil {

	public static String criaMatricula(TipoUsuario tipoUsuario, String ultimaMatricula) {

		if (tipoUsuario == TipoUsuario.ALUNO) {
			if (ultimaMatricula != null) {
				String[] stringList = ultimaMatricula.split("A");
				return "A" + aplicaRegraMatricula(stringList[1]);
			}
			return "A000001";
		} else {
			if (ultimaMatricula != null) {
				String[] stringList = ultimaMatricula.split("P");
				return "P" + aplicaRegraMatricula(stringList[1]);
			}
			return "P000001";
		}

	}

	private static String aplicaRegraMatricula(String ultimaMatricula) {

		Long novaMatricula = Long.parseLong(ultimaMatricula) + 1;

		return String.format("%06d", novaMatricula) ;

	}

}
