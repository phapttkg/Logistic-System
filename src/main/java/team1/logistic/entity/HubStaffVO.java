package team1.logistic.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="hub_staff")
@PrimaryKeyJoinColumn(name = "hub_staff_id")
public class HubStaffVO extends Account{
	
	private String lghub_id;
	
	public HubStaffVO() {
		super();
	}
	
	public HubStaffVO(String lghub_id) {
		super();
		this.lghub_id = lghub_id;
	}

	public String getLghub_id() {
		return lghub_id;
	}

	public void setLghub_id(String lghub_id) {
		this.lghub_id = lghub_id;
	}

}
