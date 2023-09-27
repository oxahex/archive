package boj.silver;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @date    2023-09-27
 * @title   이친수(2193) S3
 * @tags    DP
 * @input
 * 첫째 줄에 N(1 <= N <= 90)이 주어진다.
 * @output
 * 첫째 줄에 N자리 이친수의 개수를 출력한다.
 */
public class BOJ_2193 {

    /**
     * <pre>
     *     점화식을 확인하면 피보나치임을 알 수 있는데,
     *     일단 dp 배열을 활용해서 문제를 풀면
     *     dp[자릿수][0 or 1] 로 표현할 수 있다.
     *
     *     특정 자릿수에서 0으로 끝나는 경우,
     *     그 앞에 올 수 있는 케이스는 이전 자릿수에서 1로 끝난 이친수 + 이전 자릿수에서 0으로 끝난 이친수가 된다.
     *     1로 끝나는 경우는 이전 자릿수에서 0으로 끝난 이친수이다.
     *
     *     f(N)[0] = f(N - 1)[0] + f(N - 1)[1]
     *     f{N}[1] = f(N - 1)[0]
     *
     *     실제 구해야 하는 값은 f(N)[0] + f(N)[1] 이다.
     *
     *     이렇게 계산해서 풀 수 있음.
     *
     *     이 점화식이 피보나치인 이유는
     *     특정 자릿수의 이친수는 반드시 1 또는 0으로 끝난다.
     *     이 때 0으로 끝나는 경우 이전 자릿수의 모든 이친수만큼 만들어진다. -> ____0
     *     1로 끝나는 경우는 이전 자릿수 중 1로 끝나는 이친수 만큼만 만들 수 있으므로 -> ___10
     *     뒤의 2자리가 고정이 된다. 따라서 N - 2 자릿수의 이친수 만큼 만들어질 수 있음.
     * </pre>
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        long[][] dp = new long[N + 1][2];
        dp[1][1] = 1;

        for (int i = 2; i < N + 1; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        System.out.println(Arrays.stream(dp[N]).sum());
    }
}