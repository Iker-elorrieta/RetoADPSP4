-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-12-2020 a las 23:14:51
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `euskalmet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entornos`
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
-- Estructura de tabla para la tabla `entornosmuni`
--

CREATE TABLE `entornosmuni` (
  `Entorno` int(11) NOT NULL,
  `Municipio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones`
--

CREATE TABLE `estaciones` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Provincia` varchar(1000) DEFAULT NULL,
  `Municipio` int(11) NOT NULL,
  `Direccion` varchar(500) NOT NULL,
  `CoordenadaX` double DEFAULT NULL,
  `CoordenadaY` double DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `Id` int(11),
  `Fecha` date,
  `Hora` varchar(10),
  `COmgm3` double,
  `NOgm3` double,
  `NO2` double,
  `NO2ICA` varchar(50),
  `NOXgm3` double,
  `PM10` double,
  `PM10ICA` varchar(50),
  `PM25` double,
  `PM25ICA` varchar(50),
  `SO2` double,
  `SO2ICA` varchar(50),
  `ICAEstacion` varchar(50),
  `Informe` int(11)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `informes`
--

CREATE TABLE `informes` (
  `Id` int(11) NOT NULL,
  `Formato` varchar(1000) DEFAULT NULL,
  `Estacion` int(11) NOT NULL,
  `Url` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `municipios`
--

CREATE TABLE `municipios` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Descripcion` varchar(1000) DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL,
  `Codigo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entornos`
--
ALTER TABLE `entornos`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`);

--
-- Indices de la tabla `entornosmuni`
--
ALTER TABLE `entornosmuni`
  ADD KEY `Entorno` (`Entorno`),
  ADD KEY `Municipio` (`Municipio`);

--
-- Indices de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`),
  ADD KEY `Municipio` (`Municipio`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Informe` (`Informe`);

--
-- Indices de la tabla `informes`
--
ALTER TABLE `informes`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Url` (`Url`),
  ADD KEY `Nombre` (`Estacion`),
  ADD KEY `Id` (`Id`);

--
-- Indices de la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD PRIMARY KEY (`Id`) USING BTREE,
  ADD UNIQUE KEY `Nombre` (`Nombre`),
  ADD KEY `Id` (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entornos`
--
ALTER TABLE `entornos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `informes`
--
ALTER TABLE `informes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT de la tabla `municipios`
--
ALTER TABLE `municipios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entornosmuni`
--
ALTER TABLE `entornosmuni`
  ADD CONSTRAINT `entornosmuni_ibfk_1` FOREIGN KEY (`Entorno`) REFERENCES `entornos` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entornosmuni_ibfk_2` FOREIGN KEY (`Municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD CONSTRAINT `estaciones_ibfk_1` FOREIGN KEY (`Municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`Informe`) REFERENCES `informes` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `informes`
--
ALTER TABLE `informes`
  ADD CONSTRAINT `informes_ibfk_1` FOREIGN KEY (`Estacion`) REFERENCES `estaciones` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
