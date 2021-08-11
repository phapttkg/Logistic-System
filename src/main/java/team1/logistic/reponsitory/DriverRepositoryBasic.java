package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team1.logistic.entity.DriverVO;
/**
 * @author The Phap
 */
@Repository
public interface DriverRepositoryBasic extends JpaRepository<DriverVO, Integer> {

}
