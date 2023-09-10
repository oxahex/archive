package boj.gold;

import java.io.*;
import java.util.*;

/**
 * @date    2023-09-09
 * @title   최소비용 구하기(1916) G5
 * @tags    Dijkstra, Graph
 * @input
 * 첫째 줄에 도시의 개수 N(1 ≤ N ≤ 1,000)이 주어지고 둘째 줄에는 버스의 개수 M(1 ≤ M ≤ 100,000)이 주어진다.
 * 그리고 셋째 줄부터 M+2줄까지 다음과 같은 버스의 정보가 주어진다.
 * 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
 * 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.
 * 그리고 M + 3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.
 * 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다.
 * @output
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 */
public class BOJ_1916 {

    /**
     * <pre>
     *     최적화가 필요한 문제
     *      - 도시 탐색 시 탐색하려는 도시의 fee가 이미 그 도시까지의 cost 보다 크면 탐색할 필요가 없다.
     *      - visited[] 역할을 어느정도 해줌.
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Bus_1916>[] graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Bus_1916(b, c));
        }


        st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[] cost = new int[N + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[S] = 0;

        PriorityQueue<Bus_1916> pq = new PriorityQueue<>((x, y) -> x.fee - y.fee);
        pq.offer(new Bus_1916(S, 0));

        while (!pq.isEmpty()) {
            Bus_1916 cur = pq.poll();
            int now = cur.dest;

            if (cur.fee > cost[now]) continue;

            for (int i = 0; i < graph[now].size(); i++) {
                Bus_1916 next = graph[cur.dest].get(i);

                if (cost[next.dest] > cost[now] + next.fee) {
                    cost[next.dest] = cost[now] + next.fee;
                    pq.add(new Bus_1916(next.dest, cost[next.dest]));
                }
            }
        }
        System.out.println(cost[E]);
    }
}

class Bus_1916 {
    int dest;
    int fee;

    public Bus_1916(int dest, int fee) {
        this.dest = dest;
        this.fee = fee;
    }
}