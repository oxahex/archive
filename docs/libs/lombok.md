# Lombok

보일러 플레이트 코드 해결

대략적인 기능

1. @setter, @Getter: JABA Bean 규약에 있는 setter, getter 자동 생성
2. @ToString: Object에 기본 구현된 ToString 대신 객체의 데이터를 보여주는 ToString을 자동 생성
3. @NoArgsConstructor, @AllArgsConstructor, @RequiredArgsConstructor: 객체 생성자를 자동으로 생성
4. @Data: Getter, Setter, ToString, Equals, HashCode 등 다양한 기능을 제공
5. @Builder: 빌더 패턴을 자동 생성하여 제공
6. @Slf4j: 해당 클래스의 logger를 자동 생성하여 제공
7. @UtilityClass: static method만 제공하는 유틸리티 성격의 클래스들의 생성자를 private로 만들어서 객체 생성을 할 수 없도록 함

@Data
중요하지 않은 데이터에 대해 사용하긴 함.
중요한 정보(개인정보 등)의 경우 ToString이 자동 생성되기 때문에.
원치 않게 Equals를 해버릴 수 있기 때문에.

@RequiredArgsConstructor
Spring에서 Bean들을 주입 받는 생성자 주입 방식으로 Java Bean을 많이 만드는데, 이럴 때 사용. 자동으로 Bean을 주입 받을 수 있음.