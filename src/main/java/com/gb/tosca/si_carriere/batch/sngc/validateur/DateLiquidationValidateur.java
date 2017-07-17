package com.gb.tosca.si_carriere.batch.sngc.validateur;

import java.util.Calendar;

import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Validateur de date de liquidation CER & RB
 * 
 * @author yarrami
 *
 */
public class DateLiquidationValidateur {

	public static boolean estValide(Calendar dateLiquidation, SngcMirTrim sngcMirTrim) {
		int daVal = Integer.parseInt(sngcMirTrim.getAnneeValidite());
		if (dateLiquidation == null) {
			// TODO [YAR] : verifer le message !
			Constantes.KOlog.info("dateLiquidation Null ! : " + sngcMirTrim.getLigneTotal());
			return false;
		} else if (dateLiquidation.get(Calendar.YEAR) >= Constantes.ANNEE_LIMIT_DATE_LIQUIDATION && daVal > dateLiquidation.get(Calendar.YEAR)) {
			Constantes.KOlog.info(Constantes.ERR_DATE_LIQUIDATION + " : " + sngcMirTrim.getLigneTotal());
			return false;
		}
		return true;
	}
}
