CREATE DATABASE  IF NOT EXISTS `silalibrodb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `silalibrodb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: silalibrodb
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `almacen`
--

DROP TABLE IF EXISTS `almacen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `almacen` (
  `idalmacen` int(11) NOT NULL AUTO_INCREMENT,
  `almacen_idlibro` int(11) DEFAULT NULL,
  `almacen_existencias` int(11) DEFAULT NULL,
  `almacen_fechaEdicionAlmacen` datetime DEFAULT NULL,
  `almacen_ideditadoPor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idalmacen`),
  KEY `almacen_administrador_idx` (`almacen_ideditadoPor`),
  KEY `almacen_libro_fk_idx` (`almacen_idlibro`),
  CONSTRAINT `almacen_administrador` FOREIGN KEY (`almacen_ideditadoPor`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `almacen_libro_fk` FOREIGN KEY (`almacen_idlibro`) REFERENCES `libro` (`idlibro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autor` (
  `idautor` int(11) NOT NULL AUTO_INCREMENT,
  `autor_nombre` varchar(45) DEFAULT NULL,
  `autor_apellidoPaterno` varchar(45) DEFAULT NULL,
  `autor_apellidoMaterno` varchar(45) DEFAULT NULL,
  `autor_idpais` int(11) DEFAULT NULL,
  PRIMARY KEY (`idautor`),
  KEY `autor_pais_idx` (`autor_idpais`),
  CONSTRAINT `autor_pais` FOREIGN KEY (`autor_idpais`) REFERENCES `pais` (`idpais`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'John','Green','',2),(2,'Gabriel','Garcia ','Marquez',3),(3,'Yeyo','Fofo','',1);
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccion` (
  `iddireccion` int(11) NOT NULL AUTO_INCREMENT,
  `direccion_calle` varchar(45) DEFAULT NULL,
  `direccion_numeroExterior` int(11) DEFAULT NULL,
  `direccion_numeroInterior` int(11) DEFAULT NULL,
  `direccion_cp` varchar(5) DEFAULT NULL,
  `direccion_colonia` varchar(45) DEFAULT NULL,
  `direccion_municipio` varchar(45) DEFAULT NULL,
  `direccion_estado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iddireccion`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (12,'Calle 13',1554,2,'07510','Colon','GAM','CDMX'),(13,'La Calle',150,3,'01518','La colonia','El municipio','CDMX');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `idlibro` int(11) NOT NULL AUTO_INCREMENT,
  `libro_sku` text,
  `libro_titulo` varchar(45) DEFAULT NULL,
  `libro_idautor` int(11) DEFAULT NULL,
  `libro_categoria` varchar(25) DEFAULT NULL,
  `librocol` varchar(70) DEFAULT NULL,
  `p_renta` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`idlibro`),
  KEY `libro_autor_idx` (`libro_idautor`),
  CONSTRAINT `libro_autor` FOREIGN KEY (`libro_idautor`) REFERENCES `autor` (`idautor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (2,'2018RSA','el perrito',3,'Arte','c://resources/portadas',200.15);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento_cuenta`
--

DROP TABLE IF EXISTS `movimiento_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movimiento_cuenta` (
  `idmovimiento_cuenta` int(11) NOT NULL AUTO_INCREMENT,
  `movimiento_cuenta_monto` decimal(10,2) DEFAULT NULL,
  `movimiento_cuenta_cargo` bit(1) DEFAULT NULL,
  `movimiento_cuenta_fecha` date DEFAULT NULL,
  `movimiento_cuenta_folioTransaccion` text,
  `movimiento_cuenta_idusuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`idmovimiento_cuenta`),
  KEY `movimiento_cuenta_usuario_idx` (`movimiento_cuenta_idusuario`),
  CONSTRAINT `movimiento_cuenta_usuario` FOREIGN KEY (`movimiento_cuenta_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento_cuenta`
--

LOCK TABLES `movimiento_cuenta` WRITE;
/*!40000 ALTER TABLE `movimiento_cuenta` DISABLE KEYS */;
INSERT INTO `movimiento_cuenta` VALUES (1,300.55,'\0','2018-06-09','0001A',9);
/*!40000 ALTER TABLE `movimiento_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `idpais` int(11) NOT NULL AUTO_INCREMENT,
  `pais_nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpais`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Mexico'),(2,'Estados Unidos'),(3,'Colombia'),(4,'Argentina'),(5,'Noruega');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renta`
--

DROP TABLE IF EXISTS `renta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `renta` (
  `idrenta` int(11) NOT NULL AUTO_INCREMENT,
  `renta_idusuario` int(11) DEFAULT NULL,
  `renta_fechaRegistro` datetime DEFAULT NULL,
  `renta_movimientoCuentaid` int(11) DEFAULT NULL,
  `renta_montoTotal` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idrenta`),
  KEY `renta_usuario_fk_idx` (`renta_idusuario`),
  KEY `renta_movimiento_fk_idx` (`renta_movimientoCuentaid`),
  CONSTRAINT `renta_movimiento_fk` FOREIGN KEY (`renta_movimientoCuentaid`) REFERENCES `movimiento_cuenta` (`idmovimiento_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `renta_usuario_fk` FOREIGN KEY (`renta_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renta`
--

LOCK TABLES `renta` WRITE;
/*!40000 ALTER TABLE `renta` DISABLE KEYS */;
/*!40000 ALTER TABLE `renta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentaventa_libro`
--

DROP TABLE IF EXISTS `rentaventa_libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rentaventa_libro` (
  `idrentaventa_libro` int(11) NOT NULL AUTO_INCREMENT,
  `rentaventa_libro_idlibro` int(11) DEFAULT NULL,
  `rentaventa_libro_idrentaventa` int(11) DEFAULT NULL,
  `rentaventa_libro_monto` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`idrentaventa_libro`),
  KEY `rentaventa_libro_fk_idx` (`rentaventa_libro_idlibro`),
  KEY `rentaventa_renta_fk_idx` (`rentaventa_libro_idrentaventa`),
  CONSTRAINT `rentaventa_libro_fk` FOREIGN KEY (`rentaventa_libro_idlibro`) REFERENCES `libro` (`idlibro`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rentaventa_renta_fk` FOREIGN KEY (`rentaventa_libro_idrentaventa`) REFERENCES `renta` (`idrenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `rentaventa_venta_fk` FOREIGN KEY (`rentaventa_libro_idrentaventa`) REFERENCES `venta` (`idventa`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentaventa_libro`
--

LOCK TABLES `rentaventa_libro` WRITE;
/*!40000 ALTER TABLE `rentaventa_libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentaventa_libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_email` varchar(45) DEFAULT NULL,
  `usuario_pass` text,
  `usuario_nombre` varchar(45) DEFAULT NULL,
  `usuario_apellidoMaterno` varchar(45) DEFAULT NULL,
  `usuario_apellidoPaterno` varchar(45) DEFAULT NULL,
  `usuario_fechaNacimiento` date DEFAULT NULL,
  `idDireccion` int(11) DEFAULT NULL,
  `usuario_administrador` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idusuario`),
  KEY `usuario_direccion_idx` (`idDireccion`),
  CONSTRAINT `usuario_direccion` FOREIGN KEY (`idDireccion`) REFERENCES `direccion` (`iddireccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'yeyoadmin@correo.com','admin','Adminyeyo','Foster','Foster','1997-06-07',13,''),(9,'yeyo@correo.com','yeyo','Yeyo','Foster','Foster','2018-06-05',12,'\0'),(10,'alam@correo.com','alampass','Alam','Riosx2','Rios','1997-06-09',13,'\0');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `idventa` int(11) NOT NULL AUTO_INCREMENT,
  `venta_idusuario` int(11) DEFAULT NULL,
  `venta_montoTotal` decimal(10,0) DEFAULT NULL,
  `venta_movimientoCuentaid` int(11) DEFAULT NULL,
  `venta_fechaRegistro` datetime DEFAULT NULL,
  PRIMARY KEY (`idventa`),
  KEY `venta_usuario_fk_idx` (`venta_idusuario`),
  KEY `venta_movimiento_fk_idx` (`venta_movimientoCuentaid`),
  CONSTRAINT `venta_movimiento_fk` FOREIGN KEY (`venta_movimientoCuentaid`) REFERENCES `movimiento_cuenta` (`idmovimiento_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `venta_usuario_fk` FOREIGN KEY (`venta_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-14  4:40:42
