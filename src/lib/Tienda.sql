-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 16-06-2017 a las 05:37:47
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Tienda`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Compra`
--

CREATE TABLE `Compra` (
  `IdCompra` int(100) NOT NULL,
  `Fecha` datetime NOT NULL,
  `IdProveedor` int(100) NOT NULL,
  `IdSucursal` int(100) NOT NULL,
  `TipoCompra` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `NumDocumento` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Subtotal` double NOT NULL,
  `IVA` double NOT NULL,
  `Percepcion` double NOT NULL,
  `Total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DetalleCompra`
--

CREATE TABLE `DetalleCompra` (
  `IdCompra` int(100) NOT NULL,
  `CodBarra` varchar(13) COLLATE latin1_spanish_ci NOT NULL,
  `Cantidad` double NOT NULL,
  `CostoUnitario` double NOT NULL,
  `IdSucursal` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `DetalleVenta`
--

CREATE TABLE `DetalleVenta` (
  `IdVenta` int(100) NOT NULL,
  `CodBarra` varchar(13) COLLATE latin1_spanish_ci NOT NULL,
  `Cantidad` double NOT NULL,
  `PrecioUnitario` double NOT NULL,
  `IdSucursal` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Inventario`
--

CREATE TABLE `Inventario` (
  `IdSucursal` int(100) NOT NULL,
  `CodBarra` varchar(13) COLLATE latin1_spanish_ci NOT NULL,
  `Cantidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `Inventario`
--

INSERT INTO `Inventario` (`IdSucursal`, `CodBarra`, `Cantidad`) VALUES
(1, '11', 122),
(1, '12', 300),
(1, '13', 221),
(1, '14', 223),
(1, '15', 672),
(2, '16', 234),
(2, '17', 423),
(2, '18', 234),
(2, '19', 532),
(2, '111', 324);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Parametro`
--

CREATE TABLE `Parametro` (
  `IdParametro` int(100) NOT NULL,
  `Nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Valor` varchar(50) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Producto`
--

CREATE TABLE `Producto` (
  `CodBarra` varchar(13) COLLATE latin1_spanish_ci NOT NULL,
  `Nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Costo` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `Producto`
--

INSERT INTO `Producto` (`CodBarra`, `Nombre`, `Costo`) VALUES
('11', 'CARGADOR HP 19V', 32.23),
('111', 'CPU DELL OPTIPLEX 775 BASICO', 68.87),
('12', 'SILLA SECRETARIAL SIN BRAZO', 27.12),
('13', 'SILLA SECRETARIAL CON BRAZO', 45.32),
('14', 'PASTA TERMICA FRASCO', 4.23),
('15', 'AIRE COMPRIMIDO ORBIC', 8.45),
('16', 'ESPUMA PARA SUPERFICIES', 7.34),
('17', 'RAM DDR2 1GB', 17.2),
('18', 'MONITOR LCD HP 21', 98.98),
('19', 'ALTAVOCES PARA PC 2OHM', 4.34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proveedor`
--

CREATE TABLE `Proveedor` (
  `IdProveedor` int(100) NOT NULL,
  `Nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Telefono` varchar(9) COLLATE latin1_spanish_ci NOT NULL,
  `Direccion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `NIT` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `NRC` int(7) NOT NULL,
  `Email` varchar(100) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `Proveedor`
--

INSERT INTO `Proveedor` (`IdProveedor`, `Nombre`, `Telefono`, `Direccion`, `NIT`, `NRC`, `Email`) VALUES
(1, 'CHINA TRUMP', '1212-4545', 'NO SE', '02130222971028', 3434346, 'nose@asaber.com'),
(2, 'OSAMA INC', '3434-5432', 'COLONIA LOS CIEN PIES', '02130222971119', 2323457, 'osama@outtlok.sd');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Sucursal`
--

CREATE TABLE `Sucursal` (
  `IdSucursal` int(100) NOT NULL,
  `Nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Direccion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Telefono` varchar(9) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `Sucursal`
--

INSERT INTO `Sucursal` (`IdSucursal`, `Nombre`, `Direccion`, `Telefono`) VALUES
(1, 'HP COMPANY', 'ALASKA', '6477-3774'),
(2, 'DELL COMPANY', 'NIGERIA', '7463-8953');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TipoPrecio`
--

CREATE TABLE `TipoPrecio` (
  `IdTipoPrecio` int(100) NOT NULL,
  `Nombre` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Utilidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Venta`
--

CREATE TABLE `Venta` (
  `IdVenta` int(100) NOT NULL,
  `IdSucursal` int(100) NOT NULL,
  `TipoVenta` char(1) COLLATE latin1_spanish_ci NOT NULL,
  `IdTipoPrecio` int(100) NOT NULL,
  `Cliente` varchar(30) COLLATE latin1_spanish_ci NOT NULL,
  `Fecha` datetime NOT NULL,
  `IVA` double NOT NULL,
  `TotalGravado` double NOT NULL,
  `Total` double NOT NULL,
  `Direccion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `Giro` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `NIT` varchar(14) COLLATE latin1_spanish_ci NOT NULL,
  `NRC` int(7) NOT NULL,
  `NDocumento` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Compra`
--
ALTER TABLE `Compra`
  ADD PRIMARY KEY (`IdCompra`,`IdSucursal`,`IdProveedor`) USING BTREE,
  ADD KEY `IdSucursal` (`IdSucursal`),
  ADD KEY `IdProveedor` (`IdProveedor`);

--
-- Indices de la tabla `DetalleCompra`
--
ALTER TABLE `DetalleCompra`
  ADD PRIMARY KEY (`IdCompra`,`CodBarra`,`IdSucursal`) USING BTREE,
  ADD KEY `IdSucursal` (`IdSucursal`),
  ADD KEY `CodBarra` (`CodBarra`);

--
-- Indices de la tabla `DetalleVenta`
--
ALTER TABLE `DetalleVenta`
  ADD PRIMARY KEY (`IdVenta`,`CodBarra`,`IdSucursal`),
  ADD KEY `IdSucursal` (`IdSucursal`),
  ADD KEY `CodBarra` (`CodBarra`);

--
-- Indices de la tabla `Inventario`
--
ALTER TABLE `Inventario`
  ADD KEY `IdSucursal` (`IdSucursal`),
  ADD KEY `IdSucursal_2` (`IdSucursal`),
  ADD KEY `CodBarra` (`CodBarra`);

--
-- Indices de la tabla `Parametro`
--
ALTER TABLE `Parametro`
  ADD PRIMARY KEY (`IdParametro`);

--
-- Indices de la tabla `Producto`
--
ALTER TABLE `Producto`
  ADD PRIMARY KEY (`CodBarra`);

--
-- Indices de la tabla `Proveedor`
--
ALTER TABLE `Proveedor`
  ADD PRIMARY KEY (`IdProveedor`);

--
-- Indices de la tabla `Sucursal`
--
ALTER TABLE `Sucursal`
  ADD PRIMARY KEY (`IdSucursal`);

--
-- Indices de la tabla `TipoPrecio`
--
ALTER TABLE `TipoPrecio`
  ADD PRIMARY KEY (`IdTipoPrecio`);

--
-- Indices de la tabla `Venta`
--
ALTER TABLE `Venta`
  ADD PRIMARY KEY (`IdVenta`,`IdSucursal`,`IdTipoPrecio`),
  ADD KEY `IdTipoPrecio` (`IdTipoPrecio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Compra`
--
ALTER TABLE `Compra`
  MODIFY `IdCompra` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Parametro`
--
ALTER TABLE `Parametro`
  MODIFY `IdParametro` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Proveedor`
--
ALTER TABLE `Proveedor`
  MODIFY `IdProveedor` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `Sucursal`
--
ALTER TABLE `Sucursal`
  MODIFY `IdSucursal` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `TipoPrecio`
--
ALTER TABLE `TipoPrecio`
  MODIFY `IdTipoPrecio` int(100) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `Venta`
--
ALTER TABLE `Venta`
  MODIFY `IdVenta` int(100) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Compra`
--
ALTER TABLE `Compra`
  ADD CONSTRAINT `Compra_ibfk_1` FOREIGN KEY (`IdSucursal`) REFERENCES `Sucursal` (`IdSucursal`),
  ADD CONSTRAINT `Compra_ibfk_2` FOREIGN KEY (`IdProveedor`) REFERENCES `Proveedor` (`IdProveedor`);

--
-- Filtros para la tabla `DetalleCompra`
--
ALTER TABLE `DetalleCompra`
  ADD CONSTRAINT `DetalleCompra_ibfk_1` FOREIGN KEY (`IdCompra`) REFERENCES `Compra` (`IdCompra`),
  ADD CONSTRAINT `DetalleCompra_ibfk_2` FOREIGN KEY (`IdSucursal`) REFERENCES `Inventario` (`IdSucursal`),
  ADD CONSTRAINT `DetalleCompra_ibfk_3` FOREIGN KEY (`CodBarra`) REFERENCES `Producto` (`CodBarra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `DetalleVenta`
--
ALTER TABLE `DetalleVenta`
  ADD CONSTRAINT `DetalleVenta_ibfk_1` FOREIGN KEY (`IdVenta`) REFERENCES `Venta` (`IdVenta`),
  ADD CONSTRAINT `DetalleVenta_ibfk_2` FOREIGN KEY (`IdSucursal`) REFERENCES `Inventario` (`IdSucursal`),
  ADD CONSTRAINT `DetalleVenta_ibfk_3` FOREIGN KEY (`CodBarra`) REFERENCES `Producto` (`CodBarra`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_DetalleVenta_1` FOREIGN KEY (`IdSucursal`) REFERENCES `Sucursal` (`IdSucursal`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `Inventario`
--
ALTER TABLE `Inventario`
  ADD CONSTRAINT `Inventario_ibfk_1` FOREIGN KEY (`IdSucursal`) REFERENCES `Sucursal` (`IdSucursal`),
  ADD CONSTRAINT `Inventario_ibfk_2` FOREIGN KEY (`CodBarra`) REFERENCES `Producto` (`CodBarra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Venta`
--
ALTER TABLE `Venta`
  ADD CONSTRAINT `Venta_ibfk_1` FOREIGN KEY (`IdTipoPrecio`) REFERENCES `TipoPrecio` (`IdTipoPrecio`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
