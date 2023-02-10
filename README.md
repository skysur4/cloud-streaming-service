# cloud-streaming-service
- 서버사이드이벤트 전송을 위한 서버 설정
- WebFlux(Netty) 기준으로 작성
- cloud-gateway-service와 동일한 방식으로 세션 체크 (게이트웨이를 통하여 로그인을 하였다면 별도의 인증 없이 사용 가능)

## Local 실행 방법 (STS 기준)
##### Run Configuration 설정
- Arguments 탭 -> VM 옵션에 아래와 같이 추가

> -Dspring.profiles.active=local

> -Dapp.home=app-home

> -DHOSTNAME=local

- 클릭 RUN

### Terminal
##### 1. 빌드
```bash
gradlew clean build --refresh-dependencies -x test
```

##### 2. 실행
```bash
java -jar -Dspring.profiles.active=local -Dapp.home=app-home -DHOSTNAME=local build/libs/cloud-streaming-service-x.x.x.jar
```

##### 3. nginx 설정
```
location /streaming {
	proxy_http_version 1.1;
	proxy_set_header Host $host;
	proxy_set_header X-Real-IP $remote_addr;

	proxy_read_timeout 30m;	#세션타임아웃 시간과 맞춰준다

	proxy_buffering off;
	proxy_cache off;

	proxy_pass http://cloud_stream;
}
```