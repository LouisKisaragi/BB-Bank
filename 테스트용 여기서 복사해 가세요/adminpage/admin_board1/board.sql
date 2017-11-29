--BB BANK 야구 커뮤니티 페이지(가칭) DATABASE 설계

--회원 정보 받아오는 테이블, 이름은 user
create table "user"(
	id varchar2(12) not null,
	pass varchar2(12) not null,
	name varchar2(24) not null,
	email varchar2(40),
	point number(6) default 0,
	joindate date default sysdate,
	admin number(1) default 0,
	visiable number(1) default 1,
	primary key(id)
);

--