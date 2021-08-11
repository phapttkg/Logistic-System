package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import team1.logistic.entity.AppRoleVO;

/**
 * 
 * @author An
 * 
 * */
public interface AppRoleRepositoryBasic extends JpaRepository <AppRoleVO, Long> {

}
