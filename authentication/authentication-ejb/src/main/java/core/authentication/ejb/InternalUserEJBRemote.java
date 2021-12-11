package core.authentication.ejb;

import javax.ejb.Remote;

import core.authentication.entities.InternalUserEntity;

@Remote
public interface InternalUserEJBRemote {
	public InternalUserEntity getUser(String userId);
	public void saveUser(InternalUserEntity internalUserEntity);
	public void updateUser(InternalUserEntity internalUserEntity);
	public void deleteUser(String userId);
	public Boolean validateUserLogin(String userId, String userPassword);
}
