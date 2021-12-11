package core.authentication.rest;

import java.net.URI;
import java.util.Base64;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

public class RestClientService {
	
	private String clientId = "890192";
	private String clientSecret = "457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179";
	
	public RestClientService() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Response invokePostMethodWithHeader(String url, String jsonObject) {
		ResteasyClient client = new ResteasyClientBuilder().disableTrustManager().build();
		WebTarget target = client.target(url);
		String auth = clientId + ":" + clientSecret;
		String authEncoded = Base64.getEncoder().encodeToString(auth.getBytes());
		return target.request()
				.header("Authorization", "Basic "+authEncoded)
				.post(Entity.entity(jsonObject, MediaType.APPLICATION_FORM_URLENCODED));
	}

	public Response invokeGetMethodWithHeader(String url, String authHeader) {
		ResteasyClient client = new ResteasyClientBuilder().disableTrustManager().build();
		WebTarget target = client.target(url);
		return target.request()
				.header("Authorization", "Bearer " + authHeader)
				.get();
	}

	public URI invokeGetMethodLogout(String url, String tokenID, String redirectUri) throws Exception {
		ResteasyClient client = new ResteasyClientBuilder().disableTrustManager().build();
		WebTarget target = client.target(url).queryParam("id_token_hint",tokenID).queryParam("post_logout_redirect_uri", redirectUri);
		Response response = target.request().get();
		URI redirectURL = null;
		if(response.getStatus() == 302) {
		  	redirectURL = response.getLocation();
		}else {
			throw new Exception("Logout Failed, status code openID = " + response.getStatus());
		}
		return redirectURL;
	}

}
