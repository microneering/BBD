/****** Object:  Stored Procedure dbo.HelloWorld    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[HelloWorld]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[HelloWorld]
GO

/****** Object:  Stored Procedure dbo.HelloWorld2    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[HelloWorld2]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[HelloWorld2]
GO

/****** Object:  Stored Procedure dbo.testDelete    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[testDelete]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[testDelete]
GO

/****** Object:  Stored Procedure dbo.testInsert    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[testInsert]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[testInsert]
GO

/****** Object:  Stored Procedure dbo.testSelect    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[testSelect]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[testSelect]
GO

/****** Object:  Stored Procedure dbo.testUpdate    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[testUpdate]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[testUpdate]
GO

/****** Object:  Table [dbo].[test]    Script Date: 5/29/2007 11:13:20 PM ******/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[test]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[test]
GO

/****** Object:  User dbo    Script Date: 5/29/2007 11:13:20 PM ******/
/****** Object:  User Duke    Script Date: 5/29/2007 11:13:20 PM ******/
if not exists (select * from dbo.sysusers where name = N'Duke' and uid < 16382)
	EXEC sp_grantdbaccess N'Duke', N'Duke'
GO

/****** Object:  User test    Script Date: 5/29/2007 11:13:20 PM ******/
if not exists (select * from dbo.sysusers where name = N'test' and uid < 16382)
	EXEC sp_grantdbaccess N'test', N'test'
GO

/****** Object:  Table [dbo].[test]    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE TABLE [dbo].[test] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[Name] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[Address] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[City] [varchar] (200) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[State] [char] (2) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,
	[Zip] [int] NULL 
) ON [PRIMARY]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.HelloWorld    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[HelloWorld] 
AS
begin
 select 'Hello World!' as 'Hello';
end 


GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[HelloWorld]  TO [Duke]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS OFF 
GO

/****** Object:  Stored Procedure dbo.HelloWorld2    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[HelloWorld2] 
   @name1 char(20)
AS
begin
  select 'Hello, '+ ltrim(rtrim(@name1))+'!' as 'Hello';
end
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[HelloWorld2]  TO [Duke]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.testDelete    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[testDelete]( @iname varchar(200) )
as
BEGIN
    delete from test 
       where name = @iname;
END ;

GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[testDelete]  TO [test]
GO

GRANT  EXECUTE  ON [dbo].[testDelete]  TO [Duke]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.testInsert    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[testInsert](
   @iname varchar(200), 
   @iaddress  varchar(200), 
   @icity varchar(200), 
   @istate varchar(200), 
   @izip int)
as
BEGIN
    insert test (name, address, city, state, zip) values(@iname, @iaddress, @icity, @istate, @izip);
END ;

GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[testInsert]  TO [test]
GO

GRANT  EXECUTE  ON [dbo].[testInsert]  TO [Duke]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.testSelect    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[testSelect](@inname varchar(255))
as
begin
  select Name, Address, City, State, Zip from test where Name = @inname;
end ;

GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[testSelect]  TO [test]
GO

GRANT  EXECUTE  ON [dbo].[testSelect]  TO [Duke]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

/****** Object:  Stored Procedure dbo.testUpdate    Script Date: 5/29/2007 11:13:21 PM ******/
CREATE PROCEDURE [dbo].[testUpdate](
   @iname varchar(200), 
   @iaddress  varchar(200), 
   @icity varchar(200), 
   @istate varchar(200), 
   @izip int)
as
BEGIN
    update test
       SET name = @iname, 
         address=@iaddress, 
         city = @icity, 
         state = @istate, 
         zip = @izip
    where name = @iname;
END ;

GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

GRANT  EXECUTE  ON [dbo].[testUpdate]  TO [test]
GO

GRANT  EXECUTE  ON [dbo].[testUpdate]  TO [Duke]
GO

