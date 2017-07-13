package testYAR.testSpringBatch;

import org.springframework.batch.item.ItemProcessor;

import testYAR.testSpringBatch.model.bd.TrimestreHorsCipav;
import testYAR.testSpringBatch.model.sngc.SngcMirTrim;

/**
 * Le Processor
 * 
 * @author yarrami
 *
 */
public class MirProcessor implements ItemProcessor<SngcMirTrim, TrimestreHorsCipav> {
	private final String MIR = "MIR";
	private final String AIR = "AIR";
	private final String TRIM = "TRIM";
	private final String TRGL = "TRGL";

	public TrimestreHorsCipav process(final SngcMirTrim sngcMirInput) throws Exception {
		TrimestreHorsCipav trimestreHorsCipav = null;
		if (estTypeEnregistrementOK(sngcMirInput) && estCodeFonctionOK(sngcMirInput)) {
			trimestreHorsCipav = new TrimestreHorsCipav();
			trimestreHorsCipav.setNumCarriere(sngcMirInput.getNumSecuriteSociale());
			trimestreHorsCipav.setOrganismeDeclarant(sngcMirInput.getNumOrganismeOrigineDeclarant());

		} else if (!estTypeEnregistrementOK(sngcMirInput)) {
			// log erreur :
			// - L’erreur « Anomalie de fichier aller »
			// - Puis la ligne correspondante du fichier SNGC
		} else if (!estCodeFonctionOK(sngcMirInput)) {
			// log erreur :
			// - L’erreur « Ne concerne pas un droit »
			// - Puis la ligne correspondante du fichier SNGC
		}
		return trimestreHorsCipav;
	}

	private boolean estTypeEnregistrementOK(final SngcMirTrim sngcMirInput) {
		return MIR.equals(sngcMirInput.getTypeEnregistrement()) || AIR.equals(sngcMirInput.getTypeEnregistrement());
	}

	private boolean estCodeFonctionOK(final SngcMirTrim sngcMirInput) {
		return sngcMirInput.getCodeFonction().indexOf(TRIM) == 0 || sngcMirInput.getCodeFonction().indexOf(TRGL) == 0;
	}
}
