package core.authentication.services;

import javax.ejb.Local;

import core.authentication.entities.InternalUserEntity;

@Local
public interface InternalUserManagerLocal {
	public InternalUserEntity getUser(String userId);
	public void saveUser(InternalUserEntity internalUserEntity);
	public void updateUser(InternalUserEntity internalUserEntity);
	public void deleteUser(String userId);
	public Boolean validateUserLogin(String userId, String userPassword);
}

