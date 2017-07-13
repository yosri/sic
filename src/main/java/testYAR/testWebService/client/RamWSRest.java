package testYAR.testWebService.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RamWSRest {
	private static String Url = "http://dlap02.sibille.lan:8086/gestionadmin-api/services/rest/v1/adherents?nir=";

	public static void getNumAdherents(String nir) {
		Client clientRest = Client.create();
		WebResource webResource = clientRest.resource(Url + nir);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("HTTP Error: " + response.getStatus());
		}
		String result = response.getEntity(String.class);
		System.out.println("Response from the Server: ");
		System.out.println(result);

	}
}
