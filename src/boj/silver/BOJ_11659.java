package boj.silver;

import java.util.Scanner;

/**
 * @date    2023-08-12
 * @title   구간 합 구하기 4(11659) S3
 * @tags    누적 합, 구간 합
 * @input
 * 첫째 줄에 수의 개수 N과 합을 구해야 하는 횟수 M이 주어진다.
 * 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다.
 * 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
 * @output
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 */
public class BOJ_11659 {

    /**
     * <pre>
     *     누적합 배열을 구할 수 있고, 구간합 계산을 해야 시간 초과가 나지 않는 문제.
     *     시간 제한이 1초이고, 입력은 최악의 경우 N이 100,000, M이 100,000.
     *
     *     누적합 배열 S, 주어진 숫자 배열 A
     *      - 합 배열: S[i] = S[i - 1] + A[i]
     *      - 구간 합: S[j] - S[i - 1]     // i에서 j 까지의 구간 합
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] sum = new int[N + 1];

        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            sum[i + 1] = sum[i] + n;
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            System.out.println(sum[end] - sum[start - 1]);
        }
    }
}
