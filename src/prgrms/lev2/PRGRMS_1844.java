package prgrms.lev2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @date    2023-09-16
 * @title   게임 맵 최단거리(1844), L2
 * @tags    BFS
 * @input
 * 게임 맵의 상태 maps가 매개변수로 주어진다.
 * @output
 * 캐릭터가 상대 팀 진영에 도착하기 위해서 지나가야 하는 칸의 개수의 최솟값을 return 하도록 solution 함수를 완성한다.
 * 단, 상대 팀 진영에 도착할 수 없을 때는 -1을 return 한다.
 */
public class PRGRMS_1844 {

    /**
     * <pre>
     *
     * </pre>
     */
    public int solution(int[][] maps) {
        int answer = -1;
        int[] X = {0, 0, 1, -1};
        int[] Y = {1, -1, 0, 0};
        int n = maps.length;
        int m = maps[0].length;

        Queue<PRGRMS_1844.N> Q = new LinkedList<>();
        Q.offer(new N(0, 0, 1));

        while (!Q.isEmpty()) {
            PRGRMS_1844.N cur = Q.poll();
            if (cur.x == n - 1 && cur.y == m - 1) answer = cur.dist;
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + X[i];
                int ny = cur.y + Y[i];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (maps[nx][ny] != 0) {
                    maps[nx][ny] = 0;
                    Q.offer(new N(nx, ny, cur.dist + 1));
                }
            }
        }
        return answer;
    }

    static class N {
        int x;
        int y;
        int dist;

        public N(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}