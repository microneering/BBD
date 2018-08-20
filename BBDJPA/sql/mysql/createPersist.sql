-- MySQL dump 10.13
--
-- Host: localhost    Database: persist
-- ------------------------------------------------------
-- Server version	5.1.22-rc-community

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
-- Table structure for table `bbdjpaentity`
--

DROP TABLE IF EXISTS `bbdjpaentity`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bbdjpaentity` (
  `objectId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `className` text NOT NULL,
  PRIMARY KEY (`objectId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `bbdjpafield`
--

DROP TABLE IF EXISTS `bbdjpafield`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bbdjpafield` (
  `fieldId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bbdjpaobjectId` int(10) unsigned NOT NULL,
  `javaclassId` int(10) unsigned NOT NULL,
  `serializedvalue` text NOT NULL,
  PRIMARY KEY (`fieldId`),
  KEY `FK_bbdjpafield_1` (`bbdjpaobjectId`),
  KEY `FK_bbdjpafield_2` (`javaclassId`),
  CONSTRAINT `FK_bbdjpafield_1` FOREIGN KEY (`bbdjpaobjectId`) REFERENCES `bbdjpaentity` (`objectId`),
  CONSTRAINT `FK_bbdjpafield_2` FOREIGN KEY (`javaclassId`) REFERENCES `bbdjpajavaclass` (`javaclassId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `bbdjpajavaclass`
--

DROP TABLE IF EXISTS `bbdjpajavaclass`;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
CREATE TABLE `bbdjpajavaclass` (
  `javaClassId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Java classes fully qualied name',
  `javaClassName` text NOT NULL,
  PRIMARY KEY (`javaClassId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'persist'
--
/*!50003 DROP PROCEDURE IF EXISTS `getBbdJpaEntityField` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`bbd`@`localhost`*/ /*!50003 PROCEDURE `getBbdJpaEntityField`(
      in entityId Integer, in fieldPosition int)
BEGIN

  create temporary table IF NOT EXISTS temp_tab1(fieldId int) ENGINE = MEMORY;

  set @a = concat('INSERT INTO temp_tab1 SELECT fieldId FROM persist.bbdjpafield ',
                  ' WHERE bbdjpaobjectId = ', entityId,
                  ' ORDER BY fieldId LIMIT ',fieldPosition, ', 1 ;');
  PREPARE stmt1 FROM @a;
  EXECUTE stmt1;
  DEALLOCATE PREPARE stmt1;

  select * from temp_tab1;


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getBbdJpaEntityId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`bbd`@`localhost`*/ /*!50003 PROCEDURE `getBbdJpaEntityId`(in objectClassName text)
BEGIN
  DECLARE myId INTEGER;
  SELECT objectId as 'objectId' into myId from persist.bbdjpaentity where className = objectClassName;

  if isnull(myId) THEN
     INSERT INTO persist.bbdjpaentity (className) values(objectClassName);
     SELECT LAST_INSERT_ID() as 'objectId';
   ELSE
     select myId as 'objectId';
   END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getJavaClass` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`bbd`@`localhost`*/ /*!50003 PROCEDURE `getJavaClass`()
BEGIN

  select * from bbdjpajavaclass order by javaClassName;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getJavaClassId` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`bbd`@`localhost`*/ /*!50003 PROCEDURE `getJavaClassId`(in jcn text)
BEGIN
  DECLARE myId INTEGER;
  SELECT javaClassId as 'javaClassId' into myId from persist.bbdjpajavaclass where javaClassName = jcn;

  if isnull(myId) THEN
     INSERT INTO persist.bbdjpajavaclass (javaclassName) values(jcn);
     SELECT LAST_INSERT_ID() as 'javaClassId';
   ELSE
     select myId as 'javaClassId';
   END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `persistBean` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `persistBean`()
BEGIN

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `putBbdJpaEntityField` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`bbd`@`localhost`*/ /*!50003 PROCEDURE `putBbdJpaEntityField`(
      in entityId Integer, in javaClassId Integer, in fieldPosition int, in fieldValue Text)
BEGIN

  Declare myId int;

  create temporary table IF NOT EXISTS temp_tab1(fieldId int) ENGINE = MEMORY;

  set @a = concat('INSERT INTO temp_tab1 SELECT fieldId FROM persist.bbdjpafield ',
                  ' WHERE bbdjpaobjectId = ', entityId,
                  ' ORDER BY fieldId LIMIT ',fieldPosition, ', 1 ;');
  PREPARE stmt1 FROM @a;
  EXECUTE stmt1;
  DEALLOCATE PREPARE stmt1;

  select fieldId into myId from temp_tab1;


  if isnull( myId) THEN
     INSERT INTO persist.bbdjpafield (bbdjpaobjectId, javaclassId, serializedValue)
              VALUES(entityId, javaClassId, fieldValue);
     SELECT LAST_INSERT_ID() as 'fieldId';
  ELSE
     UPDATE persist.bbdjpafield SET serializedValue = fieldValue;
     SELECT  myId as 'fieldId';
  END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2007-12-01 22:59:03
