-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for simpedin
CREATE DATABASE IF NOT EXISTS `simpedin` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `simpedin`;

-- Dumping structure for table simpedin.nomor
CREATE TABLE IF NOT EXISTS `nomor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.nomor: ~1 rows (approximately)
INSERT INTO `nomor` (`id`, `total`) VALUES
	(1, 1);

-- Dumping structure for table simpedin.pegawai
CREATE TABLE IF NOT EXISTS `pegawai` (
  `nip` varchar(15) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `bagian` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  PRIMARY KEY (`nip`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.pegawai: ~6 rows (approximately)
INSERT INTO `pegawai` (`nip`, `nama`, `bagian`, `jabatan`) VALUES
	('13333', 'Lukman Arifin', 'IT', 'Staff'),
	('2020', 'Firman', 'BSDM', 'Sekretaris Bagian'),
	('20201', 'Lan', 'BAUM', 'Staff'),
	('202020', 'Angki', 'BRP', 'Analis Bagian'),
	('2022', 'Aldi', 'BPDSI', 'Deputi Manager'),
	('2023', 'Filan', 'BAA', 'Analis Bagian');

-- Dumping structure for table simpedin.sppd
CREATE TABLE IF NOT EXISTS `sppd` (
  `id` int NOT NULL AUTO_INCREMENT,
  `no_sppd` varchar(50) NOT NULL,
  `kepada` varchar(255) NOT NULL,
  `perihal` varchar(255) NOT NULL,
  `tanggal_pergi` date NOT NULL,
  `tanggal_kembali` date NOT NULL,
  `jumlah_hari` int NOT NULL,
  `kota_tujuan` varchar(255) NOT NULL,
  `transportasi` varchar(255) NOT NULL,
  `zonasi` int NOT NULL,
  `lumpsum` int NOT NULL,
  `total_lumpsum` int NOT NULL,
  `transport` int NOT NULL,
  `total_transport` int NOT NULL,
  `total` int NOT NULL,
  `keterangan` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.sppd: ~1 rows (approximately)
INSERT INTO `sppd` (`id`, `no_sppd`, `kepada`, `perihal`, `tanggal_pergi`, `tanggal_kembali`, `jumlah_hari`, `kota_tujuan`, `transportasi`, `zonasi`, `lumpsum`, `total_lumpsum`, `transport`, `total_transport`, `total`, `keterangan`) VALUES
	(1, '0001/SPPD/1/C0/05/2023', 'Filan', 'SPPD dalam rangka kunjungan ke Data Center ITPLN di Bandung tanggal 9 Mei 2023', '2023-05-09', '2023-05-09', 1, 'Bandung', 'Darat', 3, 400000, 400000, 400000, 400000, 1200000, '');

-- Dumping structure for table simpedin.tarif
CREATE TABLE IF NOT EXISTS `tarif` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jabatan` varchar(255) NOT NULL,
  `zonasi` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tarif` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.tarif: ~24 rows (approximately)
INSERT INTO `tarif` (`id`, `jabatan`, `zonasi`, `tarif`) VALUES
	(1, 'Rektor / Wakil Rektor', '1', 400000),
	(2, 'Rektor / Wakil Rektor', '2', 600000),
	(3, 'Rektor / Wakil Rektor', '3', 800000),
	(4, 'Kepala Biro', '1', 400000),
	(5, 'Kepala Biro', '2', 600000),
	(6, 'Kepala Biro', '3', 800000),
	(7, 'Manager', '1', 400000),
	(8, 'Manager', '2', 600000),
	(9, 'Manager', '3', 800000),
	(10, 'Deputi Manager', '1', 400000),
	(11, 'Deputi Manager', '2', 600000),
	(12, 'Deputi Manager', '3', 800000),
	(13, 'Sekretaris Bagian', '1', 400000),
	(14, 'Sekretaris Bagian', '2', 600000),
	(15, 'Sekretaris Bagian', '3', 800000),
	(16, 'Analis Bagian', '1', 400000),
	(17, 'Analis Bagian', '2', 600000),
	(18, 'Analis Bagian', '3', 800000),
	(19, 'Staff', '1', 400000),
	(20, 'Staff', '2', 600000),
	(21, 'Staff', '3', 800000),
	(22, 'Supir', '1', 400000),
	(23, 'Supir', '2', 600000),
	(24, 'Supir', '3', 800000);

-- Dumping structure for table simpedin.transport
CREATE TABLE IF NOT EXISTS `transport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `jabatan` varchar(255) NOT NULL,
  `jenis` varchar(255) NOT NULL,
  `transport` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.transport: ~16 rows (approximately)
INSERT INTO `transport` (`id`, `jabatan`, `jenis`, `transport`) VALUES
	(1, 'Rektor / Wakil Rektor', 'Darat', 400000),
	(2, 'Rektor / Wakil Rektor', 'Udara', 600000),
	(3, 'Kepala Biro', 'Darat', 400000),
	(4, 'Kepala Biro', 'Udara', 600000),
	(5, 'Manager', 'Darat', 400000),
	(6, 'Manager', 'Udara', 600000),
	(7, 'Deputi Manager', 'Darat', 400000),
	(8, 'Deputi Manager', 'Udara', 600000),
	(9, 'Sekretaris Bagian', 'Darat', 400000),
	(10, 'Sekretaris Bagian', 'Udara', 600000),
	(11, 'Analis Bagian', 'Darat', 400000),
	(12, 'Analis Bagian', 'Udara', 600000),
	(13, 'Staff', 'Darat', 400000),
	(14, 'Staff', 'Udara', 600000),
	(15, 'Supir', 'Darat', 400000),
	(16, 'Supir', 'Darat', 600000);

-- Dumping structure for table simpedin.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` int NOT NULL COMMENT '0=superuser; 1=user;',
  `nama_lengkap` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table simpedin.user: ~2 rows (approximately)
INSERT INTO `user` (`id`, `username`, `password`, `role`, `nama_lengkap`) VALUES
	(1, 'filan', '123456', 0, 'Filan Firmansyah'),
	(3, 'rizki', '12345678', 0, 'Rizki Aulia');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
