package com.gb.tosca.si_carriere.batch.sngc.validateur;

import java.util.Calendar;

import com.gb.tosca.si_carriere.batch.sngc.model.sngc.Sngc;
import com.gb.tosca.si_carriere.batch.sngc.model.sngc.SngcMirTrim;
import com.gb.tosca.si_carriere.common.Constantes;

/**
 * Validateur de date de liquidation CER & RB
 * 
 * @author yarrami
 *
 */
public class DateLiquidationCerValidateur extends DateLiquidationValidateur {

	public static boolean estValide(Calendar dateLiquidationCer, Sngc sngc) {
		if (sngc instanceof SngcMirTrim) {
			SngcMirTrim sngcMirTrim = (SngcMirTrim) sngc;
			int daVal = Integer.parseInt(sngcMirTrim.getAnneeValidite());
			if (DateLiquidationValidateur.estValide(dateLiquidationCer, sngcMirTrim)) {
				if (dateLiquidationCer.get(Calendar.YEAR) < Constantes.ANNEE_LIMIT_DATE_LIQUIDATION && daVal >= Constantes.ANNEE_LIMIT_DATE_LIQUIDATION) {
					Constantes.KOlog.info(Constantes.ERR_DATE_LIQUIDATION + " : " + sngcMirTrim.getLigneTotal());
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
}
