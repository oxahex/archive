# Questions

## JAVA
### Static & Private
#### 실무에서 static, private 선언은 어떤 경우에 왜 하는지?
DB 연결의 경우 빈번하게 일어나므로 connection 연결이나, 연결 해제 등의 작업을 static class로 따로 생성해서 사용한다거나?

#### List를 Array로 변환 시 다음 방식이 가장 최적화 되어 있는 이유는?

```java
import java.util.ArrayList;

class _ {
    public static void main(String[] args) {
        ArrayList<String> _ = new ArrayList<>();
        String[] __ = _.toArray(new String[0]);
    }
}
```

#### Interface가 있고, Interface의 Implement가 있을 때 왜 업캐스팅을 사용하는 것이 권장되는 방식인지?