package controller;

import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import testYAR.testSpringBatch.model.sngc.SngcMirTrim;

@Path("/test")
@Produces("application/json")
public class TestController {

	final static Logger log = Logger.getLogger("TestController");

	@Path("/{id}")
	@GET
	public SngcMirTrim getTrooper(@PathParam("id") String id) {
		log.info("l'id reçu est " + id);
		return new SngcMirTrim();
	}

}