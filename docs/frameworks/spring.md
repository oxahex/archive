# Spring

## 빈 설정
### 빈의 구현체가 여러 개인 경우
1. @Primary: 해당 빈을 최우선으로 주입
2. @Qualifier("beanName"): beanName으로 지정된 Bean 주입
3. Set, List로 모두 받음
4. 프로퍼티 이름을 Bean과 동일하게 맞춤: 가장 흔함

### 빈의 스코프
1. 싱글톤: 일반적(기본값), 하나 생성하고 계속 재활용
2. Prototype: 매번 새로 만드는 방법
   1. Request: 요청에 따라 계속 새로 생성
   2. Session: 세션마다 계속 생성
   3. WebSocket

### Profile: 환경에 따라서
1. 특정 환경에서만 동작하는 Bean을 생성하기도
2. 클래스 단위에 적용하거나 메서드 단위에 적용
   1. 클래스 단위
      1. `@Configuration @Profile("test")`
      2. `@Component @Profile("test")`
   2. 메서드 단위
      1. `@Bean @Profile("test")`
3. `-Dspring.profiles.active=sandbox, text, production`
4. 프로파일 표현식
   1. `@Profile("!production")`
   2. !not, &and, |or

## Resource
[외부 자원 가져오기](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#resources)

## Resource Interface
```java
public interface Resource extends InputStreamSource {
    boolean exists();
    boolean isReadable();
    boolean isOpen();
    boolean isFile();
    URL getURL() throws IOException;
    URI getURI() throws IOException;
    File getFile() throws IOException;
    ReadableByteChannel readableChannel() throws IOException;
    long contentLength() throws IOException;
    long lastModified() throws IOException;
    Resource createRelative(String relativePath) throws IOException;
    String getFilename();
    String getDescription();
}
```

자바의 표준 클래스들은 다양한 리소스(URL, File)에 접근할 때 충분한 기능 제공 x



## Annotations
### @Entity
일종의 설정 클래스. 자바 객체지만?

```java
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
public class Cat {
    @Id
    @GeneratedValue
    Long id;
    
    private String name;
    
    @Enumrated(EnumType.STRING)
    private CatType catType;
}
```
Bulder 패턴을 사용하려고 @Builder 어노테이션 사용하려면 @NoArgsConstructor, @RequiredArgsConstructor 이것도 필요.

@Enumrated(EnumType.STRING): 기본적으로 ENUM은 1, 2, 3 식으로 동작하기 때문에 Entity 생성 시 DB에 숫자로 들어갈 수 있음. 저렇게 해두면 String으로 들어간다.


### @Repository
DB와 연결되는 부분

```java
@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {
    
}
```

JpaRepository<Repository가 활용하는 Entity, 해당 Entity의 PK 타입>


### @Service
비즈니스 로직

```java
@Service
@RequiredArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    
    @Transactional
    private void createCat() {
        Cat cat = Cat.builder()
                    .name("장영일")
                    .catType(CatType.CHEESE)
                    .build();
                        
        catRepository.save();
    }
}
```

@RequiredArgsConstructor: final로 CatRepository 필드를 만들었기 때문에 Lombok 어노테이션을 통해 저 final 타입을 받는 생성자를 자동 생성함. 내가 만들 Bean에 다른 Bean을 넣고 싶은 경우 final로 해당 Bean을 필드에 정의하고 @RequiredArgsConstructor 사용 시 자동으로 해당 Bean을 주입 받을 수 있음.


### @RestController
```java
@RestController
@RequiredArgsConstructor
public class CatController {
    private final CatService catService;
    
    @GetMapping("/cat")
    public String createCat() {
        catService.createCat();
        return "성공";
    }
}
```