package testYAR.testSpringBatch;

import org.springframework.batch.item.file.LineCallbackHandler;

import testYAR.testSpringBatch.validateur.EnteteFichierSngcValidateur;

/**
 * SkippableItemReader : appeler pour verifer la 1er ligne du fichier SNGC
 * 
 * @author yarrami
 *
 */
public class SkippableItemReader implements LineCallbackHandler {

	@Override
	public void handleLine(String line) {
		EnteteFichierSngcValidateur.valider(line);
	}
}
