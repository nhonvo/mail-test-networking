use master
go
drop database if exists mailserver
create database mailserver
go
use mailserver
go
create table [User](
	id int identity(1,1) primary key,
	username varchar(30),
	[password] varchar(8),
	fullname nvarchar(100),
	host varchar(20),
	[port] int,
	datecreated datetime
)
create table mail(
	id int identity(1,1) primary key,
	title nvarchar(30),
	content nvarchar(max),
	datecreated datetime,
	SenderId int,
	ReciverId int,
	foreign key (SenderId) references [User](id),
	foreign key (ReciverId) references [User](id)
)

go
insert into [User] values ('truongnhon','123',N'Võ Thương Trường Nhơn', '192.168.1.1', 1, 20/10/2022);
insert into [User] values ('ductai','123',N'Phạm Đức Tài', '192.168.1.1', 1, 20/10/2022)
go

insert into mail values ('test',N'Đây là nội dung test', 20/10/2022, 1, 2)
go
select * from[User]
select * from Mail