package algorithm;

import java.util.Arrays;

public class BellmanFord {
    public void bellmanFord(int V, int E, int[][] data, int start) {
        BellmanFordNode[] nodes = new BellmanFordNode[E];
        for (int i = 0; i < E; i++) {
            nodes[i] = new BellmanFordNode(data[i][0], data[i][1], data[i][2]);
        }

        int[] dist = new int[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        boolean isMinusCycle = false;
        for (int i = 0; i <= V; i++) {
            for (int j = 0; j < E; j++) {
                BellmanFordNode cur = nodes[j];
                if (dist[cur.from] == Integer.MAX_VALUE) continue;
                if (dist[cur.to] > dist[cur.from] + cur.weight) {
                    dist[cur.to] = dist[cur.from] + cur.weight;
                    if (i == V) {
                        // 업데이트 되었기 때문에 이 조건문에 걸림
                        isMinusCycle = true;
                    }
                }
            }
        }

        System.out.println("음수 사이클? : " + isMinusCycle);
        for (int i = 1; i <= V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.print("INF ");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }
}

class BellmanFordNode {
    int from;
    int to;
    int weight;

    public BellmanFordNode(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}