package team1.logistic.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import team1.logistic.entity.DriverVO;
import team1.logistic.entity.PackageVO;
import team1.logistic.entity.RouteVO;
import team1.logistic.reponsitory.DriverRepository;
import team1.logistic.reponsitory.PackageRepository;
import team1.logistic.reponsitory.RouteRepository;

/**
 * @author The Phap
 */

@Service("dividerService")
public class DividerService  {
	// DEFINE_LOCATION_PROVINCE
	enum northWest {
		LAOCAI, YENBAI, DIENBIEN, HOABINH, LAICHAU, SONLA
	};

	enum northEast {
		HAGIANG, CAOBANG, BACKAN, LANGSON, TUYENQUANG, THAINGUYEN, PHUTHO, BACGIANG, QUANGNINH
	};

	enum north {
		BACNINH, HANAM, HANOI, HAIDUONG, HAIPHONG, HUNGYEN, NAMDINH, NINHBINH, THAIBINH, VINHPHUC
	};

	enum northCentral {
		THANHHOA, NGHEAN, HATINH, QUANGBINH, QUANGTRI, THUATHIENHUE
	};

	enum southCentral {
		DANANG, QUANGNAM, QUANGNGAI, BINHDINH, PHUYEN, KHANHHOA, NINHTHUAN, BINHTHUAN
	};

	enum highLands {
		KONTUM, GIALAI, DAKLAK, DAKNONG, LAMDONG
	};

	enum southEast {
		HOCHIMINH, BARIAVUNGTAU, BINHDUONG, BINHPHUOC, DONGNAI, TAYNINH
	};

	enum southWest {
		ANGIANG, BACLIEU, BENTRE, CAMAU, CANTHO, DONGTHAP, HAUGIANG, KIENGIANG, LONGAN, SOCTRANG, TIENGIANG, TRAVINH,
		VINHLONG

	};

	private static Map<String, Integer> location = new HashMap<String, Integer>();
	private static List<String> idHub = new ArrayList<String>();

	@Autowired
	private DriverRepository driverRepo;
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private PackageRepository pkgRepo;

	public DividerService() {
		super();
		idHub.add("YenBai");
		idHub.add("ThaiNguyen");
		idHub.add("HaNoi");
		idHub.add("NgheAn");
		idHub.add("DaNang");
		idHub.add("LamDong");
		idHub.add("HoChiMinh");
		idHub.add("CanTho");

	}

	// GET_ALL_DRIVER
	public List<DriverVO> getDriverList() {
		return driverRepo.findAll();
	}


	// CHANGE_DRIVER_WORKING
		public Object changeDriver(String input) throws Exception {
				RouteVO vo = prepareRouteVO(input);
				int newDriverId = vo.getDriver_id();
				//Get route
				RouteVO route = routeRepo.getById(vo.getRoute_id());
				//Get List Package
				List<PackageVO> packageVOs = pkgRepo.getPackageByCurrentDriver(route.getDriver_id());
				for(PackageVO pkg : packageVOs ) {
					pkg.setCurrent_driver(newDriverId);
					pkgRepo.save(pkg);
				}
				//Get Driver in Route
				DriverVO driverVO = driverRepo.getById(route.getDriver_id());
				driverVO.setCurrent_hub(route.getStart_pos());
				driverRepo.save(driverVO);
				//Get Driver new
				DriverVO driver = driverRepo.getById(newDriverId);
				driver.setCurrent_hub("empty");
				driverRepo.save(driver);
				route.setDriver_id(newDriverId);
				routeRepo.save(route);		
				return true;
		}

	// GET_ROUTE_TODAY
	public List<RouteVO> getRouteToday() {
		List<RouteVO> routeVOList = new ArrayList<RouteVO>();
		for (RouteVO routeVO : routeRepo.findAll()) {
			if (routeVO.getStart_datetime().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
					&& routeVO.getStart_datetime().getMonth() == LocalDateTime.now().getMonth()
					&& routeVO.getStart_datetime().getYear() == LocalDateTime.now().getYear()) {
				routeVOList.add(routeVO);
			}
		}
		return routeVOList;
	}

	// PREPARE_ROUTE_HUB
	public void prepareRoute(String lghub_id) {
		List<PackageVO> listVO = pkgRepo.getListPackageInHub(lghub_id);
		List<PackageVO> pkgNS = new ArrayList<PackageVO>();
		List<PackageVO> pkgSN = new ArrayList<PackageVO>();
		String time = LocalDateTime.now().toString().substring(0, 19).replace(" ", "").replace("-","").replace(":", "");
		for (PackageVO pkgVO : listVO) {
			if (currentLocation(pkgVO) > endLocation(pkgVO) && pkgVO.getCurrent_driver() == -1 && pkgVO.getCurrent_shipper() == -1) {
				pkgSN.add(pkgVO);
			} else if (currentLocation(pkgVO) < endLocation(pkgVO) && pkgVO.getCurrent_driver() == -1 && pkgVO.getCurrent_shipper() == -1) {
				pkgNS.add(pkgVO);
			}
		}
		if (lghub_id.equalsIgnoreCase("CanTho")) {

			int driverId = driverRepo.getDriverByCurrentHub(lghub_id);
			if (driverId != 0 && pkgSN.size()>0) {
				RouteVO routeVO = new RouteVO();
				DriverVO driverVO = driverRepo.getById(driverId);
				driverVO.setCurrent_hub("empty");
				driverRepo.save(driverVO);
				routeVO.setRoute_id(
						lghub_id + "-" + idHub.get(idHub.indexOf(lghub_id) - 1) + "-" + time );
				routeVO.setDriver_id(driverId);
				routeVO.setStart_pos(lghub_id);
				routeVO.setEnd_pos(idHub.get(idHub.indexOf(lghub_id) - 1));
				routeVO.setStart_datetime(LocalDateTime.now());
				Set<PackageVO> pkgSet = new HashSet<PackageVO>();
				for (PackageVO pkg : pkgSN) {
					pkgSet.add(pkg);
					pkg.setCurrent_driver(driverId);
					pkgRepo.save(pkg);
				}
				routeVO.setPackagelist(pkgSet);
				routeRepo.save(routeVO);
			}

		} else if (lghub_id.equalsIgnoreCase("YenBai")) {

			int driverId = driverRepo.getDriverByCurrentHub(lghub_id);
			if (driverId != 0 && pkgNS.size()>0) {
				RouteVO routeVO = new RouteVO();
				
				routeVO.setRoute_id(
						lghub_id + "-" + idHub.get(idHub.indexOf(lghub_id) + 1) + "-"  + time);
				routeVO.setDriver_id(driverId);
				DriverVO driverVO = driverRepo.getById(driverId);
				driverVO.setCurrent_hub("empty");
				System.out.println(driverVO);
				driverRepo.save(driverVO);
				routeVO.setStart_pos(lghub_id);
				routeVO.setEnd_pos(idHub.get(idHub.indexOf(lghub_id) + 1));
				routeVO.setStart_datetime(LocalDateTime.now());
				Set<PackageVO> pkgSet = new HashSet<PackageVO>();
				for (PackageVO pkg : pkgNS) {
					pkgSet.add(pkg);
					pkg.setCurrent_driver(driverId);
					pkgRepo.save(pkg);
				}
				routeVO.setPackagelist(pkgSet);
				routeRepo.save(routeVO);
			}

		} else {

			int driverId = driverRepo.getDriverByCurrentHub(lghub_id);
			if (driverId != 0 && pkgSN.size()>0) {
				RouteVO routeSN = new RouteVO();
				DriverVO driverVO = driverRepo.getById(driverId);
				driverVO.setCurrent_hub("empty");
				driverRepo.save(driverVO);
				routeSN.setRoute_id(
						lghub_id + "-" + idHub.get(idHub.indexOf(lghub_id) - 1) + "-"  + time);
				routeSN.setDriver_id(driverId);
				routeSN.setStart_pos(lghub_id);
				routeSN.setEnd_pos(idHub.get(idHub.indexOf(lghub_id) - 1));
				routeSN.setStart_datetime(LocalDateTime.now());
				Set<PackageVO> pkgSetSN = new HashSet<PackageVO>();
				for (PackageVO pkg : pkgSN) {
					pkgSetSN.add(pkg);
					pkg.setCurrent_driver(driverId);
					pkgRepo.save(pkg);
				}
				routeSN.setPackagelist(pkgSetSN);
				routeRepo.save(routeSN);
			}

			int driverId2 = driverRepo.getDriverByCurrentHub(lghub_id);
			if (driverId2 != 0 && pkgNS.size()>0) {
				DriverVO driverVO = driverRepo.getById(driverId2);
				driverVO.setCurrent_hub("empty");
				driverRepo.save(driverVO);
				RouteVO routeNS = new RouteVO();
				routeNS.setRoute_id(
						lghub_id + "-" + idHub.get(idHub.indexOf(lghub_id) + 1) + "-"  + time);
				routeNS.setDriver_id(driverId2);
				routeNS.setStart_pos(lghub_id);
				routeNS.setEnd_pos(idHub.get(idHub.indexOf(lghub_id) + 1));
				routeNS.setStart_datetime(LocalDateTime.now());
				Set<PackageVO> pkgSetNS = new HashSet<PackageVO>();
				for (PackageVO pkg : pkgNS) {
					pkgSetNS.add(pkg);
					pkg.setCurrent_driver(driverId2);
					pkgRepo.save(pkg);
				}
				routeNS.setPackagelist(pkgSetNS);
				routeRepo.save(routeNS);
			}
		}
	}

	// CALL_SET_NEXT_HUB+PREPARE_ROUTE
	@Scheduled(fixedRate = 300000, initialDelay = 2000) // 5 phut
	public void prepareAllRoute() {

		System.out.println("run route init");
		for (int i = 0; i < idHub.size(); i++) {
			setNextHub(idHub.get(i));
			prepareRoute(idHub.get(i));

		}
	}

	// SET_NEXT_PACKAGE
	public void setNextHub(String lghub_id) {
		List<PackageVO> listVO = pkgRepo.getListPackageInHub(lghub_id);
		for (PackageVO pkgVO : listVO) {
			if (currentLocation(pkgVO) > endLocation(pkgVO)) {
				pkgVO.setNext_hub(idHub.get(idHub.indexOf(lghub_id) - 1));
				pkgRepo.save(pkgVO);
			} else if (currentLocation(pkgVO) < endLocation(pkgVO)) {
				pkgVO.setNext_hub(idHub.get(idHub.indexOf(lghub_id) + 1));
				pkgRepo.save(pkgVO);
			} else {
				pkgVO.setNext_hub("final");
				pkgRepo.save(pkgVO);
			}
		}
	}

	// RECEIVE_LOCACTION
	public Integer endLocation(PackageVO packageVO) {
		createValueArea();
		int result = -1;
		String receiver_province = packageVO.getReceiver_address()
				.substring(10, packageVO.getReceiver_address().indexOf("District")).toLowerCase().trim();
		switch (location.get(receiver_province)) {
		case 0:
			result = 0;
			return result;
		case 1:
			result = 1;
			return result;
		case 2:
			result = 2;
			return result;
		case 3:
			result = 3;
			return result;
		case 4:
			result = 4;
			return result;
		case 5:
			result = 5;
			return result;
		case 6:
			result = 6;
			return result;
		case 7:
			result = 7;
			return result;
		}
		return result;
	}

	// CRURENT_HUB_LOCATION
	public Integer currentLocation(PackageVO packageVO) {
		createValueArea();
		int result = -1;
		String currentHub = packageVO.getCurrent_hub().trim().toLowerCase();
		switch (location.get(currentHub)) {
		case 0:
			result = 0;
			return result;
		case 1:
			result = 1;
			return result;
		case 2:
			result = 2;
			return result;
		case 3:
			result = 3;
			return result;
		case 4:
			result = 4;
			return result;
		case 5:
			result = 5;
			return result;
		case 6:
			result = 6;
			return result;
		case 7:
			result = 7;
			return result;
		}
		return result;

	}

	// MAPPING_LOCATION
	public void createValueArea() {
		// create value location lowerCase when init
		for (northWest pro : northWest.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 0);
		}
		for (northEast pro : northEast.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 1);
		}
		for (north pro : north.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 2);
		}
		for (northCentral pro : northCentral.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 3);
		}
		for (southCentral pro : southCentral.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 4);
		}
		for (highLands pro : highLands.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 5);
		}
		for (southEast pro : southEast.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 6);
		}
		for (southWest pro : southWest.values()) {
			String province = pro.toString().toLowerCase().trim();
			location.put(province, 7);
		}
	}

	private RouteVO prepareRouteVO(String input) {
		RouteVO route = new RouteVO();
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (json.containsKey("route_id")) {
			route.setRoute_id(json.get("route_id").toString());
		}
		if (json.containsKey("driver_id")) {
			route.setDriver_id(Integer.valueOf(json.get("driver_id").toString()));
		}
		if (json.containsKey("start_pos")) {
			route.setStart_pos(json.get("start_pos").toString());
		}
		if (json.containsKey("end_pos")) {
			route.setEnd_pos(json.get("end_pos").toString());
		}
		if (json.containsKey("start_datetime")) {
			route.setStart_datetime(LocalDateTime.parse(json.get("start_datetime").toString()));
		}
		if (json.containsKey("end_datetime")) {
			route.setEnd_datetime(LocalDateTime.parse(json.get("end_datetime").toString()));
		}
		return route;
	}

}
