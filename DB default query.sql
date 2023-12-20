drop database tiketing;

create database Tiketing character set utf8mb4 collate utf8mb4_unicode_ci;

use Tiketing;

create table USERINFO(
ID varchar(100) primary key,
userpw varchar (100),
username varchar (20),
phone varchar(13),
birth date,
ninkname varchar(100),
userpoint numeric(10)
);

create table MOVIE (
	moviecode varchar(30) primary key,
	moviename varchar(100),
	thema varchar(30),
	runtime	numeric(3),
	agegroup numeric(2),
	salesrate numeric(5, 2),
    opendate date
    );
    
create table MOVIECENTER (
	centercode varchar(100) primary key,
	centername varchar(100),
    address varchar (30));
    
create table THEATER (
	theatercode varchar(10) primary key,
	theatername varchar(100),
	centercode varchar(100) ,
	seatprice numeric(10) ,
	cleantime numeric(3),
    totalseats varchar(3),
    foreign key(centercode) references MOVIECENTER(centercode)
    );
    
    create table SCREEN (
	screencode varchar(10) primary key,
	moviecode varchar(30),
	theatercode varchar(10),
	starttime datetime,
	endtime datetime,
	soldseats numeric(3),
    foreign key(moviecode) references MOVIE(moviecode),
    foreign key(theatercode) references THEATER(theatercode));
    
    create table RESERVATION (
	reservenumber varchar(12) primary key,
	selectseat varchar(100),
    moviecode varchar(30),
    screencode varchar(10),
    theatercode varchar(10),
    birth date,
    phone varchar(13),
    pw varchar(100),
    usercheck varchar(1),
	foreign key(moviecode) references MOVIE(moviecode),
    foreign key(screencode) references SCREEN(screencode),
    foreign key(theatercode) references THEATER(theatercode)
	);

INSERT INTO USERINFO (ID, userpw, username, phone, birth, ninkname, userpoint) VALUES ('kimtaeil','1234','김태일','010-8251-6374','2000-05-01','태일','999999999');
INSERT INTO USERINFO (ID, userpw, username, phone, birth, ninkname, userpoint) VALUES ('kimkkorry','1234','김꼬리','010-0000-0000','1900-05-21','꼬리','0');
INSERT INTO MOVIE (moviecode, moviename, thema, runtime, agegroup, salesrate,opendate) VALUES ('M001','해리포터와 마법사의돌','가족/판타지','170','12','0','2023-12-19');
INSERT INTO MOVIE (moviecode, moviename, thema, runtime, agegroup, salesrate,opendate) VALUES ('M002','어벤져스:엔드게임','액션','200','15','0','2023-12-19');
INSERT INTO MOVIECENTER (centercode, centername, address) VALUES ('C001','TIK수원역','수원시 수원역');
INSERT INTO MOVIECENTER (centercode, centername, address) VALUES ('C002','TIK매교','수원시 태일이집');
INSERT INTO THEATER (theatercode, theatername, centercode, seatprice, cleantime, totalseats) VALUES ('T001','1관','C001','14000','20','I09');
INSERT INTO THEATER (theatercode, theatername, centercode, seatprice, cleantime, totalseats) VALUES ('T002','IMAX관','C002','30000','30','G10');
INSERT INTO SCREEN (screencode, moviecode, theatercode, starttime, endtime, soldseats) VALUES ('S001','M002','T002','2023-12-20 18:00','2023-12-20 21:20','0');
INSERT INTO SCREEN (screencode, moviecode, theatercode, starttime, endtime, soldseats) VALUES ('S002','M001','T001','2023-12-21 17:00','2023-12-21 19:50','0');
INSERT INTO RESERVATION (reservenumber, selectseat, moviecode, screencode, theatercode, birth, phone, pw, usercheck) VALUES ('1234-5678-91','E01;E02','M001','S002','T001','2000-05-01','010-8251-6374','1234','O');
INSERT INTO RESERVATION (reservenumber, selectseat, moviecode, screencode, theatercode, birth, phone, pw, usercheck) VALUES ('2345-6789-10','H05;H06','M002','S001','T002','1995-11-30','010-1111-2222','1234','X');