-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2023 at 12:19 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `simpedin`
--

-- --------------------------------------------------------

--
-- Table structure for table `nomor`
--

CREATE TABLE `nomor` (
  `id` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nomor`
--

INSERT INTO `nomor` (`id`, `total`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `nip` varchar(15) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `bagian` varchar(255) NOT NULL,
  `jabatan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`nip`, `nama`, `bagian`, `jabatan`) VALUES
('2020', 'Firman', 'BSDM', 'Sekretaris Bagian'),
('20201', 'Lan', 'BAUM', 'Staff'),
('202020', 'Angki', 'BRP', 'Analis Bagian'),
('2022', 'Aldi', 'BPDSI', 'Deputi Manager'),
('2023', 'Filan', 'BAA', 'Analis Bagian'),
('432', 'fd', 'gds', 'Staff');

-- --------------------------------------------------------

--
-- Table structure for table `sppd`
--

CREATE TABLE `sppd` (
  `id` int(11) NOT NULL,
  `no_sppd` varchar(50) NOT NULL,
  `kepada` varchar(255) NOT NULL,
  `perihal` varchar(255) NOT NULL,
  `tanggal_pergi` date NOT NULL,
  `tanggal_kembali` date NOT NULL,
  `jumlah_hari` int(30) NOT NULL,
  `kota_tujuan` varchar(255) NOT NULL,
  `transportasi` varchar(255) NOT NULL,
  `zonasi` int(30) NOT NULL,
  `lumpsum` int(11) NOT NULL,
  `total_lumpsum` int(11) NOT NULL,
  `transport` int(11) NOT NULL,
  `total_transport` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `keterangan` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sppd`
--

INSERT INTO `sppd` (`id`, `no_sppd`, `kepada`, `perihal`, `tanggal_pergi`, `tanggal_kembali`, `jumlah_hari`, `kota_tujuan`, `transportasi`, `zonasi`, `lumpsum`, `total_lumpsum`, `transport`, `total_transport`, `total`, `keterangan`) VALUES
(1, '0001/SPPD/1/C0/05/2023', 'Filan', 'SPPD dalam rangka kunjungan ke Data Center ITPLN di Bandung tanggal 9 Mei 2023', '2023-05-09', '2023-05-09', 1, 'Bandung', 'Darat', 3, 400000, 400000, 400000, 400000, 1200000, '');

-- --------------------------------------------------------

--
-- Table structure for table `tarif`
--

CREATE TABLE `tarif` (
  `id` int(11) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `zonasi` varchar(10) NOT NULL,
  `tarif` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tarif`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `transport`
--

CREATE TABLE `transport` (
  `id` int(11) NOT NULL,
  `jabatan` varchar(255) NOT NULL,
  `jenis` varchar(255) NOT NULL,
  `transport` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transport`
--

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

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` int(11) NOT NULL COMMENT '0=superuser; 1=user;',
  `nama_lengkap` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `nama_lengkap`) VALUES
(1, 'filan', '123456', 0, 'Filan Firmansyah');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nomor`
--
ALTER TABLE `nomor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`nip`);

--
-- Indexes for table `sppd`
--
ALTER TABLE `sppd`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tarif`
--
ALTER TABLE `tarif`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `nomor`
--
ALTER TABLE `nomor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sppd`
--
ALTER TABLE `sppd`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tarif`
--
ALTER TABLE `tarif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `transport`
--
ALTER TABLE `transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
