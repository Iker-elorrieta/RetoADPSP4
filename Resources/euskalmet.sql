-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-01-2021 a las 16:01:10
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
  `Provincia` int(11) NOT NULL,
  `Descripcion` varchar(1000) DEFAULT NULL,
  `Latitud` double DEFAULT NULL,
  `Longitud` double DEFAULT NULL,
  `Codigo` varchar(270) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `provincias`
--

CREATE TABLE `provincias` (
  `id` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(20) NOT NULL,
  `Contrasena` varchar(30) NOT NULL,
  `Pregunta` varchar(50) NOT NULL,
  `Respuesta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `entornos`
--
ALTER TABLE `entornos`
  ADD PRIMARY KEY (`Id`),
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
  ADD KEY `Id` (`Id`),
  ADD KEY `Provincia` (`Provincia`);

--
-- Indices de la tabla `provincias`
--
ALTER TABLE `provincias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `entornos`
--
ALTER TABLE `entornos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=489;

--
-- AUTO_INCREMENT de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=215;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1949;

--
-- AUTO_INCREMENT de la tabla `informes`
--
ALTER TABLE `informes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=292;

--
-- AUTO_INCREMENT de la tabla `municipios`
--
ALTER TABLE `municipios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2258;

--
-- AUTO_INCREMENT de la tabla `provincias`
--
ALTER TABLE `provincias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

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

--
-- Filtros para la tabla `municipios`
--
ALTER TABLE `municipios`
  ADD CONSTRAINT `municipios_ibfk_1` FOREIGN KEY (`Provincia`) REFERENCES `provincias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
