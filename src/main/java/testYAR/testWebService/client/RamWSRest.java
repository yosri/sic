package testYAR.testWebService.client;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import testYAR.testWebService.model.ram.Adherents;

public class RamWSRest {
	private static String Url = "http://dlap02.sibille.lan:8086/gestionadmin-api/services/rest/v1/adherents?nir=";

	public static long getNumAdherents(String nir) throws JsonParseException, JsonMappingException, IOException {
		
		long numPersonne = -1;
		
		Client clientRest = Client.create();
		WebResource webResource = clientRest.resource(Url + nir);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: " + result);
		
		// Convertir le Json en Adhérents 
		TypeReference<List<Adherents>> mapType = new TypeReference<List<Adherents>>() {};
		ObjectMapper mapper = new ObjectMapper();
		List<Adherents> adherents = mapper.readValue(result, mapType);
		
		for (Adherents adherent : adherents) {
			if(numPersonne < adherent.getNumPersonne()){
				numPersonne = adherent.getNumPersonne();
			}
		}
		
		return numPersonne;
	}
}
