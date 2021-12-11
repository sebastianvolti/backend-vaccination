package core.reports.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import core.reports.ejb.ReportManagerEJBLocal;
import core.reports.entities.AgendaPublicEntity;


@RequestScoped
@Path("/reports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportManager {
	
	@EJB
	private ReportManagerEJBLocal reportManagerEJB;
	
	public ReportManager() throws NamingException {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/ping")
	public Response ping(){
		return Response.ok("ReportManager at your service").build();
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/agendas")
	public Response getAvailableAgendas() {
		List<AgendaPublicEntity> agendaList = reportManagerEJB.getPublicAvailableAgendas();
		return Response.ok(agendaList).header("Access-Control-Allow-Origin", "*")
				 .header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization")
				 .header("Access-Control-Allow-Credentials", "true")
				 .header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD")
				 .build();
	}


}
