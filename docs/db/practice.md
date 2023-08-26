# Practice

kobis 영화 데이터를 EC2 mariaDB에 dump.
실제 케이스를 바탕으로 여러가지 데이터를 가져오는 방법 연습.

<br />

#### 영화 정보 중 영문 제목이 없는 데이터 조회
	NULL 함수, TRIM 함수, LEN 함수

데이터에 NULL 또는 빈 값이 들어가 있을 수도 있지만, 공백이 들어가 있을 가능성을 고려할 필요가 있음.
그래서 `length() = 0` 또는 `where eng_title is null`로 단순 체크 하는 것이 아니라, 다른 가능성까지 고려하여 `trim()` 처리

```sql
select count(*)
from movie
where length(trim(eng_title)) = 0;

-- or --

select count(*)
from movie
where eng_title is null or trim(eng_title) = '';
```

<br />

#### 한국 영화 중 2001년도에 개봉한 영화 중 액션 영화를 조회
	LIKE 조건

데이터가 '액션' 이라고 잘 되어 있으면 좋겠지만 '액션(1)', '액션(2)'와 같이 정형화 되어 있지 않는 경우가 꽤 있다. varchar 데이터인 경우 그럴 수 있음. 이런 경우 역시 고려해서 포함되어 있는 식으로 찾아야 함.

```sql
select *
from movie
where
    country = '한국' and
    pub_year = 2001 and
    genre like '%액션%';

-- 액션으로 시작 : '액션%' --
-- 액션으로 끝남 : '%액션' --
```

<br />

#### 싸이더스가 2020년도에 개봉한 영화 감독의 출생년도를 조회
	IN 조건

영화 감독의 정보는 actor 테이블에 있고, 싸이더스가 2020년에 개봉한 영화 정보는 movie 테이블에서 알 수 있다. 

```sql
-- 정보 1: 싸이더스가 2020년에 개봉한 영화의 감독 이름 --
select director
from movie
where
    production like '%싸이더스%' and
    pub_year = 2020;

-- 정보 2: 해당 감독의 출생년도 --
select birth
from actor
where
    domain = '감독' and
    name = '윤성현';

-- name in () 으로 쿼리 결합 --
select birth
from actor
where
    domain = '감독' and
    name in (
        select director
        from movie
        where
            production like '%싸이더스%' and
            pub_year = 2020
        );
```

<br />

#### 배우 정보에서 직업을 중복 없이 조회
	DISTINCT, NULL 체크

`<>` 대신 `!=` 써도 된다. 대부분의 DBMS에서 문제 없으나 어떤 DBMS는 ANSI(데이터베이스 표준 지침)만 지원하기도 함.

```sql
from actor
where
    domain is not null and
    trim(domain) <> '';
```
<br />

#### 영화 감독의 국가가 독일이고, 2020년 이후에 개봉된 영화의 제목, 감독, 개봉일자, 장르를 최근 개봉일자 순으로 조회
	JOIN, COLUMN ALIAS, TABLE ALIAS, 정렬

영화 감독의 정보(국가, 이름)는 actor 테이블에서 구할 수 있음.
movie 테이블과 actor 테이블 join 필요.

```sql
select title, director, pub_year, genre
from movie as m
    join actor as a on (
        a.name = m.director and
        a.domain = '감독' and
        a.country = '독일'
    )
where m.pub_year > 2020
order by m.pub_year;

-- 이렇게 해도 되는데? 왜 이렇게는 안 할까? --
select *
from movie as m
         join actor as a on (
    a.name = m.director
    )
where
    m.pub_year > 2020 and
    a.country = '독일'and
    a.domain = '감독'
order by m.pub_year
;
```

<br />

