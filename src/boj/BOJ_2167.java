package boj;

import java.io.*;

/**
 * @date    2023-07-31
 * @title   2차원 배열의 합(2167) S5
 * @tags    DP, Prefix Sum
 * @input
 * 첫째 줄에 배열의 크기 N, M(1 ≤ N, M ≤ 300)이 주어진다.
 * 다음 N개의 줄에는 M개의 정수로 배열이 주어진다.
 * 배열에 포함되어 있는 수는 절댓값이 10,000보다 작거나 같은 정수이다.
 * 그 다음 줄에는 합을 구할 부분의 개수 K(1 ≤ K ≤ 10,000)가 주어진다.
 * 다음 K개의 줄에는 네 개의 정수로 i, j, x, y가 주어진다. (1 ≤ i ≤ x ≤ N, 1 ≤ j ≤ y ≤ M).
 * @output
 * K개의 줄에 순서대로 배열의 합을 출력한다. 배열의 합은 (2^31)-1보다 작거나 같다.
 */
public class BOJ_2167 {

    /**
     * <pre>
     *     2차원 배열 Prefix Sum 으로 누적합 테이블을 이용해 부분합을 구하는 문제
     *     누적합 테이블을 구하는 도중 Overflow 가 없고, 전체 계산을 시간 내에 할 수 있다면,
     *     그 이후에 누적합 또는 부분합을 구하는 작업의 시간복잡도는 O(N)이 된다. 상수 시간 내에 해결 가능.
     *
     *     주어진 배열(arr)
     *     1    2   4
     *     8    16  32
     *
     *     누적합 테이블(ps) - int[][] ps = new int[arr.length + 1][arr[0].length + 1]
     *     ps[1][1] = ps[1][1 - 1] + ps[1 - 1][1] - arr[1 - 1][1 - 1]
     *     0    0   0   0
     *     0    1   3   7
     *     0    9   27  63
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String size = br.readLine();
        int N = Integer.parseInt(size. split(" ")[0]);
        int M = Integer.parseInt(size. split(" ")[1]);

        String[][] arr = new String[N][M];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().split(" ");
        }

        // 누적합 테이블 생성
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + Integer.parseInt(arr[i - 1][j - 1]);
            }
        }

        // 계산하려는 범위 쌍 수
        int K = Integer.parseInt(br.readLine());

        for (int f = 0; f < K; f++) {
            String[] s = br.readLine().split(" ");
            int i = Integer.parseInt(s[0]);
            int j = Integer.parseInt(s[1]);
            int x = Integer.parseInt(s[2]);
            int y = Integer.parseInt(s[3]);

            // 누적합 계산
            int result = dp[x][y] - dp[x][j - 1] - dp[i - 1][y] + dp[i - 1][j - 1];

            bw.write(result + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
