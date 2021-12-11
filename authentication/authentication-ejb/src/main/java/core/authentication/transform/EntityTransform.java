package core.authentication.transform;

import core.authentication.dao.InternalUserDAO;
import core.authentication.entities.InternalUserEntity;

public class EntityTransform {
	
	public static InternalUserEntity transformEntity(InternalUserDAO userDAO) {
		InternalUserEntity user = null;
		if (userDAO != null) {
			user = new InternalUserEntity();
			user.setUserId(userDAO.getUserId());
			user.setUserName(userDAO.getUserName());
			user.setUserLastName(userDAO.getUserLastName());
			user.setUserEmail(userDAO.getUserEmail());
			user.setUserPassword(userDAO.getUserPassword());
		}
		
		return user;
	}
	
	public static InternalUserDAO transformEntity(InternalUserEntity userEntity) {
		InternalUserDAO user = new InternalUserDAO();
		user.setUserId(userEntity.getUserId());
		user.setUserName(userEntity.getUserName());
		user.setUserLastName(userEntity.getUserLastName());
		user.setUserEmail(userEntity.getUserEmail());
		user.setUserPassword(userEntity.getUserPassword());
		return user;
	}

}
