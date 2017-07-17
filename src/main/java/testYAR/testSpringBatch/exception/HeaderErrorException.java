package testYAR.testSpringBatch.exception;

import common.Constantes;

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
