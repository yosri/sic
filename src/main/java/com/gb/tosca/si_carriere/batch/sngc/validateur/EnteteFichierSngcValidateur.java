package com.gb.tosca.si_carriere.batch.sngc.validateur;

import org.apache.commons.lang3.StringUtils;

import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Validateur Entete Fichier Sngc
 * 
 * @author yarrami
 *
 */
public class EnteteFichierSngcValidateur {

	private final static String TY_ENR = "DDF";
	private final static String TY_TRAIT_R = "R";
	private final static String TY_TRAIT_T = "T";

	private final static int TY_TRAIT_INDEX_DEBUT = 42;
	private final static int TY_TRAIT_INDEX_FIN = 43;
	private final static int NUM_ENV_INDEX_DEBUT = 19;
	private final static int NUM_ENV_INDEX_FIN = 25;
	private final static int DT_CRE_INDEX_DEBUT = 25;
	private final static int DT_CRE_INDEX_FIN = 33;
	private final static int NB_ENR_INDEX_DEBUT = 33;
	private final static int NB_ENR_INDEX_FIN = 42;

	public static boolean estValide(String ligne) {
		// verifier longueur
		if (ligne.length() != Constantes.LONGUER_LIGNE_SNGC) {
			Constantes.KOlog.info(Constantes.ERR_LONG_ENR + " : " + ligne);
			return false;
		}

		if (!estNum(ligne.substring(NUM_ENV_INDEX_DEBUT, NUM_ENV_INDEX_FIN)) || !estNum(ligne.substring(DT_CRE_INDEX_DEBUT, DT_CRE_INDEX_FIN))
				|| !estNum(ligne.substring(NB_ENR_INDEX_DEBUT, NB_ENR_INDEX_FIN))) {
			Constantes.KOlog.info(Constantes.ERR_FORMAT_ENG + " : " + ligne);
			return false;
		}

		// verfier infos
		String tyTrait = ligne.substring(TY_TRAIT_INDEX_DEBUT, TY_TRAIT_INDEX_FIN);
		if (!(ligne.indexOf(TY_ENR) == 0 && (TY_TRAIT_R.equals(tyTrait) || TY_TRAIT_T.equals(tyTrait)))) {
			Constantes.KOlog.info(Constantes.ERR_FORMAT_ENG + " : " + ligne);
			return false;
		}

		Constantes.OKlog.info("OK " + ligne);
		return true;
	}

	private static boolean estNum(String string) {
		return StringUtils.isNumeric(string);
	}
}
