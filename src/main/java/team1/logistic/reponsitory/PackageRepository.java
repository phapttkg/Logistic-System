package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team1.logistic.entity.PackageVO;

@Repository
public interface PackageRepository extends JpaRepository<PackageVO, String>, PackageRepositoryCustom {

}
