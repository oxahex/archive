# Note

유틸 클래스의 경우 클래스 생성해서 사용하기보다 실질적은 메서드를 static 처리해서 가져다 쓰는 경우가 많음.

```java
public class CalcUtil {
    private CalcUtil() {}
	
	public static Integer sum(Integer a, Integer b) {
        return a + b;
	}
    
    public static Integer minus(Integer a, Integer b) {
        return a - b;
    }
}
```

이 경우 그냥 생성자를 private 처리하는데(생성자 못 쓰게), 이걸 매번 써주는 게 귀찮으므로 Lombok에서 @UtilityClass 어노테이션 지원함. 그거 쓰면 좋음. 

```java
@UtilityClass
public class CalcUtil {
    
	public static Integer sum(Integer a, Integer b) {
        return a + b;
	}
    
    public static Integer minus(Integer a, Integer b) {
        return a - b;
    }
}
```