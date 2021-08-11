package team1.logistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import team1.logistic.service.CompanyAPIService;

/**
 * 
 * @author An
 * 
 * */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/company_api")
public class CompanyAPIController {
	
	@Autowired
	private CompanyAPIService apiservice;
	
	@RequestMapping(value = "/getTracking", method = RequestMethod.GET)
	public Object getTracking (@RequestBody String pkg_id) {
		return apiservice.findTracking(pkg_id);
	}
	
	@RequestMapping(value = "/getFee", method = RequestMethod.GET)
	public Object getFee (@RequestBody String pkg) {
		return apiservice.calculateFee(pkg);
	}
	
	@RequestMapping(value = "/sendPackage", method = RequestMethod.POST)
	public Object sendPackage (@RequestBody String pkg) {
		return apiservice.savePackage(pkg);
	}
	
}
