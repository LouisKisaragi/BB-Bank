
--drop table business cascade CONSTRAINTS;
drop table businesstime cascade CONSTRAINTS;

drop table pointlog cascade CONSTRAINTS;
drop table board2 cascade CONSTRAINTS;
drop table board cascade CONSTRAINTS;
drop table reservation cascade CONSTRAINTS;
drop table designer cascade CONSTRAINTS;
drop table guest cascade CONSTRAINTS;
drop table event cascade CONSTRAINTS;
drop table style cascade CONSTRAINTS;

drop sequence numb1_seq;
drop sequence numb2_seq;

create sequence numb1_seq start with 1 increment by 1;
create sequence numb2_seq start with 1 increment by 1;

create table guest(
	id varchar2(12) not null,
	pass varchar2(12) not null,
	name varchar2(24) not null,
	address varchar2(40),
	phone varchar2(13)not null,
	point number(6) default 0,
	joindate date default sysdate,
	admin number(1) default 0,
	visiable number(1) default 1,
	primary key(id)
);

--id name address phone point joindate visiable

create table board(
num number(38),
guest_id varchar2(12) not null, 
title varchar2(50),
contents varchar2(1000),
image varchar2(300),
visiable number(1) default 1,
primary key(num),
foreign key(guest_id) references guest(id)
);

--후기 게시판의 테이블.
--visiable은 논리적 삭제를 구현하기 위한 필드로
-- 0이면 삭제된 것 처럼 게시판에 노출되지 않음.

create table board2(
num number(38),
guest_id varchar2(12) not null,
title varchar2(50),
contents varchar2(1000),
answer varchar2(1000) default null,
visiable number(1) default 1,
primary key(num),
foreign key(guest_id) references guest(id)
);

--QNA (1:1 문의 게시판)
--제목, 내용, 답글. 답글에 내용이 있을 시 
--답글 폼을 출력 시켜서 답글을 노출시킴.
--관리자 페이지에서는 답글을 다는 부분을 제작.

create table pointlog(
guest_id varchar2(12) not null,
p_calcul number(10),
p_cont varchar2(100),
p_date date default sysdate,
foreign key(guest_id) references guest(id)
);

--포인트 내역 테이블

