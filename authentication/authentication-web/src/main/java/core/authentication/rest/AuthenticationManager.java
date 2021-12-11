package core.authentication.rest;

import java.net.URI;
import java.util.Properties;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import core.authentication.ejb.InternalUserEJBLocal;
import core.authentication.entities.InternalUserEntity;
import core.authentication.entities.TokenResponse;
import core.authentication.services.OpenIdEJBLocal;
import core.authentication.services.SecurityEJBLocal;
import uy.edu.fing.tse.vacunasuy.serviceU.IUsuariosServiceRemote;

@RequestScoped
@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationManager {
		
	IUsuariosServiceRemote  service; //$NON-NLS-1$
	private static final String COOKIE_NAME = "TSE_JWT";
	private static final String URI_ORIGIN_VACCINATORS = "http://vacunadores07.web.elasticloud.uy";
	private static final String URI_ORIGIN_CITIZENS = "http://vacunas07.web.elasticloud.uy";

	
	@EJB
	private OpenIdEJBLocal openIdEJB;
	
	@EJB
	private SecurityEJBLocal securityEJB;
	

	@EJB
	private InternalUserEJBLocal internalUserEJB;
	
	public AuthenticationManager() throws NamingException {
		Properties props = new Properties();
	      // invoke on the bean
		props.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");   //$NON-NLS-1$
		props.put(javax.naming.Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");   //$NON-NLS-1$
		props.put(javax.naming.Context.PROVIDER_URL,"http-remoting://core-07.web.elasticloud.uy:80");  //$NON-NLS-1$
		props.put(javax.naming.Context.SECURITY_PRINCIPAL, "backoffice"); //$NON-NLS-1$
		props.put(javax.naming.Context.SECURITY_CREDENTIALS,"backoffice"); //$NON-NLS-1$
		javax.naming.Context ctx;
		
		try {
			ctx = new InitialContext(props);
			
			service =  (IUsuariosServiceRemote)ctx.lookup("ejb:usuarios/usuariosEJB/UsuariosServiceRemote!uy.edu.fing.tse.vacunasuy.serviceU.IUsuariosServiceRemote");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/ping")
	public Response ping(){
		return Response.ok(openIdEJB.ping()).build();
	}
	
	
	@GET
	@Path("/validateToken")
	public Response validateJWT(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			boolean validCookie = false;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {
						String json = securityEJB.decodeJWT(cookie.getValue());
						JSONObject user = new JSONObject(json);
						String userRole = user.getString("user_role");
						
						validCookie = ((request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_VACCINATORS) && userRole.equals("VACCINATOR")) 
							|| (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_CITIZENS) && userRole.equals("CITIZEN"))) ;
						
						if (validCookie)
							break;
					}
				}
			}
			
			if (validCookie) {
				return Response.ok("valid cookie").build();
			}
			else {
				return Response.status(401).build();
			}
		}catch(Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/cleanJWT")
	public Response cleanJWT(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {
						cookie.setValue("");
			            cookie.setPath("/");
			            cookie.setMaxAge(0);
			            response.addCookie(cookie);
			            break;
					}
				}
			}
			return Response.ok("null").build();
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/logout")
	public Response logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			String redirectURL = null;
			String urlLogout = null;
			if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_VACCINATORS)) {
				urlLogout = "http%3A%2F%2Fvacunadores07.web.elasticloud.uy%2Fhome";
			}
			else if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_CITIZENS)) {
				urlLogout = "http%3A%2F%2Fvacunas07.web.elasticloud.uy%2Fhome";
			}
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {

						/*
						 * Obtengo desde cookies el tokenID que identifica la sesión del usuario en openID,
						 * valor con el cual se realiza un método GET que cierra sesión
						 */
						String valueJWT = cookie.getValue();
						String json = securityEJB.decodeJWT(valueJWT);
						JSONObject user = new JSONObject(json);

						redirectURL = "https://auth-testing.iduruguay.gub.uy/oidc/v1/logout?id_token_hint=" 
						+ user.getString("token_id") + "&post_logout_redirect_uri=" + urlLogout;

						/*
						 * Limpio cookie
						 */
						cookie.setValue("");
			            cookie.setPath("/");
			            cookie.setMaxAge(0);
			            response.addCookie(cookie);
			            break;

					}
				}
			}
			return Response.ok(redirectURL).build();
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/public/userInfo/{code}")
	public Response userInfo(@PathParam("code") String code, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			if (code == null || code.isEmpty()) {
				return Response.serverError().build();
			}
			String redirectURI = null;
			if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_VACCINATORS)) {
				redirectURI = URI_ORIGIN_VACCINATORS;
			}
			else if (request.getHeader("Origin").equalsIgnoreCase(URI_ORIGIN_CITIZENS)) {
				redirectURI = URI_ORIGIN_CITIZENS;
			}
			
			TokenResponse token = openIdEJB.token(code, redirectURI + "/callback");
			String userInfo = openIdEJB.userInfo(token.getAccess_token());
			JSONObject user = new JSONObject(userInfo);
			String userId = user.getString("uid").replace("uy-ci-", "");
			String userRole = null;
			
			String value = securityEJB.encodeUser(userId, token.getId_token());
			if (redirectURI.equalsIgnoreCase(URI_ORIGIN_VACCINATORS)) {
				if (service.isUsuarioVacunador(userId)) {
					userRole = "VACCINATOR"; 
				}
				else {
					return Response.serverError().build();
				} 
	        }
			else if (redirectURI.equalsIgnoreCase(URI_ORIGIN_CITIZENS)) {
				userRole = "CITIZEN";
				InternalUserEntity userEntity = internalUserEJB.getUser(userId);
				if (userEntity == null) {
					user.put("new", true);	
				}	
			}
			
			user.put("role", userRole);	
		
			if ("CITIZEN".equalsIgnoreCase(userRole)) {
				String jwt = securityEJB.createJWT(token.getId_token(), userId, userRole, token.getAccess_token());
				response.addHeader("Set-Cookie", COOKIE_NAME + "=" + jwt + "; Path=/; HttpOnly;");
			}
			else if ("VACCINATOR".equalsIgnoreCase(userRole)) {
				user.put("p1", value);
			}
			
			return Response.ok(user.toString()).build();
			
		} catch (Exception e) {
			return Response.serverError().build();
		}
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/userData")
	public Response userData(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			JSONObject user = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equalsIgnoreCase(COOKIE_NAME)) {
						String valueJWT = cookie.getValue();
						String json = securityEJB.decodeJWT(valueJWT);
						JSONObject jsonData = new JSONObject(json);
					    String userInfo = openIdEJB.userInfo(jsonData.getString("access_token"));
					    user = new JSONObject(userInfo);
						user.put("role", jsonData.getString("user_role"));
			            break;
					}
				}
			}
			if (user != null) {
				return Response.ok(user.toString()).build();
			}
			else {
				return Response.serverError().build();
			}
			
		}catch(Exception e) {
			return Response.serverError().build();
		}

	}

	
	@POST
	@Path("/user/confirmation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userConfirmation(InternalUserEntity userEntity){
		try {
			if (userEntity != null) {
				internalUserEJB.saveUser(userEntity);
			}
			return Response.status(200).build();
		}catch(Exception ex) {
			ex.printStackTrace();
			return Response.status(500).entity(ex.getMessage()).build();
		}
	}

}
