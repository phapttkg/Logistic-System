package team1.logistic.reponsitory;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import team1.logistic.entity.UserRoleVO;

/**
 * 
 * @author An
 * 
 * */
public class AppRoleRepositoryImpl implements AppRoleRepositoryCustom{
    
	public AppRoleRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
    private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> RetrieveRoleNames(int staff_id) {
		String sql = "Select ur.appRole.roleName from " + UserRoleVO.class.getName() + " ur " //
                + " where ur.account.staff_id = :staff_id ";
 
        Query query = this.entityManager.createQuery(sql, String.class);
        query.setParameter("staff_id", staff_id);
        return query.getResultList();
	}
}
	

