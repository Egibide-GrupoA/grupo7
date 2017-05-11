-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 11-05-2017 a las 11:50:14
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

--
-- Volcado de datos para la tabla `aviso`
--

INSERT INTO `aviso` (`id`, `idParte`, `mensaje`, `visto`) VALUES
(9, 10, 'AVISO DE HORAS', 0),
(10, 13, 'AVISO DE HORAS', 0),
(11, 15, 'AVISO DE HORAS', 0);

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
  `piso` int(2) NOT NULL,
  `mano` char(1) NOT NULL,
  `codPostal` int(5) NOT NULL,
  `ciudad` varchar(25) NOT NULL,
  `provincia` varchar(20) NOT NULL,
  `telefono` char(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `centro`
--

INSERT INTO `centro` (`id`, `nombre`, `calle`, `numero`, `piso`, `mano`, `codPostal`, `ciudad`, `provincia`, `telefono`) VALUES
(1, 'Vitoria', 'Calle', 1, 0, 'A', 1003, 'Vitoria', 'Araba', '11111'),
(3, 'kuggk', 'jhvakjsvjkasd', 8, 0, '\0', 8888, 'asdasdasd', 'asdasd', '8888'),
(4, 'centro', 'centrocalle', 5, 6, 'P', 1234, 'ciudad', 'prov', '666666666'),
(6, 'centro3', 'centro', 10, 0, '\0', 1003, 'vitoria', 'araba', '66666666');

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

--
-- Volcado de datos para la tabla `incidencia`
--

INSERT INTO `incidencia` (`id`, `idParte`, `mensaje`, `fecha`, `resuelta`) VALUES
(2, 8, 'INCIDENCIA', '2017-05-07 14:39:19', '2017-05-07 14:39:19'),
(3, 9, '<aksjbdkasbdkljbvasdklasdñ hañkisd', '2017-05-08 18:10:32', '2017-05-08 18:10:32'),
(4, 10, 'zasdasd', '2017-05-08 18:12:24', '2017-05-08 18:12:24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parte`
--

CREATE TABLE `parte` (
  `id` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `kilometrosInicio` decimal(10,0) DEFAULT NULL,
  `kilometrosFin` decimal(10,0) DEFAULT NULL,
  `gasoil` double DEFAULT NULL,
  `peajes` double DEFAULT NULL,
  `dietas` double DEFAULT NULL,
  `otros` double DEFAULT NULL,
  `eliminado` tinyint(1) NOT NULL DEFAULT '0',
  `validado` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `parte`
--

INSERT INTO `parte` (`id`, `idPersona`, `fecha`, `kilometrosInicio`, `kilometrosFin`, `gasoil`, `peajes`, `dietas`, `otros`, `eliminado`, `validado`) VALUES
(1, 3, '2017-05-06', '1000', '2000', NULL, NULL, NULL, NULL, 0, 1),
(7, 3, '2017-05-07', '10', '12', 13, 15, 16, 17, 0, 1),
(8, 3, '2017-05-07', '100', '100', 0, 0, 0, 0, 0, 1),
(9, 3, '2017-05-07', '100', '200', 100, 0, 0, 0, 0, 1),
(10, 3, '2017-05-08', '1000', '22000', 0, 0, 0, 0, 0, 1),
(11, 3, '2017-05-08', NULL, NULL, NULL, NULL, NULL, NULL, 0, 1),
(12, 3, '2017-05-08', '10', '10', 10, 10, 10, 10, 0, 1),
(13, 3, '2017-05-08', '100', '100', 1, 1, 1, 1, 0, 1),
(14, 3, '2017-05-10', '0', '0', 0, 0, 0, 0, 0, 1),
(15, 3, '2017-05-10', '0', '0', 0, 0, 0, 0, 0, 1);

--
-- Disparadores `parte`
--
DELIMITER $$
CREATE TRIGGER `avisoHoras` BEFORE UPDATE ON `parte` FOR EACH ROW IF (SELECT SUM(TIMESTAMPDIFF(MINUTE,`horaInicio`,`horaFin`)) MINS FROM viaje WHERE idParte=OLD.id) != 480 THEN
	IF NEW.validado = 1 THEN
		INSERT INTO `aviso` (`id`, `idParte`, `mensaje`, `visto`) VALUES (NULL, OLD.id, 'AVISO DE HORAS', 0);
	END IF;
END IF
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE `trabajador` (
  `id` int(11) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `apellido1` varchar(25) NOT NULL,
  `apellido2` varchar(25) NOT NULL,
  `calle` varchar(70) NOT NULL,
  `numero` int(2) NOT NULL,
  `piso` int(2) NOT NULL,
  `mano` char(1) NOT NULL,
  `telPersonal` char(9) DEFAULT NULL,
  `telEmpresa` char(9) NOT NULL,
  `salario` decimal(10,2) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `idCategoria` int(11) NOT NULL,
  `idCentro` int(11) NOT NULL,
  `contrasena` char(32) NOT NULL COMMENT 'md5'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`id`, `dni`, `nombre`, `apellido1`, `apellido2`, `calle`, `numero`, `piso`, `mano`, `telPersonal`, `telEmpresa`, `salario`, `fechaNacimiento`, `idCategoria`, `idCentro`, `contrasena`) VALUES
(2, '7777', 'asier', 'bilbao', 'aosdhiasd', 'absdjbasdljhb', 8, 8, '8', '666666666', '877777', '10000.00', '2017-05-24', 2, 1, '47bce5c74f589f4867dbd57e9ca9f808'),
(3, '7778', 'asier', 'bilbao', 'aosdhiasd', 'absdjbasdljhb', 8, 8, '8', '666666666', '877777', '10000.00', '2017-05-24', 1, 1, '47bce5c74f589f4867dbd57e9ca9f808'),
(4, '8000', 'asier', 'bilbao', 'calvo', 'asdasd', 10, 11, '1', '666666666', '12', '13.00', '2017-05-24', 1, 1, '5d793fc5b00a2348c3fb9ab59e5ca98a'),
(5, '8001', 'Asier', 'Bilbao', 'Calvo', 'asdasdasd', 10, 2, 'c', '666666666', '999999999', '13000.00', '2017-05-24', 1, 1, 'fcea920f7412b5da7be0cf42b8c93759'),
(6, '8100', 'Asier', 'Bilbao', 'Calvo', 'Calle', 10, 11, 'A', '658888888', '111111111', '1000.23', '2017-05-24', 1, 1, '727bb542ade13dbbcc8193ff1a6e5a21'),
(9, '555', '5', '5', '5', '5', 5, 5, '5', '5', '5', '5.00', '0117-00-31', 1, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(10, '45678', 'jvjv', 'jv', 'jv', 'jv', 666, 6, '6', '6666', '666', '666.00', '2017-05-17', 1, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(12, '56781', '56', '666', '6', '6', 6, 6, '6', '66', '66', '66.00', '2017-05-24', 2, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(13, '5678', 'asd', '77', '7', '7', 7, 7, '7', '7', '7', '7.00', '2017-05-04', 2, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(14, '5678', 'asd', '77', '7', '7', 7, 7, '7', '7', '7', '7.00', '2017-05-04', 2, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(16, '45678', '66', '6', '6', '6', 6, 6, '6', '666', '6', '6.00', '2017-05-09', 2, 1, '93dfcaf3d923ec47edb8580667473987'),
(18, '91919191', 'kjbaklblkabd', 'klbkhb', 'kblk', 'hblkblk', 1, 1, 'V', '99999', '9999', '999.90', '2017-05-14', 2, 1, 'f67c2bcbfcfa30fccb36f72dca22a817'),
(19, 'ultultult', 'NombreULT', 'ap1', 'ap2', 'CALLE', 10, 1, 'C', '6666666', '6666666', '100000.00', '2017-05-24', 1, 3, '8e808b7b237811a374a7cd6cab752df3');

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

--
-- Volcado de datos para la tabla `vehiculo`
--

INSERT INTO `vehiculo` (`matricula`, `marca`, `modelo`, `color`, `fechaAlta`) VALUES
('0000AVB', 'MARCA', 'MODELO', 'COLOR', '2017-05-10 22:18:57'),
('1111AAA', 'MARCA', 'MODELO', 'COLOR', '2017-05-02 19:19:02'),
('1111BBB', '2222BBB', 'MODELO', 'COLOR', '2017-05-03 18:38:27'),
('1111CCC', 'null', 'null', 'null', '2017-05-03 19:24:38'),
('2222AAA', 'MARCA2', 'MODELO2', 'null', '2017-05-03 19:49:11'),
('8888CCC', 'NUEVO', 'NUEVO', 'NUEVO', '2017-05-10 20:00:18'),
('9999BBB', 'MARCA', 'MODELO', 'COLOR', '2017-05-10 20:04:01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viaje`
--

CREATE TABLE `viaje` (
  `id` int(11) NOT NULL,
  `idParte` int(11) NOT NULL,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  `matricula` char(8) NOT NULL,
  `albaran` varchar(25) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `viaje`
--

INSERT INTO `viaje` (`id`, `idParte`, `horaInicio`, `horaFin`, `matricula`, `albaran`) VALUES
(23, 7, '00:00:00', '01:00:00', '1111AAA', 'a'),
(24, 7, '02:00:00', '03:00:00', '1111AAA', 'b'),
(28, 8, '00:00:00', '00:00:00', '1111AAA', 'sad'),
(29, 8, '00:30:00', '03:00:00', '1111AAA', 'sad'),
(30, 9, '00:00:00', '07:00:00', '1111AAA', ''),
(31, 9, '00:00:00', '01:00:00', '1111AAA', ''),
(32, 9, '00:00:00', '01:04:00', '1111AAA', ''),
(33, 10, '00:00:00', '07:30:00', '1111CCC', 'sdfghjkl'),
(34, 10, '08:00:00', '08:30:00', '1111CCC', 'sdfghjkl'),
(35, 10, '12:00:00', '12:30:00', '1111CCC', 'sdfghjkl'),
(36, 12, '00:00:00', '08:00:00', '1111AAA', ''),
(37, 13, '00:00:00', '07:00:00', '1111AAA', ''),
(38, 15, '00:00:00', '03:00:00', '1111AAA', 'asd');

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
-- Indices de la tabla `trabajador`
--
ALTER TABLE `trabajador`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `centro`
--
ALTER TABLE `centro`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `incidencia`
--
ALTER TABLE `incidencia`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `parte`
--
ALTER TABLE `parte`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `trabajador`
--
ALTER TABLE `trabajador`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `viaje`
--
ALTER TABLE `viaje`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `aviso`
--
ALTER TABLE `aviso`
  ADD CONSTRAINT `aviso_ibfk_1` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `incidencia`
--
ALTER TABLE `incidencia`
  ADD CONSTRAINT `incidencia_ibfk_1` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`);

--
-- Filtros para la tabla `parte`
--
ALTER TABLE `parte`
  ADD CONSTRAINT `parte_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `trabajador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `trabajador`
--
ALTER TABLE `trabajador`
  ADD CONSTRAINT `trabajador_ibfk_1` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `trabajador_ibfk_2` FOREIGN KEY (`idCentro`) REFERENCES `centro` (`id`);

--
-- Filtros para la tabla `viaje`
--
ALTER TABLE `viaje`
  ADD CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`matricula`) REFERENCES `vehiculo` (`matricula`),
  ADD CONSTRAINT `viaje_ibfk_2` FOREIGN KEY (`idParte`) REFERENCES `parte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
