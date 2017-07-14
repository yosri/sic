package testYAR.testSpringBatch;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;

import common.Constantes;
import testYAR.testSpringBatch.model.sngc.Sngc;

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

	@Override
	public void onReadError(Exception ex) {
		if (ex instanceof FlatFileParseException) {
			Constantes.KOlog.info("Erreur à la ligne : " + ((FlatFileParseException) ex).getInput());
		}
	}
}