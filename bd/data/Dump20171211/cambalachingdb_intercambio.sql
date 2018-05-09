-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: cambalachingdb
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `intercambio`
--

DROP TABLE IF EXISTS `intercambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intercambio` (
  `idintercambio` int(11) NOT NULL AUTO_INCREMENT,
  `intercambio_cliente_solicita` int(11) DEFAULT NULL,
  `intercambio_cliente_recibe` int(11) DEFAULT NULL,
  `intercambio_estatus` int(11) DEFAULT NULL,
  `intercambioc_fecha_solicitud` datetime DEFAULT NULL,
  `intercambio_conformidad1` bit(1) DEFAULT NULL,
  `intercambio_conformidad2` bit(1) DEFAULT NULL,
  `intercambio_calificacion1` int(11) DEFAULT NULL,
  `intercambio_calificacion2` int(11) DEFAULT NULL,
  PRIMARY KEY (`idintercambio`),
  KEY `fk_intercambio_cliente_solicita_idx` (`intercambio_cliente_solicita`),
  KEY `fk_intercambio_cliente_recibe_idx` (`intercambio_cliente_recibe`),
  CONSTRAINT `fk_intercambio_cliente_recibe` FOREIGN KEY (`intercambio_cliente_recibe`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_intercambio_cliente_solicita` FOREIGN KEY (`intercambio_cliente_solicita`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intercambio`
--

LOCK TABLES `intercambio` WRITE;
/*!40000 ALTER TABLE `intercambio` DISABLE KEYS */;
/*!40000 ALTER TABLE `intercambio` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-11  0:39:15
