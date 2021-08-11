package team1.logistic.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
@Table(name = "route")
public class RouteVO {
	
	public RouteVO() {
		super();
	}

	public RouteVO(String route_id, int driver_id, String start_pos, String end_pos, LocalDateTime start_datetime,
			LocalDateTime end_datetime) {
		super();
		this.route_id = route_id;
		this.driver_id = driver_id;
		this.start_pos = start_pos;
		this.end_pos = end_pos;
		this.start_datetime = start_datetime;
		this.end_datetime = end_datetime;
	
	}
	
	@Id
	private String route_id;
	
	@Column(name = "driver_id")
	private int driver_id;

	@Column(name = "start_pos")
	private String start_pos;
	
	@Column(name = "end_pos")
	private String end_pos;
	
	@Column(name = "start_datetime")
	private LocalDateTime start_datetime;
	
	@Column(name = "end_datetime")
	private LocalDateTime end_datetime;
	
	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public int getDriver_id() {
		return driver_id;
	}


	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public String getStart_pos() {
		return start_pos;
	}

	public void setStart_pos(String start_pos) {
		this.start_pos = start_pos;
	}


	public String getEnd_pos() {
		return end_pos;
	}

	public void setEnd_pos(String end_pos) {
		this.end_pos = end_pos;
	}


	public LocalDateTime getStart_datetime() {
		return start_datetime;
	}

	public void setStart_datetime(LocalDateTime start_datetime) {
		this.start_datetime = start_datetime;
	}

	public LocalDateTime getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(LocalDateTime end_datetime) {
		this.end_datetime = end_datetime;
	}

	@OneToMany(cascade = { CascadeType.ALL })
	@JoinTable(name = "route_detail", joinColumns = { @JoinColumn(name = "route_id") }, inverseJoinColumns = {
			@JoinColumn(name = "pkg_id") })
    private Set<PackageVO> packagelist = new HashSet<PackageVO>();

	public Set<PackageVO> getPackagelist() {
		return packagelist;
	}

	public void setPackagelist(Set<PackageVO> packagelist) {
		this.packagelist = packagelist;
	}

	@Override
	public String toString() {
		return "RouteVO [route_id=" + route_id + ", driver_id=" + driver_id + ", start_pos=" + start_pos + ", end_pos="
				+ end_pos + ", start_datetime=" + start_datetime + ", end_datetime=" + end_datetime + ", packagelist="
				+ packagelist + "]";
	}	
	
	
}
