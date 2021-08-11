package team1.logistic.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import team1.logistic.entity.LogisticHubVO;
import team1.logistic.entity.PackageVO;
import team1.logistic.entity.RouteVO;
import team1.logistic.entity.ShipRouteVO;
import team1.logistic.entity.ShipperVO;
import team1.logistic.reponsitory.LogisticHubRepository;
import team1.logistic.reponsitory.PackageRepository;
import team1.logistic.reponsitory.RouteRepository;
import team1.logistic.reponsitory.ShipRouteVORepository;
import team1.logistic.reponsitory.ShipperRepository;

/**
 * 
 * @author Canh
 * 
 * */
@Service("hubService")
public class HubService {
	@Autowired
	private ShipperRepository shipperRepo;
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private ShipRouteVORepository shiprouteRepo;
	@Autowired
	private PackageRepository pkgRepo;
	@Autowired
	private LogisticHubRepository hub_repo;
	

	// GET TO DO LIST TODAY IN HUB
	public Object getTodayList(String lghub_id) {
		List<Object> list = new ArrayList<Object>();
		list.add(getImportRouteToday(lghub_id));
		list.add(getExportRouteToday(lghub_id));
		list.add(getShipRouteToday(lghub_id));
		return list;
	}
	
	//# GET IMPORT ROUTE LIST THAT HUB NEED TO WORK TODAY
	private List<RouteVO> getImportRouteToday(String lghub_id) {
		List<RouteVO> allRoute = new ArrayList<RouteVO>();
		List<RouteVO> importRoutesList = new ArrayList<RouteVO>();
		allRoute = routeRepo.findAll();
		for (RouteVO route : allRoute) {
			if ( route.getEnd_pos().equals(lghub_id) && route.getEnd_datetime() == null) {
				boolean check = false;
				Set<PackageVO> pkgList = new HashSet<PackageVO>(route.getPackagelist());
				Iterator<PackageVO> pkgIter = pkgList.iterator();
				while(pkgIter.hasNext()) {
					if (pkgIter.next().getCurrent_hub().equals("-1")) check = true;
				}
				if (check == true) importRoutesList.add(route);
			}
		}
		return importRoutesList;
	}
	
	//# GET SHIP ROUTE LIST THAT HUB NEED TO WORK TODAY
	private List<ShipRouteVO> getShipRouteToday(String lghub_id) {
		List<ShipRouteVO> allShipRoute = new ArrayList<ShipRouteVO>();
		List<ShipRouteVO> shipRouteList = new ArrayList<ShipRouteVO>();
		allShipRoute = shiprouteRepo.findAll();
		for (ShipRouteVO shipRoute: allShipRoute) {
			if (shipRoute.getDate().compareTo(LocalDate.now()) == 0 && checkShipRouteHub(lghub_id, shipRoute)) {
				shipRouteList.add(shipRoute);
			}
		}
		return shipRouteList;
	}
	
	//# CREATE SHIP ROUTE LIST TODAY
	private void createShipRouteToday(String lghub_id) {
		List<PackageVO> pkgList = new ArrayList<PackageVO>();
		if (lghub_id.equals("CanTho")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupSouthWest(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliverySouthWest(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("HoChiMinh")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupSouthEast(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliverySouthEast(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("LamDong")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupHighLands(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliveryHighLands(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("DaNang")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupSouthCentral(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliverySouthCentral(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("NgheAn")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupNorthCentral(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliveryNorthCentral(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("HaNoi")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupNorth(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliveryNorth(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("ThaiNguyen")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupNorthEast(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliveryNorthEast(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		if (lghub_id.equals("YenBai")) {
			List<PackageVO> pickupPkgList = pkgRepo.getPickupNorthWest(lghub_id);
			List<PackageVO> deliveryPkgList = pkgRepo.getDeliveryNorthWest(lghub_id);
			pkgList.addAll(pickupPkgList);
			pkgList.addAll(deliveryPkgList);
		}
		
		List<ShipperVO> shipperList = getShippersInHub(lghub_id);
		int packageAVG;
		packageAVG = pkgList.size() / shipperList.size();
		if (packageAVG<1) packageAVG = 1;
		if (pkgList.size() % shipperList.size() != 0) ++packageAVG;
		for (ShipperVO shipper: shipperList) {
			ShipRouteVO shipRoute = new ShipRouteVO();
			String id = (shipper.getName()+LocalDateTime.now().toString().substring(0, 19)).replace(" ", "").replace("-","").replace(":", "");
			shipRoute.setShip_route_id(id);
			shipRoute.setShipper_id(shipper.getStaff_id());
			shipRoute.setDate(LocalDate.now());
			Set<PackageVO> pkgSet = new HashSet<PackageVO>();
			int pkgCount = 0;
			for (PackageVO pkg: pkgList) {
				if (pkgCount == packageAVG) {
					break;
				}
				String[] checkStatus = pkg.getTracking_status().split("-");
				int index = checkStatus.length - 1;
				if (pkg.getCurrent_shipper() == -1 && !checkStatus[index].contains("deliver") && !checkStatus[index].contains("pick")) {
					pkg.setCurrent_shipper(shipper.getStaff_id());
					StringBuilder updateStatus = new StringBuilder(pkg.getTracking_status());
					if (updateStatus.toString().equals("new") &&  pkg.getCurrent_hub().equals("-1")) {
						updateStatus.append("-picking("+shipper.getStaff_id()+")") ;
					}
					pkg.setTracking_status(updateStatus.toString());
					pkgSet.add(pkg);
					pkgCount++;
				}
			}
			if(!pkgSet.isEmpty()) {
				shipRoute.setPackagelist(pkgSet);
				shiprouteRepo.save(shipRoute);
			}
			
		}
	}
	
	//# CHECK SHIP ROUTE HUB
	private boolean checkShipRouteHub(String lghub_id, ShipRouteVO shipRoute) {	 
		Set<PackageVO> listPkg = new HashSet<PackageVO>();
		listPkg = shipRoute.getPackagelist();
		Iterator<PackageVO> pkgIterator = listPkg.iterator();
		if(pkgIterator.hasNext()) {
			PackageVO pkg = pkgIterator.next();
			String[] checkStatus = pkg.getTracking_status().split("-");
			ShipperVO shipper = shipperRepo.getById(shipRoute.getShipper_id());
			int index = checkStatus.length - 1 ;
			if (shipper.getLghub_id().equals(lghub_id)) {
				return true;
			}
		}
		
		return false;
	}
	
	//# GET EXPORT ROUTE LIST THAT HUB NEED TO WORK TODAY
	private List<RouteVO> getExportRouteToday(String lghub_id) {
			List<RouteVO> allRoute = new ArrayList<RouteVO>();
			List<RouteVO> exportRoutesList = new ArrayList<RouteVO>();
			allRoute = routeRepo.findAll();
			for (RouteVO route : allRoute) {
				if ( route.getPackagelist().size() != 0 && route.getStart_pos().equals( lghub_id ) && LocalDate.now().compareTo(route.getStart_datetime().toLocalDate()) == 0  && route.getEnd_datetime() == null ) {
					if (checkExportedRoute(route))
					{
						exportRoutesList.add(route);
					}
				}
			}
			return exportRoutesList;
		}
	//# CHECK EXPORTED ROUTE
	private boolean checkExportedRoute(RouteVO route) {
		Set<PackageVO> pkgList = new HashSet<PackageVO>(route.getPackagelist());
		Iterator<PackageVO> pkgIterator = pkgList.iterator();
		while(pkgIterator.hasNext()) {
			if (pkgIterator.next().getCurrent_hub().equals("-1")) {
				return false;}
		}
		return true;
	}
	
	//# GET IMPORT LIST TO DO IN HUB
	public Object getImportToday(String lghub_id) {
		List<Object> list = new ArrayList<Object>();
		list.add(getImportRouteToday(lghub_id));
		list.add(getShipRouteToday(lghub_id));
		return list;
	}
	
	//# PUT IMPORT BY ROUTE
	public void putImport(RouteVO route, String lghub_id) {
		route = routeRepo.getById(route.getRoute_id());
		Set<PackageVO> listPkg = new HashSet<PackageVO>();
		listPkg = route.getPackagelist();
		Iterator<PackageVO> pkgIterator = listPkg.iterator();
		while (pkgIterator.hasNext()) {
			String pkg_id = pkgIterator.next().getPkg_id();
			PackageVO pkg_temp = pkgRepo.getById(pkg_id);
			if (!pkg_temp.getCurrent_hub().equals(lghub_id))
			{
				String updateStatus = pkg_temp.getTracking_status();
				updateStatus += "-inhub("+lghub_id+")";
				pkg_temp.setCurrent_hub(lghub_id);
				pkg_temp.setNext_hub("");
				pkg_temp.setTracking_status(updateStatus);
				route.setEnd_datetime(LocalDateTime.now());
				pkgRepo.save(pkg_temp);
			}
		}
		route.setEnd_datetime(LocalDateTime.now());
		routeRepo.save(route);
	}
	
	//# PUT IMPORT BY SHIP ROUTE
	public void putImport(ShipRouteVO shipRoute, String lghub_id) {
		shipRoute = shiprouteRepo.getById(shipRoute.getShip_route_id());
		Set<PackageVO> listPkg = new HashSet<PackageVO>();
		listPkg = shipRoute.getPackagelist();
		Iterator<PackageVO> pkgIterator = listPkg.iterator();
		while (pkgIterator.hasNext()) {
			String pkg_id = pkgIterator.next().getPkg_id();
			PackageVO pkg_temp = pkgRepo.getById(pkg_id);
			String[] checkStatus = pkg_temp.getTracking_status().split("-");
			int index = checkStatus.length - 1;
			if (pkg_temp.getCurrent_hub().equals("-1") && checkStatus[index].contains("picked")) {
				String updateStatus = pkg_temp.getTracking_status();
				updateStatus += "-inhub("+lghub_id+")";
				pkg_temp.setCurrent_hub(lghub_id);
				pkg_temp.setCurrent_shipper(-1);
				pkg_temp.setNext_hub("");
				pkg_temp.setTracking_status(updateStatus);
				shipRoute.setEnd_time(LocalDateTime.now());
				shiprouteRepo.save(shipRoute);
				pkgRepo.save(pkg_temp);
			}
		}
	}
	//# PUT IMPORT BY PACKGE
	public void putImport(PackageVO pkgImport, String lghub_id) {
		pkgImport = pkgRepo.getById(pkgImport.getPkg_id());
		String updateStatus = pkgImport.getTracking_status();
		updateStatus += "-inhub("+lghub_id+")";
		pkgImport.setCurrent_hub(lghub_id);
		pkgImport.setCurrent_shipper(-1);
		pkgImport.setNext_hub("");
		pkgImport.setTracking_status(updateStatus);
		pkgRepo.save(pkgImport);
	}
	
	//# GET EXPORT LIST TO DO IN HUB
	public Object getExportToday(String lghub_id) {
		List<Object> list = new ArrayList<Object>();
		list.add(getExportRouteToday(lghub_id));
		list.add(getShipRouteToday(lghub_id));
		return list;
	}
		
	//# PUT EXPORT BY ROUTE
	public void putExport(RouteVO route) {
		route = routeRepo.getById(route.getRoute_id());
		Set<PackageVO> listPkg = new HashSet<PackageVO>();
		listPkg = route.getPackagelist();
		int driverID = route.getDriver_id();
		Iterator<PackageVO> pkgIterator = listPkg.iterator();
		while (pkgIterator.hasNext()) {
			String pkg_id = pkgIterator.next().getPkg_id();
			PackageVO pkg_temp = pkgRepo.getById(pkg_id);
			String updateStatus = pkg_temp.getTracking_status();
			updateStatus += "-sending("+driverID+")";
			pkg_temp.setCurrent_hub("-1");
			pkg_temp.setCurrent_driver(driverID);
			pkg_temp.setTracking_status(updateStatus);
			pkgRepo.save(pkg_temp);
		}
	}
	
	//# PUT EXPORT BY SHIP ROUTE
	public void putExport(ShipRouteVO shiproute) {
		shiproute = shiprouteRepo.getById(shiproute.getShip_route_id());
		Set<PackageVO> listPkg = new HashSet<PackageVO>();
		listPkg = shiproute.getPackagelist();
		Iterator<PackageVO> pkgIterator = listPkg.iterator();
		while (pkgIterator.hasNext()) {
			String pkg_id = pkgIterator.next().getPkg_id();
			PackageVO pkg_temp = pkgRepo.getById(pkg_id);
			if (pkg_temp.getNext_hub().equals("final")) {
				pkg_temp.setCurrent_hub("-1");
				String updateStatus = pkg_temp.getTracking_status();
				updateStatus += "-sending("+shiproute.getShipper_id()+")";
				pkg_temp.setTracking_status(updateStatus);
				pkgRepo.save(pkg_temp);
			}
		}
	}
	//# PUT EXPORT BY PACKAGE
	public void putExport(PackageVO pkg) {
		pkg = pkgRepo.getById(pkg.getPkg_id());
		String nextHub = pkg.getNext_hub();
		String currentHub = pkg.getCurrent_hub();
		StringBuilder updateStatus = new StringBuilder(pkg.getTracking_status());
		if (nextHub.equals("final"))
			{updateStatus.append("-sending("+pkg.getCurrent_shipper()+")");} 
		else 
			{updateStatus.append("-sending("+pkg.getCurrent_driver()+")");}
		pkg.setCurrent_hub("-1");
		pkg.setTracking_status(updateStatus.toString());
		pkgRepo.save(pkg);
	}
	
	
	//# GET ALL SHIPPERS ARE WORKING IN HUB
	public List<ShipperVO> getShippersInHub(String lghub_id) {
		List<ShipperVO> listShipper = new ArrayList<ShipperVO>();
		List<ShipperVO> shipperInHub = new ArrayList<ShipperVO>();
		
		listShipper = shipperRepo.findAll();
		for(ShipperVO shipper: listShipper) {
			if (shipper.getLghub_id().equals(lghub_id)) {
				shipper.setPasswd("Do you wanna see? Ahihi");
				shipperInHub.add(shipper);
			}
		}
		return shipperInHub;
	}
	
	//# GET ALL PACKAGE IN HUB
	public Object getPackagesInHub(String lghub_id) {
		List<PackageVO> listPkg = new ArrayList<PackageVO>();
		List<PackageVO> listPkgInHub = new ArrayList<PackageVO>();
		
		listPkg = pkgRepo.findAll();
		for(PackageVO pkg: listPkg) {
			if (pkg.getCurrent_hub().equals(lghub_id)) {
				listPkgInHub.add(pkg);
			}
		}
		return listPkgInHub;
	}
	//# GET ROUTE, SHIP ROUTE IN HUB
	public Object getHistoryInHub(String lghub_id, LocalDate date) {
		List<ShipRouteVO> listShipRoute = new ArrayList<ShipRouteVO>();
		List<ShipRouteVO> shipRouteListInDay = new ArrayList<ShipRouteVO>();
		listShipRoute = shiprouteRepo.findAll();
		for (ShipRouteVO s: listShipRoute) {
			if (checkShipRouteHub(lghub_id, s) && s.getDate().compareTo(date) == 0) {
				shipRouteListInDay.add(s);
			}
		}
		List<RouteVO> listRoute = new ArrayList<RouteVO>();
		List<RouteVO> routeListInSpecificDay = new ArrayList<RouteVO>();
		listRoute = routeRepo.findAll();
		for (RouteVO r: listRoute) {
			if (r.getEnd_datetime() != null) {
				if (r.getEnd_pos().equals(lghub_id) && r.getEnd_datetime().toLocalDate().compareTo(date) == 0) {
					routeListInSpecificDay.add(r);
				}
			}
			if (r.getStart_pos().equals(lghub_id) && r.getStart_datetime().toLocalDate().compareTo(date) == 0) {
				routeListInSpecificDay.add(r);
			}
		}
		List<Object> resultList = new ArrayList<Object>();
		resultList.add(shipRouteListInDay);
		resultList.add(routeListInSpecificDay);
		return resultList;
	}
	
	//# PREPARE ALL SHIP ROUTE
	@Scheduled(fixedRate = 300000, initialDelay = 2000) // 5 minutes
	public void prepareAllShipRoute () {		
		System.out.println("run ship route");
		List<LogisticHubVO> hub_list = new ArrayList<LogisticHubVO>();
		hub_list = hub_repo.findAll();
		for (LogisticHubVO lghub: hub_list) {
			if(!lghub.getLghub_id().equals("empty")){
				createShipRouteToday(lghub.getLghub_id());
			}
			
		}
	}
}
