package core.authentication.services;

import java.net.URI;
import javax.ejb.Local;

import core.authentication.entities.TokenResponse;


@Local
public interface OpenIdEJBLocal {
	public String ping();
	public URI logout(String tokenId, String redirectUri) throws Exception;
	public TokenResponse token(String code, String redirectUri);
	public String userInfo(String accessToken);
}
