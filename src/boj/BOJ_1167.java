package boj;

import java.util.*;

/**
 * @date    2023-08-18
 * @title   트리의 지름(1167) G2
 * @tags    Graph, DFS, Tree
 * @input
 * 트리가 입력으로 주어진다.
 * 먼저 첫 번째 줄에서는 트리의 정점의 개수 V가 주어지고 (2 ≤ V ≤ 100,000)
 * 둘째 줄부터 V개의 줄에 걸쳐 간선의 정보가 다음과 같이 주어진다. 정점 번호는 1부터 V까지 매겨져 있다.
 * 먼저 정점 번호가 주어지고, 이어서 연결된 간선의 정보를 의미하는 정수가 두 개씩 주어지는데, 하나는 정점번호, 다른 하나는 그 정점까지의 거리이다.
 * 예를 들어 네 번째 줄의 경우 정점 3은 정점 1과 거리가 2인 간선으로 연결되어 있고, 정점 4와는 거리가 3인 간선으로 연결되어 있는 것을 보여준다.
 * 각 줄의 마지막에는 -1이 입력으로 주어진다. 주어지는 거리는 모두 10,000 이하의 자연수이다.
 * @output
 * 첫째 줄에 트리의 지름을 출력한다.
 */
public class BOJ_1167 {
    public static boolean[] visited;
    public static int[] dist;
    public static ArrayList<Edge>[] edges;

    /**
     * <pre>
     *     기본이 되는 아이디어
     *      - 두 노드 사이 경로가 가장 긴 것이 트리의 지름.
     *      - 트리를 그래프로 생각하면 시작점이 항상 1일 필요는 없음.
     *      - 임의의 노드를 root 노드로 치고 DFS 탐색을 해서 가장 먼 위치를 찾고
     *      - 가장 먼 위치를 다시 트리의 root 노드로 치고 DFS 탐색을 한다.
     *
     *     간선에 가중치가 있기 때문에 처음에는 크기가 2인 배열로 표현할까 했으나
     *     이런 경우 input이 최대 100,000 이므로 클래스로 따로 선언하는 것이 낫다.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();

        edges = new ArrayList[V + 1];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            int v = sc.nextInt();
            while (true) {
                int a = sc.nextInt();
                if (a == -1) break;
                int b = sc.nextInt();

                edges[v].add(new Edge(a, b));
            }
        }

        visited = new boolean[V + 1];
        dist = new int[V + 1];

        dfs(1);

        int max = 0;
        for (int i = 1; i < dist.length; i++) {
            if (dist[max] < dist[i]) max = i;
        }

        visited = new boolean[V + 1];
        dist = new int[V + 1];
        dfs(max);

        Arrays.sort(dist);
        System.out.println(dist[dist.length - 1]);




    }

    public static void dfs(int v) {
        if (visited[v]) return;
        visited[v] = true;

        for (Edge e : edges[v]) {
            if (!visited[e.name]) {
                dist[e.name] = dist[v] + e.value;
                dfs(e.name);
            }
        }
    }
}

class Edge {
    int name;
    int value;

    public Edge(int name, int value) {
        this.name = name;
        this.value = value;
    }
}
