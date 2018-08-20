-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.32


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema bbd
--

CREATE DATABASE IF NOT EXISTS bbd;
USE bbd;

--
-- Definition of table `bbd`.`apiunittest`
--

DROP TABLE IF EXISTS `bbd`.`apiunittest`;
CREATE TABLE  `bbd`.`apiunittest` (
  `testName` varchar(1000) NOT NULL DEFAULT '',
  `starttime` datetime NOT NULL,
  `runMS` int(11) NOT NULL DEFAULT '0',
  `rowsSelected` int(11) NOT NULL DEFAULT '0',
  `rowsDeleted` int(11) NOT NULL DEFAULT '0',
  `rowsUpdated` int(11) NOT NULL DEFAULT '0',
  `rowsInserted` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `bbd`.`apiunittest`
--

--
-- Definition of table `bbd`.`storedprocedure`
--

DROP TABLE IF EXISTS `bbd`.`storedprocedure`;
CREATE TABLE  `bbd`.`storedprocedure` (
  `BBDDatabase` char(50) NOT NULL DEFAULT '',
  `StoredProcedure` char(255) NOT NULL DEFAULT '',
  `BBDSQL` varchar(1000) NOT NULL DEFAULT '',
  `Deprecated` tinyint(1) NOT NULL DEFAULT '0',
  `TestOnly` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`BBDDatabase`,`StoredProcedure`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `bbd`.`storedprocedure`
--
INSERT INTO `bbd`.`storedprocedure` (`BBDDatabase`,`StoredProcedure`,`BBDSQL`,`Deprecated`,`TestOnly`) VALUES 
 ('bbd','APIDelete','call APIDelete(?,?)',0,0),
 ('bbd','APIInsert','call APIInsert(?,?,?,?,?)',0,0),
 ('bbd','APIUpdate','call APIUpdate(?,?,?,?,?)',0,0),
 ('bbd','APIUTInsert','call APIUTInsert(?,?,?,?,?,?,?)',0,1),
 ('bbd','getAPI','call getAPI(?,?)',0,0),
 ('bbd','getAPIs','call getAPIs()',0,0),
 ('bbd','getDB','call getDB()',0,0),
 ('bbd','getTableNames','call getTableNames(?)',0,0),
 ('bbd','getUTs','call getUTs()',0,0),
 ('persist','createTable','call createTable(?,?)',0,0),
 ('persist','getEntities','call getEntities()',0,0),
 ('persist','getEntityFields','call getEntityFieldsForBean(?)',0,0),
 ('persist','getJavaClass','call getJavaClass()',0,0),
 ('persist','persistBean','call persistBean()',0,0),
 ('persist','persistEntity','call getBbdJpaEntityId(?)',0,0),
 ('persist','persistEntityField','call putBbdJpaEntityField(?,?,?,?)',0,0),
 ('persist','persistJavaClass','call getJavaClassId(?)',0,0),
 ('test','HelloWorld','call HelloWorld()',1,0),
 ('test','HelloWorld2','call HelloWorld2(?)',0,0),
 ('test','TestDelete','call TestDelete(?)',0,0),
 ('test','TestInsert','call TestInsert(?,?,?,?,?)',0,0),
 ('test','TestInsertGK','call TestInsertGK(?,?,?,?,?)',0,0),
 ('test','TestSelect','call TestSelect(?)',0,0),
 ('test','TestUpdate','call TestUpdate(?,?,?,?,?)',0,0);
--
-- Create schema test
--

CREATE DATABASE IF NOT EXISTS test;
USE test;

--
-- Definition of table `test`.`test`
--

DROP TABLE IF EXISTS `test`.`test`;
CREATE TABLE  `test`.`test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `City` varchar(200) DEFAULT NULL,
  `State` char(2) DEFAULT NULL,
  `Zip` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2256 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `test`.`test`
--
--
-- Create schema persist
--

CREATE DATABASE IF NOT EXISTS persist;
USE persist;

--
-- Definition of table `persist`.`bbdjpaentity`
--

DROP TABLE IF EXISTS `persist`.`bbdjpaentity`;
CREATE TABLE  `persist`.`bbdjpaentity` (
  `objectId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `className` text NOT NULL,
  PRIMARY KEY (`objectId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `persist`.`bbdjpaentity`
--
INSERT INTO `persist`.`bbdjpaentity` (`objectId`,`className`) VALUES 
 (3,'myApp.myDataModel.HelloBean');

--
-- Definition of table `persist`.`bbdjpafield`
--

DROP TABLE IF EXISTS `persist`.`bbdjpafield`;
CREATE TABLE  `persist`.`bbdjpafield` (
  `fieldId` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `bbdjpaobjectId` int(10) unsigned NOT NULL,
  `javaclassId` int(10) unsigned NOT NULL,
  `serializedvalue` text NOT NULL,
  PRIMARY KEY (`fieldId`),
  KEY `FK_bbdjpafield_1` (`bbdjpaobjectId`),
  KEY `FK_bbdjpafield_2` (`javaclassId`),
  CONSTRAINT `FK_bbdjpafield_1` FOREIGN KEY (`bbdjpaobjectId`) REFERENCES `bbdjpaentity` (`objectId`),
  CONSTRAINT `FK_bbdjpafield_2` FOREIGN KEY (`javaclassId`) REFERENCES `bbdjpajavaclass` (`javaclassId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `persist`.`bbdjpafield`
--
INSERT INTO `persist`.`bbdjpafield` (`fieldId`,`bbdjpaobjectId`,`javaclassId`,`serializedvalue`) VALUES 
 (5,3,5,'test');

--
-- Definition of table `persist`.`bbdjpajavaclass`
--

DROP TABLE IF EXISTS `persist`.`bbdjpajavaclass`;
CREATE TABLE  `persist`.`bbdjpajavaclass` (
  `javaClassId` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'Java classes fully qualied name',
  `javaClassName` text NOT NULL,
  PRIMARY KEY (`javaClassId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `persist`.`bbdjpajavaclass`
--
INSERT INTO `persist`.`bbdjpajavaclass` (`javaClassId`,`javaClassName`) VALUES 
 (4,'java.lang.Integer'),
 (5,'java.lang.String');
--
-- Create schema bbd
--

CREATE DATABASE IF NOT EXISTS bbd;
USE bbd;

--
-- Definition of procedure `bbd`.`APIDelete`
--

DROP PROCEDURE IF EXISTS `bbd`.`APIDelete`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`APIDelete`(idb varchar(255), isp varchar(255))
Begin
	delete from storedProcedure
    where idb = bbddatabase and isp = storedprocedure;
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`APIInsert`
--

DROP PROCEDURE IF EXISTS `bbd`.`APIInsert`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`APIInsert`(idb varchar(255), isp varchar(255), isql varchar(1024), idep int, itest int)
Begin
	insert into storedProcedure value(idb, isp, isql, idep, itest);
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`APIUpdate`
--

DROP PROCEDURE IF EXISTS `bbd`.`APIUpdate`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`APIUpdate`(idb varchar(255), isp varchar(255), isql varchar(1024), idep int, itest int)
Begin
	update storedprocedure
    set
       bbddatabase = idb,
       storedprocedure = isp,
       bbdsql = isql,
       deprecated = idep,
       testonly = itest
     where idb = bbddatabase and isp = storedprocedure;
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`APIUTInsert`
--

DROP PROCEDURE IF EXISTS `bbd`.`APIUTInsert`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`APIUTInsert`(iTestName varchar(255), iStarttime datetime, iRunMS int, iRowsSelected int, iRowsDeleted int, iRowsUpdated int, iRowsInserted int)
Begin
	insert into APIUnitTest value(iTestName, iStartTime, iRunMS, iRowsSelected, iRowsDeleted, iRowsUpdated, iRowsInserted);
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getAPI`
--

DROP PROCEDURE IF EXISTS `bbd`.`getAPI`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`getAPI`(in db varchar(255), in sp varchar(255))
begin
  select bbdsql, deprecated, testonly from storedprocedure
     where bbddatabase = db and storedprocedure = sp;
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getAPIs`
--

DROP PROCEDURE IF EXISTS `bbd`.`getAPIs`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`getAPIs`()
BEGIN
  SELECT * from storedprocedure order by BBDDatabase, StoredProcedure;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getDB`
--

DROP PROCEDURE IF EXISTS `bbd`.`getDB`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`getDB`()
BEGIN
show databases;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getEntityFieldsForBean`
--

DROP PROCEDURE IF EXISTS `bbd`.`getEntityFieldsForBean`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `bbd`.`getEntityFieldsForBean`(in beanId int)
BEGIN

  select * from bbdjpafield where bbdjpaobjectId = beanId;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getTableNames`
--

DROP PROCEDURE IF EXISTS `bbd`.`getTableNames`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`getTableNames`(in db varchar(255))
BEGIN

  set @a = concat('show tables from ', db);
  PREPARE stmt1 FROM @a;
  EXECUTE stmt1;
  DEALLOCATE PREPARE stmt1;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `bbd`.`getUTs`
--

DROP PROCEDURE IF EXISTS `bbd`.`getUTs`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `bbd`.`getUTs`()
BEGIN
  SELECT * from apiunittest;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;
--
-- Create schema test
--

CREATE DATABASE IF NOT EXISTS test;
USE test;

--
-- Definition of procedure `test`.`HelloWorld`
--

DROP PROCEDURE IF EXISTS `test`.`HelloWorld`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`HelloWorld`()
BEGIN
  select 'Hello World!' as 'Hello';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`HelloWorld2`
--

DROP PROCEDURE IF EXISTS `test`.`HelloWorld2`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`HelloWorld2`(in name1 char(20))
BEGIN
  select CONCAT('Hello, ', name1,'!') as 'Hello';
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`testDelete`
--

DROP PROCEDURE IF EXISTS `test`.`testDelete`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`testDelete`( in iname varchar(200))
BEGIN
    delete from test 
       where name = iname;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`testInsert`
--

DROP PROCEDURE IF EXISTS `test`.`testInsert`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`testInsert`(
   iname varchar(200),
   iaddress  varchar(200),
   icity varchar(200),
   istate varchar(200),
   izip int)
BEGIN
    insert test (name, address, city, state, zip) values(iname, iaddress, icity, istate, izip);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`testInsertGK`
--

DROP PROCEDURE IF EXISTS `test`.`testInsertGK`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`testInsertGK`(
   iname varchar(200),
   iaddress  varchar(200),
   icity varchar(200),
   istate varchar(200),
   izip int)
BEGIN
    Declare rows INTEGER;

    insert test (name, address, city, state, zip) values(iname, iaddress, icity, istate, izip);

    SET rows = row_count();
    if rows = 1 then
            select 1, LAST_INSERT_ID();
    else
            select 0, -1;
    end if;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`testSelect`
--

DROP PROCEDURE IF EXISTS `test`.`testSelect`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`testSelect`(inname varchar(255))
begin
  select Name, Address, City, State, Zip from test where Name = inname;
end $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `test`.`testUpdate`
--

DROP PROCEDURE IF EXISTS `test`.`testUpdate`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `test`.`testUpdate`(
   iname varchar(200), 
   iaddress  varchar(200), 
   icity varchar(200), 
   istate varchar(200), 
   izip int)
BEGIN
    update test
       SET name = iname, 
         address=iaddress, 
         city = icity, 
         state = istate, 
         zip = izip
    where name = iname;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;
--
-- Create schema persist
--

CREATE DATABASE IF NOT EXISTS persist;
USE persist;

--
-- Definition of procedure `persist`.`getBbdJpaEntities`
--

DROP PROCEDURE IF EXISTS `persist`.`getBbdJpaEntities`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `persist`.`getBbdJpaEntities`()
BEGIN

  select * from bbdjpaentity;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getBbdJpaEntitiesFields`
--

DROP PROCEDURE IF EXISTS `persist`.`getBbdJpaEntitiesFields`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `persist`.`getBbdJpaEntitiesFields`()
BEGIN

  select objectId, className,serializedValue, javaClassName
     from bbdjpaentity bbde, bbdjpafield bbdf, bbdjpajavaclass bbdj
            where bbde.objectId = bbdf.bbdjpaobjectId and
                  bbdf.javaclassId = bbdj.javaClassId;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getBbdJpaEntityField`
--

DROP PROCEDURE IF EXISTS `persist`.`getBbdJpaEntityField`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `persist`.`getBbdJpaEntityField`(
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


END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getBbdJpaEntityId`
--

DROP PROCEDURE IF EXISTS `persist`.`getBbdJpaEntityId`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `persist`.`getBbdJpaEntityId`(in objectClassName text)
BEGIN
  DECLARE myId INTEGER;
  SELECT objectId as 'objectId' into myId from persist.bbdjpaentity where className = objectClassName;

  if isnull(myId) THEN
     INSERT INTO persist.bbdjpaentity (className) values(objectClassName);
     SELECT LAST_INSERT_ID() as 'objectId';
   ELSE
     select myId as 'objectId';
   END IF;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getEntities`
--

DROP PROCEDURE IF EXISTS `persist`.`getEntities`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `persist`.`getEntities`()
BEGIN

  select * from bbdjpaentity;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getEntityFieldsForBean`
--

DROP PROCEDURE IF EXISTS `persist`.`getEntityFieldsForBean`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `persist`.`getEntityFieldsForBean`(in entityId long)
BEGIN

  select fieldId, bbdjpaobjectId as 'entityId', javaclassId as 'classId',
            serializedvalue
     from bbdjpafield
            where bbdjpaobjectId = entityId
            order by bbdjpaobjectId, fieldId;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getJavaClass`
--

DROP PROCEDURE IF EXISTS `persist`.`getJavaClass`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `persist`.`getJavaClass`()
BEGIN

  select * from bbdjpajavaclass order by javaClassName;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`getJavaClassId`
--

DROP PROCEDURE IF EXISTS `persist`.`getJavaClassId`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `persist`.`getJavaClassId`(in jcn text)
BEGIN
  DECLARE myId INTEGER;
  SELECT javaClassId as 'javaClassId' into myId from persist.bbdjpajavaclass where javaClassName = jcn;

  if isnull(myId) THEN
     INSERT INTO persist.bbdjpajavaclass (javaclassName) values(jcn);
     SELECT LAST_INSERT_ID() as 'javaClassId';
   ELSE
     select myId as 'javaClassId';
   END IF;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`persistBean`
--

DROP PROCEDURE IF EXISTS `persist`.`persistBean`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE  `persist`.`persistBean`()
BEGIN

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `persist`.`putBbdJpaEntityField`
--

DROP PROCEDURE IF EXISTS `persist`.`putBbdJpaEntityField`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`bbd`@`localhost` PROCEDURE  `persist`.`putBbdJpaEntityField`(
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

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
