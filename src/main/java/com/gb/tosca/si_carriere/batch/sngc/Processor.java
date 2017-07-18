package com.gb.tosca.si_carriere.batch.sngc;

import java.text.ParseException;
import java.util.Calendar;

import org.springframework.batch.item.ItemProcessor;

import com.gb.tosca.si_carriere.batch.sngc.model.bd.TrimestreHorsCipav;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirErr;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrgl;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcNonMir;
import com.gb.tosca.si_carriere.batch.sngc.validateur.DateLiquidationCerValidateur;
import com.gb.tosca.si_carriere.batch.sngc.validateur.DateLiquidationRbValidateur;
import com.gb.tosca.si_carriere.batch.sngc.validateur.MirValidateur;
import com.gb.tosca.si_carriere.common.Constantes;
import com.gb.tosca.si_carriere.model.ram.Adherent;
import com.gb.tosca.si_carriere.ws.client.RamWSRest;

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
					&& DateLiquidationCerValidateur.estValide(RamWSRest.getDateLiquidationCER(adherent), sngc)
					&& DateLiquidationRbValidateur.estValide(RamWSRest.getDateLiquidationRB(adherent), sngc)) {

				// SngcMirTrim ou SngcMirTrgl valide

				// TODO [YAR]
				// 1. Si des données de trimestres hors CIPAV existent déjà pour l’adhérent concerné dans le référentiel Carrière,
				// le traitement doit historiser toutes les anciennes données et enregistrer toutes les nouvelles données dans la référentiel.

				// 2. Le traitement alimente la base de données avec les nouvelles données de trimestres hors CIPAV pour l’adhérent concerné

				trimestreHorsCipav = getTrimestreHorsCipav(sngc);
			}

			// trimestreHorsCipav = new TrimestreHorsCipav();
			// trimestreHorsCipav.setNumCarriere(sngc.getTypeEnregistrement());
			// trimestreHorsCipav.setNatureHorsCipav(sngc.getNumSecuriteSociale());
			// trimestreHorsCipav.setRegimeExterne(sngc.getCodeFonction());
			// trimestreHorsCipav.setLigneTotal(sngc.getLigneTotal());
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

	private Calendar getDate(String date) {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(Constantes.SNGC_DATE_FORMAT.parse(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return cal;
	}

	private TrimestreHorsCipav getTrimestreHorsCipav(Sngc sngc) {
		TrimestreHorsCipav trimestreHorsCipav = new TrimestreHorsCipav();
		if (sngc instanceof SngcMirTrim) {
			trimestreHorsCipav.setAnnee(Integer.parseInt(((SngcMirTrim) sngc).getAnneeValidite()));
		}
		// TODO [YAR] : // lien table de référence ?
		trimestreHorsCipav.setNatureHorsCipav(sngc.getCodeNatureTrim()); // lien table de référence ?
		trimestreHorsCipav.setRegimeExterne(sngc.getCodeRegime()); // lien table de référence ?
		if (Constantes.TY_ECH_29.equals(sngc.getTypeTitreEchange()) || Constantes.TY_ECH_49.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setTypeTrimestre("Trimestre assimilé"); // lien table de référence ?
		} else if (Constantes.TY_ECH_69.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setTypeTrimestre("Trimestre cotisé"); // lien table de référence ?
		}
		trimestreHorsCipav.setNombreTrimestre(Integer.parseInt(sngc.getNombreUniteValide()));
		trimestreHorsCipav.setTypeTitreEchange(sngc.getTypeTitreEchange());
		if (Constantes.TY_ECH_29.equals(sngc.getTypeTitreEchange()) || Constantes.TY_ECH_69.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setPriseCompteCalculDuree(true);
		} else if (Constantes.TY_ECH_49.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setPriseCompteCalculDuree(false);
		}
		if (Constantes.TY_ECH_69.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setPriseCompteCalculDepartAnticipe(true);
		} else if (Constantes.TY_ECH_29.equals(sngc.getTypeTitreEchange()) || Constantes.TY_ECH_49.equals(sngc.getTypeTitreEchange())) {
			trimestreHorsCipav.setPriseCompteCalculDepartAnticipe(false);
		}
		trimestreHorsCipav.setOrganismeDeclarant(sngc.getNumOrganismeOrigineDeclarant()); // lien table de référence ?
		trimestreHorsCipav.setDateDeclaration(getDate(sngc.getDateOrigineDeclaration()));
		trimestreHorsCipav.setDateEnregistrement(Calendar.getInstance());
		return trimestreHorsCipav;
	}
}
