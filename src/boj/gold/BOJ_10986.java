package boj.gold;

import java.util.Scanner;

/**
 * @date    2023-08-12
 * @title   나머지 합(10986) G3
 * @tags    수학, 누적 합, Modulo(나머지 연산)
 * @input
 * 첫째 줄에 N과 M이 주어진다. (1 ≤ N ≤ 10^6, 2 ≤ M ≤ 10^3)
 * 둘째 줄에 N개의 수 A1, A2, ..., AN이 주어진다. (0 ≤ A^i ≤ 10^9)
 * @output
 * 첫째 줄에 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수를 출력한다.
 */
public class BOJ_10986 {

    /**
     * <pre>
     *     핵심 아이디어
     *      - (A + B) % M 은 {(A % M) + (B % M)} % M 과 같다.
     *      - 특정 구간을 더해 나머지 연산을 한 결과는 각각 나머지 연산 후 더해 나머지 연산을 한 결과와 같다.
     *      - 구간 합 배열 S를 구하고 -> S[i] 를 나머지 연산 -> S[i] == 0 이면, A[0] - A[i] 까지의 합 % M == 0 이다.
     *      - S[i] == S[j] 가 성립하면, A[i + 1] - A[j] 까지의 합 % M == 0 이다.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        long[] sum = new long[N];
        sum[0] = sc.nextInt();

        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + sc.nextInt();
        }

        long answer = 0;
        long[] cnt = new long[M];
        for (int i = 0; i < N; i++) {
            int mod = (int) (sum[i] % M);
            if (mod == 0) answer++;
            cnt[mod]++;
        }

        for (int i = 0; i < M; i++) {
            if (cnt[i] > 1) answer += cnt[i] * (cnt[i] - 1) / 2;
        }

        System.out.println(answer);
    }
}
