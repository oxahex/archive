package boj.silver;

import java.io.*;
import java.util.*;

/**
 * @date    2023-07-25
 * @title   DFS와 BFS(1260) S2
 * @tags    Graph(그래프), DFS, BFS
 * @input
 * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
 * 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
 * 입력으로 주어지는 간선은 양방향이다.
 * @output
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다.
 * V부터 방문된 점을 순서대로 출력하면 된다.
 */
public class BOJ_1260 {

    /**
     * <pre>
     *     문제 자체는 DFS, BFS 구현 방식만 알면 금방 풀 수 있는데
     *     DFS 탐색 순서에 대해 새로 알게 된 사실이 있어서 기록함.
     *
     *     DFS 시 스택을 이용하지 않은 이유
     *     방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문해야 한다는 조건을 만족하려면
     *     Stack에 인접 정점을 작은 순, 큰 순으로 쌓은 다음 pop 하는 것이 아니라, 재귀로 작성해서 Edge가 있는 경우 끝까지 내려가도록 해야 가능하다고 생각.
     *     FILO 특성을 이용하기 때문에 인접한 정점이 있으면 무조건 스택에 한 번 들어가고 1번 예시와 같은 경우 2 -> 4 방향으로 탐색할 수 없다고 생각함.
     *
     *     하지만 스택을 이용하면서도 재귀와 같은 결과값을 출력하도록 할 수 있음.
     *     인접한 정점이고, 방문하지 않았을 때 stack.push() 하면서 visited를 true로 바꾸지 않고,
     *     Stack에서 노드를 뽑을 때 visited를 true로 바꾸면 재귀와 동일한 순서로 탐색이 가능하다.
     *
     *     stack.clear();
     *     그러나 Stack 구현 시 stack.pop() 할 때마다 stack.clear()를 해주지 않으면
     *     Stack에 들어간 정점이 중복되고, 중복 탐색을 하게 된다.
     * </pre>
     * */
    static int N, M, V;
    static int[][] edges;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        // 간선 정보 입력 - 인접 행렬
        edges = new int[N][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            edges[x][y] = 1;
            edges[y][x] = 1;
        }

        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }

        // DFS: 재귀
        visited = new boolean[N];
//        dfs(V - 1);

        // DFS: Stack
        Stack<Integer> stack = new Stack<>();
        stack.push(V - 1);

        while (!stack.isEmpty()) {
            int currentVtx = stack.pop();
            visited[currentVtx] = true;
            System.out.print((currentVtx + 1) + " ");

            stack.clear();
            for (int i = 0; i < N; i++) {
                if (edges[currentVtx][i] == 1 && !visited[currentVtx]) {
                    stack.push(i);
                }
            }
        }

        System.out.println();

        // BFS: 굳이 재귀로 구현하지 않아도 순서 잘 나올 것 같음.
        Arrays.fill(visited, false);

        Queue<Integer> q = new LinkedList<>();
        q.offer(V - 1);
        visited[V - 1] = true;

        while (!q.isEmpty()) {
            int currentVtx = q.poll();
            System.out.print((currentVtx + 1) + " ");

            for (int i = 0; i < N; i++) {
                if (edges[currentVtx][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void dfs(int v) {
        visited[v] = true;
        System.out.print((v + 1) + " ");

        for (int i = 0; i < N; i++) {
            if (edges[v][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }
}
