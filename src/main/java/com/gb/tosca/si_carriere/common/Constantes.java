package com.gb.tosca.si_carriere.common;

import org.apache.log4j.Logger;

public class Constantes {

	public final static Logger OKlog = Logger.getLogger("OKLogger");
	public final static Logger KOlog = Logger.getLogger("KOLogger");
	public final static int LONGUER_LIGNE_SNGC = 140;
	public final static String CD_REGI_CIPAV = "FRAPA";
	public final static int ANNEE_LIMIT_DATE_LIQUIDATION = 2015;

	// Messages erreurs
	public final static String ERR_LONG_ENR = "Longueur enregistrement erroné";
	public final static String ERR_FORMAT_ENG = "Erreur de format";
	public final static String ERR_NE_CONCERNE_DROIT = "Ne concerne pas un droit";
	public final static String ERR_ANOMALIE_FICHIER_ALLER = "Anomalie de fichier aller";
	public final static String ERR_ADHERENT_NON_IDENT = "Adhérent non identifié";
	public final static String ERR_DATE_LIQUIDATION = "Droits acquis après l’année de 1ère liquidation";
}
