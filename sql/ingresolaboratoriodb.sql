CREATE DATABASE  IF NOT EXISTS `ingresolaboratoriodb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `ingresolaboratoriodb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: ingresolaboratoriodb
-- ------------------------------------------------------
-- Server version	5.6.20

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
-- Table structure for table `equipos`
--

DROP TABLE IF EXISTS `equipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipos` (
  `id_equipo` int(11) NOT NULL AUTO_INCREMENT,
  `id_laboratorio` int(11) NOT NULL,
  `ip` varchar(45) NOT NULL,
  `mac` varchar(45) NOT NULL,
  `numero` int(11) NOT NULL COMMENT 'Número de equipo en el laboratorio',
  `estado` int(11) NOT NULL COMMENT '0->Libre\n1->Ocupado\n3->No disponible',
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `mac_UNIQUE` (`mac`),
  UNIQUE KEY `ip_UNIQUE` (`ip`),
  KEY `equipoLaboratorio_idx` (`id_laboratorio`),
  CONSTRAINT `equipoLaboratorio` FOREIGN KEY (`id_laboratorio`) REFERENCES `laboratorios` (`id_laboratorio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (17,6,'192.168.1.10','AABBDDCCA1',2,0),(18,6,'192.168.1.1','AAAAAAAAAAAA',1,0),(19,6,'192.168.1.2','BBBBBBBBBBBB',3,0),(20,6,'192.168.1.4','dddddddddddd',3,0),(21,6,'192.168.1.9','111111111111',8,0),(22,6,'192.168.1.11','222222222222',7,0),(23,6,'192.168.7.2','888888888888',9,0),(30,6,'192.168.8.23','AAAAA6A66666',89,0),(31,6,'192.167.1.2','22AAAAAAAAAA',23,0),(37,6,'192.168.2.34','623556637736',6,0),(38,6,'192.168.1.67','CCCCCCCCCCCC',76,0),(39,6,'192.123.234.4','CCACC1233434',8,0),(40,6,'172.16.17.23','ABABACACCACC',6,0),(41,6,'192.67.23.123','191282727172',12,0),(42,6,'200.0.29.177','133232323323',23,0),(43,6,'234.233.3.5','addddddddddd',122,0),(44,6,'23.23.4.32','877843444343',78,0),(45,6,'172.156.1.2','ddededeeeeee',9,0),(48,6,'192.167.23.4','dddd233333dd',23,0),(49,6,'12.34.45.455','ABBBBAABBABA',7,0),(52,6,'23.3.3.3.99','eeeecccccccc',1,0),(53,7,'192.167.3.4','accacaccaaaa',1,0);
/*!40000 ALTER TABLE `equipos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laboratorios`
--

DROP TABLE IF EXISTS `laboratorios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `laboratorios` (
  `id_laboratorio` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(45) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`id_laboratorio`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (6,'A01','Sistemas informaticos','Laboraatorio para estudiantes de'),(7,'A02','Electronica','Laboratorio para electronica');
/*!40000 ALTER TABLE `laboratorios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sesiones`
--

DROP TABLE IF EXISTS `sesiones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sesiones` (
  `id_sesion` int(11) NOT NULL AUTO_INCREMENT,
  `id_equipo` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `bloqueada` smallint(6) NOT NULL COMMENT '0->activa\n1->terminada',
  `fecha_hora_inicio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_hora_fin` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_sesion`),
  KEY `sesionEquipo_idx` (`id_equipo`),
  KEY `usuarioEquipo_idx` (`id_usuario`),
  CONSTRAINT `sesionEquipo` FOREIGN KEY (`id_equipo`) REFERENCES `equipos` (`id_equipo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sesionUsuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesiones`
--

LOCK TABLES `sesiones` WRITE;
/*!40000 ALTER TABLE `sesiones` DISABLE KEYS */;
INSERT INTO `sesiones` VALUES (48,17,35,1,'2014-10-30 03:28:31','2014-10-30 03:35:46'),(49,17,35,1,'2014-10-30 03:38:18','2014-10-30 03:41:20'),(51,17,36,1,'2014-10-30 03:42:10','2014-10-30 03:42:42'),(52,17,36,1,'2014-10-30 03:43:04','2014-10-30 03:43:09'),(53,17,36,1,'2014-10-30 03:50:47','2014-10-30 03:50:54'),(54,17,38,1,'2014-10-30 03:52:49','2014-10-30 03:53:15');
/*!40000 ALTER TABLE `sesiones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(10) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `nombres` varchar(45) NOT NULL,
  `apellidos` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `celular` varchar(10) NOT NULL,
  `rol_usuario` int(11) NOT NULL COMMENT '0->estudiante\n1->administrador',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  UNIQUE KEY `celular_UNIQUE` (`celular`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (34,'1104322084','admin','CESAR','CALVA','cesar@gmail.com','0978452371',1),(35,'0705462745','admin','DIEGO JACINTO','ROMERO RMIJOS','diego.romero@kradac.com','0969748969',1),(36,'1100656808','admin','augusto','calva','cesar','1123432',0),(38,'1105581316','admin','ANDREA','PATIÑO','mayuri.150293@gmail.com','0969748968',0),(42,'0123456789','admin','hgghhjjjk','ghhjjhjkkj','hjjjoiihbkh','98779889',0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-10-30  0:23:26
