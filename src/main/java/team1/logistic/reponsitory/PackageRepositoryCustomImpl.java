package team1.logistic.reponsitory;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import team1.logistic.entity.PackageVO;

/**
 * 
 * @author Canh
 * 
 * */

public class PackageRepositoryCustomImpl implements PackageRepositoryCustom{
	
	public PackageRepositoryCustomImpl() {
		super();
	}
	@Autowired(required = true)
	private EntityManager entityManager;
	
	/**
	 * @author The Phap
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getListPackageInHub(String lghub_id) {
		String hql ="SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id";
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		List<PackageVO> list = new ArrayList<PackageVO>();
		list.addAll(query.getResultList());
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPackageByCurrentDriver(int driver_id) {
		String hql ="SELECT pkg FROM PackageVO pkg WHERE pkg.current_driver = :driver_id";
		Query query = entityManager.createQuery(hql);
		query.setParameter("driver_id", driver_id);
		List<PackageVO> list = query.getResultList();
		return list;
	}
	
	/**
	 * 
	 * @author Canh
	 * 
	 * */

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupSouthWest(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 "
				+ "OR pkg.sender_address like :hub7 "
				+ "OR pkg.sender_address like :hub8 "
				+ "OR pkg.sender_address like :hub9 "
				+ "OR pkg.sender_address like :hub10 "
				+ "OR pkg.sender_address like :hub11 "
				+ "OR pkg.sender_address like :hub12 "
				+ "OR pkg.sender_address like :hub13 )" ;
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%AnGiang%");
		query.setParameter("hub2", "%BacLieu%");
		query.setParameter("hub3", "%BenTre%");
		query.setParameter("hub4", "%CaMau%");
		query.setParameter("hub5", "%CanTho%");
		query.setParameter("hub6", "%DongThap%");
		query.setParameter("hub7", "%HauGiang%");
		query.setParameter("hub8", "%KienGiang%");
		query.setParameter("hub9", "%LongAn%");
		query.setParameter("hub10", "%SocTrang%");
		query.setParameter("hub11", "%TienGiang%");
		query.setParameter("hub12", "%TraVinh%");
		query.setParameter("hub13", "%VinhLong%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliverySouthWest(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 "
				+ "OR pkg.receiver_address like :hub7 "
				+ "OR pkg.receiver_address like :hub8 "
				+ "OR pkg.receiver_address like :hub9 "
				+ "OR pkg.receiver_address like :hub10 "
				+ "OR pkg.receiver_address like :hub11 "
				+ "OR pkg.receiver_address like :hub12 "
				+ "OR pkg.receiver_address like :hub13 )" ;
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%AnGiang%");
		query.setParameter("hub2", "%BacLieu%");
		query.setParameter("hub3", "%BenTre%");
		query.setParameter("hub4", "%CaMau%");
		query.setParameter("hub5", "%CanTho%");
		query.setParameter("hub6", "%DongThap%");
		query.setParameter("hub7", "%HauGiang%");
		query.setParameter("hub8", "%KienGiang%");
		query.setParameter("hub9", "%LongAn%");
		query.setParameter("hub10", "%SocTrang%");
		query.setParameter("hub11", "%TienGiang%");
		query.setParameter("hub12", "%TraVinh%");
		query.setParameter("hub13", "%VinhLong%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupSouthEast(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 )";
	
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%HoChiMinh%");
		query.setParameter("hub2", "%VungTau%");
		query.setParameter("hub3", "%BinhDuong%");
		query.setParameter("hub4", "%BinhPhuoc%");
		query.setParameter("hub5", "%DongNai%");
		query.setParameter("hub6", "%TayNinh%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliverySouthEast(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 )";

		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%HoChiMinh%");
		query.setParameter("hub2", "%VungTau%");
		query.setParameter("hub3", "%BinhDuong%");
		query.setParameter("hub4", "%BinhPhuoc%");
		query.setParameter("hub5", "%DongNai%");
		query.setParameter("hub6", "%TayNinh%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupHighLands(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 )";
	
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%KonTum%");
		query.setParameter("hub2", "%GiaLai%");
		query.setParameter("hub3", "%DakLak%");
		query.setParameter("hub4", "%DakNong%");
		query.setParameter("hub5", "%LamDong%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliveryHighLands(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 )";

		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%KonTum%");
		query.setParameter("hub2", "%GiaLai%");
		query.setParameter("hub3", "%DakLak%");
		query.setParameter("hub4", "%DakNong%");
		query.setParameter("hub5", "%LamDong%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupSouthCentral(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 "
				+ "OR pkg.sender_address like :hub7 "
				+ "OR pkg.sender_address like :hub8 )";

		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%DaNang%");
		query.setParameter("hub2", "%QuangNam%");
		query.setParameter("hub3", "%QuangNgai%");
		query.setParameter("hub4", "%BinhDinh%");
		query.setParameter("hub5", "%PhuYen%");
		query.setParameter("hub6", "%KhanhHoa%");
		query.setParameter("hub7", "%NinhThuan%");
		query.setParameter("hub8", "%BinhThuan%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliverySouthCentral(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 "
				+ "OR pkg.receiver_address like :hub7 "
				+ "OR pkg.receiver_address like :hub8 )";
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%DaNang%");
		query.setParameter("hub2", "%QuangNam%");
		query.setParameter("hub3", "%QuangNgai%");
		query.setParameter("hub4", "%BinhDinh%");
		query.setParameter("hub5", "%PhuYen%");
		query.setParameter("hub6", "%KhanhHoa%");
		query.setParameter("hub7", "%NinhThuan%");
		query.setParameter("hub8", "%BinhThuan%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupNorthCentral(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%ThanhHoa%");
		query.setParameter("hub2", "%NgheAn%");
		query.setParameter("hub3", "%HaTinh%");
		query.setParameter("hub4", "%QuangBinh%");
		query.setParameter("hub5", "%QuangTri%");
		query.setParameter("hub6", "%Hue%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliveryNorthCentral(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 )";
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%ThanhHoa%");
		query.setParameter("hub2", "%NgheAn%");
		query.setParameter("hub3", "%HaTinh%");
		query.setParameter("hub4", "%QuangBinh%");
		query.setParameter("hub5", "%QuangTri%");
		query.setParameter("hub6", "%Hue%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupNorth(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 "
				+ "OR pkg.sender_address like :hub7 "
				+ "OR pkg.sender_address like :hub8 "
				+ "OR pkg.sender_address like :hub9 "
				+ "OR pkg.sender_address like :hub10 )";
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%BacNinh%");
		query.setParameter("hub2", "%HaNam%");
		query.setParameter("hub3", "%HaNoi%");
		query.setParameter("hub4", "%HaiDuong%");
		query.setParameter("hub5", "%HaiPhong%");
		query.setParameter("hub6", "%HungYen%");
		query.setParameter("hub7", "%NamDinh%");
		query.setParameter("hub8", "%NinhBinh%");
		query.setParameter("hub9", "%ThaiBinh%");
		query.setParameter("hub10", "%VinhPhuc%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliveryNorth(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 "
				+ "OR pkg.receiver_address like :hub7 "
				+ "OR pkg.receiver_address like :hub8 "
				+ "OR pkg.receiver_address like :hub9 "
				+ "OR pkg.receiver_address like :hub10 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%BacNinh%");
		query.setParameter("hub2", "%HaNam%");
		query.setParameter("hub3", "%HaNoi%");
		query.setParameter("hub4", "%HaiDuong%");
		query.setParameter("hub5", "%HaiPhong%");
		query.setParameter("hub6", "%HungYen%");
		query.setParameter("hub7", "%NamDinh%");
		query.setParameter("hub8", "%NinhBinh%");
		query.setParameter("hub9", "%ThaiBinh%");
		query.setParameter("hub10", "%VinhPhuc%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupNorthEast(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 "
				+ "OR pkg.sender_address like :hub7 "
				+ "OR pkg.sender_address like :hub8 "
				+ "OR pkg.sender_address like :hub9 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%HaGiang%");
		query.setParameter("hub2", "%CaoBang%");
		query.setParameter("hub3", "%BacKan%");
		query.setParameter("hub4", "%LangSon%");
		query.setParameter("hub5", "%TuyenQuang%");
		query.setParameter("hub6", "%ThaiNguyen%");
		query.setParameter("hub7", "%PhuTho%");
		query.setParameter("hub8", "%BacGiang%");
		query.setParameter("hub9", "%QuangNinh%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliveryNorthEast(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 "
				+ "OR pkg.receiver_address like :hub7 "
				+ "OR pkg.receiver_address like :hub8 "
				+ "OR pkg.receiver_address like :hub9 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%HaGiang%");
		query.setParameter("hub2", "%CaoBang%");
		query.setParameter("hub3", "%BacKan%");
		query.setParameter("hub4", "%LangSon%");
		query.setParameter("hub5", "%TuyenQuang%");
		query.setParameter("hub6", "%ThaiNguyen%");
		query.setParameter("hub7", "%PhuTho%");
		query.setParameter("hub8", "%BacGiang%");
		query.setParameter("hub9", "%QuangNinh%");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getPickupNorthWest(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :hub AND pkg.current_shipper = :shipper AND pkg.current_driver = :driver AND ( "
				+ "pkg.sender_address like :hub1 "
				+ "OR pkg.sender_address like :hub2 "
				+ "OR pkg.sender_address like :hub3 "
				+ "OR pkg.sender_address like :hub4 "
				+ "OR pkg.sender_address like :hub5 "
				+ "OR pkg.sender_address like :hub6 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("hub", "-1");
		query.setParameter("shipper", -1);
		query.setParameter("driver", -1);
		query.setParameter("hub1", "%LaoCai%");
		query.setParameter("hub2", "%YenBai%");
		query.setParameter("hub3", "%DienBien%");
		query.setParameter("hub4", "%HoaBinh%");
		query.setParameter("hub5", "%LaiChau%");
		query.setParameter("hub6", "%SonLa%");
		
		return  query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PackageVO> getDeliveryNorthWest(String lghub_id) {
		String hql = "SELECT pkg FROM PackageVO pkg WHERE pkg.current_hub = :lghub_id AND pkg.current_shipper = :shipper AND ( "
				+ "pkg.receiver_address like :hub1 "
				+ "OR pkg.receiver_address like :hub2 "
				+ "OR pkg.receiver_address like :hub3 "
				+ "OR pkg.receiver_address like :hub4 "
				+ "OR pkg.receiver_address like :hub5 "
				+ "OR pkg.receiver_address like :hub6 )";
		
		Query query = entityManager.createQuery(hql);
		query.setParameter("lghub_id", lghub_id);
		query.setParameter("shipper", -1);
		query.setParameter("hub1", "%LaoCai%");
		query.setParameter("hub2", "%YenBai%");
		query.setParameter("hub3", "%DienBien%");
		query.setParameter("hub4", "%HoaBinh%");
		query.setParameter("hub5", "%LaiChau%");
		query.setParameter("hub6", "%SonLa%");
		return query.getResultList();
	}

}
