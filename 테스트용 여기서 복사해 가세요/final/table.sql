--계정 생성 및 접근권한 부여
--user id bbsystem / user password bb12345

-- 계정 생성
--create user bbsystem identified by bb12345;
 
-- bbsystem 계정 오라클 접근 권한 부여
-- grant connect, dba, resource to bbsystem;
-- bbsystem 계정은 dba(데이터베이스 관리자)의 권한을 가져야 합니다. 
-- 이 계정은 테이블 생성, 수정, 삭제 모든 권한을 가지고 있으니 관리에 주의를 바랍니다.

--회원의 정보를 담을 회원 테이블
create table member(
    pnum number(7,0) DEFAULT 0 NOT NULL,
	id varchar2(20) not null,
	pass varchar2(18) not null,
	name varchar2(12) not null,
    nickname varchar2(50) not null,
	email varchar2(200) not null,
	point number(7,0) default 100 not null,
	joindate timestamp default sysdate not null,
	--admin number(1) default 1,
	--어드민 로그인 안될 경우 넣을 것.
	logindate varchar2(20) default 0,
	super_m number(1) default 1,
    -- 유저와 관리자를 구분하는 키, 가입하면 유저는 1만 받는다.
	primary key(pnum)
	-- 만약에 기존의 설계대로 안될경우 아래의 문장으로 바꾸시오.
	--primary key(id) foreign key(pnum)
);

drop table member;

CREATE SEQUENCE MEMBER_SEQ
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;

--포인트 내역 테이블
create table pointlog(
member_id varchar2(12) not null,
p_calcul number(10),
p_cont varchar2(100),
p_date date default sysdate,
foreign key(member_id) references member(id)
);

CREATE table "BOARD"(
	"NUM" NUMBER(7,0) NOT NULL,
	"WRITER" VARCHAR2(50) NOT NULL,
	"SUBJECT" VARCHAR2(500) NOT NULL,
	"PASS" VARCHAR2(10) NOT NULL,
	"READCOUNT" NUMBER(5,0) DEFAULT 0 NOT NULL,
	"REF" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"STEP" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"DEPTH" NUMBER(3,0) DEFAULT 0 NOT NULL,
	"REGDATE" TIMESTAMP DEFAULT SYSDATE NOT NULL,
	"CONTENT" VARCHAR2(4000) NOT NULL,
	"IP" VARCHAR2(20) NOT NULL,
	"BN" NUMBER(2,0) NOT NULL,
	"ORIGIN_FILENAME" VARCHAR2(256),
	"SERVER_FILENAME" VARCHAR2(256),
	"FILESIZE" NUMBER(7,0),
	"FILETYPE" VARCHAR2(256),
	"PREFACE" VARCHAR2(50) NOT NULL,
	"MEM" NUMBER(1,0) DEFAULT 0 NOT NULL,
	constraint "BOARD_PK" primary key ("NUM")
	-- 만약에 기존의 설계대로 안될경우 아래의 문장으로 바꾸시오.
	--primary key(id) foreign key(pnum)
	
	);

CREATE SEQUENCE BOARD_SEQ
	START WITH 1
	INCREMENT BY 1
	NOMAXVALUE
	NOCACHE
	NOCYCLE;


--리플기능 구현을 위한 테이블
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
"MEM" NUMBER(1,0) DEFAULT 0 NOT NULL,
constraint "BOARDCOMMENT_PK" primary key ("NUM"));

--나중에 해제 할것 지금은 주석.
--게임테이블
--CREATE table GAME(
--id varchar2(12) not null,
--GNUM NUMBER(7,0) NOT NULL,
--TEAM1 VARCHAR2(20) NOT NULL,
--TEAM2 VARCHAR2(20) NOT NULL,
--STARTDAY VARCHAR2(500) NOT NULL,
--ENDDAY VARCHAR2(500) NOT NULL,
--PLAY VARCHAR2(20) NOT NULL,
--WINNER VARCHAR2(20) NOT NULL,
--LEAGUE VARCHAR2(20) NOT NULL,
--TEAM1VOTE NUMBER(10,0) DEFAULT 0,
--TEAM2VOTE NUMBER(10,0) DEFAULT 0,
--constraint GNUM_PK primary key (gnum));

--CREATE SEQUENCE GNUM_SEQ
--	START WITH 1
--	INCREMENT BY 1
--	NOMAXVALUE
--	NOCACHE
--	NOCYCLE;

--CREATE table VOTEPLAYER(
--VOTENUM NUMBER(7,0) NOT NULL,
--VOTENICK VARCHAR2(50) NOT NULL,
--VOTEID VARCHAR2(20) NOT NULL,
--VOTEGAMENUM NUMBER(7,0) NOT NULL,
--VOTETEAM VARCHAR2(20) NOT NULL,
--VOTECOMMENT VARCHAR2(500) NOT NULL,
--constraint VOTENUM_PK primary key (VOTENUM));

--CREATE SEQUENCE VOTENUM_SEQ
--	START WITH 1
--	INCREMENT BY 1
--	NOMAXVALUE
--	NOCACHE
--	NOCYCLE;