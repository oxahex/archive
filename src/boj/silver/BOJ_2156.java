package boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date    2023-09-29
 * @title   포도주 시식(2156) S1
 * @tags    DP
 * @input
 * 첫째 줄에 포도주 잔의 개수 n이 주어진다. (1 ≤ n ≤ 10,000)
 * 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다.
 * 포도주의 양은 1,000 이하의 음이 아닌 정수이다.
 * @output
 * 첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
 */
public class BOJ_2156 {

    /**
     * <pre>
     *     접근
     *      문제를 해결할 수 있는 최소 단위를 생각해보면
     *      1. 포도주가 1잔 => 1잔이 최대 양
     *      2. 포도주가 2잔 => (1 + 2)잔이 최대 양
     *      3. 포도주가 3잔 => a, b, c
     *                      a + b, b + c, a + c 중 최댓값이 마실 수 있는 최대 양이 된다.
     *
     *      6, 10, 13, 9, 8, 1 포도주가 주어지는 경우
     *      6            => 6
     *      6, 10        => 16
     *      6, 10, 13    => 6 + 10, 6 + 13, 10 + 13 중 답이 있다.
     *
     *      dp[i] 배열에 현재까지의 최대 양을 저장하도록 하려면 어떻게 해야 하나?
     *      이렇게 문제에 접근했다.
     *
     *     a b c d e 가 있고, 대문자가 선택한 잔이라고 가정하면
     *     a B C d E
     *     A B c D E
     *     이렇게 선택할 수 있다. E는 어차피 합산할 값이니까 비교해야 하는 케이스는
     *     1. B + C
     *     2. A + B + D
     *     이렇게 2가지다.
     *
     *     dp에 누적합 값을 저장하므로
     *     1. dp[n - 2]
     *     2. dp[n - 3] + 이전 와인잔의 양
     *     이렇게 두 가지 중 최댓값을 저장하면 된다.
     *
     *     그런데 이런 케이스가 있을 수 있다.
     *     A B c d E F
     *     최대 2연속 까지만 마실 수 있기 때문에 현재 와인을 마시지 않는 경우도 고려해야 한다.
     *     위의 두 케이스는 E 잔을 반드시 마시는 경우임.
     *
     *     6 10 13 9 8 1
     *     6 10 13          ->  10 + 13 = 23
     *     6 10 13 9        ->  6 + 10 + 9 = 28
     *     6 10 13 9 8      ->  6 + 10 + 9 + 8 = 33
     *     6 10 13 9 8 1    ->  9, 8, 1 연속으로 마실 수 없는데
     *                          마지막 1을 마시기 위해서 10 + 13 + 8 + 1 = 32로 갱신
     *                          이 값은 6 10 13의 결과로 돌아가 마지막 1을 마실 수 있는 쪽으로 계산된 것
     *
     *     따라서 1을 마시지 않는 경우도 고려해야 한다.
     *     마시지 않는 경우는 해당 와인잔을 마시는 경우가 이전 dp 값 보다 작으면 무시하는 것임
     *     max(dp[n - 1], 반드시 마시는 경우 최댓값)
     * </pre>
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n + 1];
        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = w[1];
        if (n == 1) {
            System.out.println(dp[1]);
            return;
        }
        dp[2] = w[1] + w[2];

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2], dp[i - 3] + w[i - 1]) + w[i]);
        }


        System.out.println(dp[n]);

    }
}