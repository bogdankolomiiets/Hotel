-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: mariam
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bank` (
  `bankId` int(11) NOT NULL AUTO_INCREMENT,
  `bankFullName` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bankShortName` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `bankIdentifierCode` int(11) DEFAULT NULL,
  `bankTaxpayerIdentificationNumber` int(11) DEFAULT NULL,
  `bankCardNumber` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`bankId`),
  UNIQUE KEY `bank_bankIdentifierCode_uindex` (`bankIdentifierCode`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'my own super bank \"STAR-bank\"','bank STAR-bank',1234567890,1234566541,'0111101011110100');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client` (
  `clientId` int(11) NOT NULL AUTO_INCREMENT,
  `clientName` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `clientSurname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `clientPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `clientEmail` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `clientPass` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `clientRole` int(11) DEFAULT NULL,
  PRIMARY KEY (`clientId`),
  UNIQUE KEY `client_clientEmail_uindex` (`clientEmail`),
  UNIQUE KEY `client_clientPhone_uindex` (`clientPhone`),
  KEY `client_fk` (`clientRole`),
  CONSTRAINT `client_fk` FOREIGN KEY (`clientRole`) REFERENCES `client_roles` (`clientRoleId`)
) ENGINE=InnoDB AUTO_INCREMENT=378 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='The table of clients';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (19,'Bogdan','Kolomiiets','555444777','bogdan.kolomiiets@gmail.com','$2a$10$tFwWV6cXVYffyS2AW2CQf.l1EZbs7u5avwJyU9HQApCxvRz.P1BVS',1),(118,'Тімур','Коломієць','555','timur.kolomiiets@gmail.com','$2a$10$G7bjSt.Bl23L.YOCgqmOZurFAaTOuIFpDknieKLmTCkVrMEtkZu7C',0);
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_roles`
--

DROP TABLE IF EXISTS `client_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `client_roles` (
  `clientRoleId` int(11) NOT NULL,
  `clientRoleName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`clientRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_roles`
--

LOCK TABLES `client_roles` WRITE;
/*!40000 ALTER TABLE `client_roles` DISABLE KEYS */;
INSERT INTO `client_roles` VALUES (0,'USER'),(1,'MANAGER'),(2,'ADMINISTRATOR');
/*!40000 ALTER TABLE `client_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hotel` (
  `hotelID` int(11) NOT NULL AUTO_INCREMENT,
  `hotelName` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hotelAddress` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hotelPhone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hotelCountOfFloors` int(11) NOT NULL,
  `hotelCountOfStars` int(11) NOT NULL,
  PRIMARY KEY (`hotelID`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (24,'Mariam','Vinnytsia, vul. Peremogu 25','+380683111845',3,4);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payment` (
  `paymentId` int(11) NOT NULL AUTO_INCREMENT,
  `paymentBankId` int(11) NOT NULL,
  `paymentAmount` double NOT NULL,
  `paymentClientId` int(11) NOT NULL,
  `paymentStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`paymentId`),
  KEY `payment_bank_fk` (`paymentBankId`),
  KEY `payment_client_fk` (`paymentClientId`),
  CONSTRAINT `payment_bank_fk` FOREIGN KEY (`paymentBankId`) REFERENCES `bank` (`bankId`),
  CONSTRAINT `payment_client_fk` FOREIGN KEY (`paymentClientId`) REFERENCES `client` (`clientId`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `query`
--

DROP TABLE IF EXISTS `query`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `query` (
  `queryId` int(11) NOT NULL AUTO_INCREMENT,
  `queryRoomType` int(11) DEFAULT NULL,
  `queryRoomLevel` int(11) DEFAULT NULL,
  `queryRoomBookingDate` datetime DEFAULT NULL,
  `queryRomStartDate` date DEFAULT NULL,
  `queryRoomEndDate` date DEFAULT NULL,
  `queryAmount` double DEFAULT NULL,
  `queryClientEmail` varchar(30) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `queryStatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`queryId`),
  KEY `query_roomlevel_fk` (`queryRoomLevel`),
  KEY `query_roomtype_fk` (`queryRoomType`),
  KEY `query_query_status_fk` (`queryStatus`),
  CONSTRAINT `query_query_status_fk` FOREIGN KEY (`queryStatus`) REFERENCES `query_status` (`queryStatusId`),
  CONSTRAINT `query_roomlevel_fk` FOREIGN KEY (`queryRoomLevel`) REFERENCES `roomlevel` (`roomLevelID`),
  CONSTRAINT `query_roomtype_fk` FOREIGN KEY (`queryRoomType`) REFERENCES `roomtype` (`roomTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Clients queries';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `query`
--

LOCK TABLES `query` WRITE;
/*!40000 ALTER TABLE `query` DISABLE KEYS */;
/*!40000 ALTER TABLE `query` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `query_status`
--

DROP TABLE IF EXISTS `query_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `query_status` (
  `queryStatusId` int(11) NOT NULL,
  `queryStatusName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`queryStatusId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `query_status`
--

LOCK TABLES `query_status` WRITE;
/*!40000 ALTER TABLE `query_status` DISABLE KEYS */;
INSERT INTO `query_status` VALUES (1,'PROCESSING'),(2,'DENY'),(3,'SUCCESS');
/*!40000 ALTER TABLE `query_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `room` (
  `roomNumber` int(11) NOT NULL,
  `roomTypeId` int(11) NOT NULL,
  `roomLevelId` int(11) NOT NULL,
  `roomPrice` double DEFAULT NULL,
  `roomBookingDate` datetime DEFAULT NULL,
  `roomStartDate` date DEFAULT NULL,
  `roomEndDate` date DEFAULT NULL,
  `hotelID` int(11) DEFAULT NULL,
  `clientID` int(11) DEFAULT NULL,
  PRIMARY KEY (`roomNumber`),
  KEY `room_hotel_fk` (`hotelID`),
  KEY `room_roomtype_fk` (`roomTypeId`),
  KEY `room_roomlevel_fk` (`roomLevelId`),
  CONSTRAINT `room_hotel_fk` FOREIGN KEY (`hotelID`) REFERENCES `hotel` (`hotelID`) ON DELETE CASCADE,
  CONSTRAINT `room_roomlevel_fk` FOREIGN KEY (`roomLevelId`) REFERENCES `roomlevel` (`roomLevelID`),
  CONSTRAINT `room_roomtype_fk` FOREIGN KEY (`roomTypeId`) REFERENCES `roomtype` (`roomTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Hotel rooms';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,1,200,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(2,1,1,200,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(3,1,2,350,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(4,1,2,350,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(5,2,1,450,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(6,2,1,450,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(7,2,2,500,'2019-06-23 00:48:34','2019-06-24','2019-06-27',24,118),(8,2,2,500,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(9,2,2,500,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(10,2,3,600,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(11,2,3,600,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(12,2,3,600,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(13,3,1,600,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(14,3,2,650,'2019-06-23 00:58:57','2019-06-24','2019-06-28',24,118),(15,3,3,700,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(16,4,1,700,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(17,4,1,700,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(18,4,2,750,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(19,4,2,750,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(20,4,3,800,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(21,5,3,1000,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(22,5,4,1200,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0),(23,5,4,1200,'1900-01-01 00:00:00','1900-01-01','1900-01-01',24,0);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomlevel`
--

DROP TABLE IF EXISTS `roomlevel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roomlevel` (
  `roomLevelID` int(11) NOT NULL AUTO_INCREMENT,
  `roomLevelName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`roomLevelID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Levels of rooms';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomlevel`
--

LOCK TABLES `roomlevel` WRITE;
/*!40000 ALTER TABLE `roomlevel` DISABLE KEYS */;
INSERT INTO `roomlevel` VALUES (1,'ECONOMY'),(2,'STANDARD'),(3,'IMPROVED'),(4,'DELUXE');
/*!40000 ALTER TABLE `roomlevel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roomtype` (
  `roomTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `roomTypeName` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`roomTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Types of room';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roomtype`
--

LOCK TABLES `roomtype` WRITE;
/*!40000 ALTER TABLE `roomtype` DISABLE KEYS */;
INSERT INTO `roomtype` VALUES (1,'SINGLE'),(2,'DOUBLE'),(3,'TRIPLE'),(4,'QUAD'),(5,'KING');
/*!40000 ALTER TABLE `roomtype` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-23  1:05:38
