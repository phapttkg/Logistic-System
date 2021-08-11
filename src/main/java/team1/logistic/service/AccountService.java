package team1.logistic.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team1.logistic.controller.WebFlowController;
import team1.logistic.entity.Account;
import team1.logistic.entity.AppRoleVO;
import team1.logistic.entity.DriverVO;
import team1.logistic.entity.HubStaffVO;
import team1.logistic.entity.ShipperVO;
import team1.logistic.entity.UserRoleVO;
import team1.logistic.reponsitory.AccountRepository;
import team1.logistic.reponsitory.AppRoleRepository;
import team1.logistic.reponsitory.DriverRepository;
import team1.logistic.reponsitory.HubStaffRepository;
import team1.logistic.reponsitory.ShipperRepository;
import team1.logistic.reponsitory.UserRoleRepository;

/**
 * 
 * @author An
 * 
 * */
@Service("accountservice")
public class AccountService  implements UserDetailsService {
	
	@Autowired
	private AccountRepository account_repo;
	@Autowired
	private DriverRepository driver_repo;
	@Autowired
	private ShipperRepository shipper_repo;
	@Autowired
	private HubStaffRepository staff_repo;
	@Autowired
	private AppRoleRepository approle_repo;
	@Autowired
	private UserRoleRepository userrole_repo;

	public AccountService() {
		super();
	}
	

	public UserDetails checkLogin (int id, String passwd) {
		Account acc = account_repo.getById(id);
		UserDetails user = null;
		System.out.println(acc);
		if (passwd.equals(acc.getPasswd())) {
			user = this.loadUserByUsername(Integer.toString(id));
		}
		return user;
	}
	
	public Boolean sign_in (HubStaffVO acc) {
		try {
			AppRoleVO approle;
			UserRoleVO userrole;
			String hub_id = acc.getLghub_id();
			switch (acc.getRole()) {
				case "driver":  DriverVO a = new DriverVO(); 
								a.setStaff_id(acc.getStaff_id());
								a.setName(acc.getName());
								a.setPasswd(encrytePassword(acc.getPasswd()));//front-end ma hoa san password
								a.setPhone_num(acc.getPhone_num());
								a.setRole(acc.getRole());
								approle = new AppRoleVO (4L,"ROLE_DRIVER");
							    driver_repo.save(a); 
							    
								userrole = new UserRoleVO(a,approle);
								userrole_repo.save(userrole);

							    break; 
				case "shipper": ShipperVO b = new ShipperVO();
								b.setStaff_id(acc.getStaff_id());
								b.setName(acc.getName());
								b.setPasswd(encrytePassword(acc.getPasswd()));
								b.setPhone_num(acc.getPhone_num());
								b.setRole(acc.getRole());
								b.setLghub_id(hub_id);
								approle = new AppRoleVO (5L,"ROLE_SHIPPER");
								shipper_repo.save(b); 
								
								userrole = new UserRoleVO(b,approle);
								userrole_repo.save(userrole);

								break;
				case "hub_staff" : HubStaffVO c = new HubStaffVO();
									c.setStaff_id(acc.getStaff_id());
									c.setName(acc.getName());
									c.setPasswd(encrytePassword(acc.getPasswd()));
									c.setPhone_num(acc.getPhone_num());
									c.setRole(acc.getRole());
									c.setLghub_id(hub_id);
									approle = new AppRoleVO (3L,"ROLE_HUB_STAFF");
									
								    staff_repo.save(c);
								    
								    userrole = new UserRoleVO(c,approle);
									userrole_repo.save(userrole);
									
								    break;
				case "divider" :Account d = new Account();
								d.setStaff_id(acc.getStaff_id());
								d.setName(acc.getName());
								d.setPasswd(encrytePassword(acc.getPasswd()));
								d.setPhone_num(acc.getPhone_num());
								d.setRole(acc.getRole());
								approle = new AppRoleVO (2L,"ROLE_DIVIDER");
							    account_repo.save(d);
							    userrole = new UserRoleVO(d,approle);
								userrole_repo.save(userrole);
								
							    break;
			}		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
		
	public Account viewAccountDetail (int id){
		System.out.println(id);
		Account acc;
		try {
		acc = account_repo.findById(id).get();
		} catch (Exception e) {
			return new Account();
		}
		acc.setPasswd("");
		return acc;
	}
	
	public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append("UserName:").append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }
	
	 public static String encrytePassword(String password) {
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	        return encoder.encode(password);
	    }

	@Override
	public UserDetails loadUserByUsername(String staffid) throws UsernameNotFoundException {
		// id duoi dang String
		int staff_id = Integer.parseInt(staffid);
		
		Account account = this.account_repo.getById(staff_id);
		if(account.isEnabled()) {account = null;}
        if (account == null) {
            System.out.println("Account not found! " + staff_id);
            throw new UsernameNotFoundException("Account " + staff_id + " was not found in the database");
        }
        WebFlowController.staff_id.add(staff_id);
        System.out.println("Found Account: " + account);
 
        // [ROLE_USER, ROLE_ADMIN,..]
  
        List<String> roleNames = this.approle_repo.RetrieveRoleNames(account.getStaff_id());
        
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
 
        UserDetails userDetails = (UserDetails) new User(String.valueOf(account.getName()), //
        		account.getPasswd(), grantList);
		 
		
        return userDetails;
	}
	
	public List<Cookie> prepareCookie (int staff_id) {
		List<Cookie> list = new ArrayList<>();
		String role = account_repo.getById(staff_id).getRole();
		Cookie ck1 = null;
		Cookie ck2 = null;
		switch (role) {
			case "driver" : 
							ck1 = new Cookie ("driver_id",String.valueOf(staff_id));
							break;
			case "hub_staff" :
							ck1 = new Cookie ("hub_staff_id",String.valueOf(staff_id));
							ck2 = new Cookie ("lghub_id",staff_repo.getById(staff_id).getLghub_id());
							break;
			case "divider" :
							ck1 = new Cookie ("divider_id",String.valueOf(staff_id));
							break;
			case "shipper" :
							ck1 = new Cookie ("shipper_id",String.valueOf(staff_id));
							ck2 = new Cookie ("lghub_id",shipper_repo.getById(staff_id).getLghub_id());
							break;
		}
			list.add(ck1); list.add(ck2);
		return list;
	}


	public Boolean changepasswd(Account acc) {
		try {
		String newpass = encrytePassword(acc.getPasswd());
		Account modifyacc = account_repo.findById(acc.getStaff_id()).get();
		modifyacc.setPasswd(newpass);
		account_repo.save(modifyacc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	public Boolean update(Account acc) {
		try {
		System.out.println(acc);
		Account acc1 = account_repo.findById(acc.getStaff_id()).get();
		acc1.setName(acc.getName());
		acc1.setPhone_num(acc.getPhone_num());
		acc1.setEnabled(acc.isEnabled());
		account_repo.save(acc1);
		} catch (Exception e) {
			return false;
		}
		return true;
	}


	public Boolean toggle(int staff_id) {
		try {
		Account acc = account_repo.findById(staff_id).get();
		if (acc.isEnabled()) {
		acc.setEnabled(false);
		} else acc.setEnabled(true);
		account_repo.save(acc);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
