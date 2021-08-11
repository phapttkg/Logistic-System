package team1.logistic.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Canh
 * 
 * */
@Entity
@Table(name="logistic_hub")
public class LogisticHubVO {
	@Id
	private String lghub_id;
	
	@Column(name="lghub_phone")
	private String lghub_phone;

	public String getLghub_id() {
		return lghub_id;
	}

	public void setLghub_id(String lghub_id) {
		this.lghub_id = lghub_id;
	}

	public String getLghub_phone() {
		return lghub_phone;
	}

	public void setLghub_phone(String lghub_phone) {
		this.lghub_phone = lghub_phone;
	}	
	
}
