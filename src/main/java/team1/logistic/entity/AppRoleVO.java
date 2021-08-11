package team1.logistic.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 
 * @author An
 * 
 * */
@Entity
@Table(name = "app_role", //
        uniqueConstraints = { //
                @UniqueConstraint(name = "APP_ROLE_UK", columnNames = "role_name") })
public class AppRoleVO {

    @Id
    @GeneratedValue
    @Column(name = "Role_Id", nullable = false)
    private Long roleId;
 
    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;
        
    public AppRoleVO() {
		super();
	}
    
    public AppRoleVO(Long roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}
    
	public Long getRoleId() {
        return roleId;
    }
 
	public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
 
    public String getRoleName() {
        return roleName;
    }
 
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
     
}