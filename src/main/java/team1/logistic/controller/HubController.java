package team1.logistic.controller;
import team1.logistic.entity.PackageVO;
import team1.logistic.entity.RouteVO;
import team1.logistic.entity.ShipRouteVO;
import team1.logistic.entity.ShipperVO;
import team1.logistic.service.HubService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Canh
 * 
 * */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("hub")
public class HubController {
	@Autowired
	private HubService hubService;
	
	@GetMapping("/")
	public ResponseEntity<Object>  getTodayList(@CookieValue String lghub_id) {		
		Object list = hubService.getTodayList(lghub_id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/package")
	public ResponseEntity<Object> getPackageInHub(@CookieValue String lghub_id) {
		Object list = hubService.getPackagesInHub(lghub_id);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/import")
	public ResponseEntity<Object> getImportListToDay(@CookieValue String lghub_id) {
		Object importTodayList = hubService.getImportToday(lghub_id);		
		return ResponseEntity.ok(importTodayList);
	}
	
	
	@PutMapping("/import/import_action.do")
	public void putImport(@RequestBody String importID, @CookieValue String lghub_id) throws ParseException {
		if (importID.contains("drcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(importID);
			RouteVO route = new RouteVO();
			route.setRoute_id(obj.get("drcode").toString());
			hubService.putImport(route, lghub_id);
		}
		if (importID.contains("srcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(importID);
			ShipRouteVO shipRoute = new ShipRouteVO();
			shipRoute.setShip_route_id(obj.get("srcode").toString());
			hubService.putImport(shipRoute, lghub_id);
			
		}		
		if (importID.contains("pcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(importID);
			PackageVO pkg = new PackageVO();
			pkg.setPkg_id(obj.get("pcode").toString());
			hubService.putImport(pkg, lghub_id);
		}
	}
	
	@GetMapping("/export")
	public ResponseEntity<Object> getExportListToDay(@CookieValue String lghub_id) {
		Object exportTodayList = hubService.getExportToday(lghub_id);
		
		return ResponseEntity.ok(exportTodayList);
	}
	
	@PutMapping("/export/export_action.do")
	public void putExport(@RequestBody String exportID, @CookieValue String lghub_id) throws ParseException {
		if (exportID.contains("drcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(exportID);
			RouteVO route = new RouteVO();
			route.setRoute_id(obj.get("drcode").toString());
			hubService.putExport(route);
		}
		if (exportID.contains("srcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(exportID);
			ShipRouteVO shipRoute = new ShipRouteVO();
			shipRoute.setShip_route_id(obj.get("srcode").toString());
			hubService.putExport(shipRoute);
			
		}		
		if (exportID.contains("pcode")) {
			JSONParser parser = new JSONParser();  
			JSONObject obj = (JSONObject) parser.parse(exportID);
			PackageVO pkg = new PackageVO();
			pkg.setPkg_id(obj.get("pcode").toString());
			hubService.putExport(pkg);
		}
	}
	
	@GetMapping("/shipper_list")
	public ResponseEntity<List<ShipperVO>> getShipperListInHub(@CookieValue String lghub_id) {
		List<ShipperVO> shipperList = new LinkedList<ShipperVO>();
		shipperList = hubService.getShippersInHub(lghub_id);
		return ResponseEntity.ok(shipperList);
	}
	
	@GetMapping("/history")
	public ResponseEntity<Object> getHistoryInHub(@CookieValue String lghub_id,@CookieValue String history) throws ParseException {
		LocalDate date = LocalDate.parse(history);
		System.out.println(history);
		Object hisInHub = hubService.getHistoryInHub(lghub_id, date);
		return ResponseEntity.ok(hisInHub);
	}
	
	
}
