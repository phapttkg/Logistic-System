package team1.logistic.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import team1.logistic.entity.PackageVO;
import team1.logistic.entity.ShipRouteVO;
import team1.logistic.service.ShipperService;

/**
 * @author Huan
 *
 */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("shipper/")
public class ShipperController {

	@Autowired
	private ShipperService shipperService;

	
	//Test Show all shipper
	@GetMapping("shipper")
	public ResponseEntity<Object> getPackageList(@CookieValue int shipper_id){
	return ResponseEntity.ok(shipperService.getShipRouteList(shipper_id));
	
	}
	
	@GetMapping("/package")
	public Set<PackageVO> getPackageToday(@CookieValue int shipper_id) {
	
		return shipperService.getPackageToday(shipper_id);
	}
		
	@GetMapping("/history")
	public Set<ShipRouteVO> getPackageHistory(@CookieValue int shipper_id) {
	
		return shipperService.getPackageHistory(shipper_id);
	}
	
	@PostMapping("/pick.do")
	public Object getPackageById(@RequestBody String pkg_id, @CookieValue int shipper_id) {
		System.out.println(pkg_id + " " + shipper_id);
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"'));
		return shipperService.pickPackageById(pkg_id,shipper_id);
	}
	
	@PostMapping("/drop.do")
	public Object dropPackage(@RequestBody String pkg_id, @CookieValue int shipper_id) {
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));		
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);		
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"'));
		System.out.println("here   "+pkg_id);
		return shipperService.dropPackageById(pkg_id,shipper_id);
	}
	@PostMapping("/export.do")	
	public Object exportPackage(@RequestBody String pkg_id, @CookieValue int shipper_id) {
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));		
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);		
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"')).trim();
		System.out.println("here   "+pkg_id);
		return shipperService.exportPackageById(pkg_id,shipper_id);
	}
	@PostMapping("/import.do")
	public Object importPackage(@RequestBody String pkg_id, @CookieValue int shipper_id) {
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));		
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);		
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"'));
		System.out.println("here   "+pkg_id);
		return shipperService.importPackageById(pkg_id,shipper_id);
	}
	
	@PostMapping("/cancel.do")
	public Boolean cancelpackage(@RequestBody String pkg_id,@CookieValue int shipper_id) {
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));		
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);		
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"')).trim();
		System.out.println("here   "+pkg_id);
		
		return shipperService.cancelpackage(pkg_id, shipper_id);
	}
	
	@PostMapping("/return.do")
	public Boolean returnpackage(@RequestBody String pkg_id,@CookieValue int shipper_id) {
		pkg_id = pkg_id.substring(pkg_id.indexOf(':'));		
		pkg_id = pkg_id.substring(pkg_id.indexOf('"')+1);		
		pkg_id = pkg_id.substring(0, pkg_id.indexOf('"')).trim();
		System.out.println("here   "+pkg_id);
		return  shipperService.returnpackage(pkg_id,shipper_id);
	}
}
