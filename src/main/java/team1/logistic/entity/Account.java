package team1.logistic.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 
 * @author An
 * 
 * */

@Entity
@Table (name = "account")
@Inheritance(strategy = InheritanceType.JOINED)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staff_id;
	@Column(name="passwd")
	private String passwd;	
	@Column(name="name")
	private String name;
	@Column(name="phone_num")
	private String phone_num;
	@Column(name="role")
	private String role;
	

	
    @Column(name = "enabled", length = 1, nullable = false)
    private boolean enabled;
		
	public Account() {
		super();

	}
	
	

	public Account(int staff_id, String passwd, String name, String phone_num, String role) {
		super();
		this.staff_id = staff_id;
		this.passwd = passwd;
		this.name = name;
		this.phone_num = phone_num;
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}



	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}



	@Override
	public String toString() {
		return "Account [staff_id=" + staff_id + ", name=" + name + ", phone_num=" + phone_num
				+ ", role=" + role + "]";
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
