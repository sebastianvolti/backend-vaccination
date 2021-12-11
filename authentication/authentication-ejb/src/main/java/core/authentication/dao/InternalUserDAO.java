package core.authentication.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="internal_user")
public class InternalUserDAO {

	@Id 
	@Column(name="user_id")
	private String userId;
	
	
	@Column(nullable=false, name="user_name")
	private String userName;
	
	@Column(nullable=false, name="user_last_name")
	private String userLastName;
	
	@Column(nullable=false, name="user_email")
	private String userEmail;
	
	
	@Column(nullable=true, name="user_password")
	private String userPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
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

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
