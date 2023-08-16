package boj;

import java.io.*;
import java.util.*;

/**
 * @date    2023-08-16
 * @title   연결 요소의 개수(11724) S2
 * @tags    Graph, DFS, BFS
 * @input
 * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2)
 * 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다. (1 ≤ u, v ≤ N, u ≠ v)
 * 같은 간선은 한 번만 주어진다.
 * @output
 * 첫째 줄에 연결 요소의 개수를 출력한다.
 */
public class BOJ_11724 {

    /**
     * <pre>
     *     스택을 이용하지 않고 재귀 함수를 이용해 DFS 구현.
     *      - 그래프 연결 요소(Graph Component)는 나누어진 각각의 그래프 덩어리를 의미함.
     *      - 보통 DFS 탐색 시, 연결된 요소를 끝까지 탐색하기 때문에 방향 없이 이어진 그래프는 최초 진입 시 연결된 요소를 모두 탐색하게 된다.
     *      - 연결되어 있지 않은 개별적인 연결 요소는 DFS 한 번 수행으로 해결되지 않으므로 반복문으로 방문하지 않은 노드가 있는지 탐색 필요.
     *     따라서 main 함수 내부 dfs 실행 반복문에서 실행된 dfs 수가 연결 요소의 개수가 된다.
     * </pre>
     * */
    public static boolean[] visited;
    public static ArrayList<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 정점 개수
        int M = Integer.parseInt(st.nextToken());   // 간선 개수

        edges = new ArrayList[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        // 간선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges[a].add(b);
            edges[b].add(a);
        }

        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                cnt++;
                dfs(i);
            }
        }

        System.out.println(cnt);
    }

    public static void dfs(int v) {
        if (visited[v]) return;
        visited[v] = true;

        for (int i = 0; i < edges[v].size(); i++) {
            int n = edges[v].get(i);
            if (!visited[n]) dfs(n);
        }
    }
}
