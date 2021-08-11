package team1.logistic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import team1.logistic.entity.DriverVO;
import team1.logistic.entity.RouteVO;
import team1.logistic.service.DividerService;

/**
 * @author The Phap
 */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("divider")
public class DividerController{
	@Autowired
	private DividerService dividerService;

	// GET_ALL_DRIVER 
	@GetMapping("/driver_list")
	public ResponseEntity<List<DriverVO>> getDriverList() {
		List<DriverVO> driverList = new ArrayList<DriverVO>();
		driverList = dividerService.getDriverList();
		return ResponseEntity.ok(driverList);
	}

	// GET_ROUTE_WORKING_LIST 
	@GetMapping("/route")
	public ResponseEntity<List<RouteVO>> getRouteToday() {
		List<RouteVO> routeToday = new ArrayList<RouteVO>();
		routeToday = dividerService.getRouteToday();
		return ResponseEntity.ok(routeToday);
	}

	// Change Driver Request 
	@RequestMapping(value="/route/modify.do", method=RequestMethod.POST)
	public Object changeDriver(@RequestBody String input) throws Exception {
		return dividerService.changeDriver(input);
	}
	
 
}
