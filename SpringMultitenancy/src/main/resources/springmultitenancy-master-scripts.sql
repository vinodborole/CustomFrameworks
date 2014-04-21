CREATE DATABASE `master` /*!40100 DEFAULT CHARACTER SET utf8 */;


CREATE TABLE `productrole` (
  `productroleid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`productroleid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `productuser` (
  `productuserid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `emailid` varchar(45) NOT NULL,
  `productroleid` int(11) NOT NULL,
  `isactive` bit(1) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`productuserid`),
  KEY `productuser_productrole_fk_idx` (`productroleid`),
  CONSTRAINT `productuser_productrole_fk` FOREIGN KEY (`productroleid`) REFERENCES `productrole` (`productroleid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `tenantinfo` (
  `tenantid` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `schemaname` varchar(45) DEFAULT NULL,
  `host` varchar(45) NOT NULL,
  `port` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`tenantid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `mailinfo` (
  `mailid` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(45) NOT NULL,
  `port` int(11) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `auth` bit(1) NOT NULL,
  `replyto` varchar(45) DEFAULT NULL,
  `mailfrom` varchar(45) DEFAULT NULL,
  `ssl` bit(1) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`mailid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `organizations` (
  `organizationid` int(11) NOT NULL AUTO_INCREMENT,
  `organizationname` varchar(45) NOT NULL,
  `tenantid` int(11) NOT NULL,
  `mailid` int(11) NOT NULL,
  `isactive` bit(1) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`organizationid`),
  KEY `organizations_mailinfo_fk_idx` (`mailid`),
  KEY `organizations_tenantinfo_fk_idx` (`tenantid`),
  CONSTRAINT `organizations_mailinfo_fk` FOREIGN KEY (`mailid`) REFERENCES `mailinfo` (`mailid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `organizations_tenantinfo_fk` FOREIGN KEY (`tenantid`) REFERENCES `tenantinfo` (`tenantid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `ldapinfo` (
  `ldapid` int(11) NOT NULL AUTO_INCREMENT,
  `host` varchar(45) NOT NULL,
  `domain` varchar(45) DEFAULT NULL,
  `authentication` varchar(45) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`ldapid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `emailid` varchar(45) NOT NULL,
  `authenticationtype` varchar(45) NOT NULL,
  `ldapid` int(11) DEFAULT NULL,
  `organizationid` int(11) NOT NULL,
  `isactive` bit(1) NOT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  KEY `users_ldap_fk_idx` (`ldapid`),
  KEY `users_organizations_fk_idx` (`organizationid`),
  CONSTRAINT `users_ldap_fk` FOREIGN KEY (`ldapid`) REFERENCES `ldapinfo` (`ldapid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `users_organizations_fk` FOREIGN KEY (`organizationid`) REFERENCES `organizations` (`organizationid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `organizationsadmin` (
  `organizationsadminid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `organizationid` int(11) NOT NULL,
  `isactive` bit(1) NOT NULL,
  `createdate` datetime DEFAULT NULL,
  `createdby` int(11) DEFAULT NULL,
  `lastupdatedate` datetime DEFAULT NULL,
  `lastupdatedby` int(11) DEFAULT NULL,
  PRIMARY KEY (`organizationsadminid`),
  KEY `organizationsadmin_users_fk_idx` (`userid`),
  KEY `organizationsadmin_organizations_fk_idx` (`organizationid`),
  CONSTRAINT `organizationsadmin_users_fk` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `organizationsadmin_organizations_fk` FOREIGN KEY (`organizationid`) REFERENCES `organizations` (`organizationid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;



INSERT INTO `master`.`productrole` (`productroleid`,`name`,`createdate`,`createdby`) VALUES (1,'ADMIN',CURDATE(),1);
INSERT INTO `master`.`productuser`(`firstname`,`lastname`,`emailid`,`productroleid`,`isactive`,`createdate`,`createdby`) VALUES ('admin','admin','admin@springmultitenancy.com',1,true,CURDATE(),1);



INSERT INTO `master`.`tenantinfo`(`type`,`name`,`host`,`port`,`username`,`password`,`createdate`,`createdby`) VALUES('mysql','client','localhost',3306,'root','root',CURDATE(),1);
INSERT INTO `master`.`tenantinfo`(`type`,`name`,`host`,`port`,`username`,`password`,`createdate`,`createdby`) VALUES('mysql','client1','localhost',3306,'root','root',CURDATE(),1);


INSERT INTO `master`.`mailinfo`(`host`,`port`,`username`,`password`,`auth`,`replyto`,`mailfrom`,`ssl`,`createdate`,`createdby`) VALUES('localhost',25,'vinod@abc.com','abc1234',true,'admin@abc.com','admin@abc.com',false,CURDATE(),1);
INSERT INTO `master`.`mailinfo`(`host`,`port`,`username`,`password`,`auth`,`replyto`,`mailfrom`,`ssl`,`createdate`,`createdby`) VALUES('localhost',25,'vinod@xyz.com','xyz1234',true,'admin@xyz.com','admin@xyz.com',false,CURDATE(),1);


INSERT INTO `master`.`organizations`(`organizationname`,`tenantid`,`mailid`,`isactive`,`createdate`,`createdby`) VALUES('abc',1,1,true,CURDATE(),1);
INSERT INTO `master`.`organizations`(`organizationname`,`tenantid`,`mailid`,`isactive`,`createdate`,`createdby`) VALUES('xyz',2,2,true,CURDATE(),1);

INSERT INTO `master`.`ldapinfo`(`host`,`domain`,`createdate`,`createdby`) VALUES('abc.com','abc',CURDATE(),1);
INSERT INTO `master`.`ldapinfo`(`host`,`domain`,`createdate`,`createdby`) VALUES('xyz.com','xyz',CURDATE(),1);


INSERT INTO `master`.`users`(`emailid`,`authenticationtype`,`organizationid`,`isactive`,`createdate`,`createdby`) VALUES('vinod@abc.com','BASIC',1,true,CURDATE(),1);
INSERT INTO `master`.`users`(`emailid`,`authenticationtype`,`organizationid`,`isactive`,`createdate`,`createdby`) VALUES('borole@xyz.com','BASIC',2,true,CURDATE(),1);


INSERT INTO `master`.`organizationsadmin`(`userid`,`organizationid`,`isactive`,`createdate`,`createdby`) VALUES(1,1,true,CURDATE(),1);
INSERT INTO `master`.`organizationsadmin`(`userid`,`organizationid`,`isactive`,`createdate`,`createdby`) VALUES(2,2,true,CURDATE(),1);


