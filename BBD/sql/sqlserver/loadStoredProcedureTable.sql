use bbd;

delete from StoredProcedure;

insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values ('bbd','APIDelete','exec APIDelete(?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','APIInsert','exec APIInsert(?,?,?,?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','APIUpdate','exec APIUpdate(?,?,?,?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','APIUTInsert','exec APIUTInsert(?,?,?,?,?,?,?)',0,1);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','getAPI','exec getAPI(?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','getAPIs','exec getAPIs()',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','getDB','exec getDB()',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','getTableNames','exec getTableNames(?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('bbd','getUTs','exec getUTs()',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('persist','createTable','exec createTable(?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('persist','persistBean','exec persistBean()',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('test','HelloWorld','exec HelloWorld()',1,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('test','HelloWorld2','exec HelloWorld2(?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('test','TestDelete','exec TestDelete(?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values 	('test','TestInsert','exec TestInsert(?,?,?,?,?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values ('test','TestSelect','exec TestSelect(?)',0,0);
insert into StoredProcedure
 (BBDDatabase,StoredProcedure,BBDSQL,Deprecated,TestOnly)
   values ('test','TestUpdate','exec TestUpdate(?,?,?,?,?)',0,0);