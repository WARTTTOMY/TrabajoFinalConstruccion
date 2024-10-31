-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 24-10-2024 a las 15:54:06
-- Versión del servidor: 8.2.0
-- Versión de PHP: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `hospital`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `articulos`
--

DROP TABLE IF EXISTS `articulos`;
CREATE TABLE IF NOT EXISTS `articulos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `cantidad` int NOT NULL,
  `descripcion` text,
  `id_habitacion` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `id_habitacion` (`id_habitacion`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `articulos`
--

INSERT INTO `articulos` (`id`, `nombre`, `cantidad`, `descripcion`, `id_habitacion`) VALUES
(9, 'Cama', 7, 'Cama para dormir xd', 2),
(8, 'pc', 2, 'Para uso exclusivo', 2),
(10, 'Hola', 21, '141', 131);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `citas`
--

DROP TABLE IF EXISTS `citas`;
CREATE TABLE IF NOT EXISTS `citas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `id_empleado` bigint DEFAULT NULL,
  `id_paciente` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_paciente` (`id_paciente`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

DROP TABLE IF EXISTS `empleados`;
CREATE TABLE IF NOT EXISTS `empleados` (
  `id` bigint NOT NULL,
  `nombre_empleado` varchar(255) NOT NULL,
  `apellido_empleado` varchar(255) NOT NULL,
  `edad_empleado` int NOT NULL,
  `telefono_empleado` varchar(20) NOT NULL,
  `fecha_nacimiento_empleado` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre_empleado`, `apellido_empleado`, `edad_empleado`, `telefono_empleado`, `fecha_nacimiento_empleado`) VALUES
(1, 'Juan', 'Pérez', 30, '1234567890', '1993-01-01'),
(542247, 'JORGE LUIS', 'ALVARADO CUESTA', 21, '3022739834', '2024-09-30'),
(23433333, 'Cristiano', 'Ronaldo', 39, '222222', '2024-09-30'),
(6666666666, 'xxxxxxxxxxx', 'xxxxxxx', 25, '44444444', '2024-09-30'),
(2323232323, 'hhhhhhhhh', 'hhhhhhhh', 30, '999999', '2024-10-14'),
(8888888888, 'Juan Manuel', 'Banquet', 19, '88888888', '2024-10-11'),
(0, 'Isapinga', 'Gomez', 20, '2222222222', '2024-10-09'),
(7777, 'iiiiiiiii', 'qqqqqqq', 31, '666666666', '2024-10-09'),
(741, 'JORGE LUIS', 'ALVARADO CUESTA', 98, '3022739834', '2024-10-01'),
(234324, 'Mauro', 'Zapata', 36, '324543', '2024-10-01');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

DROP TABLE IF EXISTS `habitaciones`;
CREATE TABLE IF NOT EXISTS `habitaciones` (
  `id` int NOT NULL,
  `tipo_habitacion` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`id`, `tipo_habitacion`) VALUES
(3, 'sencilla'),
(2, 'individual'),
(4, 'Compartida'),
(5, 'Individual'),
(6, 'Individual'),
(7, 'Individual'),
(0, 'Individual'),
(131, 'Individual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ingresos_pacientes`
--

DROP TABLE IF EXISTS `ingresos_pacientes`;
CREATE TABLE IF NOT EXISTS `ingresos_pacientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_paciente` bigint DEFAULT NULL,
  `ciudad` varchar(255) DEFAULT NULL,
  `motivo` text,
  `tiene_acompanante` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_paciente` (`id_paciente`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ingresos_pacientes`
--

INSERT INTO `ingresos_pacientes` (`id`, `id_paciente`, `ciudad`, `motivo`, `tiene_acompanante`) VALUES
(1, 8, 'bogota', 'gripe', 1),
(2, 85, 'Bogota', 'Gripe', 1),
(3, 8, 'ddd', 'ss', 1),
(4, 8, 'Bogota', 'Gripe', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pacientes`
--

DROP TABLE IF EXISTS `pacientes`;
CREATE TABLE IF NOT EXISTS `pacientes` (
  `id` int NOT NULL,
  `nom_paciente` varchar(50) NOT NULL,
  `ape_paciente` varchar(50) NOT NULL,
  `edad_paciente` int NOT NULL,
  `direccion_paciente` varchar(50) NOT NULL,
  `telefono_paciente` varchar(20) DEFAULT NULL,
  `eps_paciente` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pacientes`
--

INSERT INTO `pacientes` (`id`, `nom_paciente`, `ape_paciente`, `edad_paciente`, `direccion_paciente`, `telefono_paciente`, `eps_paciente`) VALUES
(8, 'JORGE LUIS', 'ALVARADO CUESTA', 8, 'DIAG 42 CC #34-90', '3022739', 'uyu'),
(85, 'JORGE LUIS', 'ALVARADO CUESTA', 78, 'DIAG 42 CC #34-90', '3022739834', 'uyu'),
(334, 'Thiago', 'Zapata', 7, 'CIRUELOS', '22222222', 'NUEVA EPS'),
(8954, 'JORGE LUIS', 'ALVARADO CUESTA', 54, 'DIAG 42 CC #34-90', '3022739834', 'uyu'),
(7657567, 'JORGE LUIS', 'ALVARADO CUESTA', 66, 'DIAG 42 CC #34-90', '3022739834', 'uyu');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente_servicio`
--

DROP TABLE IF EXISTS `paciente_servicio`;
CREATE TABLE IF NOT EXISTS `paciente_servicio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `paciente_id` int NOT NULL,
  `servicio_id` int NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `paciente_id` (`paciente_id`),
  KEY `servicio_id` (`servicio_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `paciente_servicio`
--

INSERT INTO `paciente_servicio` (`id`, `paciente_id`, `servicio_id`, `precio`) VALUES
(1, 8, 5659, 30000.00),
(2, 8, 5658, 20000.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `salidas_pacientes`
--

DROP TABLE IF EXISTS `salidas_pacientes`;
CREATE TABLE IF NOT EXISTS `salidas_pacientes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_paciente` bigint DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `monto_total` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_paciente` (`id_paciente`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

DROP TABLE IF EXISTS `servicios`;
CREATE TABLE IF NOT EXISTS `servicios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `detalle` text,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5716 DEFAULT CHARSET=utf8mb4 ;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id`, `nombre`, `precio`, `detalle`) VALUES
(5696, 'Lavanderia', 10000.00, 'Lavado de ropa'),
(5697, 'Guarderia', 20000.00, 'Guarderia de mascotas'),
(5698, 'Peluqueria', 30000.00, 'Corte de cabello'),
(5699, 'Cuidado de mascotas', 40000.00, 'Cuidado de mascotas'),
(5700, 'Lavanderia', 10000.00, 'Lavado de ropa'),
(5701, 'Guarderia', 20000.00, 'Guarderia de mascotas'),
(5702, 'Peluqueria', 30000.00, 'Corte de cabello'),
(5703, 'Cuidado de mascotas', 40000.00, 'Cuidado de mascotas'),
(5704, 'Lavanderia', 10000.00, 'Lavado de ropa'),
(5705, 'Guarderia', 20000.00, 'Guarderia de mascotas'),
(5706, 'Peluqueria', 30000.00, 'Corte de cabello'),
(5707, 'Cuidado de mascotas', 40000.00, 'Cuidado de mascotas'),
(5708, 'Lavanderia', 10000.00, 'Lavado de ropa'),
(5709, 'Guarderia', 20000.00, 'Guarderia de mascotas'),
(5710, 'Peluqueria', 30000.00, 'Corte de cabello'),
(5711, 'Cuidado de mascotas', 40000.00, 'Cuidado de mascotas'),
(5712, 'Lavanderia', 10000.00, 'Lavado de ropa'),
(5713, 'Guarderia', 20000.00, 'Guarderia de mascotas'),
(5714, 'Peluqueria', 30000.00, 'Corte de cabello'),
(5715, 'Cuidado de mascotas', 40000.00, 'Cuidado de mascotas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL,
  `username` varchar(191) NOT NULL,
  `password` varchar(191) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
