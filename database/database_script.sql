CREATE DATABASE `cinematicketbookingsystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `user_name` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `users` (`id`,`first_name`,`last_name`,`user_name`,`password`,`role`,`email`,`phone`) VALUES (1,'Administrator',NULL,'admin','password','ADMIN','admin@gmail.com',NULL);
INSERT INTO `users` (`id`,`first_name`,`last_name`,`user_name`,`password`,`role`,`email`,`phone`) VALUES (2,'User Test',NULL,'user','password','USER','user@gmail.com','22695245192');