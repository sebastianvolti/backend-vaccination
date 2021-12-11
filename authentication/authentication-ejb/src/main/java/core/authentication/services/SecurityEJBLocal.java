package core.authentication.services;

import javax.ejb.Local;

import org.json.JSONObject;

@Local
public interface SecurityEJBLocal {
	public String createJWT(String tokenId, String userId, String userRole, String accessToken);
	public String decodeJWT(String jwt);
	public String encodeUser(String userId, String tokenId);
	public String decodeUser(String jwt);
}