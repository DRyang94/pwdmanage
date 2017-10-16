-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: passwordmanager
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `log_op`
--

DROP TABLE IF EXISTS `log_op`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_op` (
  `LOG_ID` int(11) NOT NULL,
  `USER_ID` char(32) DEFAULT NULL,
  `LOGIN_IP` varchar(20) NOT NULL,
  `TYPE_ID` int(11) NOT NULL COMMENT '操作类型\n',
  `OPERATION` varchar(1000) DEFAULT NULL COMMENT '具体操作内容\n',
  `CREATE_DATE` datetime NOT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`LOG_ID`),
  KEY `fk_opType_idx` (`TYPE_ID`),
  KEY `fk_userID` (`USER_ID`),
  CONSTRAINT `fk_op` FOREIGN KEY (`TYPE_ID`) REFERENCES `log_optype` (`TYPE_ID`),
  CONSTRAINT `fk_userID` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_op`
--

LOCK TABLES `log_op` WRITE;
/*!40000 ALTER TABLE `log_op` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_op` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_optype`
--

DROP TABLE IF EXISTS `log_optype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_optype` (
  `TYPE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TYPE_NAME` varchar(45) NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_optype`
--

LOCK TABLES `log_optype` WRITE;
/*!40000 ALTER TABLE `log_optype` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_optype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_databases`
--

DROP TABLE IF EXISTS `pm_databases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_databases` (
  `DB_ID` char(32) NOT NULL,
  `DB_NAME` varchar(50) NOT NULL,
  `SYSTEM_INFO` varchar(50) NOT NULL,
  `SYSTEM_VERSION` varchar(50) NOT NULL,
  `IP` varchar(20) NOT NULL,
  `PORT` int(10) NOT NULL,
  PRIMARY KEY (`DB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_databases`
--

LOCK TABLES `pm_databases` WRITE;
/*!40000 ALTER TABLE `pm_databases` DISABLE KEYS */;
/*!40000 ALTER TABLE `pm_databases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_mainframes`
--

DROP TABLE IF EXISTS `pm_mainframes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_mainframes` (
  `MAINFRAME_ID` char(32) NOT NULL,
  `SYSTEM_INFO` varchar(50) NOT NULL,
  `SYSTEM_VERSION` varchar(50) NOT NULL,
  `IP` varchar(20) NOT NULL,
  `PORT` int(10) NOT NULL,
  PRIMARY KEY (`MAINFRAME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_mainframes`
--

LOCK TABLES `pm_mainframes` WRITE;
/*!40000 ALTER TABLE `pm_mainframes` DISABLE KEYS */;
/*!40000 ALTER TABLE `pm_mainframes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pm_user`
--

DROP TABLE IF EXISTS `pm_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pm_user` (
  `USER_ID` char(32) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PWD` varchar(50) NOT NULL,
  `MAINFRAME_ID` char(32) DEFAULT NULL,
  `DATABASE_ID` char(32) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`USERNAME`),
  UNIQUE KEY `DATABASE_USER_ID_UNIQUE` (`USER_ID`),
  KEY `fk_pmMain` (`MAINFRAME_ID`),
  KEY `fk_pmDB` (`DATABASE_ID`),
  CONSTRAINT `fk_pmDB` FOREIGN KEY (`DATABASE_ID`) REFERENCES `pm_databases` (`DB_ID`),
  CONSTRAINT `fk_pmMain` FOREIGN KEY (`MAINFRAME_ID`) REFERENCES `pm_mainframes` (`MAINFRAME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pm_user`
--

LOCK TABLES `pm_user` WRITE;
/*!40000 ALTER TABLE `pm_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `pm_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_function`
--

DROP TABLE IF EXISTS `sys_function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_function` (
  `FUN_ID` char(32) NOT NULL,
  `FUN_NAME` varchar(50) NOT NULL,
  `FUN_URL` varchar(100) NOT NULL,
  `FUN_STATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FUN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_function`
--

LOCK TABLES `sys_function` WRITE;
/*!40000 ALTER TABLE `sys_function` DISABLE KEYS */;
INSERT INTO `sys_function` VALUES ('795ae80d8a3911e7a501c45444fb4cc1','insert','nothing',NULL),('7d7032ee8a3911e7a501c45444fb4cc1','delete','nothing',NULL),('b23995c98a3911e7a501c45444fb4cc1','modify','nothing',NULL),('bdddd7ca8a3911e7a501c45444fb4cc1','import','nothing',NULL),('bfce175b8a3911e7a501c45444fb4cc1','export','nothing',NULL),('d7822f778a3911e7a501c45444fb4cc1','assign','nothing',NULL);
/*!40000 ALTER TABLE `sys_function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_rfrelation`
--

DROP TABLE IF EXISTS `sys_rfrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_rfrelation` (
  `UF_ID` char(32) NOT NULL,
  `ROLE_ID` char(32) NOT NULL,
  `FUN_ID` char(32) NOT NULL,
  PRIMARY KEY (`UF_ID`),
  KEY `fk_rfFunID` (`FUN_ID`),
  KEY `fk_rfRoleID` (`ROLE_ID`),
  CONSTRAINT `fk_rfFunID` FOREIGN KEY (`FUN_ID`) REFERENCES `sys_function` (`FUN_ID`),
  CONSTRAINT `fk_rfRoleID` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_rfrelation`
--

LOCK TABLES `sys_rfrelation` WRITE;
/*!40000 ALTER TABLE `sys_rfrelation` DISABLE KEYS */;
INSERT INTO `sys_rfrelation` VALUES ('99012cbb8a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','795ae80d8a3911e7a501c45444fb4cc1'),('9d6fb7b08a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','7d7032ee8a3911e7a501c45444fb4cc1'),('a281bcb98a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','bdddd7ca8a3911e7a501c45444fb4cc1'),('a47762308a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','bfce175b8a3911e7a501c45444fb4cc1'),('b1aea4058a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','d7822f778a3911e7a501c45444fb4cc1'),('c6c9f4138a5211e7b2e2c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1','b23995c98a3911e7a501c45444fb4cc1');
/*!40000 ALTER TABLE `sys_rfrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_role` (
  `ROLE_ID` char(32) NOT NULL,
  `ROLE_NAME` varchar(50) NOT NULL,
  `COUNT` int(11) NOT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_role`
--

LOCK TABLES `sys_role` WRITE;
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` VALUES ('24efdefb8a3111e7a501c45444fb4cc1','user',0),('a55bfa218a3011e7a501c45444fb4cc1','admin',0);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_urrelation`
--

DROP TABLE IF EXISTS `sys_urrelation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_urrelation` (
  `UR_ID` char(32) NOT NULL,
  `USER_ID` char(32) NOT NULL,
  `ROLE_ID` char(32) NOT NULL,
  PRIMARY KEY (`UR_ID`),
  KEY `fk_urUserID` (`USER_ID`),
  KEY `fk_urROLEID` (`ROLE_ID`),
  CONSTRAINT `fk_urROLEID` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`),
  CONSTRAINT `fk_urUserID` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_urrelation`
--

LOCK TABLES `sys_urrelation` WRITE;
/*!40000 ALTER TABLE `sys_urrelation` DISABLE KEYS */;
INSERT INTO `sys_urrelation` VALUES ('d9cc87688a3211e7a501c45444fb4cc1','882b82d38a3111e7a501c45444fb4cc1','a55bfa218a3011e7a501c45444fb4cc1');
/*!40000 ALTER TABLE `sys_urrelation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user` (
  `USER_ID` char(32) NOT NULL,
  `USERNAME` varchar(50) NOT NULL,
  `PWD` varchar(50) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `ID_UNIQUE` (`USER_ID`),
  UNIQUE KEY `NAME_UNIQUE` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES ('882b82d38a3111e7a501c45444fb4cc1','admin','123456','2017-08-26 15:38:20','','');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_access`
--

DROP TABLE IF EXISTS `sys_user_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_access` (
  `USER_ID` char(32) NOT NULL,
  `DB_ID` char(32) DEFAULT NULL,
  `MAINFRAME_ID` char(32) DEFAULT NULL,
  `ACCESS_USERID` char(32) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `fk_acDBID` (`DB_ID`),
  KEY `fk_acMAINID` (`MAINFRAME_ID`),
  CONSTRAINT `fk_acDBID` FOREIGN KEY (`DB_ID`) REFERENCES `pm_databases` (`DB_ID`),
  CONSTRAINT `fk_acMAINID` FOREIGN KEY (`MAINFRAME_ID`) REFERENCES `pm_mainframes` (`MAINFRAME_ID`),
  CONSTRAINT `fk_acUSERID` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_access`
--

LOCK TABLES `sys_user_access` WRITE;
/*!40000 ALTER TABLE `sys_user_access` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_access` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-29  8:34:17
