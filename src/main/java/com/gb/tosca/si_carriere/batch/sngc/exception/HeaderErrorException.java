package com.gb.tosca.si_carriere.batch.sngc.exception;

import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Exception lecture entete fichier SNGC
 * 
 * @author yarrami
 *
 */
public class HeaderErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public HeaderErrorException(String ex) {
		Constantes.KOlog.info(ex);
	}

}
