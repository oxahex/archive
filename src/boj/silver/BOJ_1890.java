package boj.silver;

import java.io.*;
import java.util.StringTokenizer;

/**
 * @date    2023-08-01
 * @title   점프(1890) S1
 * @tags    DP
 * @input
 * 첫째 줄에 게임 판의 크기 N (4 ≤ N ≤ 100)이 주어진다
 * 그 다음 N개 줄에는 각 칸에 적혀져 있는 수가 N개씩 주어진다.
 * 칸에 적혀있는 수는 0보다 크거나 같고, 9보다 작거나 같은 정수이며, 가장 오른쪽 아래 칸에는 항상 0이 주어진다.
 * @output
 * 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 문제의 규칙에 맞게 갈 수 있는 경로의 개수를 출력한다.
 * 경로의 개수는 (2^63) - 1 보다 작거나 같다.
 */
public class BOJ_1890 {

    /**
     * <pre>
     *
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        long[][] visited = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0][0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int jump = board[i][j];
                if (jump == 0) continue;
                if (i + jump < N) visited[i + jump][j] += visited[i][j];
                if (j + jump < N) visited[i][j + jump] += visited[i][j];
            }
        }

        System.out.println(visited[N - 1][N - 1]);

        br.close();
    }
}
