package boj.gold;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @date    2023-09-06
 * @title   물통(2251) G5
 * @tags    BFS, Graph
 * @input
 * 첫째 줄에 세 정수 A, B, C가 주어진다.
 * @output
 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
 */
public class BOJ_2251 {

    /**
     * <pre>
     *     물 이동 시 한 물통이 비거나 다른 물통이 가득 찰 때까지 부어야 함.
     *     이동 가능한 경로는 총 6가지
     *      - A -> B
     *      - A -> C
     *      - B -> A
     *      - B -> C
     *      - C -> A
     *      - C -> B
     *     이 부분을 배열로 구현
     *     S = {0, 0, 1, 1, 2, 2}
     *     R = {1, 2, 0, 2, 0, 1}
     *
     *     int max[]            // 처음에 주어지는 각 물통의 최대 부피
     *     boolean[][] visited  // A, B의 값을 인덱스로 가지는 2차원 배열(A, B 값을 알면 C는 고정)
     *     boolean[] answer     // A가 0일 때 true로 변경, C 물의 양을 인덱스로 가지는 1차원 배열
     *
     *     Q.add(new AB(0, 0))  // 최초 A, B에 물이 없는 상태부터 탐색
     *     visited[0][0] = true     // 방문 처리
     *     answer[max[2]] = true    // C 최대 부피는 항상 답에 포함된다.
     *
     *     물을 주고받는 부분 구현
     *      1. 일단 물을 보냄
     *      2. 넘치면 넘치는 만큼 주는 쪽으로 돌려보내고, 받는 쪽은 최댓값으로 채움
     *      3. 주고받기가 끝나면 이전에 나왔던 조합인지 visited 배열 황긴
     *      4. 나왔던 조합이 아니면 Q에 삽입
     *      5. 이 때 A가 0이면(next[0] == 0) answer[next[3]] = true로 만들어 정답 기록
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] max = {sc.nextInt(), sc.nextInt(), sc.nextInt()};
        boolean[][] visited = new boolean[201][201];
        boolean[] answer = new boolean[201];
        int[] S = {0, 0, 1, 1, 2, 2};
        int[] R = {1, 2, 0, 2, 0, 1};

        Queue<AB_2251> Q = new LinkedList<>();
        Q.add(new AB_2251(0, 0));

        visited[0][0] = true;
        answer[max[2]] = true;

        while (!Q.isEmpty()) {
            AB_2251 now = Q.poll();
            int a = now.A;
            int b = now.B;
            int c = max[2] - a - b;

            for (int i = 0; i < 6; i++) {
                int[] next = {a, b, c};
                int s = S[i];
                int r = R[i];

                // 받는 쪽에 주는 쪽을 더함
                next[r] += next[s];
                next[s] = 0;

                // 넘치면
                if (next[r] > max[r]) {
                    next[s] = next[r] - max[r];
                    next[r] = max[r];
                }

                // 이런 물의 양 조합이 기존에 없다면 방문 처리
                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    Q.add(new AB_2251(next[0], next[1]));

                    // A의 물의 양이 0이면 정답 배열에 추가
                    if (next[0] == 0) answer[next[2]] = true;
                }
            }
        }

        for (int i = 0; i < 201; i++) {
            if (answer[i]) System.out.print(i + " ");
        }
    }
}

class AB_2251 {
    int A;
    int B;

    public AB_2251(int a, int b) {
        this.A = a;
        this.B = b;
    }
}