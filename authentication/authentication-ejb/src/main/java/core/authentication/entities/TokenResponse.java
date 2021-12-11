package core.authentication.entities;

public class TokenResponse {

	private String access_token;
	private String refresh_token;
	private String token_type;
	private Long expires_in;
	private String id_token;
	private String error;
	private String error_description;
	private String state;

	public TokenResponse() {
		super();
	}

	public TokenResponse(String access_token, String refresh_token, String token_type, Long expires_in,
			String id_token, String error, String error_description, String state) {
		super();
		this.access_token = access_token;
		this.refresh_token = refresh_token;
		this.token_type = token_type;
		this.expires_in = expires_in;
		this.id_token = id_token;
		this.error = error;
		this.error_description = error_description;
		this.state = state;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public void setId_token(String id_token) {
		this.id_token = id_token;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

	public String getState() {
		return state;
	}

	public String getId_token() {
		return id_token;
	}

	public void setState(String state) {
		this.state = state;
	}

}
