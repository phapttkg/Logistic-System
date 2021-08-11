package team1.logistic.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @author Huan
 *
 */
@Entity
@Table(name = "shipper")
@PrimaryKeyJoinColumn(name = "shipper_id")
public class ShipperVO extends Account{
	
	private String lghub_id;

	public String getLghub_id() {
		return lghub_id;
	}
	public void setLghub_id(String lghub_id) {
		this.lghub_id = lghub_id;
	}
	public ShipperVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ShipperVO( String lghub_id) {
		super();
		this.lghub_id = lghub_id;
	}


}
