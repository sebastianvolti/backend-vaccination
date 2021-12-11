package core.authentication.services;

import javax.ejb.Remote;

import org.json.JSONObject;

@Remote
public interface SecurityEJBRemote {
	public String createJWT(String tokenId, String userId, String userRole, String accessToken);
	public String decodeJWT(String jwt);
	public String encodeUser(String userId, String tokenId);
	public String decodeUser(String jwt);
}