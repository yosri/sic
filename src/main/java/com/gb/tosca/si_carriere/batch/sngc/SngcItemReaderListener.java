package com.gb.tosca.si_carriere.batch.sngc;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;

import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.common.Constantes;

/**
 * SngcItemReaderListener : pour la gestion des lignes qui ne mappe pas le model de donnees
 * 
 * @author yarrami
 *
 */
public class SngcItemReaderListener implements ItemReadListener<Sngc> {

	@Override
	public void beforeRead() {
		// Vide
	}

	@Override
	public void afterRead(Sngc sngc) {
		// Vide
	}

	/**
	 * US01-04-1 Contrôler l’intégrité d’une ligne du fichier SNGC retour de type autre que DDF : longueur de l’enregistrement est égale à 140
	 */
	@Override
	public void onReadError(Exception ex) {
		if (ex instanceof FlatFileParseException) {
			// Est appelé si : la ligne ne fait pas 140 caracteres (moin ou plus)
			System.err.println("FlatFileParseException : " + ((FlatFileParseException) ex).getInput());
			Constantes.KOlog.info(Constantes.ERR_LONG_ENR + " : " + ((FlatFileParseException) ex).getInput());
		}
	}
}