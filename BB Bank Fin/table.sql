﻿--계정 생성 및 접근권한 부여
--user id bbsystem / user password bb12345

-- 계정 생성
--create user bbsystem identified by bb12345;
 
-- bbsystem 계정 오라클 접근 권한 부여
-- grant connect, dba, resource to bbsystem;
-- bbsystem 계정은 dba(데이터베이스 관리자)의 권한을 가져야 합니다. 
-- 이 계정은 테이블 생성, 수정, 삭제 모든 권한을 가지고 있으니 관리에 주의를 바랍니다.

--회원의 정보를 담을 회원 테이블
create table member(
	id varchar2(12) not null,
	pass varchar2(12) not null,
	name varchar2(24) not null,
    nickname varchar2(50) not null,
	email varchar2(40),
	point number(6) default 0,
	joindate date default sysdate,
	admin number(1) default 0,
	super_m number(1) default 1,
    -- 유저와 관리자를 구분하는 키, 가입하면 유저는 1만 받는다.
	primary key(id)
);

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

--게시판 테이블
create table board(
num number(38),
member_id varchar2(12) not null, 
title varchar2(50),
contents varchar2(1000),
image varchar2(300),
primary key(num),
foreign key(member_id) references member(id)
);

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
“MEM” NUMBER(1,0) DEFAULT 0 NOT NULL,
constraint "BOARDCOMMENT_PK" primary key ("NUM"));