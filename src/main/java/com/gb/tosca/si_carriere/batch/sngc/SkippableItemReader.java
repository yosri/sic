package com.gb.tosca.si_carriere.batch.sngc;

import org.springframework.batch.item.file.LineCallbackHandler;

import com.gb.tosca.si_carriere.batch.sngc.exception.HeaderErrorException;
import com.gb.tosca.si_carriere.batch.sngc.validateur.EnteteFichierSngcValidateur;

/**
 * SkippableItemReader : appeler pour verifer la 1er ligne du fichier SNGC
 * 
 * @author yarrami
 *
 */
public class SkippableItemReader implements LineCallbackHandler {

	@Override
	public void handleLine(String line) {
		if (!EnteteFichierSngcValidateur.estValide(line)) {
			throw new HeaderErrorException();
		}
	}
}
