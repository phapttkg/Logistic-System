package team1.logistic.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import team1.logistic.entity.Account;

/**
 * 
 * @author An
 * 
 * */
public interface AccountRepositoryBasic extends JpaRepository<Account, Integer> {}
