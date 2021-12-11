package core.authentication.services;
import java.net.URI;
import javax.ejb.Stateless;

import core.authentication.entities.TokenResponse;
import core.authentication.exceptions.OpenIdException;
import core.authentication.rest.RestClientService;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;

@Stateless
public class OpenIdEJB implements OpenIdEJBLocal, OpenIdEJBRemote {
	
	private RestClientService restClientService;
    /**
     * Default constructor. 
     */
    public OpenIdEJB() {
    	restClientService = new RestClientService();
    }
    
    @Override
	public String ping() {
		return "OpenID Authentication Manager at your service";
	}
    
    @Override
	public TokenResponse token(String code, String redirectUri) {
		String url = "https://auth-testing.iduruguay.gub.uy/oidc/v1/token";
		String grantType = "authorization_code";
		String body = "code=" + code + "&grant_type=" + grantType + "&redirect_uri=" + redirectUri;
		Response resultInvocation = restClientService.invokePostMethodWithHeader(url, body);
		TokenResponse tokenResponse = resultInvocation.readEntity(TokenResponse.class);
		if (resultInvocation.getStatus() != HttpStatus.SC_OK) {
			throw new OpenIdException(tokenResponse.getError_description());
		}
		return tokenResponse;
	}

    @Override
	public String userInfo(String accessToken) {
		String url = "https://auth-testing.iduruguay.gub.uy/oidc/v1/userinfo";
		Response resultInvocation = restClientService.invokeGetMethodWithHeader(url, accessToken);
		if (resultInvocation.getStatus() != HttpStatus.SC_OK) {
			throw new OpenIdException("error al obtener la informacion del usuario");
		}
		return resultInvocation.readEntity(String.class);
	}
	
    @Override
	public URI logout(String tokenId, String redirectUri) throws Exception {
		String url = "https://auth-testing.iduruguay.gub.uy/oidc/v1/logout";
		URI redirectURL = restClientService.invokeGetMethodLogout(url, tokenId, redirectUri);
		return redirectURL;
	}
  
}
