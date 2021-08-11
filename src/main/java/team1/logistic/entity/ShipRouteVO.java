package team1.logistic.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "ship_route")
public class ShipRouteVO {

	@Id
	@Column(name = "ship_route_id")
	private String ship_route_id;

	@Column(name = "shipper_id")
	private int shipper_id;

	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "end_time")
	private LocalDateTime end_time;

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public ShipRouteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShipRouteVO(String ship_route_id, int shipper_id, LocalDate date) {
		super();
		this.ship_route_id = ship_route_id;
		this.shipper_id = shipper_id;
		this.date = date;
	}

	public String getShip_route_id() {
		return ship_route_id;
	}

	public int getShipper_id() {
		return shipper_id;
	}

	public void setShipper_id(int shipper_id) {
		this.shipper_id = shipper_id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setShip_route_id(String ship_route_id) {
		this.ship_route_id = ship_route_id;
	}

	//@JsonManagedReference(value="ship_route")
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "ship_route_detail", joinColumns = { @JoinColumn(name = "ship_route_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pkg_id") })
	private Set<PackageVO> packagelist = new HashSet<PackageVO>();

	public Set<PackageVO> getPackagelist() {
		return packagelist;
	}

	public void setPackagelist(Set<PackageVO> packagelist) {
		this.packagelist = packagelist;
	}
}
