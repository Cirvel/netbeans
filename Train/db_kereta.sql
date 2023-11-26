-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 11, 2023 at 09:23 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kereta`
--

-- --------------------------------------------------------

--
-- Table structure for table `jurusan`
--

CREATE TABLE `jurusan` (
  `id_jurusan` int(11) NOT NULL,
  `jurusan` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jurusan`
--

INSERT INTO `jurusan` (`id_jurusan`, `jurusan`, `harga`) VALUES
(2, 'YOGYAKARTA-JAKARTA', 900000),
(3, 'JAKARTA-YOGYAKARTA', 900000),
(4, 'SURABAYA-JAKARTA', 1000000),
(5, 'SURABAYA-JAKARTA', 1000000),
(6, 'BANDUNG-JAKARTA', 800000),
(7, 'JAKARTA-BANDUNG', 800000);

-- --------------------------------------------------------

--
-- Table structure for table `kereta`
--

CREATE TABLE `kereta` (
  `id_kereta` varchar(11) NOT NULL,
  `nama_kereta` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kereta`
--

INSERT INTO `kereta` (`id_kereta`, `nama_kereta`) VALUES
('KRL001', 'ARGO PARAHYANGAN'),
('KRL002', 'ARGO BROMO'),
('KRL003', 'SEMBRANI');

-- --------------------------------------------------------

--
-- Table structure for table `tiket`
--

CREATE TABLE `tiket` (
  `id_tiket` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `id_kereta` varchar(11) NOT NULL,
  `nama_kereta` varchar(255) NOT NULL,
  `id_jurusan` int(11) NOT NULL,
  `jurusan` varchar(255) NOT NULL,
  `jenis_tiket` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah_beli` int(255) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tiket`
--

INSERT INTO `tiket` (`id_tiket`, `nama`, `id_kereta`, `nama_kereta`, `id_jurusan`, `jurusan`, `jenis_tiket`, `harga`, `jumlah_beli`, `total_harga`) VALUES
(9, 'Greg Paul', 'KRL002', 'ARGO BROMO', 3, 'JAKARTA-YOGYAKARTA', 'Bisnis', 900000, 2, 1800000),
(10, 'Boris Gvardiya', 'KRL003', 'SEMBRANI', 6, 'BANDUNG-JAKARTA', 'Ekonomi', 800000, 1, 800000),
(11, 'John Smith', 'KRL001', 'ARGO PARAHYANGAN', 2, 'YOGYAKARTA-JAKARTA', 'Ekspres', 900000, 4, 3600000),
(12, 'Oki Takato', 'KRL002', 'ARGO BROMO', 4, 'SURABAYA-JAKARTA', 'Eksekutif', 1000000, 2, 1000000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`id_jurusan`);

--
-- Indexes for table `kereta`
--
ALTER TABLE `kereta`
  ADD PRIMARY KEY (`id_kereta`);

--
-- Indexes for table `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`id_tiket`),
  ADD KEY `id_kereta` (`id_kereta`),
  ADD KEY `id_jurusan` (`id_jurusan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jurusan`
--
ALTER TABLE `jurusan`
  MODIFY `id_jurusan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tiket`
--
ALTER TABLE `tiket`
  MODIFY `id_tiket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
