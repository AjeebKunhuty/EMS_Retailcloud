CREATE DATABASE  IF NOT EXISTS `ems_retailcloud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ems_retailcloud`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ems_retailcloud
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `head` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `head` (`head`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`head`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Development','2008-01-01',2),(2,'Production','2008-04-01',3),(4,'Human Resources','2008-10-01',5);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `salary` decimal(15,2) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `joining_date` date DEFAULT NULL,
  `bonus_percentage` decimal(5,2) DEFAULT NULL,
  `department` int DEFAULT NULL,
  `manager` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `manager` (`manager`),
  KEY `department` (`department`),
  CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`manager`) REFERENCES `employee` (`id`),
  CONSTRAINT `employee_ibfk_3` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Ajeeb Kunhuty','2002-08-11',264000.00,'One Anthem','Java Developer','2025-11-01',5.00,1,4),(2,'Prajith P','2001-01-01',400000.00,'Kozhikode','Lead Software Engineer','2022-09-01',15.00,1,5),(3,'Danu Sabu','2000-01-02',350000.00,'Kollam','Product Manager','2024-08-01',10.00,2,5),(4,'Athul NK','2001-01-02',400000.00,'Kozhikode','Software Engineer I','2022-08-01',15.00,1,2),(5,'Alu Thomas','1998-01-03',600000.00,'Kottayam','HR','2024-04-01',15.00,4,2),(6,'Ashin Sabu','2002-01-04',264000.00,'Malappuram','Product Specialist I','2025-08-11',5.00,1,3),(7,'Tony Stark','2002-01-04',264000.00,'California','Developer I','2025-11-01',10.00,1,2),(8,'Bruce Banner','1994-01-04',264000.00,'Texas','Developer I','2025-11-01',10.00,1,2),(9,'Peter Parker','2000-01-04',264000.00,'New York','Developer I','2025-11-01',10.00,1,2),(10,'Joaquin Torres','2004-01-04',264000.00,'Washington','Developer I','2025-11-01',10.00,1,2),(11,'John Economos','2000-01-04',264000.00,'Washington','Developer I','2025-11-01',10.00,1,2),(12,'Happy Diwali','2000-01-04',264000.00,'Washington','Developer I','2025-10-20',10.00,1,2),(13,'Happy Hogan','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(14,'Tom Holland','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(15,'Mammootty','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(16,'Mohanlal','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(17,'Leonardo','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(18,'Donald','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(19,'Lionel','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(20,'Cristiano','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(21,'Kevin','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,2,3),(22,'Macallister','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,4,3),(23,'John Doe','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,4,3),(24,'Jane Doe','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,4,3),(25,'May Parker','2000-11-04',264000.00,'Washington','Product Engineer I','2025-10-30',10.00,4,3),(27,'Shashi Kumar','2000-11-04',264001.00,'Kozhikode','Product Specialist I','2025-11-30',10.00,2,3),(28,'Erling','2000-11-04',264000.25,'Norway','Product Engineer I','2025-11-30',5.00,1,4);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-17 19:23:51
