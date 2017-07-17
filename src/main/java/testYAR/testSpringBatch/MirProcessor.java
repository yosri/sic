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

	private String nir;

	public TrimestreHorsCipav process(final Sngc sngc) throws Exception {

		TrimestreHorsCipav trimestreHorsCipav = null;
		if (sngc instanceof SngcMirTrim || sngc instanceof SngcMirTrgl) {
			if (nir != null && sngc.getNumSecuriteSociale() != null && sngc.getNumSecuriteSociale().equals(nir)) {
				// on n'appel pas le web service => utiliser le dernier resultat
				System.err.println("meme NIR !");
			} else {
				nir = sngc.getNumSecuriteSociale();
				// Appel WS
			}
			trimestreHorsCipav = new TrimestreHorsCipav();
			trimestreHorsCipav.setNumCarriere(sngc.getTypeEnregistrement());
			trimestreHorsCipav.setNatureHorsCipav(sngc.getNumSecuriteSociale());
			trimestreHorsCipav.setRegimeExterne(sngc.getCodeFonction());
			trimestreHorsCipav.setLigneTotal(sngc.getLigneTotal());
		} else if (sngc instanceof SngcMirErr) {
			System.err.println("SngcMirErr : " + sngc.getLigneTotal());
			Constantes.KOlog.info("Ne concerne pas un droit : " + sngc.getLigneTotal());
		} else if (sngc instanceof SngcNonMir) {
			System.err.println("SngcNonMir : " + sngc.getLigneTotal());
			Constantes.KOlog.info("Anomalie de fichier aller : " + sngc.getLigneTotal());
		}

		return trimestreHorsCipav;
	}
}
