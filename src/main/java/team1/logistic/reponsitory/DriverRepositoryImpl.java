package team1.logistic.reponsitory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import team1.logistic.entity.DriverVO;

/**
 * @author The Phap
 */
public class DriverRepositoryImpl implements DriverRepositoryCustom {

	public DriverRepositoryImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public int getDriverByCurrentHub(String lghub_id) {
		String hql = "SELECT driver FROM DriverVO driver WHERE driver.current_hub = :current_hub";
		Query query = entityManager.createQuery(hql);
		query.setParameter("current_hub", lghub_id);
		List<DriverVO> drivers = query.getResultList();
		if(drivers.isEmpty()) {
			return 0;
		}
		return drivers.get(0).getStaff_id();
	}
	
	

}
