package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team1.logistic.entity.ShipRouteVO;

@Repository
public interface ShipRouteVORepository extends JpaRepository<ShipRouteVO, String> {

}
