package core.authentication.entities;
import java.io.Serializable;

public class InternalUserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5019097375960424437L;
	private String userId;
	private String userName;
	private String userLastName;
	private String userEmail;
	private String userPassword;

	
	public InternalUserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public InternalUserEntity(String userId, String userName, String userLastName, String userEmail, String userPassword) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public String getUserLastName() {
		return userLastName;
	}


	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

	
}
