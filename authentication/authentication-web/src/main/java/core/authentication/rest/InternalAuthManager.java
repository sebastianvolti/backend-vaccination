package core.authentication.rest;

import java.net.URI;

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

import core.authentication.ejb.InternalUserEJBLocal;
import core.authentication.entities.InternalUserEntity;

@RequestScoped
@Path("/internal/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InternalAuthManager {
	
	
	@EJB
	private InternalUserEJBLocal internalUserEJB;
	
	public InternalAuthManager() throws NamingException {}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/ping")
	public Response ping(){
		return Response.ok("InternalAuthManager at your service").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/user/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		try {
			InternalUserEntity user = internalUserEJB.getUser(userId);
			return Response.ok(user).build();
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/user")
	public Response saveUser(InternalUserEntity user) {
		try {
			internalUserEJB.saveUser(user);
			return Response.ok("").build();
		}catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/user")
	public Response updateUser(InternalUserEntity user) {
		try {
			internalUserEJB.updateUser(user);
			return Response.ok("").build();
		}catch(Exception e) {
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/user/{userId}")
	public Response deleteUser(@PathParam("userId") String userId) {
		try {
			internalUserEJB.deleteUser(userId);
			return Response.ok("").build();
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/login/{userId}")
	public Response validateUserLogin(@PathParam("userId") String userId, @QueryParam("pass") String pass) {
		try {
			Boolean loginSuccess = internalUserEJB.validateUserLogin(userId, pass);
			return Response.ok(loginSuccess).build();
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}
	

}
