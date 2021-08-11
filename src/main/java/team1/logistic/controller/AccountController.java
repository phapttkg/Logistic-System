package team1.logistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team1.logistic.entity.Account;
import team1.logistic.entity.HubStaffVO;
import team1.logistic.service.AccountService;

/**
 * 
 * @author An
 * 
 * */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private AccountService accountservice;
 	@RequestMapping(value ="/sign-up.do", method=RequestMethod.POST)
 	public Boolean sign_up(@RequestBody HubStaffVO acc)  {
		return accountservice.sign_in(acc);
	}
 	
 	@RequestMapping(value ="/toggleenable.do", method=RequestMethod.POST)
 	public Boolean disable(@RequestBody Account acc)  {
		return accountservice.toggle(acc.getStaff_id());
	}
 	
 	@RequestMapping(value ="/edit.do", method=RequestMethod.POST)
 	public Boolean edit(@RequestBody Account acc)  {
		return accountservice.update(acc);
	}
 	
 	@RequestMapping(value ="/detail.do", method=RequestMethod.POST)
 	public Account detail(@RequestBody Account acc)  {
		return accountservice.viewAccountDetail(acc.getStaff_id());
	}
 	
 	@RequestMapping(value ="/changepassword.do", method=RequestMethod.POST)
 	public Boolean changepassword(@RequestBody Account acc)  {
		return accountservice.changepasswd(acc);
	}
 		
}
