CREATE TABLE `actor` (
  `actor_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  PRIMARY KEY (`actor_id`)
);

INSERT INTO `actor` VALUES (1,'Johnny','Depp','1963-06-09'),(2,'Orlando','Bloom','1977-01-13'),(3,'Keira','Knightley','1985-03-26'),(4,'Robert','Downey','1965-04-04'),(5,'Chris','Evans','1981-06-13'),(6,'Mark','Ruffalo','1967-11-22'),(7,'Chris','Hemsworth','1983-08-11'),(8,'Scarlett','Johansson','1984-11-22'),(9,'Jimmy','Renner','1971-01-07'),(11,'Anne','Hathaway','1982-11-12'),(12,'Jessica','Chastain','1977-03-24'),(13,'Tim','Robbins','1958-10-16'),(14,'Morgan','Freeman','1937-06-01'),(15,'Ivan','Mykolaichuk','1941-06-15'),(16,'Larisa','Kadochnikova','1937-08-30'),(17,'Mykola','Grinko','1920-03-22'),(18,'Melissa','McCarthy','1970-08-26'),(19,'Kristen','Wiig','1973-08-22'),(20,'Tom','Holland','1996-06-01'),(21,'Maree','Zendaya','1996-09-01'),(22,'Nonso','Anozie','1978-11-17'),(23,'Adeel','Akhtar','1980-09-18'),(24,'Brad','Pitt','1963-12-18'),(25,'Marlon','Brando','1924-04-03'),(26,'Al','Pacino','1940-04-25'),(27,'James','Caan','1940-03-26'),(28,'Harrison','Ford','1942-07-13'),(29,'Sean','Connery','1930-08-25');

CREATE TABLE `actorsinmovies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_id` int NOT NULL,
  `actor_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `movie_id` (`movie_id`),
  KEY `actor_id` (`actor_id`),
  CONSTRAINT `actorsinmovies_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`),
  CONSTRAINT `actorsinmovies_ibfk_2` FOREIGN KEY (`actor_id`) REFERENCES `actor` (`actor_id`)
);


INSERT INTO `actorsinmovies` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,4),(5,2,5),(6,2,6),(7,2,7),(8,2,8),(11,3,11),(12,3,12),(18,6,18),(19,6,19),(20,6,5),(21,7,20),(22,7,21),(23,7,4),(24,7,5),(25,7,8),(35,8,22),(36,8,23);

CREATE TABLE `director` (
  `director_id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `birth_date` date NOT NULL,
  PRIMARY KEY (`director_id`)
);

INSERT INTO `director` VALUES (1,'Je','Bruckheimer','1943-09-21'),(2,'Anthony','Russo','1970-02-03'),(3,'Christopher','Nolan','1970-07-30'),(4,'Frank','Darabont','1959-01-28'),(5,'Sergei','Parajanov','1924-01-09'),(6,'Paul','Feig','1962-09-17'),(7,'Kevin','Feige','1973-07-02'),(8,'Robert','Downey','1965-04-04'),(9,'Francis','Coppola','1939-04-07'),(10,'Steven','Spielberg','1946-12-18'),(11,'Peter','Jackson','1961-10-31');

CREATE TABLE `movie` (
  `movie_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `release_date` date NOT NULL,
  `country` varchar(255) NOT NULL,
  `director_id` int NOT NULL,
  PRIMARY KEY (`movie_id`),
  KEY `director_id` (`director_id`),
  CONSTRAINT `movie_ibfk_1` FOREIGN KEY (`director_id`) REFERENCES `director` (`director_id`)
);

INSERT INTO `movie` VALUES (1,'Pirates of the Caribbean 5','2017-05-26','USA',1),(2,'Avengers: Endgame','2019-04-22','USA',2),(3,'Interstellar','2014-10-26','United Kingdom',3),(6,'Ghostbusters','2016-07-09','USA',6),(7,'Spider-Man: Homecoming','2017-06-28','USA',7),(8,'Sweet Tooth','2021-06-04','New Zeeland',8),(9,'Indiana Jones and the Last Crusade','1989-05-24','USA',10),(10,'New Film','2022-12-21','Ukraine',3);