# Embedded Redis

보통 별도 서버를 가지는 Redis를 공용으로 만들어 놓고 사용함. 이걸 쓸 일은 거의 없겠지만 그냥 공부 목적으로 해봄.

Embedded 환경이기 때문에 서버가 켜지면 메모리에 올라가고, 꺼지면 제거.

사용 목적은 SpinLock을 활용한 동시성 제어.
DB로도 Lock 제어가 가능하지만 DB를 벗어난 Lock 제어가 필요할 때, DB 의존성이 높은 Lock을 하게 되면 성능이 떨어짐 Serialize를 쓴다거나 하면. 자유로운 Lock, 빠른 Lock을 구현하기 위해서. 

Embedded Redis 라이브러리 사용 시 주의할 점은... 해당 Bean이 Redis Repository보다 빨리 뜰 수 있도록 패키지 순서를 위쪽으로 해야 함(애초에 이 라이브러리가 그렇게 잘 만들어지지 않음...)


## 실행 설정
### RedisConfiguration
Spring Boot가 기동하면서 Bean을 등록할 때 Redis를 실행하고, 종료되면서 Bean을 삭제할 때 Redis가 종료되도록 설정.


```java
@Configuration
public class RedisConfiguration {
    @Value("${spring.data.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) redisServer.stop();
    }
}
```

@PostConstruct: Bean의 생성주기에 있는 이 클래스를 Bean으로 등록할 때, 등록 하자마자 동작

@PreDestroy: Bean을 지우기 직전(파괴하기 직전)에 동작하도록

### RedisRepositoryConfiguration

Redis 서버는 어플리케이션과 별도로 떠있음. 여기에 접근하기 위한 Repository를 등록하면서 여기에 RedissonClient를 등록

```java
@Configuration
public class RedisRepositoryConfiguration {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort);
        
        return Redisson.create(config);
    }
}
```

## Lock