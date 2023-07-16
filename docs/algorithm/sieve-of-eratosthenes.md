# 에라토스테네스의 체
Sieve of Eratosthenes

```java
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int n = 101;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(isPrime.length); i++) {
            if (isPrime[i]) {
                int j = 2;
                while (i * j <= n) {
                    isPrime[i * j] = false;
                    j++;
                }
            }
        }

        for (int i = 0; i < n + 1; i++) {
            if (isPrime[i]) {
                System.out.println(i);
            }
        }
    }
}
```