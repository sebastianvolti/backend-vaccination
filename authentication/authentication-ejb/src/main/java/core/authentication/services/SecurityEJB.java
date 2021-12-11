package core.authentication.services;

import java.util.Date;

import javax.ejb.Stateless;

import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Stateless
public class SecurityEJB implements SecurityEJBLocal, SecurityEJBRemote{
	
	public String jwtSecret = "457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179";
	// JWT token defaults
	private static final String TOKEN_ISSUER = "secure-api";
	private static final String TOKEN_AUDIENCE = "secure-app";
	private static final String TOKEN_ID = "idt";
	private static final String ACCESS_TOKEN = "accessToken";
	private static final String USER_ID = "userId";
	private static final String USER_ROLE = "userRole";
	private static final long EXPIRATION_TIME = 24*60*60*1000;	//24 horas en milisegundos
	
	@Override
	public String createJWT(String tokenId, String userId, String userRole, String accessToken) {
		long expirationTime;
		try {
			expirationTime = EXPIRATION_TIME;
		}catch(Exception e) {
			//default value
			expirationTime = EXPIRATION_TIME;
		}
		Date expirationDate =  new Date(System.currentTimeMillis() + expirationTime);
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
		return JWT.create().withClaim(TOKEN_ID, tokenId).withClaim(ACCESS_TOKEN, accessToken).withClaim(USER_ID, userId).withClaim(USER_ROLE, userRole).withIssuer(TOKEN_ISSUER).withAudience(TOKEN_AUDIENCE).withExpiresAt(expirationDate).sign(algorithm);
	}

	@Override
	public String decodeJWT(String jwtParam) {
		DecodedJWT jwt = JWT.decode(jwtParam);
		Claim tokenID = jwt.getClaim(TOKEN_ID);
		Claim accessToken = jwt.getClaim(ACCESS_TOKEN);
		Claim userId = jwt.getClaim(USER_ID);
		Claim userRole = jwt.getClaim(USER_ROLE);
		JSONObject res = new JSONObject();
		res.put("token_id", tokenID.asString());
		res.put("access_token", accessToken.asString());
		res.put("user_id", userId.asString());
		res.put("user_role", userRole.asString());
		return res.toString();
	}
	
	@Override
	public String encodeUser(String userId, String tokenId) {
		Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
		return JWT.create().withClaim(TOKEN_ID, tokenId).withClaim(USER_ID, userId).sign(algorithm);
	}
	
	@Override
	public String decodeUser(String jwtParam) {
		DecodedJWT jwt = JWT.decode(jwtParam);
		Claim tokenID = jwt.getClaim(TOKEN_ID);
		Claim userId = jwt.getClaim(USER_ID);
		JSONObject res = new JSONObject();
		res.put("token_id", tokenID.asString());
		res.put("user_id", userId.asString());
		return res.toString();
	}

}
