package com.gb.tosca.si_carriere.ws.client;

import java.io.IOException;
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
}
