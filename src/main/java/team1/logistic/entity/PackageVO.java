package team1.logistic.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 
 * @author An
 * 
 * */

@Entity
@Table(name = "package")
public class PackageVO implements Cloneable{

	@Id
	private String pkg_id;

	@Column(name = "created_datetime")
	private LocalDateTime created_datetime;

	@Column(name = "size")
	private String size;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "tracking_status")
	private String tracking_status;

	@Column(name = "isreturn")
	private Boolean isreturn;

	@Column(name = "delivery_fee")
	private int delivery_fee;

	@Column(name = "cod_value")
	private int cod_value;

	@Column(name = "receiver_name")
	private String receiver_name;

	@Column(name = "receiver_phone_num")
	private String receiver_phone_num;

	@Column(name = "receiver_address")
	private String receiver_address;

	@Column(name = "sender_name")
	private String sender_name;

	@Column(name = "sender_phone_num")
	private String sender_phone_num;

	@Column(name = "sender_address")
	private String sender_address;

	@Column(name = "current_hub")
	private String current_hub;

	@Column(name = "current_driver")
	private int current_driver;

	@Column(name = "current_shipper")
	private int current_shipper;
	
	@Column(name = "next_hub")
	private String next_hub;
	
	@Column(name = "pick_time")
	private LocalDateTime pick_time;
	
	@Column(name = "drop_time")
	private LocalDateTime drop_time;
	
	public PackageVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PackageVO(String pkg_id, LocalDateTime created_datetime, String size, Double weight, String tracking_status,
			Boolean isreturn, int delivery_fee, int cod_value, String receiver_name, String receiver_phone_num,
			String receiver_address, String sender_name, String sender_phone_num, String sender_address,
			String current_hub, int current_driver, int current_shipper) {
		super();
		this.pkg_id = pkg_id;
		this.created_datetime = created_datetime;
		this.size = size;
		this.weight = weight;
		this.tracking_status = tracking_status;
		this.isreturn = isreturn;
		this.delivery_fee = delivery_fee;
		this.cod_value = cod_value;
		this.receiver_name = receiver_name;
		this.receiver_phone_num = receiver_phone_num;
		this.receiver_address = receiver_address;
		this.sender_name = sender_name;
		this.sender_phone_num = sender_phone_num;
		this.sender_address = sender_address;
		this.current_hub = current_hub;
		this.current_driver = current_driver;
		this.current_shipper = current_shipper;
	}

	public String getPkg_id() {
		return pkg_id;
	}

	public void setPkg_id(String pkg_id) {
		this.pkg_id = pkg_id;
	}

	public LocalDateTime getCreated_datetime() {
		return created_datetime;
	}

	public void setCreated_datetime(LocalDateTime created_datetime) {
		this.created_datetime = created_datetime;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getTracking_status() {
		return tracking_status;
	}

	public void setTracking_status(String tracking_status) {
		this.tracking_status = tracking_status;
	}

	public Boolean getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(Boolean isreturn) {
		this.isreturn = isreturn;
	}

	public int getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(int delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public int getCod_value() {
		return cod_value;
	}

	public void setCod_value(int cod_value) {
		this.cod_value = cod_value;
	}

	public String getReceiver_name() {
		return receiver_name;
	}

	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}

	public String getReceiver_phone_num() {
		return receiver_phone_num;
	}

	public void setReceiver_phone_num(String receiver_phone_num) {
		this.receiver_phone_num = receiver_phone_num;
	}

	public String getReceiver_address() {
		return receiver_address;
	}

	public void setReceiver_address(String receiver_address) {
		this.receiver_address = receiver_address;
	}

	public String getSender_name() {
		return sender_name;
	}

	public void setSender_name(String sender_name) {
		this.sender_name = sender_name;
	}

	public String getSender_phone_num() {
		return sender_phone_num;
	}

	public void setSender_phone_num(String sender_phone_num) {
		this.sender_phone_num = sender_phone_num;
	}

	public String getSender_address() {
		return sender_address;
	}

	public void setSender_address(String sender_address) {
		this.sender_address = sender_address;
	}

	public String getCurrent_hub() {
		return current_hub;
	}

	public void setCurrent_hub(String current_hub) {
		this.current_hub = current_hub;
	}

	public int getCurrent_driver() {
		return current_driver;
	}

	public void setCurrent_driver(int current_driver) {
		this.current_driver = current_driver;
	}

	public int getCurrent_shipper() {
		return current_shipper;
	}

	public void setCurrent_shipper(int current_shipper) {
		this.current_shipper = current_shipper;
	}
	
	public String getNext_hub() {
		return next_hub;
	}

	public void setNext_hub(String next_hub) {
		this.next_hub = next_hub;
	}

	public LocalDateTime getPick_time() {
		return pick_time;
	}

	public void setPick_time(LocalDateTime pick_time) {
		this.pick_time = pick_time;
	}

	public LocalDateTime getDrop_time() {
		return drop_time;
	}

	public void setDrop_time(LocalDateTime drop_time) {
		this.drop_time = drop_time;
	}

	public Set<ShipRouteVO> getShiproutes() {
		return shiproutes;
	}

	public void setShiproutes(Set<ShipRouteVO> shiproutes) {
		this.shiproutes = shiproutes;
	}
	
	@Override
	public String toString() {
		return "PackageVO [pkg_id=" + pkg_id + ", created_datetime=" + created_datetime + ", size=" + size + ", weight="
				+ weight + ", tracking_status=" + tracking_status + ", isreturn=" + isreturn + ", delivery_fee="
				+ delivery_fee + ", cod_value=" + cod_value + ", receiver_name=" + receiver_name
				+ ", receiver_phone_num=" + receiver_phone_num + ", receiver_address=" + receiver_address
				+ ", sender_name=" + sender_name + ", sender_phone_num=" + sender_phone_num + ", sender_address="
				+ sender_address + ", current_hub=" + current_hub + ", current_driver=" + current_driver
				+ ", current_shipper=" + current_shipper + ", next_hub=" + next_hub + ", pick_time=" + pick_time
				+ ", drop_time=" + drop_time + "]";
	}

	@OneToMany(mappedBy = "packagelist")
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="pkg_id")
	@JsonBackReference(value="route")
	Set<RouteVO> routes = new HashSet<>();

	public Set<RouteVO> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<RouteVO> routes) {
		this.routes = routes;
	}
	
	@OneToMany(mappedBy = "packagelist")
	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "pkg_id")
	@JsonBackReference(value="ship_route")
	Set<ShipRouteVO> shiproutes = new HashSet<>();
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
