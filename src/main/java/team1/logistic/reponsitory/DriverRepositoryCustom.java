package team1.logistic.reponsitory;

import org.springframework.stereotype.Repository;

/**
 * @author The Phap
 */

@Repository
public interface DriverRepositoryCustom  {
	public int getDriverByCurrentHub(String hub);

}
