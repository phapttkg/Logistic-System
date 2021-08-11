package team1.logistic.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "driver" )
@PrimaryKeyJoinColumn(name = "driver_id")
public class DriverVO extends Account{

	private String current_hub;

	
	public DriverVO() {
		super();
	}
	
	public DriverVO(String current_hub) {
		super();
		this.current_hub = current_hub;
		
	}
	
	public String getCurrent_hub() {
		return current_hub;
	}
	public void setCurrent_hub(String current_hub) {
		this.current_hub = current_hub;
	}

	@Override
	public String toString() {
		return "DriverVO [current_hub=" + current_hub + "]";
	}
	
	
}
