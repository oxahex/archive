package algorithm;

public class DP {

    // O(N^2)
    public static int recursion(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursion(n - 1) + recursion(n - 2);
        }
    }

    // O(N)
    public static int tabulationDP(int n) {
        int[] dp = new int[n < 2 ? 2 : n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // O(N)
    // 메모리를 밖에 선언하고, memoizationDP를 재귀함수로 쓰겠음.
    static int[] dp = new int[8];   // 7 찾는 거니까 간단히 표현. 가변으로 하려면 별도의 recursion 함수 필요함
    public static int memoizationDP(int n) {
        if (n <= 2) return 1;
        if (dp[n] != 0) return dp[n];   // 재귀호출 해서 데이터가 있는 상태

        dp[n] = memoizationDP(n - 1) + memoizationDP(n - 2);    // 계산한 적 없을 때만 재귀 호출
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("recursion: " + recursion(7));
        System.out.println("Tabulation DP: " + tabulationDP(7));
        System.out.println("Memoization DP: " + memoizationDP(7));
    }
}
