--회원정보 테스트 테이블
create table persontest (
	num			 number	 primary key,	--일련번호
	name		 varchar2(20)  not null,	--이름
	age			 number	 default 0,		--나이
	phone		 varchar2(20),			--전화번호
	address		 varchar2(100)			--주소
);

--입력 예
insert into persontest values (1, '홍길동', 20, '010-111-2222', '서울시 강남구 삼성동 11');
