-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2023 at 05:43 AM
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
-- Database: `db_spp`
--

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(11) NOT NULL,
  `nama_kelas` varchar(10) NOT NULL,
  `kompetensi_keahlian` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `nama_kelas`, `kompetensi_keahlian`) VALUES
(1, 'X-TKI. 1', 'Teknologi Komunikasi Informatika'),
(2, 'X-TKI. 2', 'Teknologi Komunikasi Informatika'),
(3, 'X-TKI. 3', 'Teknologi Komunikasi Informatika'),
(4, 'X-TKI. 4', 'Teknologi Komunikasi Informatika'),
(5, 'X-MP', 'Menuju Perkantoran'),
(6, 'XI-MM1', 'Multimedia'),
(7, 'XI-MM2', 'Multimedia'),
(8, 'XI-MM3', 'Multimedia'),
(9, 'XI-RPL', 'Rekayasa Perangkat Lunak'),
(10, 'XI-OTKP', 'Otomatisasi Tata Kelola Perkantoran'),
(11, 'XII-MM1', 'Multimedia'),
(12, 'XII-MM2', 'Multimedia'),
(13, 'XII-RPL', 'Rekayasa Perangkat Lunak'),
(14, 'XII-OTKP', 'Otomatisasi Tata Kelola Perkantoran');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `id_pembayaran` int(11) NOT NULL,
  `id_petugas` int(11) NOT NULL,
  `nisn` int(10) NOT NULL,
  `tgl_bayar` date NOT NULL,
  `bulan_dibayar` varchar(8) NOT NULL,
  `tahun_dibayar` varchar(4) NOT NULL,
  `id_spp` int(11) NOT NULL,
  `jumlah_bayar` int(11) NOT NULL COMMENT 'Rupiah'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`id_pembayaran`, `id_petugas`, `nisn`, `tgl_bayar`, `bulan_dibayar`, `tahun_dibayar`, `id_spp`, `jumlah_bayar`) VALUES
(1, 2, 2, '2023-11-01', 'Januari', '2023', 4, 150000),
(2, 6, 9, '2023-11-01', 'Februari', '2024', 5, 200000),
(3, 2, 6, '2023-11-01', 'Februari', '2023', 4, 125000),
(4, 1, 12, '2023-11-02', 'Mei', '2024', 5, 100000),
(5, 1, 10, '2023-11-02', 'Maret', '2022', 3, 125000),
(6, 1, 5, '2023-11-02', 'November', '2024', 5, 90000),
(7, 4, 11, '2023-11-02', 'Mei', '2023', 4, 200000),
(8, 4, 8, '2023-11-02', 'Juli', '2023', 4, 150000),
(9, 7, 12, '2023-11-03', 'Juni', '2024', 5, 125000),
(10, 1, 9, '2023-11-03', 'Januari', '2024', 5, 250000),
(11, 7, 1, '2023-11-03', 'Agustus', '2022', 3, 240000),
(12, 6, 8, '2023-11-04', 'Mei', '2023', 4, 130000),
(13, 6, 3, '2023-11-04', 'Februari', '2023', 4, 400000),
(14, 6, 9, '2023-11-04', 'April', '2024', 5, 50000),
(15, 1, 8, '2023-11-06', 'April', '2023', 4, 150000),
(16, 1, 1, '2023-11-06', 'Januari', '2022', 3, 120000),
(17, 1, 1, '2023-11-06', 'Februari', '2022', 3, 120000),
(19, 1, 3, '2023-11-06', 'Januari', '2023', 4, 100000),
(20, 1, 17, '2023-12-01', 'Juli', '2024', 5, 40000000),
(21, 1, 9, '2023-11-06', 'Maret', '2024', 5, 56000),
(22, 1, 8, '2023-11-06', 'Juni', '2023', 4, 89000);

-- --------------------------------------------------------

--
-- Table structure for table `petugas`
--

CREATE TABLE `petugas` (
  `id_petugas` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nama_petugas` varchar(50) NOT NULL,
  `level` enum('admin','petugas') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `petugas`
--

INSERT INTO `petugas` (`id_petugas`, `username`, `password`, `nama_petugas`, `level`) VALUES
(1, 'admin', 'admin', 'Jean-Louise-Le-Petit Oui-Oui-Deboire', 'admin'),
(2, 'petugas', 'petugas', 'Barry Aberfoth Sherrington Dukes Cunningham III', 'petugas'),
(3, 'petugas2', 'petugas2', 'Fritz Dietrich Von Hamburg Paul Schlesien', 'petugas'),
(4, 'petugas3', 'petugas3', 'Boris Aleksandrovitch Kovalski Donova Torestkinaya', 'petugas'),
(5, 'admin2', 'admin2', 'Juan Halvarez Los Suenoz Linha De Tigo', 'admin'),
(6, 'nimrod', 'nimrod', 'JACOB', 'admin'),
(7, 'petugas4', 'petugas4', 'Konagawa Izibuchi Shimazi Ki No Takahisa', 'petugas'),
(8, 'peteugas5', 'peteugas5', 'Uvuvuevuevue Anatwewewe', 'petugas'),
(9, 'admin3', 'admin3', 'Margot Amber Pavolia Izbucni', 'admin'),
(10, 'petugas6', 'petugas6', 'Mei Liung Bei Hu Tou Kong', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nisn` int(10) NOT NULL,
  `nis` char(8) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `alamat` text NOT NULL,
  `no_tlp` varchar(13) NOT NULL,
  `id_spp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nisn`, `nis`, `nama`, `id_kelas`, `alamat`, `no_tlp`, `id_spp`) VALUES
(1, '39405357', 'Lucas Kumiega', 1, 'Kurwo Str., Peacesaw', '483-2039-9932', 3),
(2, '58238492', 'Ludwig Axer', 7, 'Austro Road, Bodapest', '832-9044-0438', 4),
(3, '27035734', 'Aleksei Sudayev', 8, '227 Street, Musskow', '921-1942-0233', 4),
(4, '75932314', 'Margot Gratte', 9, 'Goering Strasse, Merlin', '821-4202-2110', 4),
(5, '94826538', 'Pierre Lefayette', 11, 'Mugen Route, Jaris', '722-9382-4823', 5),
(6, '84239869', 'John Clarke', 10, 'Baker Street, Mashington PC', '832-9322-1329', 4),
(7, '92339483', 'Travis Croats', 4, 'Stonewall Frontier, Teksias', '932-3448-2332', 3),
(8, '93725431', 'Juan Halvarez', 9, 'Roa De Janito, Burasile', '823-9234-3292', 4),
(9, '48670923', 'Genie Stoner', 14, 'Trove Reule, Brittany', '823-3492-3251', 5),
(10, '38239846', 'Zho Tao', 3, 'Senhou Street, Dong Tong', '621-0993-4630', 3),
(11, '53639382', 'Jane Entiach', 7, 'Freddy Alley, Mondon', '592-8323-6498', 4),
(12, '49207382', 'Kyuji Konagawa', 11, 'Arisu Track, Dokyo', '923-9492-9423', 5),
(13, '49231822', 'Gomen Yuuka', 6, 'Millenium Park, Ark', '832-9423-5999', 4),
(17, '38491945', 'Leo Vincada', 11, '5th Route, Mediolanum', '772-3924-5733', 5),
(18, '54782023', 'Donatello Vivida', 9, 'Mid Park, Tannenberg', '832-9594-2634', 4),
(19, '52484200', 'Mai Ryouna', 3, 'Kokutai Street, Iwate', '532-8234-3284', 4),
(20, '58393690', 'William Charles', 11, '2nd Sweet Road, New Dork', '662-4823-1384', 5),
(21, '23491274', 'Fajar Dawn', 12, '20th Road, Sifutown', '832-9231-5722', 5),
(22, '88321143', 'Hatami Erika', 10, 'Gohome Path, Gehenna', '892-3882-5589', 4),
(23, '19942024', 'Jane Does', 4, 'Leeroy Street, Calicornia', '921-2993-8843', 3);

-- --------------------------------------------------------

--
-- Table structure for table `spp`
--

CREATE TABLE `spp` (
  `id_spp` int(11) NOT NULL,
  `tahun` int(11) NOT NULL,
  `nominal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `spp`
--

INSERT INTO `spp` (`id_spp`, `tahun`, `nominal`) VALUES
(1, 2020, 100000),
(2, 2021, 200000),
(3, 2022, 300000),
(4, 2023, 450000),
(5, 2024, 600000),
(6, 2025, 800000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`id_pembayaran`),
  ADD KEY `id_petugas` (`id_petugas`),
  ADD KEY `id_spp` (`id_spp`),
  ADD KEY `nisn` (`nisn`);

--
-- Indexes for table `petugas`
--
ALTER TABLE `petugas`
  ADD PRIMARY KEY (`id_petugas`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nisn`),
  ADD UNIQUE KEY `nis` (`nis`),
  ADD KEY `id_kelas` (`id_kelas`),
  ADD KEY `id_spp` (`id_spp`);

--
-- Indexes for table `spp`
--
ALTER TABLE `spp`
  ADD PRIMARY KEY (`id_spp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kelas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `id_pembayaran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `petugas`
--
ALTER TABLE `petugas`
  MODIFY `id_petugas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `nisn` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `spp`
--
ALTER TABLE `spp`
  MODIFY `id_spp` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`id_petugas`) REFERENCES `petugas` (`id_petugas`),
  ADD CONSTRAINT `pembayaran_ibfk_2` FOREIGN KEY (`nisn`) REFERENCES `siswa` (`nisn`),
  ADD CONSTRAINT `pembayaran_ibfk_3` FOREIGN KEY (`id_spp`) REFERENCES `spp` (`id_spp`);

--
-- Constraints for table `siswa`
--
ALTER TABLE `siswa`
  ADD CONSTRAINT `siswa_ibfk_1` FOREIGN KEY (`id_spp`) REFERENCES `spp` (`id_spp`),
  ADD CONSTRAINT `siswa_ibfk_2` FOREIGN KEY (`id_kelas`) REFERENCES `kelas` (`id_kelas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
