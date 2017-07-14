package testYAR.testSpringBatch.model.bd;

import java.util.Calendar;

/**
 * TrimestreHorsCipav
 * 
 * @author yarrami
 *
 */
public class TrimestreHorsCipav {
	private String numCarriere;
	private Integer annee;
	private String natureHorsCipav;
	private String regimeExterne;
	private String typeTrimestre;
	private Integer nombreTrimestre;
	private Boolean priseCompteCalculDuree;
	private Boolean priseCompteCalculDepartAnticipe;
	private String organismeDeclarant;
	private Calendar dateDeclaration;
	private String ligneTotal;

	public String getNumCarriere() {
		return numCarriere;
	}

	public void setNumCarriere(String numCarriere) {
		this.numCarriere = numCarriere;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getNatureHorsCipav() {
		return natureHorsCipav;
	}

	public void setNatureHorsCipav(String natureHorsCipav) {
		this.natureHorsCipav = natureHorsCipav;
	}

	public String getRegimeExterne() {
		return regimeExterne;
	}

	public void setRegimeExterne(String regimeExterne) {
		this.regimeExterne = regimeExterne;
	}

	public String getTypeTrimestre() {
		return typeTrimestre;
	}

	public void setTypeTrimestre(String typeTrimestre) {
		this.typeTrimestre = typeTrimestre;
	}

	public Integer getNombreTrimestre() {
		return nombreTrimestre;
	}

	public void setNombreTrimestre(Integer nombreTrimestre) {
		this.nombreTrimestre = nombreTrimestre;
	}

	public Boolean getPriseCompteCalculDuree() {
		return priseCompteCalculDuree;
	}

	public void setPriseCompteCalculDuree(Boolean priseCompteCalculDuree) {
		this.priseCompteCalculDuree = priseCompteCalculDuree;
	}

	public Boolean getPriseCompteCalculDepartAnticipe() {
		return priseCompteCalculDepartAnticipe;
	}

	public void setPriseCompteCalculDepartAnticipe(Boolean priseCompteCalculDepartAnticipe) {
		this.priseCompteCalculDepartAnticipe = priseCompteCalculDepartAnticipe;
	}

	public String getOrganismeDeclarant() {
		return organismeDeclarant;
	}

	public void setOrganismeDeclarant(String organismeDeclarant) {
		this.organismeDeclarant = organismeDeclarant;
	}

	public Calendar getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(Calendar dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public String getLigneTotal() {
		return ligneTotal;
	}

	public void setLigneTotal(String ligneTotal) {
		this.ligneTotal = ligneTotal;
	}
}
