# SQL

특정 문자가 포함되어 있는 데이터를 검색
```sql
-- 특정 문자열로 시작 --
SELECT 필드명 FROM 테이블명 WHERE 필드명 LIKE '문자열%';

-- 특정 문자열로 끝남 --
SELECT 필드명 FROM 테이블명 WHERE 필드명 LIKE '%문자열';

-- 특정 문자를 포함 --
SELECT 필드명 FROM 테이블명 WHERE 필드명 LIKE '%문자열%';

-- 복수개의 특정 문자를 포함하는 데이터를 검색하기 위해서는 OR 연산자 이용
SELECT 필드명 FROM 테이블명 WHERE 필드명 LIKE '%문자열1%' OR 필드명 LIKE '%문자열2%';
```