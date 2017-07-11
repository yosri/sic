package testYAR.testSpringBatch;

import org.springframework.batch.item.ItemProcessor;

import testYAR.testSpringBatch.model.SngcDdf;
import testYAR.testSpringBatch.model.SngcMirTrim;

public class MirProcessor implements ItemProcessor<SngcMirTrim, SngcMirTrim>
{
	private final String MIR = "MIR";
	private final String AIR = "AIR";
	private final String TRIM = "TRIM";
	private final String TRGL = "TRGL";
    public SngcMirTrim process(final SngcMirTrim sngcMirInput) throws Exception
    {
    	SngcMirTrim sngcMirOutput=null;
        if (estTypeEnregistrementOK(sngcMirInput) && estZoneTrimRegimeOK(sngcMirInput)) {
        	sngcMirOutput = new SngcMirTrim();
        	sngcMirOutput.setTypeEnregistrement(sngcMirInput.getTypeEnregistrement());
        	sngcMirOutput.setZoneAnomaliesRetours(sngcMirInput.getZoneAnomaliesRetours());
        	sngcMirOutput.setZoneIdAssure(sngcMirInput.getZoneIdAssure());
        	sngcMirOutput.setZonePartenaire(sngcMirInput.getZonePartenaire());
        	sngcMirOutput.setZoneTrimestresRegime(sngcMirInput.getZoneTrimestresRegime());
        }else if (!estTypeEnregistrementOK(sngcMirInput)) {
        	// log erreur :
        	// - L’erreur « Anomalie de fichier aller SNGC 1 »
        	// - Puis la ligne correspondante du fichier SNGC
        }else if (!estZoneTrimRegimeOK(sngcMirInput)) {
        	// log erreur :
        	// - L’erreur « Ne concerne pas un droit »
        	// - Puis la ligne correspondante du fichier SNGC
        }
        return sngcMirOutput;
    }
    
    private boolean estTypeEnregistrementOK(final SngcMirTrim sngcMirInput) {
    	return MIR.equals(sngcMirInput.getTypeEnregistrement()) || AIR.equals(sngcMirInput.getTypeEnregistrement());
    }
    
    private boolean estZoneTrimRegimeOK(final SngcMirTrim sngcMirInput) {
    	return sngcMirInput.getZoneTrimestresRegime().indexOf(TRIM)==0 || sngcMirInput.getZoneTrimestresRegime().indexOf(TRGL)==0;
    }
}
