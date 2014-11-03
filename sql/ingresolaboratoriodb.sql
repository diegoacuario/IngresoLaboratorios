CREATE DATABASE  IF NOT EXISTS `ingresolaboratoriodb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ingresolaboratoriodb`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ingresolaboratoriodb
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
  `ip` varchar(45) CHARACTER SET latin1 NOT NULL,
  `mac` varchar(45) CHARACTER SET latin1 NOT NULL,
  `numero` int(11) NOT NULL COMMENT 'Número de equipo en el laboratorio',
  `estado` int(11) NOT NULL COMMENT '0->Disponible\n1->Libre\n2->Ocupado',
  PRIMARY KEY (`id_equipo`),
  UNIQUE KEY `mac_UNIQUE` (`mac`),
  UNIQUE KEY `ip_UNIQUE` (`ip`),
  KEY `equipoLaboratorio_idx` (`id_laboratorio`),
  CONSTRAINT `equipoLaboratorio` FOREIGN KEY (`id_laboratorio`) REFERENCES `laboratorios` (`id_laboratorio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipos`
--

LOCK TABLES `equipos` WRITE;
/*!40000 ALTER TABLE `equipos` DISABLE KEYS */;
INSERT INTO `equipos` VALUES (2,1,'192.168.10.9',' B8975A5C02B9',9,2);
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
  `codigo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `nombre` varchar(45) CHARACTER SET latin1 NOT NULL,
  `descripcion` varchar(255) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`id_laboratorio`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laboratorios`
--

LOCK TABLES `laboratorios` WRITE;
/*!40000 ALTER TABLE `laboratorios` DISABLE KEYS */;
INSERT INTO `laboratorios` VALUES (1,'UNL01','Ingeniería de sistemas','Laboratorio para estudiantes de la carrera en sistemas infórmaticos y computación');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sesiones`
--

LOCK TABLES `sesiones` WRITE;
/*!40000 ALTER TABLE `sesiones` DISABLE KEYS */;
INSERT INTO `sesiones` VALUES (2,2,1,1,'2014-11-03 20:53:08','2014-11-03 20:53:14'),(3,2,1,1,'2014-11-03 20:53:29','2014-11-03 21:01:35'),(4,2,1,1,'2014-11-03 20:55:05','2014-11-03 20:55:12'),(5,2,1,1,'2014-11-03 21:01:47','2014-11-03 21:03:26');
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
  `cedula` varchar(10) CHARACTER SET latin1 NOT NULL,
  `clave` varchar(45) CHARACTER SET latin1 NOT NULL,
  `nombres` varchar(45) CHARACTER SET latin1 NOT NULL,
  `apellidos` varchar(45) CHARACTER SET latin1 NOT NULL,
  `correo` varchar(45) CHARACTER SET latin1 NOT NULL,
  `celular` varchar(10) CHARACTER SET latin1 NOT NULL,
  `rol_usuario` int(11) NOT NULL COMMENT '0->estudiante\n1->administrador',
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `cedula_UNIQUE` (`cedula`),
  UNIQUE KEY `correo_UNIQUE` (`correo`),
  UNIQUE KEY `celular_UNIQUE` (`celular`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'0705462745','admin','DIEGO JACINTO','ROMERO ARMIJOS','diego.romero@kradac.com','0969748969',0),(2,'1105581316','admin','ANDREA MARIBEL','PATIÑO ABAD','mayuri.150293@gmail.com','0969748968',1);
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

-- Dump completed on 2014-11-03 17:18:06
