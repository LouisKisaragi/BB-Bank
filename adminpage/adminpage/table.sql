create table member(
    id varchar2(12) not null,
	pass varchar2(12) not null,
	nickname varchar2(24) not null,
	email varchar2(40),
	point number(6) default 0,
	joindate date default sysdate,
	admin number(1) default 0,
	visiable number(1) default 1,
	primary key(id)
);

CREATE table "BOARD"(
	"NUM"	NUMBER(7,0) NOT NULL,
	"WRITER"	VARCHAR2(12) NOT NULL,
	"SUBJECT" VARCHAR2(500) NOT NULL,
	"PASS"	VARCHAR2(10) NOT NULL,
	"READCOUNT" NUMBER(5,0) DEFAULT 0 NOT NULL,
	"REF"	NUMBER(3,0) DEFAULT 0 NOT NULL,
	"STEP" 	NUMBER(3,0) DEFAULT 0 NOT NULL,
	"DEPTH"	NUMBER(3,0) DEFAULT 0 NOT NULL,
	"REGDATE" TIMESTAMP DEFAULT SYSDATE NOT NULL,
	"CONTENT" VARCHAR2(4000) NOT NULL,
	"IP" VARCHAR2(20) NOT NULL,
	"BN" NUMBER(2,0) NOT NULL,
    "ORIGIN_FILENAME" VARCHAR(256),
	"SERVER_FILENAME" VARCHAR2(256),
	"FILESIZE" NUMBER(7,0),
	"FILETYPE" VARCHAR2(256),
	"PREFACE" VARCHAR2(50),

	constraint "BOARD_PK" primary key ("NUM")
);

CREATE table "BOARDCOMMENT"(
"NUM" NUMBER(7,0) NOT NULL,
"WRITER" VARCHAR2(12) NOT NULL,
"PASS" VARCHAR2(10) NOT NULL,
"REF" NUMBER(3,0) DEFAULT 0 NOT NULL,
"STEP" NUMBER(3,0) DEFAULT 0 NOT NULL,
"DEPTH" NUMBER(3,0) DEFAULT 0 NOT NULL,
"REGDATE" TIMESTAMP DEFAULT SYSDATE NOT NULL,
"CONTENT" VARCHAR2(4000) NOT NULL,
"IP" VARCHAR2(20) NOT NULL,
"BN" NUMBER(7,0) NOT NULL,
constraint "BOARDCOMMENT_PK" primary key ("NUM"));

--foreign key(member_id) references member(id)
--로그인 연결이 안될 경우 추가 바람.

create table pointlog(
member_id varchar2(12) not null,
p_calcul number(10),
p_cont varchar2(100),
p_date date default sysdate,
foreign key(member_id) references member(id)
);