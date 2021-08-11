package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team1.logistic.entity.LogisticHubVO;

/**
 * 
 * @author Canh
 * 
 * */

@Repository
public interface LogisticHubRepository extends JpaRepository<LogisticHubVO, String>{
	
}
