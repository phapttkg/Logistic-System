package team1.logistic.reponsitory;

import java.util.List;

import team1.logistic.entity.PackageVO;

/**
 * 
 * @author Canh
 * 
 * */

public interface PackageRepositoryCustom {

	public List<PackageVO> getListPackageInHub(String lghub_id);
	public List<PackageVO> getPickupSouthWest(String lghub_id);
	public List<PackageVO> getDeliverySouthWest(String lghub_id);
	public List<PackageVO> getPickupSouthEast(String lghub_id);
	public List<PackageVO> getDeliverySouthEast(String lghub_id);
	public List<PackageVO> getPickupHighLands(String lghub_id);
	public List<PackageVO> getDeliveryHighLands(String lghub_id);
	public List<PackageVO> getPickupSouthCentral(String lghub_id);
	public List<PackageVO> getDeliverySouthCentral(String lghub_id);
	public List<PackageVO> getPickupNorthCentral(String lghub_id);
	public List<PackageVO> getDeliveryNorthCentral(String lghub_id);
	public List<PackageVO> getPickupNorth(String lghub_id);
	public List<PackageVO> getDeliveryNorth(String lghub_id);
	public List<PackageVO> getPickupNorthEast(String lghub_id);
	public List<PackageVO> getDeliveryNorthEast(String lghub_id);
	public List<PackageVO> getPickupNorthWest(String lghub_id);
	public List<PackageVO> getDeliveryNorthWest(String lghub_id);
	public List<PackageVO> getPackageByCurrentDriver(int driver_id);
	
}
