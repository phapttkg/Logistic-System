package team1.logistic.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team1.logistic.entity.PackageVO;
import team1.logistic.reponsitory.PackageRepository;

/**
 * 
 * @author An
 * 
 * */
@Service("companyAPIService")
public class CompanyAPIService {
	enum north {HOA_BINH, SON_LA, DIEN_BIEN, LAI_CHAU, LAO_CAI, YEN_BAI, PHU_THO,
				HA_GIANG, TUYEN_QUANG, CAO_BANG, BAC_KAN, THAI_NGUYEN, LANG_SON, BAC_GIANG, 
				QUANG_NINH, HA_NOI, BAC_NINH, HA_NAM, HAI_DUONG, HAI_PHONG, HUNG_YEN, NAM_DINH, 
				THAI_BINH, VINH_PHUC, NINH_BINH  };
	enum central { THANH_HOA, NGHE_AN, HA_TINH, QUANG_BINH, QUANG_TRI, THUA_THIEN_HUE, DA_NANG,
				QUANG_NAM, QUANG_NGAI, BINH_DINH, PHU_YEN, KHANH_HOA, NINH_THUAN, BINH_THUAN,
				KON_TUM, GIA_LAI, DAK_LAK, DAK_NONG, LAM_DONG};
	enum south { TPHCM, BA_RIA_VUNG_TAU, BINH_DUONG, BINH_PHUOC, DONG_NAI, TAY_NINH, AN_GIANG,
				BAC_LIEU, BEN_TRE, CA_MAU, CAN_THO, DONG_THAP, HAU_GIANG, KIEN_GIANG, LONG_AN,
				SOC_TRANG, TIEN_GIANG, TRA_VINH, VINH_LONG};
	private static Map<String,Integer> location = new HashMap<String,Integer>();
	
	@Autowired
	private PackageRepository pkg_repo;
		
	public CompanyAPIService() {
		super();
		for (north a: north.values()) {
			String b = a.toString().replace("_", "");
			b = b.toLowerCase();
            location.put(b, 1);
        }
		for (central a: central.values()) {
			String b = a.toString().replace("_", "");
			b = b.toLowerCase();
            location.put(b, 2);
        }
		for (south a: south.values()) {
			String b = a.toString().replace("_", "");
			b = b.toLowerCase();
            location.put(b, 3);
        }
		
	}
	
	public String findTracking (String pkg_id) {
		PackageVO pkg = preparepkg(pkg_id);
		String result;
		try {
		result = pkg_repo.findById(pkg.getPkg_id()).get().getTracking_status();
		} catch (Exception e) {
			result = "We don't have this id package";
			return result;
		};
		return result;
	}
	
	
	public int calculateFee (String input) {
		PackageVO pkg = preparepkg(input);
		Double fee;
		Double size_fix = 0.0;
		Double weight = 0.0;

		String sender = pkg.getSender_address()
				.substring(10, pkg.getSender_address().indexOf("District")).toLowerCase().trim();
		String receiver = pkg.getReceiver_address()
				.substring(10, pkg.getReceiver_address().indexOf("District")).toLowerCase().trim();
		
		if (location.get(sender)!=null && location.get(receiver)!=null) {
		if (sender.compareTo(receiver)!=0) {
			if (location.get(sender).toString().equals(location.get(receiver).toString())) {
				fee = 20000.0;
			} else {
				fee = 30000.0;
			}
		} else {
			fee=10000.0;
		}
		} else {
			fee = -1.0;
		}
		if (pkg.getSize()!=null) {
			size_fix= -1.0;
		switch (pkg.getSize()) {
			case "vBig": size_fix = 2.5 ; break;
			case "Big": size_fix = 2.1; break;
			case "Medium": size_fix = 1.8 ; break;
			case "Small": size_fix = 1.4 ; break;
			case "vSmall": size_fix = 1.0 ; break;
		}
		} else {
			size_fix= -1.0;
		}
		try {
		weight = pkg.getWeight();
		if (weight<1.0) weight = 1.0;
		}
		catch (Exception e) {
			weight = -1.0;
		}
		weight = Math.sqrt(weight);
		
		fee = fee*size_fix*weight;
		
		return fee.intValue() ;
	}
	
	private PackageVO preparepkg(String input) {
		PackageVO pkg = new PackageVO();
		JSONParser parser = new JSONParser();
		JSONObject json = null;
		try {
			json = (JSONObject) parser.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (json.containsKey("pkg_id"))
		{
			pkg.setPkg_id(json.get("pkg_id").toString());
		}
		if (json.containsKey("size"))
		{
			pkg.setSize(json.get("size").toString());
		}
		if (json.containsKey("tracking_status"))
		{
			pkg.setTracking_status(json.get("tracking_status").toString());
		}
		if (json.containsKey("receiver_name"))
		{
			pkg.setReceiver_name(json.get("receiver_name").toString());
		}
		if (json.containsKey("receiver_phone_num"))
		{
			pkg.setReceiver_phone_num(json.get("receiver_phone_num").toString());
		}
		if (json.containsKey("receiver_address"))
		{
			pkg.setReceiver_address(json.get("receiver_address").toString());
		}
		if (json.containsKey("sender_name"))
		{
			pkg.setSender_name(json.get("sender_name").toString());
		}
		if (json.containsKey("sender_phone_num"))
		{
			pkg.setSender_phone_num(json.get("sender_phone_num").toString());
		}
		if (json.containsKey("sender_address"))
		{
			pkg.setSender_address(json.get("sender_address").toString());
		}
		if (json.containsKey("current_hub"))
		{
			pkg.setCurrent_hub(json.get("current_hub").toString());
		}
		if (json.containsKey("next_hub"))
		{
			pkg.setNext_hub(json.get("next_hub").toString());
		}
		if (json.containsKey("weight"))
		{
			pkg.setWeight(Double.valueOf(json.get("weight").toString()));
		}
		if (json.containsKey("delivery_fee"))
		{
			pkg.setDelivery_fee(Integer.valueOf(json.get("delivery_fee").toString()));
		}
		if (json.containsKey("cod_value"))
		{
			pkg.setCod_value(Integer.valueOf(json.get("cod_value").toString()));
		}
		if (json.containsKey("current_driver"))
		{
			pkg.setCurrent_driver(Integer.valueOf(json.get("current_driver").toString()));
		}
		if (json.containsKey("current_shipper"))
		{
			pkg.setCurrent_shipper(Integer.valueOf(json.get("current_shipper").toString()));
		}
		if (json.containsKey("pick_time"))
		{
			pkg.setPick_time(LocalDateTime.parse(json.get("pick_time").toString()));
		}
		if (json.containsKey("drop_time"))
		{
			pkg.setDrop_time(LocalDateTime.parse(json.get("drop_time").toString()));
		}
		if (json.containsKey("created_datetime"))
		{
			pkg.setCreated_datetime(LocalDateTime.parse(json.get("created_datetime").toString()));
		}
		pkg.setIsreturn(false);
		
		return pkg;
	}

	public Boolean savePackage (String input) {
		
		PackageVO pkg = preparepkg(input);
		pkg.setTracking_status("new");
		pkg.setCurrent_driver(-1);
		pkg.setCurrent_shipper(-1);
		pkg.setCurrent_hub("-1");
		pkg.setCreated_datetime(LocalDateTime.now());
//		System.out.println(pkg.getCreated_datetime().toString().substring(0, 19));
		try {
		pkg_repo.save(pkg);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
}
