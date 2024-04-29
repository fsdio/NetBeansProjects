-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 18, 2023 at 07:03 PM
-- Server version: 11.2.0-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `201943501285_absensi`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetAbsenUser` (IN `inUser` VARCHAR(50), IN `inDate` BIGINT(20))  DETERMINISTIC SELECT A.status, A.confirm, A.lembur, A.terlambat, A.denda, A.catatan FROM `tb_absen` AS A  WHERE A.karyawan=inUser  AND A.date=inDate$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetCountDescription` (IN `startDays` BIGINT(15), IN `endDays` BIGINT(15))   BEGIN
  DECLARE sum_karyawan INT;
  DECLARE sum_admin INT;
  DECLARE sum_divisi INT;
  DECLARE sum_pola_kerja INT;
  DECLARE sum_hadir INT;
  DECLARE sum_terlambat INT;
  DECLARE sum_izin INT;
  DECLARE sum_cuti INT;
  
  SELECT COUNT(*) INTO sum_karyawan FROM `tb_karyawan` WHERE `divisi` <> 'admin';
  SELECT COUNT(*) INTO sum_admin FROM `tb_karyawan` WHERE `divisi` = 'admin';
  SELECT COUNT(*) INTO sum_divisi FROM `tb_divisi`;
  SELECT COUNT(*) INTO sum_pola_kerja FROM `tb_pola_kerja`;
  SELECT COUNT(*) INTO sum_hadir FROM `tb_absen` WHERE (`status`='hadir' AND (date BETWEEN startDays AND endDays));
  SELECT COUNT(*) INTO sum_terlambat FROM `tb_absen` WHERE ((`status`='hadir' AND `terlambat`=1) AND (date BETWEEN startDays AND endDays));
  SELECT COUNT(*) INTO sum_izin FROM `tb_absen` WHERE (`status`='izin' AND (date BETWEEN startDays AND endDays));
  SELECT COUNT(*) INTO sum_cuti FROM `tb_absen` WHERE (`status`='cuti' AND (date BETWEEN startDays AND endDays));
  
  SELECT sum_karyawan, sum_admin, sum_divisi, sum_pola_kerja, sum_hadir, sum_terlambat, sum_izin, sum_cuti;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetProfile` (IN `inUsername` VARCHAR(50), IN `startMonth` BIGINT(20), IN `endMonth` BIGINT(20))  DETERMINISTIC SELECT K.*, SUM(G.bonus), SUM(G.denda+A.denda), D.gaji, ((D.gaji-SUM(G.denda+A.denda))+SUM(G.bonus)) AS `gaji` FROM `tb_karyawan` AS K JOIN `tb_divisi` AS D ON K.divisi=D.nama JOIN `tb_gaji` AS G ON G.karyawan=K.nama JOIN `tb_absen` AS A ON A.karyawan=K.nama WHERE (K.nama = inUsername) AND (G.date BETWEEN startMonth AND endMonth)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTableAbsen` (IN `inStatus` VARCHAR(10), IN `inDate` BIGINT(15))  DETERMINISTIC SELECT `karyawan`, `confirm`, `lembur`, `terlambat`, `denda`, `catatan`, `status`, `date` FROM tb_absen WHERE (status=inStatus AND date=inDate)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTableDays` (IN `startDays` BIGINT(15), IN `endDays` BIGINT(15))   BEGIN

SELECT K.nama, K.jabatan, K.divisi, A.status FROM `tb_karyawan` AS `K` JOIN `tb_absen` AS `A` ON K.nama=A.karyawan WHERE A.date BETWEEN startDays AND endDays;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `GetTableGaji` (IN `currentDate` BIGINT(15))  DETERMINISTIC SELECT g.karyawan, g.bonus, g.denda, a.lembur, d.gaji, ((g.bonus+(a.lembur*150000)+d.gaji)-g.denda) AS total FROM `tb_gaji` AS g JOIN `tb_karyawan` AS k ON g.karyawan=k.nama JOIN tb_divisi AS d ON k.divisi=d.nama JOIN `tb_absen` AS a ON k.nama=a.karyawan WHERE g.date=currentDate$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_absen`
--

CREATE TABLE `tb_absen` (
  `karyawan` varchar(50) NOT NULL,
  `status` enum('cuti','izin','alpa','hadir','libur') NOT NULL DEFAULT 'alpa',
  `catatan` varchar(250) DEFAULT NULL,
  `absen_in` bigint(15) DEFAULT NULL,
  `absen_out` bigint(15) DEFAULT NULL,
  `date` bigint(15) NOT NULL,
  `lembur` tinyint(1) NOT NULL DEFAULT 0,
  `terlambat` tinyint(1) NOT NULL DEFAULT 0,
  `confirm` tinyint(1) NOT NULL DEFAULT 0,
  `denda` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_absen`
--

INSERT INTO `tb_absen` (`karyawan`, `status`, `catatan`, `absen_in`, `absen_out`, `date`, `lembur`, `terlambat`, `confirm`, `denda`) VALUES
('admin', 'hadir', '#', 1690998363000, 1690998363000, 1690995600000, 1, 1, 1, 10000),
('rizki wahyudi', 'hadir', '#', 1692369395034, 1691082000000, 1692291600000, 1, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_divisi`
--

CREATE TABLE `tb_divisi` (
  `nama` varchar(50) NOT NULL,
  `gaji` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_divisi`
--

INSERT INTO `tb_divisi` (`nama`, `gaji`) VALUES
('admin', 5000000),
('cleaning service', 5000000),
('sales', 6000000),
('security', 5500000),
('service advisor', 7000000),
('software developer', 8000000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_gaji`
--

CREATE TABLE `tb_gaji` (
  `karyawan` varchar(50) NOT NULL,
  `bonus` int(11) DEFAULT NULL,
  `denda` int(11) DEFAULT NULL,
  `date` bigint(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_gaji`
--

INSERT INTO `tb_gaji` (`karyawan`, `bonus`, `denda`, `date`) VALUES
('rizki wahyudi', 1000, 50000, 1691082000000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_karyawan`
--

CREATE TABLE `tb_karyawan` (
  `nama` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL,
  `gender` enum('pria','wanita') NOT NULL,
  `ktp` varchar(16) NOT NULL,
  `divisi` varchar(50) NOT NULL,
  `jabatan` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `pola_kerja` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_karyawan`
--

INSERT INTO `tb_karyawan` (`nama`, `password`, `gender`, `ktp`, `divisi`, `jabatan`, `phone`, `pola_kerja`) VALUES
('admin', 'admin', 'pria', '3212345645356674', 'admin', 'admin', '083', 'admin'),
('rizki wahyudi', 'rizki', 'wanita', '3206452143', 'service advisor', 'Programming', '123', 'karyawan');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pola_kerja`
--

CREATE TABLE `tb_pola_kerja` (
  `nama` varchar(50) NOT NULL,
  `absen_in` time NOT NULL,
  `absen_out` time NOT NULL,
  `catatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_pola_kerja`
--

INSERT INTO `tb_pola_kerja` (`nama`, `absen_in`, `absen_out`, `catatan`) VALUES
('admin', '08:00:00', '17:00:00', 'ADMIN'),
('karyawan', '08:00:00', '17:00:00', 'jadwal masuk dan keluar karyawan'),
('sales', '09:00:00', '15:00:00', 'jangan terlambat ok.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_absen`
--
ALTER TABLE `tb_absen`
  ADD PRIMARY KEY (`karyawan`,`date`);

--
-- Indexes for table `tb_divisi`
--
ALTER TABLE `tb_divisi`
  ADD PRIMARY KEY (`nama`);

--
-- Indexes for table `tb_gaji`
--
ALTER TABLE `tb_gaji`
  ADD PRIMARY KEY (`karyawan`);

--
-- Indexes for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD PRIMARY KEY (`nama`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD UNIQUE KEY `phone_2` (`phone`),
  ADD KEY `pola_kerja` (`pola_kerja`),
  ADD KEY `divisi` (`divisi`);

--
-- Indexes for table `tb_pola_kerja`
--
ALTER TABLE `tb_pola_kerja`
  ADD PRIMARY KEY (`nama`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_absen`
--
ALTER TABLE `tb_absen`
  ADD CONSTRAINT `tb_absen_ibfk_1` FOREIGN KEY (`karyawan`) REFERENCES `tb_karyawan` (`nama`);

--
-- Constraints for table `tb_gaji`
--
ALTER TABLE `tb_gaji`
  ADD CONSTRAINT `tb_gaji_ibfk_1` FOREIGN KEY (`karyawan`) REFERENCES `tb_karyawan` (`nama`);

--
-- Constraints for table `tb_karyawan`
--
ALTER TABLE `tb_karyawan`
  ADD CONSTRAINT `tb_karyawan_ibfk_3` FOREIGN KEY (`pola_kerja`) REFERENCES `tb_pola_kerja` (`nama`),
  ADD CONSTRAINT `tb_karyawan_ibfk_4` FOREIGN KEY (`divisi`) REFERENCES `tb_divisi` (`nama`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
