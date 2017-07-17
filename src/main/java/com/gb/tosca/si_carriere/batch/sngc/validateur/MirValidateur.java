package com.gb.tosca.si_carriere.batch.sngc.validateur;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Validateur ligne Fichier Sngc MirTrim
 * 
 * @author yarrami
 *
 */
public class MirValidateur {

	private final static String TY_IDENT = "1";
	private final static String CD_MAJ = "U";
	private final static String TY_UNIT = "2";
	private final static List<String> LIST_TY_ECH = Arrays.asList("29", "49", "69");

	public static boolean estValide(Sngc sngc) {
		if (conditionMirTrim(sngc) || !StringUtils.isNumeric(sngc.getNombreUniteValide()) || !StringUtils.isNumeric(sngc.getDateOrigineDeclaration())) {
			Constantes.KOlog.info(Constantes.ERR_FORMAT_ENG + " : " + sngc.getLigneTotal());
			return false;
		}

		if (!TY_IDENT.equals(sngc.getTypeIdAssure()) || !CD_MAJ.equals(sngc.getCodeMaj()) || !TY_UNIT.equals(sngc.getTypeUnitesValidees())
				|| !LIST_TY_ECH.contains(sngc.getTypeTitreEchange())) {
			Constantes.KOlog.info(Constantes.ERR_FORMAT_ENG + " : " + sngc.getLigneTotal());
			return false;
		}

		return true;
	}

	private static boolean conditionMirTrim(Sngc sngc) {
		return (sngc instanceof SngcMirTrim && !StringUtils.isNumeric(((SngcMirTrim) sngc).getAnneeValidite()));
	}

}
