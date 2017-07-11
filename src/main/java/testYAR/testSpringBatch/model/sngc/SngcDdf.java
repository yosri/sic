package testYAR.testSpringBatch.model.sngc;

public class SngcDdf {                                                                                                     
    private String typeEnregistrement; // Long : 3
    private String zoneDefEchange; // Long : 8
    private String zoneDefEmetteur; // Long : 8
    private String zoneDefFichier; // Long : 24
	public String getZoneDefEchange() {
		return zoneDefEchange;
	}
	public void setZoneDefEchange(String zoneDefEchange) {
		this.zoneDefEchange = zoneDefEchange;
	}
	public String getTypeEnregistrement() {
		return typeEnregistrement;
	}
	public void setTypeEnregistrement(String typeEnregistrement) {
		this.typeEnregistrement = typeEnregistrement;
	}
	public String getZoneDefEmetteur() {
		return zoneDefEmetteur;
	}
	public void setZoneDefEmetteur(String zoneDefEmetteur) {
		this.zoneDefEmetteur = zoneDefEmetteur;
	}
	public String getZoneDefFichier() {
		return zoneDefFichier;
	}
	public void setZoneDefFichier(String zoneDefFichier) {
		this.zoneDefFichier = zoneDefFichier;
	}
    
}