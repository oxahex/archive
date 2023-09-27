package boj.silver;

import java.io.*;
import java.util.Arrays;

/**
 * @date    2023-09-27
 * @title   스티커(9465) S1
 * @tags    DP
 * @input
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다.
 * 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다.
 * 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
 * @output
 * 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최댓값을 출력한다.
 */
public class BOJ_9465 {

    /**
     * <pre>
     *     Bottom-up으로 생각하면 이전 값이 다음 값에 어떤 영향을 미치는지 알기가 어려웠음.
     *     거꾸로 생각해서 가장 마지막까지 어떤 기준으로 스티커를 뗀다고 가정했을 때,
     *     그 위치에 오려면 어떤 스티커를 떼서 왔을까를 생각해봤다.
     *
     *     50   10  100 20  40
     *     30   50  70  10  60
     *
     *     이렇게 주어진다면 40, 60을 뗄 때(마지막 칸 까지 왔을 때) 어떤 경로로 왔을까?
     *      - 이전 칸 대각선 -> 지금 위치
     *      - 이전 이전 칸의 두 값 중 큰 값 -> 지금 위치
     *     이렇게 올 수 있음.
     *     이전 이전 칸의 경우는, 모든 케이스 중 가장 큰 경우를 선택 + 현재 위치 값을 더해서 만들어진 결이므로
     *     이전 이전 칸의 두 값 중 큰 값을 비교하지 않아도 그냥 대각선 위치에 있는 값을 선택하면 무조건 둘 중 큰 값을 고르게 된다.
     * </pre>
     */
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            String[] up = br.readLine().split(" ");
            String[] down = br.readLine().split(" ");

            int[][] sticker = new int[2][N];
            for (int s = 0; s < N; s++) {
                sticker[0][s] = Integer.parseInt(up[s]);
                sticker[1][s] = Integer.parseInt(down[s]);
            }

            System.out.println(Arrays.toString(sticker[0]));
            System.out.println(Arrays.toString(sticker[1]));

            int[][] dp = new int[2][N + 2];
            for (int j = 2; j < N + 2; j++) {
                System.out.println("j: " + j);
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][ j - 2]) + sticker[0][j - 2];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + sticker[1][j - 2];
            }

            System.out.println("dp 0: " + Arrays.toString(dp[0]));
            System.out.println("dp 1: " + Arrays.toString(dp[1]));

            bw.write(Math.max(dp[0][N + 1], dp[1][N + 1]) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}