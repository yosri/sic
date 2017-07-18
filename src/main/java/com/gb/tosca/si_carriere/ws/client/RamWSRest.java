package com.gb.tosca.si_carriere.ws.client;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.tosca.si_carriere.model.ram.Adherent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RamWSRest {

	private static String Url = "http://dlap02.sibille.lan:8086/gestionadmin-api/services/rest/v1/adherents?nir=";

	/**
	 * US01-05 Récupérer l’identifiant fonctionnel d’un adhérent à partir de son NIR
	 * 
	 * @param nir
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Adherent getAdherent(String nir) throws JsonParseException, JsonMappingException, IOException {

		Client clientRest = Client.create();
		WebResource webResource = clientRest.resource(Url + nir);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: " + result);

		// Convertir le Json en Adhérents
		TypeReference<List<Adherent>> mapType = new TypeReference<List<Adherent>>() {
		};
		ObjectMapper mapper = new ObjectMapper();
		List<Adherent> adherents = mapper.readValue(result, mapType);

		Adherent adherentChoisi = null;
		for (Adherent adherent : adherents) {
			if (adherentChoisi.getNumPersonne() < adherent.getNumPersonne()) {
				adherentChoisi = adherent;
			}
		}

		return adherentChoisi;
	}

	/**
	 * US01-06 Récupérer la date de liquidation CER de l’adhérent
	 * 
	 * @param adherent
	 * @return
	 */
	public static Calendar getDateLiquidationCER(Adherent adherent) {
		// TODO [YAR] : Il s’agit d’une donnée Carrière dont RAM est maître.
		// Conception à voir pour récupérer cette données dans RAM ou SI Carrière (attention à l’architecture)

		return Calendar.getInstance();
	}

	/**
	 * US01-07 Récupérer la date de liquidation RB de l’adhérent
	 * 
	 * @param adherent
	 * @return
	 */
	public static Calendar getDateLiquidationRB(Adherent adherent) {
		// TODO [YAR] : Il s’agit d’une donnée LAO dont RAM est maître.
		// Cette donnée doit donc être récupérée via un Web Service exposé par LAO (qui va lui-même chercher la données dans RAM)
		// Mais ce web service n’étant pas encore dispobible 2 solutions :
		// - Un WS mocké
		// - Modification du WS-ADH-02 – Récupération des données adhérent complètes (prévue dans la propale)

		return Calendar.getInstance();
	}
}
