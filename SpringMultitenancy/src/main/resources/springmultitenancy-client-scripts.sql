CREATE DATABASE `client` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `isactive` bit(1) NOT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT INTO `client`.`users`(`username`,`password`,`isactive`,`createdate`,`createdby`) VALUES('vinod@abc.com','vinod',true,CURDATE(),1);