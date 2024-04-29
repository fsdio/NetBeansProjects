-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 12, 2023 at 10:42 PM
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
-- Database: `201943501282_mineral`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getOrder` (IN `inUSER` VARCHAR(50), IN `start` BIGINT(20), IN `end` BIGINT(20))   SELECT O.id, O.date, O.alamat, O.quantity, O.harga, O.type_buy, O.status_buy FROM `order` AS O WHERE O.username=inUSer AND (O.date BETWEEN start AND end)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getProfileTopup` (IN `inUSER` VARCHAR(50), IN `start` BIGINT(20), IN `end` BIGINT(20))  DETERMINISTIC SELECT T.id, T.status, T.nominal, T.type_buy, U.phone, U.saldo, T.date, U.password, T.catatan FROM `topup` AS T JOIN `user` AS U ON T.username=U.username WHERE U.username=inUSER AND ( T.date BETWEEN start AND end )$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `order`
--

CREATE TABLE `order` (
  `id` int(5) NOT NULL,
  `username` varchar(50) NOT NULL,
  `alamat` text NOT NULL,
  `quantity` int(3) NOT NULL,
  `harga` int(11) NOT NULL,
  `type_buy` enum('cod','cash','saldo') NOT NULL,
  `status_buy` enum('batal','berhasil','proses') NOT NULL DEFAULT 'proses',
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `order`
--

INSERT INTO `order` (`id`, `username`, `alamat`, `quantity`, `harga`, `type_buy`, `status_buy`, `date`) VALUES
(20, 'user', 'Bandung', 1, 5000, 'cash', 'berhasil', 1691854087000);

-- --------------------------------------------------------

--
-- Table structure for table `topup`
--

CREATE TABLE `topup` (
  `id` int(5) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nominal` int(11) NOT NULL,
  `type_buy` enum('qris','bank') NOT NULL,
  `status` enum('tolak','proses','terima') NOT NULL,
  `catatan` text NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `topup`
--

INSERT INTO `topup` (`id`, `username`, `nominal`, `type_buy`, `status`, `catatan`, `date`) VALUES
(2, 'user', 10000, 'bank', 'terima', '#', 1691824782425),
(3, 'user', 75000, 'bank', 'terima', '', 1691826195000),
(4, 'user', 10000, 'qris', 'terima', '', 1691826718000),
(5, 'user', 20000, 'qris', 'terima', 'biasa kak', 1691828066000),
(6, 'user', 20000, 'qris', 'terima', 'hemat', 1691828222000),
(7, 'yajit', 25000, 'qris', 'terima', 'bang sudah bayar', 1691854540000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `saldo` int(11) NOT NULL DEFAULT 0,
  `type` enum('admin','pelanggan') NOT NULL DEFAULT 'pelanggan'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `password`, `phone`, `saldo`, `type`) VALUES
('admin', 'admin', '083174506600', 250000, 'admin'),
('user', 'user', '083174506601', 105000, 'pelanggan'),
('yajit', 'yajit', '0831', 25000, 'pelanggan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user` (`username`);

--
-- Indexes for table `topup`
--
ALTER TABLE `topup`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_topup` (`username`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order`
--
ALTER TABLE `order`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `topup`
--
ALTER TABLE `topup`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `order`
--
ALTER TABLE `order`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `topup`
--
ALTER TABLE `topup`
  ADD CONSTRAINT `fk_topup` FOREIGN KEY (`username`) REFERENCES `user` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
