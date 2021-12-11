package core.authentication.ejb;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import core.authentication.entities.InternalUserEntity;
import core.authentication.services.InternalUserManagerLocal;


@Stateless
public class InternalUserEJB implements InternalUserEJBLocal, InternalUserEJBRemote {
	
	@EJB
	private InternalUserManagerLocal internalUserManagerEJB;
	
    /**
     * Default constructor. 
     */
    public InternalUserEJB() {
    }
    
	@Override
	public InternalUserEntity getUser(String userId) {
		return internalUserManagerEJB.getUser(userId);
	}
	
	@Override
	public void saveUser(InternalUserEntity internalUserEntity) {
		internalUserManagerEJB.saveUser(internalUserEntity);
	}
	
	@Override
	public void updateUser(InternalUserEntity internalUserEntity) {
		internalUserManagerEJB.updateUser(internalUserEntity);
	}
	
	@Override
	public void deleteUser(String userId) {
		internalUserManagerEJB.deleteUser(userId);
	}
	
	@Override
	public Boolean validateUserLogin(String userId, String userPassword) {
		return internalUserManagerEJB.validateUserLogin(userId, userPassword);
	}
	
	
}
