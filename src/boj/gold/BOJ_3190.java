package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @date    2023-07-22
 * @title   뱀(3190) G4
 * @tags    구현, 자료구조, 시뮬레이션, Deque(덱), Queue(큐)
 * @input
 * 첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100)
 * 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
 * 다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다.
 * 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
 * 다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
 * 다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데, 정수 X와 문자 C로 이루어져 있으며,
 * 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
 * X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다
 * @output
 * 첫째 줄에 게임이 몇 초에 끝나는지 출력한다.
 */
public class BOJ_3190 {

    /**
     * <pre>
     *     자료구조나 알고리즘? 이라기보다는 시뮬레이션 문제였고, 내가 생각한 핵심은 다음과 같다.
     *      - 뱀위 위치를 어떻게 기록할 것인가?
     *      - 게임이 끝나는 조건을 어떻게 판정할 것인가?
     *      - 오른쪽, 왼쪽으로 뱀이 이동할 때 뱀 머리의 위치를 어떻게 계산할 것인가?
     *
     *     뱀의 위치
     *      LinkedList를 이용했다. 최초 뱀의 위치인 (0, 0), (0, 1) 부터
     *      방향에 따라 이동할 때마다 뱀의 머리와 꼬리는 각각 list.get(list.size - 1), list.get(0)으로 접근 가능해야 함.
     *      길이가 길어지는 경우 list.get(0)을 제거하지 않고, list.add(현재 행, 현재 열)로 사이즈를 키운다.
     *      길이가 길어지지 않고 이동만 하는 경우 list.add(현재 행, 현재 열)로 머리를 이동시키고, list.remove(0)으로 꼬리를 제거한다.
     *
     *     게임이 끝나는 조건
     *      벽에 부딪히는 경우, 뱀의 헤드 idx가 각각 0보다 작아지거나, N과 같거나 커질 때
     *      뱀의 몸통에 부딪히는 경우는, 헤드를 제외한 나머지를 list에서 순차 탐색한다.
     *
     *     뱀 머리 위치
     *      회전은 왼쪽, 오른쪽만 가능한데, 뱀의 현재 방향은 동서남북이 모두 가능하다.
     *      그러나 회전으로 인해 현재 어느 방향을 보게 되는지 알면 그 이후로는 모두 전진하는 것으로 볼 수 있음.
     *      최초 시작은 동쪽이고 오른쪽 회전 시: 동 -> 남 -> 서 -> 북 순으로 돌게 된다.
     *      이걸 배열에 집어넣음.
     *                      동  남  서  북
     *      int[] rowDir = {0, 1, 0, -1};
     *      int[] colDir = {1, 0, -1, 0};
     * </pre>
     * */
    static int N;
    static int direction = 0;  // 동: 0, 남: 1, 서: 2, 북: 3 (회전 방향 순)
    static int[] rowDir = {0, 1, 0, -1};
    static int[] colDir = {1, 0, -1, 0};
    static List<int[]> snake = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];
        for (int i = 0; i < K; i++) {
            String applePosition = br.readLine();
            int x = Integer.parseInt(applePosition.split(" ")[0]) - 1;
            int y = Integer.parseInt(applePosition.split(" ")[1]) - 1;

            board[x][y] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        Map<Integer, String> dirOrder = new HashMap<>();
        for (int i = 0; i < L; i++) {
            String order = br.readLine();
            dirOrder.put(Integer.parseInt(order.split(" ")[0]), order.split(" ")[1]);
        }

        snake.add(new int[] {0, 0});

        int sec = 0;
        int x = snake.get(snake.size() - 1)[0];
        int y = snake.get(snake.size() - 1)[1];
        while (true) {
            sec++;

            x += rowDir[direction];
            y += colDir[direction];

            if (isOver(x, y)) break;

            // 사과

            System.out.println(x + ", " + y);
            if (board[x][y] == 1) {
                board[x][y] = 0;
                snake.add(new int[] {x, y});
            } else {
                snake.add(new int[] {x, y});
                snake.remove(0);
            }

            if (dirOrder.containsKey(sec)) {
                if (Objects.equals(dirOrder.get(sec), "D")) {   // 오른쪽 회전
                    direction += 1;
                    if (direction == 4) direction = 0;
                } else {
                    direction -= 1;
                    if (direction == -1) direction = 3;
                }
            }
        }

        System.out.println(sec);
        br.close();
    }

    static boolean isOver(int headX, int headY) {
        // 뱀의 머리가 벽 밖으로 나간 경우
        if (headX < 0 || headY < 0) return true;
        if (headX >= N || headY >= N) return true;

        // 뱀의 머리가 뱀의 몸통과 만난 경우
        int snakeIdx = 0;
        while (snakeIdx < snake.size()) {
            if (snake.get(snakeIdx)[0] == headX && snake.get(snakeIdx)[1] == headY) return true;
            snakeIdx++;
        }

        return false;
    }
}
