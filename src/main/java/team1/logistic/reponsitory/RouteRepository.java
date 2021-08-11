package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team1.logistic.entity.RouteVO;
/**
 * @author The Phap
 */
@Repository
public interface RouteRepository extends JpaRepository<RouteVO, String> {

}
