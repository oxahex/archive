# Command Line

이제 이걸 매번 찾는 것도 지친다...

<br />

**포트 번호로 프로세스 정보 출력**
```shell
# macOS
lsof -i :[포트번호]
```

**포트 킬**
```shell
# macOS
kill -9 [포트번호]
```

**Local -> EC2 Server 파일 업로드**
```shell
# macOS
scp -i [파일 경로] [업로드할 파일 이름] [ec2 user]@[EC2 public IP]:[경로]
```

**EC2 Server -> Local 파일 다운로드**
```shell
# macOS
scp -i [pem파일경로] [ec2 user]@[ec2 public ip]:[경로] [다운로드 파일의 로컬 경로] 
```

**파일 이동**
```shell
# macOS
mv [파일 이름] [이동 위치]
mv -n [파일 이름] [이동 위치]     # 덮어 쓰지 않음
mv -i [파일 이름] [이동 위치]     # 덮어 씀
```