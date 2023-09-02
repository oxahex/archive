# Command Line

이제 이걸 매번 찾는 것도 지친다...

<br />

## `chmod`
권한 부여
```shell
# chmod [옵션] [모드] [파일명]
chmod -R        # 하위 파일과 디렉토리 모든 권한 변경
chmod -v        # 실행되고 있는 모든 파일을 나열한다.
chmod -c        # 권한이 변경된 파일 내용을 출력한다.
```
### 문자열 모드
```shell
#chmod [옵션] (reference) (operator) (modes) [파일명]
chmod ug+rw test    # test 파일의 user, group 멤버들에게 읽기 쓰기 권한 부여
chmod u=rwx,g+x test    # test 파일의 user는 읽기, 쓰기, 실행 권한 부여, group 멤버들에게 실행 권한 부여 
```
- reference(대상)
  - u: user의 권한(사용자의 권한)
  - g: group의 권한(파일의 group 멤버인 사용자의 권한)
  - o: other의 권한(user, group의 멤버가 아닌 사용자의 권한)
  - a: all의 권한(위의 셋을 포함하는 모든 사용자의 권한)
- operator
  - +: 해당 권한을 추가한다
  - -: 해당 권한을 제거한다
  - =: 해당 권한을 설정한대로 변경한다
- modes
  - r: read 권한(읽기)
  - w: write 권한(쓰기)
  - x: excute 권한(실행)
  - -: 권한 없음

### 8진법 수 모드
```shell
# chmod [옵션] (8진법 수) [파일명]
# r = 4, w = 2, x = 1
chmod 777 test      # test 파일의 user, group, other 권한을 모두 rwx로 변경
chmod 4755 test     # test 파일의 user id 설정을 지정하고, user에게 rwx 권한 부여, group과 other에게 r-x 권한 부여
```

## `cp`
새로운 original data와 새로운 inode2가 생성되고 원본 파일을 복사한 data가 생김.
inode가 다르면 완벽히 다른 파일이라고 볼 수 있음. cp -a 옵션을 줘도 inode는 다르다.

## `lsof`
포트 번호로 프로세스 정보 출력
```shell
# macOS
lsof -i :[포트번호]
```

## `ls`
```shell
# ls [옵션] [디렉토리 경로]
ls -l     # 파일을 나열 시 full-format
ls -a     # 경로 안의 모든 파일을 나열(숨김파일 포함)
ls -R     # 위치한 디렉토리 하부 디렉토리 파일까지 모두 출력
ls -h     # 파일 크기를 해석하기 편하게 출력
ls -r     # 출력 결과를 내림차순으로 정렬
ls -t     # 출력 결과를 파일이 수정된 시간을 기준으로 정렬
```

## `kill`
포트 킬(시스템 시그널하고 연결되어 있으므로 다시 정리 필요)
```shell
# macOS
kill -9 [포트번호]
```

## `scp`
- Local -> EC2 Server 파일 업로드
- EC2 -> Local 파일 다운로드
```shell
# macOS
scp -i [pem 파일 경로] [업로드할 파일 경로] [ec2 user]@[EC2 public IP]:[경로]
scp -i [pem 파일 경로] [ec2 user]@[ec2 public ip]:[경로] [다운로드 파일의 로컬 경로]
```

## ``mv``
파일 이동
```shell
# macOS
mv [파일 이름] [이동 위치]
mv -n [파일 이름] [이동 위치]     # 덮어 쓰지 않음
mv -i [파일 이름] [이동 위치]     # 덮어 씀
```

## `grep`
- 특정 파일에서 지정한 문자열이나 정규표현식을 포함한 행을 출력해주는 명령어
- pipe(|)를 사용해 다른 명령어와 함께 사용하는 경우가 많음
- 종류
  - `grep`: 다중 패턴 검색(정규식 사용 가능)
  - `egrep` 정규표현식 패턴 검색
  - `fgrep`: 문자열 패턴검색

```shell
# grep [옵션] [패턴] [파일명]
grep -c         # 일치하는 행의 수 출력
grep -i         # 대소문자 구분 X
grep -v         # 일치하지 않는 행만 출력
grep -n         # 포함된 행의 번호를 함께 출력
grep -l         # 패턴이 포함된 파일의 이름 출력
grep -w         # 단어와 일치하는 행만 출력
grep -x         # 라인과 일치하는 행만 출력
grep -r         # 하위 디렉토리를 포함한 모든 파일에서 검색 
grep -m숫자     # 최대로 표시될 수 있는 결과 제한
grep -E         # 패턴을 정규 표현식으로 찾기
grep -F         # 패턴을 문자열로 찾기
```
```shell
grep 'a*' test.txt        # 특정 파일에서 ''a'로 시작하는 단어를 모두 찾기
grep [a-c] test.txt       # 특정 파일에서 a, b, c로 시작하는 단어를 모두 찾기
grep [aA]bc test.txt      # 특정 파일에서 abc 또는 Abc로 시작하는 단어를 모두 찾기
grep '^[ab]' test.txt     # 특정 파일에서 a나 b로 시작하는 모든 행 찬기
grep 'ab'[0-9] test.txt   # 특정 파일에서 ab로 시작되고 0~9 숫자로 끝나는 모든 행 찾기
```

## `ps`
- 프로세스의 현재 상태를 보여준다.

```shell
# ps [옵션]
```
```shell
# 소유자 정보를 제외한 모든 프로세스를 보여주게 된다.
ps -e
ps ax
# 모든 프로세스에 대한 정보를 full-format으로 본다.
ps -ef
ps aux
# 돌고 있는 프로세스 목록을 뽑아서 그 중 특정 프로세스를 확인할 수 있다.
# e.g. nginx가 정상적으로 돌고 있는지 확인하고 싶을 때
ps -ef | grep nginx
# 특정 프로세스의 PID를 이용해 확인할 수 있다.
ps -fp [pid]
# 특정 사용자 중심으로 프로세스 확인할 수 있다.
ps -U root -u root u
```