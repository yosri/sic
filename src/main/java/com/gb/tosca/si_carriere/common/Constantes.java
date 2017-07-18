package com.gb.tosca.si_carriere.common;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;

public class Constantes {

	public final static Logger OKlog = Logger.getLogger("OKLogger");
	public final static Logger KOlog = Logger.getLogger("KOLogger");
	public final static int LONGUER_LIGNE_SNGC = 140;
	public final static String CD_REGI_CIPAV = "FRAPA";
	public final static int ANNEE_LIMIT_DATE_LIQUIDATION = 2015;
	public final static String TY_ECH_29 = "29";
	public final static String TY_ECH_49 = "49";
	public final static String TY_ECH_69 = "69";
	public final static List<String> LIST_TY_ECH = Arrays.asList(TY_ECH_29, TY_ECH_49, TY_ECH_69);
	public final static SimpleDateFormat SNGC_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd", Locale.FRANCE);

	// Messages erreurs
	public final static String ERR_LONG_ENR = "Longueur enregistrement erroné";
	public final static String ERR_FORMAT_ENG = "Erreur de format";
	public final static String ERR_NE_CONCERNE_DROIT = "Ne concerne pas un droit";
	public final static String ERR_ANOMALIE_FICHIER_ALLER = "Anomalie de fichier aller";
	public final static String ERR_ADHERENT_NON_IDENT = "Adhérent non identifié";
	public final static String ERR_DATE_LIQUIDATION = "Droits acquis après l’année de 1ère liquidation";
	public final static String ERR_BD = "Erreur technique lors de l’enregistrement";
}
