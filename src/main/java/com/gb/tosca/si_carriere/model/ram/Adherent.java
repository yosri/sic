package com.gb.tosca.si_carriere.model.ram;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Adherent {

	@JsonProperty
	private Long numPersonne;
	@JsonProperty
	private String nom;
	@JsonProperty
	private String prenom;
	@JsonProperty
	private String nomNaissance;
	@JsonProperty
	private String dateNaissance;
	@JsonProperty
	private String lieuNaissance;
	@JsonProperty
	private String civilite;
	@JsonProperty
	private Boolean decede;
	@JsonProperty
	private Boolean sensible;
	// private List regimesLiquides;
	@JsonProperty
	private String codeRegime;
	@JsonProperty
	private BigDecimal txMinoration;
	private Map<String, Object> otherProperties = new HashMap<String, Object>();

	public long getNumPersonne() {
		return numPersonne;
	}

	public void setNumPersonne(long numPersonne) {
		this.numPersonne = numPersonne;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNomNaissance() {
		return nomNaissance;
	}

	public void setNomNaissance(String nomNaissance) {
		this.nomNaissance = nomNaissance;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getLieuNaissance() {
		return lieuNaissance;
	}

	public void setLieuNaissance(String lieuNaissance) {
		this.lieuNaissance = lieuNaissance;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public boolean isDecede() {
		return decede;
	}

	public void setDecede(boolean decede) {
		this.decede = decede;
	}

	public boolean isSensible() {
		return sensible;
	}

	public void setSensible(boolean sensible) {
		this.sensible = sensible;
	}

	/*
	 * public List getRegimesLiquides() { return regimesLiquides; } public void setRegimesLiquides(List regimesLiquides) { this.regimesLiquides = regimesLiquides; }
	 */
	public String getCodeRegime() {
		return codeRegime;
	}

	public void setCodeRegime(String codeRegime) {
		this.codeRegime = codeRegime;
	}

	public BigDecimal getTxMinoration() {
		return txMinoration;
	}

	public void setTxMinoration(BigDecimal txMinoration) {
		this.txMinoration = txMinoration;
	}

	@JsonAnyGetter
	public Map<String, Object> any() {
		return otherProperties;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		otherProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "numPersonne : " + numPersonne + " - nom : " + nom + " - prenom : " + prenom + " - dateNaissance : " + dateNaissance;
	}

}
