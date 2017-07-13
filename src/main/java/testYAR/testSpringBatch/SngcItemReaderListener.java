package testYAR.testSpringBatch;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.item.file.FlatFileParseException;

import common.Constantes;
import testYAR.testSpringBatch.model.sngc.SngcMirTrim;

/**
 * SngcItemReaderListener : pour la gestion des lignes qui ne mappe pas le model de donnees
 * 
 * @author yarrami
 *
 */
public class SngcItemReaderListener implements ItemReadListener<SngcMirTrim> {

	@Override
	public void beforeRead() {
		// Vide
	}

	@Override
	public void afterRead(SngcMirTrim item) {
		// Vide
	}

	@Override
	public void onReadError(Exception ex) {
		FlatFileParseException exception = (FlatFileParseException) ex;
		Constantes.KOlog.info("Erreur à la ligne : " + exception.getInput());
	}
}