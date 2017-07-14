package testYAR.testSpringBatch;

import org.springframework.batch.item.ItemProcessor;

import common.Constantes;
import testYAR.testSpringBatch.model.bd.TrimestreHorsCipav;
import testYAR.testSpringBatch.model.sngc.Sngc;
import testYAR.testSpringBatch.model.sngc.SngcMirErr;
import testYAR.testSpringBatch.model.sngc.SngcMirTrgl;
import testYAR.testSpringBatch.model.sngc.SngcMirTrim;
import testYAR.testSpringBatch.model.sngc.SngcNonMir;

/**
 * Le Processor
 * 
 * @author yarrami
 *
 */
public class MirProcessor implements ItemProcessor<Sngc, TrimestreHorsCipav> {

	public TrimestreHorsCipav process(final Sngc sngc) throws Exception {

		TrimestreHorsCipav trimestreHorsCipav = null;
		if (sngc instanceof SngcMirTrim || sngc instanceof SngcMirTrgl) {
			trimestreHorsCipav = new TrimestreHorsCipav();
			trimestreHorsCipav.setNumCarriere(sngc.getTypeEnregistrement());
			trimestreHorsCipav.setNatureHorsCipav(sngc.getNumSecuriteSociale());
			trimestreHorsCipav.setRegimeExterne(sngc.getCodeFonction());
			trimestreHorsCipav.setLigneTotal(sngc.getLigneTotal());
		} else if (sngc instanceof SngcMirErr) {
			Constantes.KOlog.info("Ne concerne pas un droit : " + sngc.getLigneTotal());
		} else if (sngc instanceof SngcNonMir) {
			Constantes.KOlog.info("Anomalie de fichier aller : " + sngc.getLigneTotal());
		}

		return trimestreHorsCipav;
	}
}
