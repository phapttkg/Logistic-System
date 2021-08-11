package team1.logistic.reponsitory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import team1.logistic.entity.Account;

/**
 * 
 * @author An
 * 
 * */
public class AccountRepositoryImpl  implements AccountRepositoryCustom{
	
	public AccountRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired(required = true)
	private EntityManager entityManager;

	@Override
	public Account findAccount(String name) {
		  try {
	            String sql = "Select e from " + Account.class.getName() + " e " //
	                    + " Where e.name = :name ";
	 
	            Query query = entityManager.createQuery(sql, Account.class);
	            query.setParameter("name", name);
	 
	            return (Account) query.getSingleResult();
	        } catch (NoResultException e) {
	            return null;
	      }
	}
}
	
	
	

