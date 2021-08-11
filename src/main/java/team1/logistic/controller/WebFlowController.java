package team1.logistic.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import team1.logistic.reponsitory.AccountRepository;
import team1.logistic.service.AccountService;

/**
 * @author An
 * 
 * */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/")
public class WebFlowController implements ErrorController{
	static public Queue<Integer> staff_id = new LinkedList<>();
	@Autowired 
	AccountService accountservice;
	@Autowired
	AccountRepository account_repo;
	
	@RequestMapping(value ="home", method=RequestMethod.GET)
	public void home (HttpServletRequest request, HttpServletResponse response) {
		int staffid = staff_id.remove();
		List<Cookie> list = accountservice.prepareCookie(staffid);
		for (Cookie e: list) {
		if (e!=null)	
		response.addCookie(e);
		}
		String role = account_repo.getById(staffid).getRole();
		switch (role) {
		case "driver" : 
			try {
				response.sendRedirect("http://localhost:3000/driver/container");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
		case "hub_staff" :
			try {
				response.sendRedirect("http://localhost:3000/hub/");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
		case "divider" :
			try {
				response.sendRedirect("http://localhost:3000/divider/driver_list");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
		case "shipper" :
			try {
				response.sendRedirect("http://localhost:3000/shipper/package");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
		case "admin" :
			try {
				response.sendRedirect("http://localhost:3000/account/sign-up");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
						break;
	}
		
	}
	
	@RequestMapping(value ="log_out", method=RequestMethod.GET)
	public void logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie1 = new Cookie("JSESSIONID", "");
        cookie1.setMaxAge(0);
        response.addCookie(cookie1);
        Cookie cookie2 = new Cookie("hub_staff_id", "");
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);
        Cookie cookie3 = new Cookie("driver_id", "");
        cookie3.setMaxAge(0);
        response.addCookie(cookie3);
        Cookie cookie4 = new Cookie("shipper_id", "");
        cookie4.setMaxAge(0);
        response.addCookie(cookie4);
        Cookie cookie5 = new Cookie("divider_id", "");
        cookie5.setMaxAge(0);
        response.addCookie(cookie5);
        Cookie cookie = new Cookie("lghub_id", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        
        try {
			response.sendRedirect("http://localhost:3000/signout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
 	@GetMapping("403")
 	public void NotAllowed(HttpServletResponse response) {
 		try {
			response.sendRedirect("http://localhost:3000/exception/403");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	@GetMapping("error")
 	public void PageNotFound(HttpServletResponse response) {
 		try {
			response.sendRedirect("http://localhost:3000/exception/404");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
 	
 	private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }
}
