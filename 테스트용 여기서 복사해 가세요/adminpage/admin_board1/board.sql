--BB BANK �߱� Ŀ�´�Ƽ ������(��Ī) DATABASE ����

--ȸ�� ���� �޾ƿ��� ���̺�, �̸��� user
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