package boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @date    2023-07-23
 * @title   사탕 게임(3085) S2
 * @tags    BF(브루트포스), 구현
 * @input
 * 첫째 줄에 보드의 크기 N이 주어진다. (3 ≤ N ≤ 50)
 * 다음 N개 줄에는 보드에 채워져 있는 사탕의 색상이 주어진다.
 * 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y로 주어진다.
 * 사탕의 색이 다른 인접한 두 칸이 존재하는 입력만 주어진다.
 * @output
 * 첫째 줄에 상근이가 먹을 수 있는 사탕의 최대 개수를 출력한다.
 */
public class BOJ_3085 {
    /**
     * <pre>
     *     브루트포스 문제를 풀 때는 이게 브루트포스로 될 것인지 아닌지를 판단하는 게 가장 어려운 것 같다.
     *      - 수행해야 하는 경우의 수를 대략 계산해야 함.
     *      - 이 방식으로 하면 시간은 조금 걸려도 아무튼 답은 나오는 것인지 명확히 하고 시작해야 함.
     *
     *     구슬이 최대 50개이기 때문에,
     *      - 각각의 구슬 위치를 변경함(오른쪽과 변경, 아래와 변경)
     *          -> 4 * N^2 인데, 2 * N^2 으로 줄일 수 있음: 어차피 오른쪽과 아래만 탐색하면 위쪽과 왼쪽은 자동으로 탐색된다.
     *      - 구슬 변경 시마다 행, 열을 각각 탐색해 최대로 이어진 구슬이 몇 개인지 탐색
     *          -> 2 * N^2 인데, 만약 최초에 전체 행과 열을 한 번 파악하고, 변경이 된 부분과 연관있는 행과 열만 탐색한다면 더 줄일 수 있을 것 같긴 함.
     *
     *     위의 대략적인 계산을 합하면 4 * N^2 이므로, 이 작업을 해도 최악의 경우 2500^2
     * </pre>
     */
    static int N;
    static String[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new String[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().split("");
//            System.out.println(i + ": " + Arrays.toString(board[i]));
        }

        int maxCandy = checkMaxCandy();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 오른쪽과 swap 후 행과 열 체크
                if (j + 1 < N) {
                    swap(i, j, i, j + 1);
                    maxCandy = Math.max(checkMaxCandy(), maxCandy);
                    swap(i, j + 1, i, j);
                }

                // 아래쪽과 swap 후 행과 열 체크
                if (i + 1 < N) {
                    swap(i, j, i + 1, j);
                    maxCandy = Math.max(checkMaxCandy(), maxCandy);
                    swap(i + 1, j, i, j);
                }
            }
        }

        System.out.println(maxCandy);
    }

    public static void swap(int x1, int y1, int x2, int y2) {
        String tmp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = tmp;
    }

    public static int checkMaxCandy() {
        // 행
        int rowMax = 1;
        String rowStartColor = "";
        for (int i = 0; i < N; i++) {
            int max = 1;
            for (int j = 0; j < N; j++) {
//                System.out.print("(" + i + ", " + j + ") " + rowStartColor + " - " + board[i][j] + " ");
                if (rowStartColor.equals(board[i][j])) {
                    max++;
                    if (rowMax < max) rowMax = max;
                } else {
                    max = 1;
                    rowStartColor = board[i][j];
                }
            }
//            System.out.println("rowMax: " + rowMax + ", max: " + max);
            rowStartColor = "";
        }

        System.out.println();

        // 열
        int colMax = 1;
        String colStartColor = "";
        for (int i = 0; i < N; i++) {
            int max = 1;
            for (int j = 0; j < N; j++) {
//                System.out.print("(" + j + ", " + i + ") " + colStartColor + " - " + board[j][i] + " ");
                if (colStartColor.equals(board[j][i])) {
                    max++;
                    if (colMax < max) colMax = max;
                } else {
                    max = 1;
                    colStartColor = board[j][i];
                }
            }
//            System.out.println("colMax: " + colMax + ", max: " + max);
            colStartColor = "";
        }

        return Math.max(rowMax, colMax);
    }
}
