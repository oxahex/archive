package boj.silver;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @date    2023-09-25
 * @title   쉬운 계단 수(10844) S1
 * @tags    DP
 * @input
 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
 * @output
 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
 */
public class BOJ_10844 {

    /**
     * <pre>
     *     숫자가 하나의 자릿수를 차지하면, 그 다음에 올 수 있는 수는 정해져 있음.
     *     0으로 끝나면 -> 1만 올 수 있음
     *     1 ~ 8로 끝나면 -> -1, +1한 경우가 모두 올 수 있음
     *     9로 끝나는 경우 -> 8만 올 수 있음
     * </pre>
     */
    final static long MOD = 1000000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        long[][] dp = new long[N + 1][10];

        // 1자리수의 경우 초기화(1 ~ 9 까지 1번씩)
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {          // 자릿수 만큼
            for (int j = 0; j < 10; j++) {      // 0 ~ 9 확인
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1] % MOD;
                } else if (j == 9){
                    dp[i][j] = dp[i - 1][8] % MOD;
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        // 해당 자릿수의 총 합
        System.out.println(Arrays.stream(dp[N]).sum() % MOD);
    }
}