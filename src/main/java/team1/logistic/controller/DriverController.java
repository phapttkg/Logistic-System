package team1.logistic.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import team1.logistic.entity.RouteVO;
import team1.logistic.service.DriverService;
/**
 * @author Long
 */
@RestController
@RequestMapping("driver")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
public class DriverController {
	@Autowired
	private DriverService driverService;

	@GetMapping("/container")
	@ResponseBody
	public ResponseEntity<Object> getPackageListToday(@CookieValue int driver_id) {
		return ResponseEntity.ok(driverService.getPackageListToday(driver_id));
	}

	@GetMapping("/history")
	@ResponseBody
	public Set<RouteVO> getDriverHistory(@CookieValue int driver_id) {
		return driverService.getDriverHistory(driver_id);
	}

	@PutMapping("/container/sendtracking.do")
	public Object sendTracking(@CookieValue String driver_id, @CookieValue String routeID) {
		int driver = Integer.parseInt(driver_id);
		return driverService.sendTracking(routeID, driver);
	}


}
