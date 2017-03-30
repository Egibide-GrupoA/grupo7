-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 30-03-2017 a las 19:08:52
-- Versión del servidor: 5.6.35
-- Versión de PHP: 7.0.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `program7`
--
CREATE DATABASE IF NOT EXISTS `program7` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `program7`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `aviso`
--

CREATE TABLE `aviso` (
  `id` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `mensaje` text NOT NULL,
  `visto` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`) VALUES
(1, 'Logistica-Transporte'),
(2, 'Administración');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `centro`
--

CREATE TABLE `centro` (
  `id` int(11) NOT NULL,
  `nombre` varchar(35) NOT NULL,
  `calle` varchar(70) NOT NULL,
  `numero` int(2) NOT NULL,
  `codPostal` int(5) NOT NULL,
  `ciudad` varchar(25) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `telefono` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `incidencia`
--

CREATE TABLE `incidencia` (
  `id` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `mensaje` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `resuelta` timestamp NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parte`
--

CREATE TABLE `parte` (
  `id` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `kilometrosInicio` decimal(10,0) NOT NULL,
  `kilometrosFin` decimal(10,0) NOT NULL,
  `gasoil` double DEFAULT NULL,
  `peajes` double DEFAULT NULL,
  `dietas` double DEFAULT NULL,
  `otros` double DEFAULT NULL,
  `eliminado` tinyint(1) NOT NULL DEFAULT '0',
  `validado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido1` varchar(25) NOT NULL,
  `apellido2` varchar(25) NOT NULL,
  `calle` varchar(70) NOT NULL,
  `portal` int(2) NOT NULL,
  `piso` int(2) NOT NULL,
  `mano` char(1) NOT NULL,
  `telPersonal` char(9) DEFAULT NULL,
  `telEmpresa` char(9) NOT NULL,
  `salario` decimal(10,0) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `idCategoria` int(11) NOT NULL,
  `idCentro` int(11) NOT NULL,
  `contrasena` char(32) NOT NULL COMMENT 'md5'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `matricula` char(8) NOT NULL COMMENT 'Matriculas provisionales de 8',
  `marca` varchar(30) NOT NULL,
  `modelo` varchar(30) NOT NULL,
  `color` varchar(30) NOT NULL,
  `fechaAlta` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `id` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `horaInicio` int(11) NOT NULL,
  `horaFin` int(11) NOT NULL,
  `matricula` char(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `aviso`
--
ALTER TABLE `aviso`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idParte` (`idParte`);

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `centro`
--
ALTER TABLE `centro`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `incidencia`
--
ALTER TABLE `incidencia`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idParte` (`idParte`);

--
-- Indices de la tabla `parte`
--
ALTER TABLE `parte`
  ADD PRIMARY KEY (`id`),
  ADD KEY `parte_ibfk_1` (`idPersona`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoria` (`idCategoria`),
  ADD KEY `centro` (`idCentro`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`matricula`);

--
-- Indices de la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD PRIMARY KEY (`id`),
  ADD KEY `matricula` (`matricula`),
  ADD KEY `idParte` (`idParte`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `aviso`
--
ALTER TABLE `aviso`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `incidencia`
--
ALTER TABLE `incidencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `parte`
--
ALTER TABLE `parte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aviso`
--
ALTER TABLE `aviso`
  ADD CONSTRAINT `aviso_ibfk_1` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`);

--
-- Filtros para la tabla `incidencia`
--
ALTER TABLE `incidencia`
  ADD CONSTRAINT `incidencia_ibfk_1` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`);

--
-- Filtros para la tabla `parte`
--
ALTER TABLE `parte`
  ADD CONSTRAINT `parte_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`id`);

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`id`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`),
  ADD CONSTRAINT `viaje_ibfk_2` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
