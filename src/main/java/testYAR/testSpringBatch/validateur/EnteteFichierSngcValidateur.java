package testYAR.testSpringBatch.validateur;

import common.Constantes;
import testYAR.testSpringBatch.exception.HeaderErrorException;

/**
 * Validateur Entete Fichier Sngc
 * 
 * @author yarrami
 *
 */
public class EnteteFichierSngcValidateur {

	private final static String TY_ENR = "DDF";
	private final static String TY_TRAIT = "R";
	private final static String ERR = "Données non réelles";

	public static void valider(String ligne) {
		boolean valid = false;
		try {
			String tyTrait = ligne.substring(42, 43);
			valid = (ligne.indexOf(TY_ENR) == 0 && TY_TRAIT.equals(tyTrait));
		} catch (Exception e) {
			valid = false;
		}
		if (!valid) {
			Constantes.KOlog.info(EnteteFichierSngcValidateur.ERR);
			Constantes.KOlog.info(ligne);
			throw new HeaderErrorException();
		}
	}
}
