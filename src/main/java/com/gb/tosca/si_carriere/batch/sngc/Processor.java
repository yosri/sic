package com.gb.tosca.si_carriere.batch.sngc;

import org.springframework.batch.item.ItemProcessor;

import com.gb.tosca.si_carriere.batch.sngc.model.bd.TrimestreHorsCipav;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirErr;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrgl;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcNonMir;
import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Le Processor
 * 
 * @author yarrami
 *
 */
public class Processor implements ItemProcessor<Sngc, TrimestreHorsCipav> {

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
