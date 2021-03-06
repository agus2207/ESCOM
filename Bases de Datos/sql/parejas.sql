-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: parejas
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hombre`
--

DROP TABLE IF EXISTS `hombre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hombre` (
  `nomh` char(15) NOT NULL,
  `edad` int(3) NOT NULL,
  PRIMARY KEY (`nomh`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hombre`
--

LOCK TABLES `hombre` WRITE;
/*!40000 ALTER TABLE `hombre` DISABLE KEYS */;
INSERT INTO `hombre` VALUES ('ALBERTO',26),('ALEXIS',30),('ARTURO',31),('CARLOS',28),('EDUARDO',27),('ENRIQUE',29),('HECTOR',33),('LUIS',19),('MARIO',33),('PABLO',25),('VICTOR',31);
/*!40000 ALTER TABLE `hombre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hsim`
--

DROP TABLE IF EXISTS `hsim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hsim` (
  `nomh` char(15) NOT NULL,
  `nomm` char(15) NOT NULL,
  KEY `FKHSH` (`nomh`),
  KEY `FKHSM` (`nomm`),
  CONSTRAINT `FKHSH` FOREIGN KEY (`nomh`) REFERENCES `hombre` (`nomh`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKHSM` FOREIGN KEY (`nomm`) REFERENCES `mujer` (`nomm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hsim`
--

LOCK TABLES `hsim` WRITE;
/*!40000 ALTER TABLE `hsim` DISABLE KEYS */;
INSERT INTO `hsim` VALUES ('ALBERTO','LETICIA'),('ARTURO','LAURA'),('ALEXIS','PAOLA'),('CARLOS','PAOLA'),('ALBERTO','DANIELA'),('LUIS','LILIANA'),('PABLO','WENDY'),('MARIO','LILIANA'),('ALBERTO','ADRIANA'),('ARTURO','ADRIANA'),('ALEXIS','ADRIANA'),('CARLOS','ADRIANA'),('EDUARDO','ADRIANA'),('LUIS','ADRIANA'),('PABLO','ADRIANA'),('MARIO','ADRIANA'),('VICTOR','ADRIANA'),('VICTOR','SANDY');
/*!40000 ALTER TABLE `hsim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matrim`
--

DROP TABLE IF EXISTS `matrim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `matrim` (
  `nomh` char(15) NOT NULL,
  `nomm` char(15) NOT NULL,
  KEY `FKMH` (`nomh`),
  KEY `FKMM` (`nomm`),
  CONSTRAINT `FKMH` FOREIGN KEY (`nomh`) REFERENCES `hombre` (`nomh`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKMM` FOREIGN KEY (`nomm`) REFERENCES `mujer` (`nomm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matrim`
--

LOCK TABLES `matrim` WRITE;
/*!40000 ALTER TABLE `matrim` DISABLE KEYS */;
INSERT INTO `matrim` VALUES ('ARTURO','LAURA'),('CARLOS','LILIANA'),('EDUARDO','PAOLA'),('LUIS','ALMA'),('PABLO','WENDY'),('VICTOR','SANDY');
/*!40000 ALTER TABLE `matrim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `msim`
--

DROP TABLE IF EXISTS `msim`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `msim` (
  `nomh` char(15) NOT NULL,
  `nomm` char(15) NOT NULL,
  KEY `FKMSH` (`nomh`),
  KEY `FKMSM` (`nomm`),
  CONSTRAINT `FKMSH` FOREIGN KEY (`nomh`) REFERENCES `hombre` (`nomh`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKMSM` FOREIGN KEY (`nomm`) REFERENCES `mujer` (`nomm`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msim`
--

LOCK TABLES `msim` WRITE;
/*!40000 ALTER TABLE `msim` DISABLE KEYS */;
INSERT INTO `msim` VALUES ('ALBERTO','ALMA'),('ALBERTO','LETICIA'),('ARTURO','LAURA'),('PABLO','WENDY'),('ALBERTO','ADRIANA'),('ALEXIS','PAOLA'),('ALBERTO','DANIELA'),('PABLO','LILIANA'),('MARIO','LAURA'),('MARIO','LILIANA'),('MARIO','PAOLA'),('MARIO','ALMA'),('MARIO','WENDY'),('MARIO','SANDY'),('VICTOR','SANDY');
/*!40000 ALTER TABLE `msim` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mujer`
--

DROP TABLE IF EXISTS `mujer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mujer` (
  `nomm` char(15) NOT NULL,
  `edad` int(3) NOT NULL,
  PRIMARY KEY (`nomm`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mujer`
--

LOCK TABLES `mujer` WRITE;
/*!40000 ALTER TABLE `mujer` DISABLE KEYS */;
INSERT INTO `mujer` VALUES ('ADRIANA',27),('ALMA',19),('DANIELA',25),('LAURA',30),('LETICIA',33),('LILIANA',32),('PAOLA',24),('SANDY',31),('WENDY',25);
/*!40000 ALTER TABLE `mujer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-28  0:50:46
