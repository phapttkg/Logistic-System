-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.19-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for logistic_system
DROP DATABASE IF EXISTS `logistic_system`;
CREATE DATABASE IF NOT EXISTS `logistic_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `logistic_system`;

-- Dumping structure for table logistic_system.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `staff_id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `passwd` varchar(100) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  `phone_num` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.account: ~56 rows (approximately)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`staff_id`, `name`, `passwd`, `role`, `phone_num`, `enabled`) VALUES
	(1, 'Admin', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'admin', '0123456789', b'0'),
	(2, 'Hub Staff 1', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(3, 'Hub Staff 2', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(4, 'Hub Staff 3', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(5, 'Hub Staff 4', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(6, 'Hub Staff 5', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(7, 'Hub Staff 6', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(8, 'Hub Staff 7', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(9, 'Hub Staff 8', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'hub_staff', '0123456789', b'0'),
	(10, 'Divider ', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'divider', '0123456789', b'0'),
	(11, 'Driver Staff 1', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(12, 'Driver Staff 2', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(13, 'Driver Staff 3', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(14, 'Driver Staff 4', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(15, 'Driver Staff 5', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(16, 'Driver Staff 6', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(17, 'Driver Staff 7', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(18, 'Driver Staff 8', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(19, 'Driver Staff 9', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(20, 'Driver Staff 10', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(21, 'Driver Staff 11', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(22, 'Driver Staff 12', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(23, 'Driver Staff 13', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(24, 'Driver Staff 14', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(25, 'Shipper Staff 1', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(26, 'Shipper Staff 2', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(27, 'Shipper Staff 3', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(28, 'Shipper Staff 4', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(29, 'Shipper Staff 5', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(30, 'Shipper Staff 6', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(31, 'Shipper Staff 7', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(32, 'Shipper Staff 8', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(33, 'Shipper Staff 9', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(34, 'Shipper Staff 10', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(35, 'Shipper Staff 11', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(36, 'Shipper Staff 12', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(37, 'Shipper Staff 13', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(38, 'Shipper Staff 14', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(39, 'Shipper Staff 15', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(40, 'Shipper Staff 16', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'shipper', '0123456789', b'0'),
	(41, 'Driver Staff 15', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(42, 'Driver Staff 16', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(43, 'Driver Staff 17', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(44, 'Driver Staff 18', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(45, 'Driver Staff 19', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(46, 'Driver Staff 20', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(47, 'Driver Staff 21', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(48, 'Driver Staff 22', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(49, 'Driver Staff 23', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(50, 'Driver Staff 24', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(51, 'Driver Staff 25', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(52, 'Driver Staff 26', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(53, 'Driver Staff 27', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(54, 'Driver Staff 28', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(55, 'Driver Staff 29', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0'),
	(56, 'Driver Staff 30', '$2a$10$3JM5W44PSy/xk6mfPNmS2OmfLkDXG4ut57S3Bd58eg3EMFr3GJDhC', 'driver', '0123456789', b'0');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- Dumping structure for table logistic_system.app_role
DROP TABLE IF EXISTS `app_role`;
CREATE TABLE IF NOT EXISTS `app_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(30) NOT NULL,
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE KEY `APP_ROLE_UK` (`role_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.app_role: ~5 rows (approximately)
/*!40000 ALTER TABLE `app_role` DISABLE KEYS */;
INSERT INTO `app_role` (`role_id`, `role_name`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_DIVIDER'),
	(4, 'ROLE_DRIVER'),
	(3, 'ROLE_HUB_STAFF'),
	(5, 'ROLE_SHIPPER');
/*!40000 ALTER TABLE `app_role` ENABLE KEYS */;

-- Dumping structure for table logistic_system.driver
DROP TABLE IF EXISTS `driver`;
CREATE TABLE IF NOT EXISTS `driver` (
  `driver_id` mediumint(9) NOT NULL,
  `current_hub` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  KEY `FK_driver_logistic_hub` (`current_hub`),
  CONSTRAINT `FK__account2` FOREIGN KEY (`driver_id`) REFERENCES `account` (`staff_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_driver_logistic_hub` FOREIGN KEY (`current_hub`) REFERENCES `logistic_hub` (`lghub_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.driver: ~30 rows (approximately)
/*!40000 ALTER TABLE `driver` DISABLE KEYS */;
INSERT INTO `driver` (`driver_id`, `current_hub`) VALUES
	(24, 'CanTho'),
	(43, 'CanTho'),
	(56, 'CanTho'),
	(19, 'DaNang'),
	(44, 'DaNang'),
	(45, 'DaNang'),
	(18, 'empty'),
	(14, 'HaNoi'),
	(15, 'HaNoi'),
	(46, 'HaNoi'),
	(47, 'HaNoi'),
	(22, 'HoChiMinh'),
	(23, 'HoChiMinh'),
	(41, 'HoChiMinh'),
	(48, 'HoChiMinh'),
	(20, 'LamDong'),
	(21, 'LamDong'),
	(49, 'LamDong'),
	(50, 'LamDong'),
	(16, 'NgheAn'),
	(17, 'NgheAn'),
	(42, 'NgheAn'),
	(51, 'NgheAn'),
	(12, 'ThaiNguyen'),
	(13, 'ThaiNguyen'),
	(52, 'ThaiNguyen'),
	(53, 'ThaiNguyen'),
	(11, 'YenBai'),
	(54, 'YenBai'),
	(55, 'YenBai');
/*!40000 ALTER TABLE `driver` ENABLE KEYS */;

-- Dumping structure for table logistic_system.hub_staff
DROP TABLE IF EXISTS `hub_staff`;
CREATE TABLE IF NOT EXISTS `hub_staff` (
  `hub_staff_id` mediumint(9) NOT NULL,
  `lghub_id` varchar(30) NOT NULL,
  PRIMARY KEY (`hub_staff_id`) USING BTREE,
  KEY `FK__logistic_hub1` (`lghub_id`),
  CONSTRAINT `FK__logistic_hub1` FOREIGN KEY (`lghub_id`) REFERENCES `logistic_hub` (`lghub_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_hub_staff_account` FOREIGN KEY (`hub_staff_id`) REFERENCES `account` (`staff_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.hub_staff: ~8 rows (approximately)
/*!40000 ALTER TABLE `hub_staff` DISABLE KEYS */;
INSERT INTO `hub_staff` (`hub_staff_id`, `lghub_id`) VALUES
	(9, 'CanTho'),
	(6, 'DaNang'),
	(4, 'HaNoi'),
	(8, 'HoChiMinh'),
	(7, 'LamDong'),
	(5, 'NgheAn'),
	(3, 'ThaiNguyen'),
	(2, 'YenBai');
/*!40000 ALTER TABLE `hub_staff` ENABLE KEYS */;

-- Dumping structure for table logistic_system.logistic_hub
DROP TABLE IF EXISTS `logistic_hub`;
CREATE TABLE IF NOT EXISTS `logistic_hub` (
  `lghub_id` varchar(30) NOT NULL,
  `lghub_phone` varchar(15) NOT NULL,
  `lghub_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`lghub_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.logistic_hub: ~9 rows (approximately)
/*!40000 ALTER TABLE `logistic_hub` DISABLE KEYS */;
INSERT INTO `logistic_hub` (`lghub_id`, `lghub_phone`, `lghub_address`) VALUES
	('CanTho', '0123456789', 'Cần Thơ'),
	('DaNang', '0123456789', 'Đà Nẵng'),
	('empty', '0000000000', 'empty'),
	('HaNoi', '0123456789', 'Hà Nội'),
	('HoChiMinh', '0123456789', 'Hồ Chí Minh'),
	('LamDong', '0123456789', 'Lâm Đồng'),
	('NgheAn', '0123456789', 'Nghệ An'),
	('ThaiNguyen', '0123456789', 'Thái Nguyên'),
	('YenBai', '0123456789', 'Yên Bái');
/*!40000 ALTER TABLE `logistic_hub` ENABLE KEYS */;

-- Dumping structure for table logistic_system.package
DROP TABLE IF EXISTS `package`;
CREATE TABLE IF NOT EXISTS `package` (
  `pkg_id` varchar(20) NOT NULL,
  `created_datetime` datetime NOT NULL,
  `size` varchar(10) NOT NULL COMMENT 'vBig,Big,Medium,Small,vSmall',
  `weight` double NOT NULL,
  `tracking_status` varchar(300) DEFAULT NULL,
  `isreturn` bit(1) DEFAULT NULL,
  `delivery_fee` int(11) DEFAULT NULL,
  `cod_value` int(11) DEFAULT NULL,
  `receiver_name` varchar(50) NOT NULL,
  `receiver_phone_num` varchar(50) NOT NULL,
  `receiver_address` varchar(50) NOT NULL,
  `sender_name` varchar(50) NOT NULL,
  `sender_phone_num` varchar(50) NOT NULL,
  `sender_address` varchar(50) NOT NULL,
  `current_hub` varchar(30) DEFAULT NULL,
  `current_shipper` mediumint(9) DEFAULT NULL,
  `current_driver` mediumint(9) DEFAULT NULL,
  `next_hub` varchar(50) DEFAULT NULL,
  `pick_time` datetime DEFAULT NULL,
  `drop_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pkg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.package: ~0 rows (approximately)
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` (`pkg_id`, `created_datetime`, `size`, `weight`, `tracking_status`, `isreturn`, `delivery_fee`, `cod_value`, `receiver_name`, `receiver_phone_num`, `receiver_address`, `sender_name`, `sender_phone_num`, `sender_address`, `current_hub`, `current_shipper`, `current_driver`, `next_hub`, `pick_time`, `drop_time`) VALUES
	('P001', '2021-07-23 10:39:01', 'vSmall', 2, 'new', b'0', 10000, 0, 'Nguyen Thi Bug', '987654321', 'Province: KhanhHoa District: 1', 'Tran Van Fix', '123456789', 'Province: TayNinh District: 1', '-1', -1, -1, NULL, NULL, NULL);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;

-- Dumping structure for table logistic_system.persistent_logins
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.persistent_logins: ~0 rows (approximately)
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;

-- Dumping structure for table logistic_system.route
DROP TABLE IF EXISTS `route`;
CREATE TABLE IF NOT EXISTS `route` (
  `route_id` varchar(100) NOT NULL,
  `driver_id` mediumint(9) NOT NULL,
  `start_pos` varchar(30) NOT NULL,
  `end_pos` varchar(30) NOT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `end_datetime` datetime DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  KEY `FK_route_driver` (`driver_id`),
  KEY `FK_route_logistic_hub` (`start_pos`),
  KEY `FK_route_logistic_hub_2` (`end_pos`),
  CONSTRAINT `FK_route_driver` FOREIGN KEY (`driver_id`) REFERENCES `driver` (`driver_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_route_logistic_hub` FOREIGN KEY (`start_pos`) REFERENCES `logistic_hub` (`lghub_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_route_logistic_hub_2` FOREIGN KEY (`end_pos`) REFERENCES `logistic_hub` (`lghub_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.route: ~0 rows (approximately)
/*!40000 ALTER TABLE `route` DISABLE KEYS */;
/*!40000 ALTER TABLE `route` ENABLE KEYS */;

-- Dumping structure for table logistic_system.route_detail
DROP TABLE IF EXISTS `route_detail`;
CREATE TABLE IF NOT EXISTS `route_detail` (
  `pkg_id` varchar(20) NOT NULL,
  `route_id` varchar(100) NOT NULL,
  PRIMARY KEY (`pkg_id`,`route_id`),
  KEY `FK_route_detail_route` (`route_id`),
  CONSTRAINT `FK__package` FOREIGN KEY (`pkg_id`) REFERENCES `package` (`pkg_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_route_detail_route` FOREIGN KEY (`route_id`) REFERENCES `route` (`route_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.route_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `route_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `route_detail` ENABLE KEYS */;

-- Dumping structure for table logistic_system.shipper
DROP TABLE IF EXISTS `shipper`;
CREATE TABLE IF NOT EXISTS `shipper` (
  `shipper_id` mediumint(9) NOT NULL,
  `lghub_id` varchar(30) NOT NULL,
  PRIMARY KEY (`shipper_id`),
  KEY `FK__logistic_hub` (`lghub_id`),
  CONSTRAINT `FK__account` FOREIGN KEY (`shipper_id`) REFERENCES `account` (`staff_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK__logistic_hub` FOREIGN KEY (`lghub_id`) REFERENCES `logistic_hub` (`lghub_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.shipper: ~16 rows (approximately)
/*!40000 ALTER TABLE `shipper` DISABLE KEYS */;
INSERT INTO `shipper` (`shipper_id`, `lghub_id`) VALUES
	(39, 'CanTho'),
	(40, 'CanTho'),
	(33, 'DaNang'),
	(34, 'DaNang'),
	(29, 'HaNoi'),
	(30, 'HaNoi'),
	(37, 'HoChiMinh'),
	(38, 'HoChiMinh'),
	(35, 'LamDong'),
	(36, 'LamDong'),
	(31, 'NgheAn'),
	(32, 'NgheAn'),
	(27, 'ThaiNguyen'),
	(28, 'ThaiNguyen'),
	(25, 'YenBai'),
	(26, 'YenBai');
/*!40000 ALTER TABLE `shipper` ENABLE KEYS */;

-- Dumping structure for table logistic_system.ship_route
DROP TABLE IF EXISTS `ship_route`;
CREATE TABLE IF NOT EXISTS `ship_route` (
  `ship_route_id` varchar(100) NOT NULL,
  `shipper_id` mediumint(9) NOT NULL,
  `date` date NOT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ship_route_id`),
  KEY `FK__shipper` (`shipper_id`),
  CONSTRAINT `FK__shipper` FOREIGN KEY (`shipper_id`) REFERENCES `shipper` (`shipper_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.ship_route: ~0 rows (approximately)
/*!40000 ALTER TABLE `ship_route` DISABLE KEYS */;
/*!40000 ALTER TABLE `ship_route` ENABLE KEYS */;

-- Dumping structure for table logistic_system.ship_route_detail
DROP TABLE IF EXISTS `ship_route_detail`;
CREATE TABLE IF NOT EXISTS `ship_route_detail` (
  `ship_route_id` varchar(100) NOT NULL,
  `pkg_id` varchar(20) NOT NULL,
  PRIMARY KEY (`ship_route_id`,`pkg_id`) USING BTREE,
  KEY `FK__package1` (`pkg_id`),
  CONSTRAINT `FK__package1` FOREIGN KEY (`pkg_id`) REFERENCES `package` (`pkg_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_ship_route_detail_ship_route` FOREIGN KEY (`ship_route_id`) REFERENCES `ship_route` (`ship_route_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.ship_route_detail: ~0 rows (approximately)
/*!40000 ALTER TABLE `ship_route_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `ship_route_detail` ENABLE KEYS */;

-- Dumping structure for table logistic_system.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `id` mediumint(9) NOT NULL AUTO_INCREMENT,
  `staff_id` mediumint(9) NOT NULL,
  `role_id` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `USER_ROLE_UK` (`staff_id`,`role_id`) USING BTREE,
  KEY `FK_user_role_app_role` (`role_id`) USING BTREE,
  CONSTRAINT `FK_user_role_account` FOREIGN KEY (`staff_id`) REFERENCES `account` (`staff_id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_app_role` FOREIGN KEY (`role_id`) REFERENCES `app_role` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table logistic_system.user_role: ~56 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`id`, `staff_id`, `role_id`) VALUES
	(1, 1, 1),
	(2, 2, 3),
	(3, 3, 3),
	(4, 4, 3),
	(5, 5, 3),
	(6, 6, 3),
	(7, 7, 3),
	(8, 8, 3),
	(9, 9, 3),
	(10, 10, 2),
	(11, 11, 4),
	(12, 12, 4),
	(13, 13, 4),
	(14, 14, 4),
	(15, 15, 4),
	(16, 16, 4),
	(17, 17, 4),
	(18, 18, 4),
	(19, 19, 4),
	(20, 20, 4),
	(21, 21, 4),
	(22, 22, 4),
	(23, 23, 4),
	(24, 24, 4),
	(25, 25, 5),
	(26, 26, 5),
	(27, 27, 5),
	(28, 28, 5),
	(29, 29, 5),
	(30, 30, 5),
	(31, 31, 5),
	(32, 32, 5),
	(33, 33, 5),
	(34, 34, 5),
	(35, 35, 5),
	(36, 36, 5),
	(37, 37, 5),
	(38, 38, 5),
	(39, 39, 5),
	(40, 40, 5),
	(42, 41, 4),
	(41, 42, 4),
	(43, 43, 4),
	(44, 44, 4),
	(45, 45, 4),
	(46, 46, 4),
	(47, 47, 4),
	(48, 48, 4),
	(49, 49, 4),
	(50, 50, 4),
	(51, 51, 4),
	(52, 52, 4),
	(53, 53, 4),
	(54, 54, 4),
	(55, 55, 4),
	(56, 56, 4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
