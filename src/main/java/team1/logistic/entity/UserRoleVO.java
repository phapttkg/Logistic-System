package team1.logistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author An
 * 
 * */
@Entity
@Table(name = "user_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "USER_ROLE_UK", columnNames = { "staff_id", "role_id" }) })
public class UserRoleVO {

	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false)
	    private Long id;
	 
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "staff_id", nullable = false)
	    private Account account;
	 
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "role_id", nullable = false)
	    private AppRoleVO appRole;
	    
	    public UserRoleVO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserRoleVO(Account account, AppRoleVO appRole) {
			super();
			this.account = account;
			this.appRole = appRole;
		}

		public Long getId() {
	        return id;
	    }
	 
	    public void setId(Long id) {
	        this.id = id;
	    }

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}

		public AppRoleVO getAppRole() {
			return appRole;
		}

		public void setAppRole(AppRoleVO appRole) {
			this.appRole = appRole;
		}

		@Override
		public String toString() {
			return "UserRoleVO [id=" + id + ", account=" + account + ", appRole=" + appRole + "]";
		}
		
		
	    
	
}
