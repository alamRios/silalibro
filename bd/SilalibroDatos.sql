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
-- Dumping data for table `almacen`
--

LOCK TABLES `almacen` WRITE;
/*!40000 ALTER TABLE `almacen` DISABLE KEYS */;
/*!40000 ALTER TABLE `almacen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,'John','Green','',2),(2,'Gabriel','Garcia ','Marquez',3),(3,'Yeyo','Fofo','',1),(5,'A','Castells','null',2),(6,'Andreu','S','Tanenbaum',2),(7,'Umberto','Eco','',1),(8,'Carl','Sagan','',2);
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (12,'Calle 13',1554,2,'07510','Colon','GAM','CDMX'),(13,'La Calle',150,3,'01518','La colonia','El municipio','CDMX'),(14,'sila',2,1,'55266','sila','sila','Mexico');
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (7,'1','Ciudades de Papel',1,'Literatura','c://resources/portadas',300.00,250.00,0),(8,'2','Doce cuentos peregrinos',2,'Literatura','c://resources/portadas',250.90,150.00,1),(9,'3','Noticia de un secuestro',2,'Literatura','c://resources/portadas',250.90,150.00,3),(10,'4','El nombre de la rosa',7,'Literatura','c://resources/portadas',130.00,80.00,2),(11,'5','De amor y otros cuentoq',2,'Literatura','c://resources/portadas',240.00,95.00,5),(12,'6','Hilatura del Algodon',5,'Tecnologia','c://resources/portadas',200.00,100.00,3),(13,'7','Sistemas Operativos Modernos',6,'Tecnologia','c://resources/portadas',250.00,100.00,4),(15,'7','Cosmos',3,'Literatura','c://resources/portadas',230.00,70.00,2),(16,'8','El mundo y sus demonios',8,'Ciencia','c://resources/portadas',300.00,70.00,3);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `movimiento_cuenta`
--

LOCK TABLES `movimiento_cuenta` WRITE;
/*!40000 ALTER TABLE `movimiento_cuenta` DISABLE KEYS */;
INSERT INTO `movimiento_cuenta` VALUES (1,300.55,'\0','2018-06-09','0001A',9),(9,400.00,'\0','2018-06-15','1',10),(10,230.00,'','2018-06-15','20',10),(11,400.00,'\0','2018-06-15','3',10),(12,230.00,'','2018-06-15','32',10);
/*!40000 ALTER TABLE `movimiento_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
INSERT INTO `pais` VALUES (1,'Mexico'),(2,'Estados Unidos'),(3,'Colombia'),(4,'Argentina'),(5,'Noruega');
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `renta`
--

LOCK TABLES `renta` WRITE;
/*!40000 ALTER TABLE `renta` DISABLE KEYS */;
INSERT INTO `renta` VALUES (8,10,'2018-06-15 11:49:13',10,230.00);
/*!40000 ALTER TABLE `renta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rentaventa_libro`
--

LOCK TABLES `rentaventa_libro` WRITE;
/*!40000 ALTER TABLE `rentaventa_libro` DISABLE KEYS */;
/*!40000 ALTER TABLE `rentaventa_libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'yeyoadmin@correo.com','admin','Adminyeyo','Foster','Foster','1997-06-07',13,''),(9,'yeyo@correo.com','yeyo','Yeyo','Foster','Foster','2018-06-05',12,'\0'),(10,'alam@correo.com','alampass','Alam','Riosx2','Rios','1997-06-09',13,'\0'),(11,'silalibro@silalibro.com','silalibro','Silalibro','Silalibro','Silalibro','2018-06-14',14,'');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2018-06-15 13:05:02
