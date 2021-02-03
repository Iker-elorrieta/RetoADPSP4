-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2021 a las 16:44:03
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
-- Estructura de tabla para la tabla `entornosfavoritos`
--

CREATE TABLE `entornosfavoritos` (
  `usuario` int(11) NOT NULL,
  `entorno` int(11) NOT NULL
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
-- Estructura de tabla para la tabla `fotos`
--

CREATE TABLE `fotos` (
  `Id` int(11) NOT NULL,
  `Usuario` int(11) NOT NULL,
  `Foto` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotosentornos`
--

CREATE TABLE `fotosentornos` (
  `Id` int(11) NOT NULL,
  `Usuario` int(11) NOT NULL,
  `Entorno` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotosmunicipios`
--

CREATE TABLE `fotosmunicipios` (
  `Id` int(11) NOT NULL,
  `Usuario` int(11) NOT NULL,
  `Municipio` int(11) NOT NULL
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
  `Url` varchar(500) NOT NULL,
  `Hash` varchar(200) NOT NULL
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
-- Estructura de tabla para la tabla `municipiosfavoritos`
--

CREATE TABLE `municipiosfavoritos` (
  `usuario` int(11) NOT NULL,
  `municipio` int(11) NOT NULL
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
-- Indices de la tabla `entornosfavoritos`
--
ALTER TABLE `entornosfavoritos`
  ADD KEY `usuario` (`usuario`),
  ADD KEY `usuario_2` (`usuario`),
  ADD KEY `entorno` (`entorno`);

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
-- Indices de la tabla `fotos`
--
ALTER TABLE `fotos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Usuario` (`Usuario`);

--
-- Indices de la tabla `fotosentornos`
--
ALTER TABLE `fotosentornos`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Usuario` (`Usuario`),
  ADD KEY `Entorno` (`Entorno`);

--
-- Indices de la tabla `fotosmunicipios`
--
ALTER TABLE `fotosmunicipios`
  ADD PRIMARY KEY (`Id`),
  ADD KEY `Usuario` (`Usuario`),
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
-- Indices de la tabla `municipiosfavoritos`
--
ALTER TABLE `municipiosfavoritos`
  ADD KEY `usuario` (`usuario`),
  ADD KEY `municipio` (`municipio`);

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
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=163;

--
-- AUTO_INCREMENT de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT de la tabla `fotos`
--
ALTER TABLE `fotos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `fotosentornos`
--
ALTER TABLE `fotosentornos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `fotosmunicipios`
--
ALTER TABLE `fotosmunicipios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3633;

--
-- AUTO_INCREMENT de la tabla `informes`
--
ALTER TABLE `informes`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=293;

--
-- AUTO_INCREMENT de la tabla `municipios`
--
ALTER TABLE `municipios`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=752;

--
-- AUTO_INCREMENT de la tabla `provincias`
--
ALTER TABLE `provincias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entornosfavoritos`
--
ALTER TABLE `entornosfavoritos`
  ADD CONSTRAINT `entornosfavoritos_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `entornosfavoritos_ibfk_2` FOREIGN KEY (`entorno`) REFERENCES `entornos` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

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
-- Filtros para la tabla `fotos`
--
ALTER TABLE `fotos`
  ADD CONSTRAINT `fotos_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `fotosentornos`
--
ALTER TABLE `fotosentornos`
  ADD CONSTRAINT `fotosentornos_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fotosentornos_ibfk_2` FOREIGN KEY (`Entorno`) REFERENCES `entornos` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `fotosmunicipios`
--
ALTER TABLE `fotosmunicipios`
  ADD CONSTRAINT `fotosmunicipios_ibfk_1` FOREIGN KEY (`Usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fotosmunicipios_ibfk_2` FOREIGN KEY (`Municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;

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

--
-- Filtros para la tabla `municipiosfavoritos`
--
ALTER TABLE `municipiosfavoritos`
  ADD CONSTRAINT `municipiosfavoritos_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `municipiosfavoritos_ibfk_2` FOREIGN KEY (`municipio`) REFERENCES `municipios` (`Id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
