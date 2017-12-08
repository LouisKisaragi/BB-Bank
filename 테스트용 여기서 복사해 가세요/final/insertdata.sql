--데이터 베이스 명령
select * from member;

DROP TABLE pointlog CASCADE CONSTRAINT;
DROP TABLE member CASCADE CONSTRAINT;

-- 관리자 계정
insert into member values(0, 'admin1', 'admin1', 
'관리자', 'admin', 'admin@admin', default, default, default, 0);

-- 유저계정
insert into member values(1, 'user1', 'user1', 
'lee', 'lee', 'lee@test.user', default, default, default, default);