package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @date    2023-08-29
 * @title   이분 그래프(1707) G4
 * @tags    Graph, DFS
 * @input
 * 입력은 여러 개의 테스트 케이스로 구성되어 있는데,
 * 첫째 줄에 테스트 케이스의 개수 K가 주어진다.
 * 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V와 간선의 개수 E가 빈 칸을 사이에 두고 순서대로 주어진다.
 * 각 정점에는 1부터 V까지 차례로 번호가 붙어 있다.
 * 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데,
 * 각 줄에 인접한 두 정점의 번호 u, v (u ≠ v)가 빈 칸을 사이에 두고 주어진다.
 * @output
 * K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.
 */
public class BOJ_1707 {
    public static ArrayList<Integer>[] edges;
    public static boolean[] visited;
    public static int[] set;
    public static boolean isCycle;


    /**
     * <pre>
     *     문제 접근
     *      - 이분 그래프가 될 수 없는 경우: 그래프에 사이클이 존재한다.
     *      - 반대로, 트리 형태의 그래프인 경우 이분 그래프가 될 수 있다.
     *
     *     간선이 있는 다음 정점 탐색 시,
     *      - 방문한 적 없는 경우 -> 부모 정점과 다른 집합을 세팅한다.
     *      - 방문한 적 있는 경우 -> 집합을 확인 -> 부모와 같은 집합에 속해 있다면 사이클.
     *                            -> 방문한 적 있더라도 집합이 다를 수 있음.
     *
     *     그래프가 비연결형 그래프일 가능성이 있기 때문에 모든 노드를 탐색해야 함.
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            System.out.println("NEW");
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            System.out.println("V: " + V + ", E: " + E);
            // 정점 개수만큼 ArrayList 배열 생성 후 초기화
            edges = new ArrayList[V + 1];
            visited = new boolean[V + 1];
            set = new int[V + 1];
            isCycle = false;
            for (int j = 1; j <= V; j++) {
                edges[j] = new ArrayList<>();
            }

            // 연결 정보 입력
            for (int k = 0; k < E; k++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                System.out.println("a: " + a + ", b: " + b);

                edges[a].add(b);
                edges[b].add(a);
            }

            // 모든 정점 수행, 비연결 그래프 존재 가능성
            for (int t = 1; t <= V; t++) {
                System.out.println(t + ": " + edges[t]);
                if (!isCycle) dfs(t);
                else break;
            }

            if (isCycle) System.out.println("NO");
            else System.out.println("YES");
        }
    }

    public static void dfs(int v) {
        System.out.println("v: " + v);
        System.out.println("v: " + edges[v]);
        visited[v] = true;
        for (int i : edges[v]) {
            if (!visited[i]) {
                set[i] = (set[v] + 1) % 2;
                dfs(i);
            } else if (set[i] == set[v]) {
                // 같은 집합에 존재
                isCycle = true;
            }
        }

    }
}
