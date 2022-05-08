-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: movielib
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actor` (
  `actor_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  PRIMARY KEY (`actor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Johnny','Depp','1963-06-09'),(2,'Orlando','Bloom','1977-01-13'),(3,'Keira','Knightley','1985-03-26'),(4,'Robert','Downey','1965-04-04'),(5,'Chris','Evans','1981-06-13'),(6,'Mark','Ruffalo','1967-11-22'),(7,'Chris','Hemsworth','1983-08-11'),(8,'Scarlett','Johansson','1984-11-22'),(9,'Jeremy','Renner','1971-01-07'),(10,'Matthew','McConaughey','1969-11-04'),(11,'Anne','Hathaway','1982-11-12'),(12,'Jessica','Chastain','1977-03-24'),(13,'Tim','Robbins','1958-10-16'),(14,'Morgan','Freeman','1937-06-01'),(15,'Ivan','Mykolaichuk','1941-06-15'),(16,'Larisa','Kadochnikova','1937-08-30'),(17,'Mykola','Grinko','1920-03-22'),(18,'Melissa','McCarthy','1970-08-26'),(19,'Kristen','Wiig','1973-08-22'),(20,'Tom','Holland','1996-06-01'),(21,'Maree','Zendaya','1996-09-01'),(22,'Nonso','Anozie','1978-11-17'),(23,'Adeel','Akhtar','1980-09-18');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actorsinmovies`
--

DROP TABLE IF EXISTS `actorsinmovies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actorsinmovies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `actor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_id` (`movie_id`),
  KEY `actor_id` (`actor_id`),
  CONSTRAINT `actorsinmovies_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  CONSTRAINT `actorsinmovies_ibfk_2` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`actor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actorsinmovies`
--

LOCK TABLES `actorsinmovies` WRITE;
/*!40000 ALTER TABLE `actorsinmovies` DISABLE KEYS */;
INSERT INTO `actorsinmovies` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,2,5),(6,2,6),(7,2,7),(8,2,8),(9,2,9),(10,3,10),(11,3,11),(12,3,12),(13,4,13),(14,4,14),(15,5,15),(16,5,16),(17,5,17),(18,6,18),(19,6,19),(20,6,5),(21,7,20),(22,7,21),(23,7,4),(24,7,5),(25,7,8);
/*!40000 ALTER TABLE `actorsinmovies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `director` (
  `director_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  PRIMARY KEY (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Jerry','Bruckheimer','1943-09-21'),(2,'Anthony','Russo','1970-02-03'),(3,'Christopher','Nolan','1970-07-30'),(4,'Frank','Darabont','1959-01-28'),(5,'Sergei','Parajanov','1924-01-09'),(6,'Paul','Feig','1962-09-17'),(7,'Kevin','Feige','1973-07-02'),(8,'Robert','Downey','1965-04-04');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `release_date` date NOT NULL,
  `country` varchar(255) NOT NULL,
  `director_id` int NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `director_id` (`director_id`),
  CONSTRAINT `movie_ibfk_1` FOREIGN KEY (`director_id`) REFERENCES `director` (`director_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,'Pirates of the Caribbean 5','2017-05-26','USA',1),(2,'Avengers: Endgame','2019-04-22','USA',2),(3,'Interstellar','2014-10-26','United Kingdom',3),(4,'The Shawshank Redemption','1994-09-10','USA',4),(5,'Shadows of Forgotten Ancestors','1965-03-22','Ukraine',5),(6,'Ghostbusters','2016-07-09','USA',6),(7,'Spider-Man: Homecoming','2017-06-28','USA',7),(8,'Sweet Tooth','2021-06-04','New Zeeland',8);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-06 21:54:59
