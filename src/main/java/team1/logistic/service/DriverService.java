package team1.logistic.service;

import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hamcrest.text.IsEmptyString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.logistic.entity.DriverVO;
import team1.logistic.entity.PackageVO;
import team1.logistic.entity.RouteVO;
import team1.logistic.reponsitory.DriverRepository;
import team1.logistic.reponsitory.PackageRepository;
import team1.logistic.reponsitory.RouteRepository;
/**
 * @author Long
 */
@Service("driverService")
public class DriverService {
	
	@Autowired
	private RouteRepository routeRepo;
	@Autowired
	private PackageRepository pkgRepo;
	@Autowired
	private DriverRepository driverRepo;

	public Object getPackageListToday(int driver_id) {
		RouteVO result = new RouteVO();

		for (RouteVO route : routeRepo.findAll()) {
			if ((route.getStart_datetime().toLocalDate().compareTo(LocalDate.now()) == 0)
					&& (route.getDriver_id() == driver_id) && route.getEnd_datetime() == null) {
				result = route;
			}
		}
		return result;
	}

	public Set<RouteVO> getDriverHistory(int driver_id) {

		Set<RouteVO> result1 = new HashSet<RouteVO>();
		for (RouteVO route : routeRepo.findAll()) {
			if ((route.getStart_datetime().getMonth() == LocalDateTime.now().getMonth())
					&& (route.getDriver_id() == driver_id)) {
				result1.add(route);
			}
		}
		return result1;
	}

	// send request: package list has arrived to hub
	public Object sendTracking(String str, int driver_id) {
	
			if(str.length()<10) {
				
				try { 
					System.out.print("theo pack_id");
			System.out.print(pkgRepo.getById(str));
			PackageVO pkg = pkgRepo.getById(str);
			DriverVO driver = driverRepo.getById(driver_id);
			String status = pkg.getTracking_status();
			String[] statusList = status.split("-");
			int index = statusList.length-1;
			if (statusList[index].contains("sending")) {
				String tracking = status + "-arriving("+driver_id+")";
				pkg.setCurrent_driver(driver_id);
				pkg.setTracking_status(tracking);
				pkgRepo.saveAndFlush(pkg);
				return true;
			} if(statusList[index].contains("arriving") && !statusList[index].contains("arrived")) {
				
				String tracking = status + "-arrived("+driver_id+")";
				pkg.setTracking_status(tracking);
				pkg.setCurrent_driver(-1);				
				driver.setCurrent_hub(pkg.getNext_hub());
				driverRepo.saveAndFlush(driver);
				pkgRepo.saveAndFlush(pkg);
				return true;
			}else {
				return false;
			}
			}
		 catch (Exception e) {
			return false;
		}}
			else
		{
				try {
					System.out.print("theo route");
					RouteVO route = routeRepo.getById(str);
					for(PackageVO pkg : route.getPackagelist())
					
					{	
					DriverVO driver = driverRepo.getById(driver_id);
					String status = pkg.getTracking_status();
					String[] statusList = status.split("-");
					int index = statusList.length-1;
					if (statusList[index].contains("sending")) {
						String tracking = status + "-arriving("+driver_id+")";
						pkg.setCurrent_driver(driver_id);
						pkg.setTracking_status(tracking);
						pkgRepo.saveAndFlush(pkg);
					} else {
						if(statusList[index].contains("arriving") && !statusList[index].contains("arrived")) {
						String tracking = status + "-arrived("+driver_id+")";
						pkg.setTracking_status(tracking);
						pkg.setCurrent_driver(-1);					
						driver.setCurrent_hub(pkg.getNext_hub());
						driverRepo.saveAndFlush(driver);
						pkgRepo.saveAndFlush(pkg);
					}}
					}
				} catch (Exception e) {
					return false;
				}
				return true;
		}
	}
//	public Object sendTracking(RouteVO route, int driver_id) {
//		try {
//			for(PackageVO pkg :route.getPackagelist())
//			
//			{	
//			DriverVO driver = driverRepo.getById(driver_id);
//			String status = pkg.getTracking_status();
//			String[] statusList = status.split("-");
//			int index = statusList.length-1;
//			if (statusList[index].contains("sending")) {
//				String tracking = status + "-arriving("+driver_id+")";
//				pkg.setCurrent_driver(driver_id);
//				pkg.setTracking_status(tracking);
//				pkgRepo.saveAndFlush(pkg);
//			} else {
//				String tracking = status + "-arrived("+driver_id+")";
//				pkg.setTracking_status(tracking);
//				pkg.setCurrent_driver(-1);
//				driver.setStatus("free");
//				driver.setCurrent_hub(pkg.getNext_hub());
//				driverRepo.saveAndFlush(driver);
//				pkgRepo.saveAndFlush(pkg);
//			}
//			}
//		} catch (Exception e) {
//			return false;
//		}
//		return true;
//	}
}

	




