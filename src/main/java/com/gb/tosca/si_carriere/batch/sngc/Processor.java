package com.gb.tosca.si_carriere.batch.sngc;

import java.util.Calendar;

import org.springframework.batch.item.ItemProcessor;

import com.gb.tosca.si_carriere.batch.sngc.model.bd.TrimestreHorsCipav;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirErr;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrgl;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcNonMir;
import com.gb.tosca.si_carriere.batch.sngc.validateur.DateLiquidationCERValidateur;
import com.gb.tosca.si_carriere.batch.sngc.validateur.DateLiquidationRBValidateur;
import com.gb.tosca.si_carriere.batch.sngc.validateur.MirValidateur;
import com.gb.tosca.si_carriere.common.Constantes;
import com.gb.tosca.si_carriere.testWebService.client.RamWSRest;
import com.gb.tosca.si_carriere.testWebService.model.ram.Adherent;

/**
 * Le Processor
 * 
 * @author yarrami
 *
 */
public class Processor implements ItemProcessor<Sngc, TrimestreHorsCipav> {

	private String nir = null;
	private Adherent adherent = null;

	@Override
	public TrimestreHorsCipav process(final Sngc sngc) throws Exception {

		TrimestreHorsCipav trimestreHorsCipav = null;
		if (sngc instanceof SngcMirTrim || sngc instanceof SngcMirTrgl) {
			if (sngc.getNumSecuriteSociale() != null && !sngc.getNumSecuriteSociale().equals(nir)) {
				nir = sngc.getNumSecuriteSociale();
				try {
					adherent = RamWSRest.getAdherent(nir);
				} catch (Exception e) {
					// TODO [YAR] : si on a des problemes dans l'appel du WS ???
					System.err.println("adherent null !");
				}

				if (adherent != null) {
					System.err.println("nir : " + nir + " -> adherent : " + adherent.toString());
				}

			}

			if (MirValidateur.estValide(sngc) && estAdherentValide(adherent, sngc) && !Constantes.CD_REGI_CIPAV.equals(sngc.getCodeRegime())
					&& DateLiquidationCERValidateur.estValide(getDateLiquidationCER(adherent), sngc)
					&& DateLiquidationRBValidateur.estValide(getDateLiquidationRB(adherent), sngc)) {

				// SngcMirTrim ou SngcMirTrgl valide

				// TODO [YAR]
				// 1. Si des données de trimestres hors CIPAV existent déjà pour l’adhérent concerné dans le référentiel Carrière,
				// le traitement doit historiser toutes les anciennes données et enregistrer toutes les nouvelles données dans la référentiel.

				// 2. Le traitement alimente la base de données avec les nouvelles données de trimestres hors CIPAV pour l’adhérent concerné

				trimestreHorsCipav = new TrimestreHorsCipav();
				if (sngc instanceof SngcMirTrim) {
					trimestreHorsCipav.setAnnee(Integer.parseInt(((SngcMirTrim) sngc).getAnneeValidite()));
				}
				// TODO [YAR] ...
				trimestreHorsCipav.setNumCarriere(sngc.getTypeEnregistrement());
				trimestreHorsCipav.setNatureHorsCipav(sngc.getNumSecuriteSociale());
				trimestreHorsCipav.setRegimeExterne(sngc.getCodeFonction());
				trimestreHorsCipav.setLigneTotal(sngc.getLigneTotal());
			}
		} else if (sngc instanceof SngcMirErr) {
			System.err.println("SngcMirErr : " + sngc.getLigneTotal());
			Constantes.KOlog.info(Constantes.ERR_NE_CONCERNE_DROIT + " : " + sngc.getLigneTotal());
		} else if (sngc instanceof SngcNonMir) {
			System.err.println("SngcNonMir : " + sngc.getLigneTotal());
			Constantes.KOlog.info(Constantes.ERR_ANOMALIE_FICHIER_ALLER + " : " + sngc.getLigneTotal());
			// TODOD [YAR] verifer le message d'erreur : Erreur type d’enregistrement
		}

		return trimestreHorsCipav;
	}

	private boolean estAdherentValide(Adherent adherent, Sngc sngc) {
		if (adherent == null) {
			Constantes.KOlog.info(Constantes.ERR_ADHERENT_NON_IDENT + " : " + sngc.getLigneTotal());
			return false;
		}
		return true;
	}

	private Calendar getDateLiquidationCER(Adherent adherent) {
		// TODO [YAR] : Il s’agit d’une donnée Carrière dont RAM est maître.
		// Conception à voir pour récupérer cette données dans RAM ou SI Carrière (attention à l’architecture)

		return Calendar.getInstance();
	}

	private Calendar getDateLiquidationRB(Adherent adherent) {
		// TODO [YAR] : Il s’agit d’une donnée LAO dont RAM est maître.
		// Cette donnée doit donc être récupérée via un Web Service exposé par LAO (qui va lui-même chercher la données dans RAM)
		// Mais ce web service n’étant pas encore dispobible 2 solutions :
		// - Un WS mocké
		// - Modification du WS-ADH-02 – Récupération des données adhérent complètes (prévue dans la propale)

		return Calendar.getInstance();
	}
}
