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

#### 시카고에서 진행하는 축제 중 영문 제목이 없는 경우 한글 제목으로 보여주고, 장르가 없는 경우 기타로 표시, 홈페이지가 없는 경우 홈페이지 없음으로 조회
	CASE 문

CASE 가 하나인 경우 IF 문으로 대체 가능

```sql
select
    case
        when ifnull(f.eng_title, '') = ''then f.title
        else f.eng_title
    end as title,
    case
        when ifnull(f.gerne, '') = '' then '기타'
        else f.gerne
    end as genre,
    case
        when ifnull(f.homepage, '') = '' then '홈페이지 없음'
        else f.homepage
    end as homepage
from festival as f
where
    country = '미국' and
    city = '시카고';
```

<br />

#### 영화 상영관 회사별로 좌석 수가 가장 많은 값을 구하고, 이를 순서대로 조회
	row_number(), max() 함수, group by, order by

`row_number() over (순서, 차순)` 으로 순서를 매길 수 있음.
biz_name으로 `group by` 처리 해서 데이터를 가져옴. 

```sql
select
    row_number() over (order by max(seat_count) desc) as ranking,
    biz_name,
    max(seat_count) as max_seat_count
from
    screen
where
    biz_name is not null and
    trim(biz_name) != ''
group by biz_name
order by max_seat_count desc
;
```
`group by`와 `distinct` 차이가 궁금했는데, 찾아보니 둘 다 중복된 데이터를 지울 수 있지만 `group by`의 경우 집계 데이터를 구할 때 사용한다. 단순히 unique한 행을 구하려면 `distinct`가 유리하지만 그 값을 가지고 어떤 집계를 하려면 `group by`를 사용해야 함.

```sql
# '2021-03-28 16:00:00' ~ '2021-03-28 16:59:59' 사이에 접근한 사원 수 
select count(distinct emp.id)
from employee emp
join access_log access on (access.emp_id = emp.id)
where access.access_dt between '2021-03-28 16:00:00' and '2021-03-28 16:59:59' 
# 결과
# 3


# 사원 별로 '2021-03-28 16:00:00' ~ '2021-03-28 16:59:59' 사이에 접근한 회수 
select emp.name, count(emp.id)
from employee emp
join access_log access on (access.emp_id = emp.id)
where access.access_dt between '2021-03-28 16:00:00' and '2021-03-28 16:59:59' 
group by emp.id

# 결과
# kim 3
# park 1
# lee 3
```

<br />

#### 국가별 영화 정보의 개수를 조회(단, 국가가 없는 경우 '국가 미상'으로 처리)
	group by, order by, trim()

그런데 그리스(1), 미국(2) 이런식으로 나오는 데이터에 대해서는 어떻게 처리할 수 있을지?

```sql
select
    if(ifnull(country, '') = '', '국가 미상', country),
    count(*) as movie_count
from movie
group by country
order by movie_count desc;
```

<br />

#### CGV 극장 중에서 스크린 수가 가장 많은 극장 순위를 5위까지 조회
	order by, row_number, limit

원래 string 검색은 코드값으로 함. 실습이므로 그냥 text로 검색

```sql
select
    row_number() over (order by screen_count desc) as ranking,
    s.*
from screen as s
where biz_name = 'CJ올리브네트웍스(주)'
order by screen_count desc
limit 5;
```

<br />

#### 전국 극장의 스크린 수의 평균과 '서울시'에 위치한 극장의 스크린 수의 평균을 구하고 둘의 차이를 조회. 최종 차이의 평균은 소수점 첫째 자리에서 반올림. 
	round() 함수, avg() 함수, sub query

서브쿼리는 쿼리의 결과가 다른 쿼리에 그대로 사용되는 것임.
`round(number, 자릿수)`로 소수점 첫째자리 까지 구함.

```sql
select
    T1.*,
    round((T1.screen_count_all_avg - T1.screen_count_seoul_avg), 1) as avg_diff
from
    (
        select
            (
                select avg(screen_count)
                from screen
            ) as screen_count_all_avg,
            (
                select avg(screen_count)
                from screen
                where sido = '서울시'
            ) as screen_count_seoul_avg
    ) as T1
```

<br />

#### 한국에서만 하는 축제 중에서 도시별로 진행하는 축제가 10개 이상인 도시를 가장 많이 진행하는 순서로 출력(단, 도시 정보가 없는 경우 제외)

`distinct`와 `group by`의 차이인데, `where`절에서는 집계 함수를 사용할 수 없다.

```sql
select
    city,
    count(*) as festival_count
from festival
where
    country = '한국' and
    city is not null and trim(city) != ''
group by city
having festival_count >= 10
order by festival_count desc;
```

<br />

#### 영화인 정보에서 직업이 배우가 아닌 사람 중에서 1980년 ~ 1990년 사이 출생자를 조회(생년월일 정보가 유효하지 않은 사람은 제외)
	in, is not null, length() 함수, trim() 함수, str_to_date() 함수, between A and B

데이터 타입이 datetime 이고, 이 데이터의 유효성을 검사할 때는 여러가지로 생각해야 함.

```sql
select *
from actor
where domain not in ('배우')
  and birth is not null
  and length(trim(birth)) = 10
  and str_to_date(birth, '%Y-%m-%d') is not null
  and year(str_to_date(birth, '%Y-%m-%d')) between 1980 and 1990
order by str_to_date(birth, '%Y-%m-%d')
;

```

<br />

#### 헝가리 출신의 영화인 중 직업이 배우인 사람의 생일을 출생년도, 월, 일자를 각각 출력하는 SQL문 작성(단, 생일이 입력되지 않은 사람은 제외)
	str_to_date, year() 함수, month() 함수, day() 함수, length() 함수, trim() 함수, is not null

```sql
select
    name,
    eng_name,
    domain,
    year(str_to_date(birth, '%Y-%m-%d')) as birth_year,
    month(str_to_date(birth, '%Y-%m-%d')) as birth_month,
    day(str_to_date(birth, '%Y-%m-%d')) as birth_day
from actor
where country = '헝가리'
  and domain = '배우'
  and birth is not null
  and length(trim(birth)) = 10
  and str_to_date(birth, '%Y-%m-%d') is not null
;
```

<br />

#### 서울시에 위치한 극장가 중에서 강남구가 아닌 극장의 좌석 수의 합을 조회하는 SQL문 작성(좌석 수는 천 단위로 콤마 표시)
	format() 함수, sum() 함수, not in

gungu로 `group by` 하고 `sum(sear_count)` 대신 `count(seat_count)` 하면 그룹핑한 gungu 별 `seat_count`의 개수가 나온다.

```sql
select
    format(sum(seat_count), 0)
from screen
where sido = '서울시'
  and gugun not in ('강남구')
;
```

<br />

#### 전국의 CGV 극장의 이름과 규모 조회. 규모는 스크린 수가 5보다 작으면 '소', 5보다 크거나 같고 10보다 작으면 '중', 10보다 크거나 같으면 '대'로 표시.
	case문, like

```sql
select
    s.code,
    s.sido,
    s.gugun,
    s.screen_name,
    s.screen_count,
    case
        when s.screen_count < 5 then '소'
        when s.screen_count < 10 then '중'
        else '대'
    end as screen_scale
from screen as s
where screen_name like '%CGV%'
;
```

<br />

#### 출신지가 프랑스나 이탈리아면서 직업이 촬영이거나 편집인 영화인 조회
	and 조건, or 조건, in 조건

in을 쓰면 or 조건 처리가 된다.

```sql
select *
from actor
where country in ('프랑스', '이탈리아')
    and domain in ('촬영', '편집')
;
```

<br />

#### 2010년 ~ 2020년 기간중 연도별로 상영된 영화의 수를 조회
	count() 함수, between A and B
숫자나 datetime 자료형일 때 ₩between A and B` 사용 가능

```sql
select
    pub_year,
    format(count(*), 0) as pub_count
from movie
where pub_year between 2010 and 2020
group by pub_year;
```

<br />

#### 한국인 중에서 직업이 배우인 사람의 이름을 조회하되, 개인정보 보호를 위해서 이름 중간에 '*' 처리(단, 이름은 최소 두 글자 이상. 외자 이름은 경우 성만 표시, 네 글자 이름인 경우 성과 맨 마지막 글자만 노출, 그 이상인 경우 첫 성만 보여주고 나머지는 마스크 처리)
	length() 함수, trim() 함수, char_length() 함수, rpad() 함수, substring() 함수

`length()`는 영어. 숫자, 특수문자 등은 1로, 한글은 3으로 계산하기 때문에 한글 데이터일 때 오류 발생 가능함. 따라서 `char_length()` 사용 권장.

`substring(시작 번째, 부터 몇 개)` 이다. 

`rpad()`는 오른쪽에 특정 문자를 원하는 자리수만큼 넣음. `lpad()`는 왼쪽에 원하는 자리수만큼 넣음

```sql
select
    name,
    case
        when char_length(trim(name)) = 2 then concat(substring(trim(name), 1, 1), '*')
        when char_length(trim(name)) = 3 then concat(substring(trim(name), 1, 1), '*', substring(trim(name), 3))
        when char_length(trim(name)) = 4 then concat(substring(trim(name), 1, 1), '**', substring(trim(name), 4))
        else rpad(substring(trim(name), 1, 1), char_length(trim(name)), '*')
    end as name_mask
from actor
where country = '한국'
  and domain in ('배우')
  and char_length(trim(name)) > 1
;
```

<br />

#### 한국인이면서 '배'씨 성을 가진 사람 중 '준'으로 끝나는 이름을 가진 영화인 조회
	like문, 와일드카드_

'배%준' 이면 '배준'도 가져온다.
'배_' 이면 외자 이름만 가져온다.

```sql
select *
from actor
where country in ('한국')
    and name like '배_준'
```

<br />