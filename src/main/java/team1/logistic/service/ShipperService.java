package team1.logistic.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.logistic.entity.PackageVO;
import team1.logistic.entity.ShipRouteVO;
import team1.logistic.reponsitory.PackageRepository;
import team1.logistic.reponsitory.ShipRouteVORepository;


/**
 * @author Huan
 *
 */
@Service("shipperService")
public class ShipperService {

	@Autowired()
	private PackageRepository packageRepo;
	@Autowired
	private ShipRouteVORepository shipRouteRepo;
	
	// PackageToday Tra ve PackageVO
	public Set<PackageVO> getPackageToday(int shipper_id) {
		Set<PackageVO> result = new HashSet<PackageVO>();
		
		for (ShipRouteVO route : shipRouteRepo.findAll()) {
			if ((route.getDate().getDayOfMonth() == LocalDate.now().getDayOfMonth())
					&& (route.getShipper_id() == shipper_id)) {
				result.addAll(route.getPackagelist());
			}
		}
		return result;
	}

	// Show packagelist history
	public Set<ShipRouteVO> getPackageHistory(int shipper_id) {
		Set<ShipRouteVO> result = new HashSet<ShipRouteVO>();
		Set<PackageVO> pkgId = new HashSet<PackageVO>();
		for (ShipRouteVO route : shipRouteRepo.findAll()) {
			if ((route.getDate().getMonth() == LocalDate.now().getMonth()) 
					&& (route.getShipper_id() == shipper_id)
				&& (pkgId.addAll(route.getPackagelist()))
					) {
				result.add(route);
			}
		}
		return result;

	}

//Show set ShipRoute by shipper id
	public Set<ShipRouteVO> getShipRouteList(int shipper_id) {
		Set<ShipRouteVO> result = new HashSet<ShipRouteVO>();
		for (ShipRouteVO route : shipRouteRepo.findAll()) {
			if (route.getShipper_id() == shipper_id) {

				result.add(route);
			}
		}
		return result;
	}
	
	// pick package
	public Boolean pickPackageById(String pkg_id, int shipper_id) {
		try {
			
			PackageVO pkg = packageRepo.getById(pkg_id);			
			
			System.out.println(pkg.getTracking_status());
			if(!pkg.getTracking_status().contains("picked")) {
				pkg.setTracking_status(pkg.getTracking_status()+"-picked("+shipper_id+")");
				pkg.setPick_time(LocalDateTime.now());
				pkg.setCurrent_shipper(shipper_id);
				packageRepo.saveAndFlush(pkg);
				
				}
			
			
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// drop package
	public Boolean dropPackageById(String pkg_id, int shipper_id) {
		try {
			PackageVO pkg = packageRepo.getById(pkg_id);
			if(!pkg.getTracking_status().contains("delivered")) {
			pkg.setTracking_status(pkg.getTracking_status()+"-delivered("+shipper_id+")");
			pkg.setDrop_time(LocalDateTime.now());
			pkg.setCurrent_shipper(-1);
			packageRepo.saveAndFlush(pkg);
			Boolean kq = true;
			for (ShipRouteVO route : shipRouteRepo.findAll())
			{
				for(PackageVO pack : route.getPackagelist())
				{
					if (!pack.getTracking_status().contains("delivered"))
					{
						kq= false;
					}
					
				}
				if(kq) {
					route.setEnd_time(LocalDateTime.now());
					shipRouteRepo.saveAndFlush(route);
				}
			}
			
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	//import
	public Boolean importPackageById(String pkg_id, int shipper_id) {
		try {
			PackageVO pkg = packageRepo.getById(pkg_id);
			
				pkg.setCurrent_shipper(shipper_id);
				StringBuilder updateStatus = new StringBuilder(pkg.getTracking_status());
				updateStatus.append("-delivering("+pkg.getCurrent_shipper()+")");
				pkg.setTracking_status(updateStatus.toString());
			
			packageRepo.saveAndFlush(pkg);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	//export	
	public Boolean exportPackageById(String pkg_id, int shipper_id) {
		try {
			PackageVO pkg = packageRepo.getById(pkg_id);
				pkg.setCurrent_shipper(-1);	
				packageRepo.saveAndFlush(pkg);
				Boolean kq = true;
				for (ShipRouteVO route : shipRouteRepo.findAll())
				{
					for(PackageVO pack : route.getPackagelist())
					{
						if (!pack.getTracking_status().contains("picked") && pack.getCurrent_shipper() != -1)
						{
							kq= false;
						}
						
					}
					if(kq) {
						route.setEnd_time(LocalDateTime.now());
						shipRouteRepo.saveAndFlush(route);
					}
				}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
//	private ShipRouteVO prepareshiproute (String input) {
//		ShipRouteVO shiproute = new ShipRouteVO();
//		JSONParser parser = new JSONParser();
//		JSONObject json = null;
//		try {
//			json = (JSONObject) parser.parse(input);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if (json.containsKey("ship_route_id"))
//		{
//			shiproute.setShip_route_id(json.get("ship_route_id").toString());
//		}
//		if (json.containsKey("shipper_id"))
//		{
//			shiproute.setShipper_id(Integer.valueOf(json.get("shipper_id").toString()));
//		}
//		if (json.containsKey("date"))
//		{
//			shiproute.setDate(LocalDate.parse(json.get("date").toString()));
//		}
//		return shiproute;
//	}
	
	public Boolean cancelpackage(String pkg_id, int shipper_id) {

		try {
		PackageVO pkg = packageRepo.getById(pkg_id);
		if(!pkg.getTracking_status().contains("cancel")) {
		pkg.setNext_hub("cancel");
		pkg.setTracking_status(pkg.getTracking_status()+"-cancel("+shipper_id+")");
		pkg.setCurrent_shipper(0);
		packageRepo.save(pkg);
		}
		} catch (Exception e){
			return false;
		}
		return true;
	}

	public Boolean returnpackage(String pkg_id, int shipper_id) {
		try {
			PackageVO pack = packageRepo.getById(pkg_id);
		
			if(!pack.getTracking_status().contains("return")) {
			PackageVO pkg = packageRepo.getById(pkg_id);
			pkg.setNext_hub("final");
			pkg.setDrop_time(LocalDateTime.now());
			pkg.setTracking_status(pkg.getTracking_status()+"-return("+shipper_id+")");
			pkg.setCurrent_shipper(0);
			packageRepo.save(pkg);
			PackageVO newpkg = (PackageVO)pkg.clone();
			newpkg.setPkg_id(pkg.getPkg_id()+"RE"+shipper_id);
			newpkg.setCreated_datetime(LocalDateTime.now());
			newpkg.setTracking_status("new");
			newpkg.setIsreturn(true);
			newpkg.setDelivery_fee(pkg.getDelivery_fee()*2);
			newpkg.setPick_time(null);
			newpkg.setDrop_time(null);
			newpkg.setNext_hub(null);
			newpkg.setCurrent_shipper(-1);
			newpkg.setSender_name(pkg.getReceiver_name());
			newpkg.setSender_address(pkg.getReceiver_address());
			newpkg.setSender_phone_num(pkg.getReceiver_phone_num());
			newpkg.setReceiver_name(pkg.getSender_name());
			newpkg.setReceiver_address(pkg.getSender_address());
			newpkg.setReceiver_phone_num(pkg.getSender_phone_num());
			packageRepo.save(newpkg);
			Boolean kq = true;
			for (ShipRouteVO route : shipRouteRepo.findAll())
			{
				for(PackageVO pack2 : route.getPackagelist())
				{
					if (!pack2.getTracking_status().contains("return") && pack2.getCurrent_shipper() != 0)
					{
						kq= false;
					}
					
				}
				if(kq) {
					route.setEnd_time(LocalDateTime.now());
					shipRouteRepo.saveAndFlush(route);
				}
			}
			}
			} catch (Exception e){
				return false;
			}
			return true;
	}
}
