package core.authentication.services;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import core.authentication.dao.InternalUserDAO;
import core.authentication.entities.InternalUserEntity;
import core.authentication.transform.EntityTransform;

@Singleton
@LocalBean
@ConcurrencyManagement
public class InternalUserManager implements InternalUserManagerLocal {
	
	@PersistenceContext(name = "TSE")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public InternalUserManager() {

    }
    
	public InternalUserEntity getUser(String userId) {
		InternalUserDAO user = em.find(InternalUserDAO.class, userId);
		return EntityTransform.transformEntity(user);
	}
	
	@Lock(LockType.WRITE)
	public void saveUser(InternalUserEntity internalUserEntity) {
		InternalUserDAO user = EntityTransform.transformEntity(internalUserEntity);
		em.persist(user);
	}
    
	@Lock(LockType.WRITE)
	public void updateUser(InternalUserEntity internalUserEntity) {
		InternalUserDAO user = em.find(InternalUserDAO.class, internalUserEntity.getUserId());
		user.setUserName(internalUserEntity.getUserName());
		user.setUserPassword(internalUserEntity.getUserPassword());
		em.merge(user);
	}
	
	@Lock(LockType.WRITE)
	public void deleteUser(String userId) {
		String stringQuery = "DELETE FROM InternalUserDAO u WHERE u.userId = '" + userId + "'" ;
		Query query = em.createQuery(stringQuery);
		query.executeUpdate();
	}

	public Boolean validateUserLogin(String userId, String userPassword) {
		InternalUserDAO user = em.find(InternalUserDAO.class, userId);
		return (user != null && user.getUserPassword().equals(userPassword)) ? true : false;
	}
}
