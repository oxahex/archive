package boj;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @date    2023-08-12
 * @title   구간 합 구하기 5(11660) S1
 * @tags    DP, 누적 합, 구간 합
 * @input
 * 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다. (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000)
 * 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다.
 * 다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다.
 * 표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다. (x1 ≤ x2, y1 ≤ y2)
 * @output
 * 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
 */
public class BOJ_11660 {

    /**
     * <pre>
     *     2차원 배열의 누적 합과 구간 합을 구해서 풀어야 하는 문제.
     *     입력이 최악의 경우 100,000 이므로 DP로 풀지 않으면 시간 초과.
     *
     *     2차원 누적 합 배열을 S, 주어진 배열 A 라고 할 때,
     *     누적 합: S[i][j] = S[i][j - 1] + A[i][j] + S[i - 1][j] + S[i - 1][j - 1]
     *
     *     2차원 구간 합 (x1, y1) 부터 (x2, y2) 까지의 누적 합을 구하는 경우,
     *     Sum = S[x2, y2] - S[x1 - 1][y2] - S[x2, y1 -1] + S[x1 -1][y1 - 1]
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] sum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sum[i][j] = sum[i - 1][j] + Integer.parseInt(st.nextToken()) + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
            System.out.println(result);
        }
    }
}
