package core.authentication.services;

import java.net.URI;
import javax.ejb.Remote;

import core.authentication.entities.TokenResponse;


@Remote
public interface OpenIdEJBRemote {
	public String ping();
	public URI logout(String tokenId, String redirectUri) throws Exception;
	public TokenResponse token(String code, String redirectUri);
	public String userInfo(String accessToken);
}
