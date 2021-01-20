-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 20, 2021 at 05:30 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `euskalmet`
--

-- --------------------------------------------------------

--
-- Table structure for table `entornos`
--

CREATE TABLE `entornos` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(5000) DEFAULT NULL,
  `Tipo` varchar(100) DEFAULT NULL,
  `Territorio` varchar(100) DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `entornosmuni`
--

CREATE TABLE `entornosmuni` (
  `Entorno` int(11) NOT NULL,
  `Municipio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `estaciones`
--

CREATE TABLE `estaciones` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Provincia` int(11) NOT NULL,
  `Municipio` int(11) NOT NULL,
  `Direccion` varchar(500) NOT NULL,
  `CoordenadaX` double DEFAULT NULL,
  `CoordenadaY` double DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `horario`
--

CREATE TABLE `horario` (
  `Id` int(11) NOT NULL,
  `Fecha` date DEFAULT NULL,
  `Hora` varchar(10) DEFAULT NULL,
  `COmgm3` double DEFAULT NULL,
  `NOgm3` double DEFAULT NULL,
  `NO2` double DEFAULT NULL,
  `NO2ICA` varchar(50) DEFAULT NULL,
  `NOXgm3` double DEFAULT NULL,
  `PM10` double DEFAULT NULL,
  `PM10ICA` varchar(50) DEFAULT NULL,
  `PM25` double DEFAULT NULL,
  `PM25ICA` varchar(50) DEFAULT NULL,
  `SO2` double DEFAULT NULL,
  `SO2ICA` varchar(50) DEFAULT NULL,
  `ICAEstacion` varchar(50) DEFAULT NULL,
  `Informe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `informes`
--

CREATE TABLE `informes` (
  `Id` int(11) NOT NULL,
  `Formato` varchar(1000) DEFAULT NULL,
  `Estacion` int(11) NOT NULL,
  `Url` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `municipios`
--

CREATE TABLE `municipios` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Provincia` int(11) NOT NULL,
  `Descripcion` varchar(1000) DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL,
  `Codigo` varchar(270) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `provincias`
--

CREATE TABLE `provincias` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE `usuario` (
  `usuario` varchar(20) NOT NULL,
  `e-mail` varchar(30) NOT NULL,
  `contrasena` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `entornos`
--
ALTER TABLE `entornos`
  ADD PRIMARY KEY (`Id`),
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`);

--
-- Indexes for table `entornosmuni`
--
ALTER TABLE `entornosmuni`
  ADD KEY `Entorno` (`Entorno`),
  ADD KEY `Municipio` (`Municipio`);

--
-- Indexes for table `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`),
  ADD KEY `Municipio` (`Municipio`),
  ADD KEY `Provincia` (`Provincia`);

--
-- Indexes for table `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Informe` (`Informe`);

--
-- Indexes for table `informes`
--
ALTER TABLE `informes`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Url` (`Url`),
  ADD KEY `Nombre` (`Estacion`),
  ADD KEY `Id` (`Id`);

--
-- Indexes for table `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`),
  ADD KEY `Provincia` (`Provincia`);

--
-- Indexes for table `provincias`
--
ALTER TABLE `provincias`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`usuario`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `entornos`
--
ALTER TABLE `entornos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=489;

--
-- AUTO_INCREMENT for table `estaciones`
--
ALTER TABLE `estaciones`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=215;

--
-- AUTO_INCREMENT for table `horario`
--
ALTER TABLE `horario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1949;

--
-- AUTO_INCREMENT for table `informes`
--
ALTER TABLE `informes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=292;

--
-- AUTO_INCREMENT for table `municipios`
--
ALTER TABLE `municipios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2257;

--
-- AUTO_INCREMENT for table `provincias`
--
ALTER TABLE `provincias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `entornosmuni`
--
ALTER TABLE `entornosmuni`
  ADD CONSTRAINT `entornosmuni_ibfk_1` FOREIGN KEY (`Entorno`) REFERENCES `entornos` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entornosmuni_ibfk_2` FOREIGN KEY (`Municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `estaciones`
--
ALTER TABLE `estaciones`
  ADD CONSTRAINT `estaciones_ibfk_1` FOREIGN KEY (`Municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `estaciones_ibfk_2` FOREIGN KEY (`Provincia`) REFERENCES `provincias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`Informe`) REFERENCES `informes` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `informes`
--
ALTER TABLE `informes`
  ADD CONSTRAINT `informes_ibfk_1` FOREIGN KEY (`Estacion`) REFERENCES `estaciones` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `municipios_ibfk_1` FOREIGN KEY (`Provincia`) REFERENCES `provincias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
