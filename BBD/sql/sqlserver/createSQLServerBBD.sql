/****** Object:  Database bbd    Script Date: 6/26/2007 3:48:14 AM ******/
IF EXISTS (SELECT name FROM master.dbo.sysdatabases WHERE name = N'bbd')
	DROP DATABASE [bbd]
GO

CREATE DATABASE [bbd]  ON (NAME = N'bbd_Data', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL$SQLS2000\data\bbd_Data.MDF' , SIZE = 1, FILEGROWTH = 10%) LOG ON (NAME = N'bbd_Log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL$SQLS2000\data\bbd_Log.LDF' , SIZE = 1, FILEGROWTH = 10%)
 COLLATE SQL_Latin1_General_CP1_CI_AS
GO

exec sp_dboption N'bbd', N'autoclose', N'false'
GO

exec sp_dboption N'bbd', N'bulkcopy', N'false'
GO

exec sp_dboption N'bbd', N'trunc. log', N'false'
GO

exec sp_dboption N'bbd', N'torn page detection', N'true'
GO

exec sp_dboption N'bbd', N'read only', N'false'
GO

exec sp_dboption N'bbd', N'dbo use', N'false'
GO

exec sp_dboption N'bbd', N'single', N'false'
GO

exec sp_dboption N'bbd', N'autoshrink', N'false'
GO

exec sp_dboption N'bbd', N'ANSI null default', N'false'
GO

exec sp_dboption N'bbd', N'recursive triggers', N'false'
GO

exec sp_dboption N'bbd', N'ANSI nulls', N'false'
GO

exec sp_dboption N'bbd', N'concat null yields null', N'false'
GO

exec sp_dboption N'bbd', N'cursor close on commit', N'false'
GO

exec sp_dboption N'bbd', N'default to local cursor', N'false'
GO

exec sp_dboption N'bbd', N'quoted identifier', N'false'
GO

exec sp_dboption N'bbd', N'ANSI warnings', N'false'
GO

exec sp_dboption N'bbd', N'auto create statistics', N'true'
GO

exec sp_dboption N'bbd', N'auto update statistics', N'true'
GO

if( ( (@@microsoftversion / power(2, 24) = 8) and (@@microsoftversion & 0xffff >= 724) ) or ( (@@microsoftversion / power(2, 24) = 7) and (@@microsoftversion & 0xffff >= 1082) ) )
	exec sp_dboption N'bbd', N'db chaining', N'false'
GO

use [bbd]
GO

/****** Object:  Stored Procedure dbo.APIDelete    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[APIDelete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[APIDelete]
GO

/****** Object:  Stored Procedure dbo.APIInsert    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[APIInsert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[APIInsert]
GO

/****** Object:  Stored Procedure dbo.APIUTInsert    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[APIUTInsert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[APIUTInsert]
GO

/****** Object:  Stored Procedure dbo.APIUpdate    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[APIUpdate]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[APIUpdate]
GO

/****** Object:  Stored Procedure dbo.getAPI    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[getAPI]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[getAPI]
GO

/****** Object:  Stored Procedure dbo.getAPIs    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[getAPIs]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[getAPIs]
GO

/****** Object:  Stored Procedure dbo.getDB    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[getDB]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[getDB]
GO

/****** Object:  Stored Procedure dbo.getTableNames    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[getTableNames]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[getTableNames]
GO

/****** Object:  Stored Procedure dbo.getUTs    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[getUTs]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[getUTs]
GO

/****** Object:  Stored Procedure dbo.prImpTestIns    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[prImpTestIns]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[prImpTestIns]
GO

/****** Object:  Table [dbo].[APIUnitTest]    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[APIUnitTest]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[APIUnitTest]
GO

/****** Object:  Table [dbo].[StoredProcedure]    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[StoredProcedure]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[StoredProcedure]
GO

/****** Object:  Table [dbo].[tblTest]    Script Date: 6/26/2007 3:48:15 AM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[tblTest]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[tblTest]
GO

/****** Object:  Login bbd    Script Date: 6/26/2007 3:48:15 AM ******/
if not exists (select * from master.dbo.syslogins where loginname = N'bbd')
BEGIN
	declare @logindb nvarchar(132), @loginlang nvarchar(132) select @logindb = N'bbd', @loginlang = N'us_english'
	if @logindb is null or not exists (select * from master.dbo.sysdatabases where name = @logindb)
		select @logindb = N'master'
	if @loginlang is null or (not exists (select * from master.dbo.syslanguages where name = @loginlang) and @loginlang <> N'us_english')
		select @loginlang = @@language
	exec sp_addlogin N'bbd', null, @logindb, @loginlang
END
GO

/****** Object:  Login test    Script Date: 6/26/2007 3:48:15 AM ******/
if not exists (select * from master.dbo.syslogins where loginname = N'test')
BEGIN
	declare @logindb nvarchar(132), @loginlang nvarchar(132) select @logindb = N'master', @loginlang = N'us_english'
	if @logindb is null or not exists (select * from master.dbo.sysdatabases where name = @logindb)
		select @logindb = N'master'
	if @loginlang is null or (not exists (select * from master.dbo.syslanguages where name = @loginlang) and @loginlang <> N'us_english')
		select @loginlang = @@language
	exec sp_addlogin N'test', null, @logindb, @loginlang
END
GO

/****** Object:  User bbd    Script Date: 6/26/2007 3:48:15 AM ******/
if not exists (select * from dbo.sysusers where name = N'bbd' and uid < 16382)
	EXEC sp_grantdbaccess N'bbd', N'bbd'
GO

/****** Object:  User test    Script Date: 6/26/2007 3:48:15 AM ******/
if not exists (select * from dbo.sysusers where name = N'test' and uid < 16382)
	EXEC sp_grantdbaccess N'test', N'test'
GO

/****** Object:  Table [dbo].[APIUnitTest]    Script Date: 6/26/2007 3:48:16 AM ******/
CREATE TABLE [dbo].[APIUnitTest] (
	[testName] [varchar] (1000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[starttime] [datetime] NOT NULL ,
	[runMS] [int] NOT NULL ,
	[rowsSelected] [int] NOT NULL ,
	[rowsDeleted] [int] NOT NULL ,
	[rowsUpdated] [int] NOT NULL ,
	[rowsInserted] [int] NOT NULL 
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[StoredProcedure]    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE TABLE [dbo].[StoredProcedure] (
	[BBDDatabase] [char] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[StoredProcedure] [char] (255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[BBDSQL] [varchar] (1000) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL ,
	[Deprecated] [tinyint] NOT NULL ,
	[TestOnly] [tinyint] NOT NULL 
) ON [PRIMARY]
GO

/****** Object:  Table [dbo].[tblTest]    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE TABLE [dbo].[tblTest] (
	[testvchar] [varchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[testint] [int] NULL 
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[APIUnitTest] ADD 
	CONSTRAINT [DF__APIUnitTe__testN__398D8EEE] DEFAULT ('') FOR [testName],
	CONSTRAINT [DF__APIUnitTe__runMS__3A81B327] DEFAULT ('0') FOR [runMS],
	CONSTRAINT [DF__APIUnitTe__rowsS__3B75D760] DEFAULT ('0') FOR [rowsSelected],
	CONSTRAINT [DF__APIUnitTe__rowsD__3C69FB99] DEFAULT ('0') FOR [rowsDeleted],
	CONSTRAINT [DF__APIUnitTe__rowsU__3D5E1FD2] DEFAULT ('0') FOR [rowsUpdated],
	CONSTRAINT [DF__APIUnitTe__rowsI__3E52440B] DEFAULT ('0') FOR [rowsInserted]
GO

ALTER TABLE [dbo].[StoredProcedure] ADD 
	CONSTRAINT [DF__StoredPro__BBDDa__412EB0B6] DEFAULT ('') FOR [BBDDatabase],
	CONSTRAINT [DF__StoredPro__Store__4222D4EF] DEFAULT ('') FOR [StoredProcedure],
	CONSTRAINT [DF__StoredPro__BBDSQ__4316F928] DEFAULT ('') FOR [BBDSQL],
	CONSTRAINT [DF__StoredPro__Depre__440B1D61] DEFAULT ('0') FOR [Deprecated],
	CONSTRAINT [DF__StoredPro__TestO__44FF419A] DEFAULT ('0') FOR [TestOnly],
	 PRIMARY KEY  CLUSTERED 
	(
		[BBDDatabase],
		[StoredProcedure]
	)  ON [PRIMARY] 
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.APIDelete    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE PROCEDURE [dbo].[APIDelete] 
  @idb varchar(255), @isp varchar(255)
AS
begin
 delete from storedProcedure
    where @idb = bbddatabase and @isp = storedprocedure;
end 


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.APIInsert    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE PROCEDURE [dbo].[APIInsert] 
   @idb varchar(255), @isp varchar(255), @isql varchar(1024), @idep int, @itest int
AS
begin
  insert into storedProcedure values(@idb, @isp, @isql, @idep, @itest);
end 


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.APIUTInsert    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE PROCEDURE [dbo].[APIUTInsert] 
   @iTestName varchar(255), @iStarttime datetime, @iRunMS int, @iRowsSelected int, @iRowsDeleted int, @iRowsUpdated int, @iRowsInserted int
AS
begin
  insert into APIUnitTest values(@iTestName, @iStartTime, @iRunMS, @iRowsSelected, @iRowsDeleted, @iRowsUpdated, @iRowsInserted);
end 


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[APIUTInsert]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[APIUTInsert]  TO [test]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.APIUpdate    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE PROCEDURE [dbo].[APIUpdate] 
   @idb varchar(255), @isp varchar(255), @isql varchar(1024), @idep int, @itest int
AS
begin
 update storedprocedure
    set
       bbddatabase = @idb,
       storedprocedure = @isp,
       bbdsql = @isql,
       deprecated = @idep,
       testonly = @itest
     where @idb = bbddatabase and @isp = storedprocedure;
end 


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.getAPI    Script Date: 6/26/2007 3:48:17 AM ******/
CREATE PROCEDURE [dbo].[getAPI] 
   @db varchar(255), @sp varchar(255)
AS
begin
  select bbdsql, deprecated, testonly from storedprocedure
     where bbddatabase =@db and storedprocedure = @sp;
end;
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[getAPI]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[getAPI]  TO [test]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.getAPIs    Script Date: 6/26/2007 3:48:18 AM ******/
CREATE PROCEDURE [dbo].[getAPIs] AS
BEGIN
  SELECT * from storedprocedure;
END;
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[getAPIs]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[getAPIs]  TO [test]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.getDB    Script Date: 6/26/2007 3:48:18 AM ******/
CREATE PROCEDURE dbo.getDB 
as
BEGIN
  select name from master..sysdatabases;
END;
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[getDB]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[getDB]  TO [test]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.getTableNames    Script Date: 6/26/2007 3:48:18 AM ******/
CREATE PROCEDURE dbo.getTableNames 
 @db varchar(255)
as
BEGIN
  exec('use '+@db+'; select name from sysobjects where type=''U''');
END;

GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[getTableNames]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[getTableNames]  TO [test]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.getUTs    Script Date: 6/26/2007 3:48:18 AM ******/
CREATE PROCEDURE dbo.getUTs as
BEGIN
  SELECT * from apiunittest;
END;


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[getUTs]  TO [bbd]
GO

GRANT  EXECUTE  ON [dbo].[getUTs]  TO [test]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.prImpTestIns    Script Date: 6/26/2007 3:48:18 AM ******/
CREATE PROCEDURE [dbo].[prImpTestIns] 
       @testVChar varchar(50), @testint int
AS
begin

   insert into tblTest ( testvchar, testint)  values(@testVChar, @testint);

end
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

