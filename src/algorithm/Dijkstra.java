package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {

    // 우선순위 큐를 이용하면 가중치가 작은 순서대로 가져올 수 있으므로 방문 배열이 필요 없음
    public void withPQ(int v, int[][] data, int start) {
        // 그래프 그림
        ArrayList<ArrayList<DijkstraNode>> graph = new ArrayList<>();

        // 정점 수만큼 반복, graph ArrayList 초기화
        // 1 증가시키고 0은 쓰지 않는 형태(편의)
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());   // ArrayList 생성
        }

        // 데이터 삽입
        for (int i = 0; i < data.length; i++) {
            // data[i][0]: 출발 정점
            // data[i][1]: 도착 정점
            // data[i][2]: 가중치
            graph.get(data[i][0]).add(new DijkstraNode(data[i][1], data[i][2]));
        }

        // DP 용 메모리 선언
        int[] dist = new int[v + 1];

        // DP 용 메모리 MAX_VALUE로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작 노드 dist는 0임
        dist[start] = 0;

        // 방문배열은 필요 없음

        // PQ 선언, pq에 들어간 노드 중 가중치가 작은 것 순으로 나오도록 정렬(Min Heap)
        // PriorityQueue<DijkstraNode> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.weight));
        PriorityQueue<DijkstraNode> pq = new PriorityQueue<>((x, y) -> x.weight - y.weight);
        pq.offer(new DijkstraNode(start, 0));

        while (!pq.isEmpty()) {
            DijkstraNode curNode = pq.poll();

            if (dist[curNode.to] < curNode.weight) continue;    // 작으면 업데이트할 필요가 없음

            // 인접 노드 탐색
            for (int i = 0; i < graph.get(curNode.to).size(); i++) {
                DijkstraNode node = graph.get(curNode.to).get(i);

                if (dist[node.to] > curNode.weight + node.weight) {
                    dist[node.to] = curNode.weight + node.weight;
                    pq.offer(new DijkstraNode(node.to, dist[node.to]));
                }
            }
        }

        // 출력
        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("연결되어 있지 않음");
            } else {
                System.out.print(dist[i] + " ");
            }
        }

    }

    public void basic(int v, int[][] data, int start) {
        // 그래프 그림
        ArrayList<ArrayList<DijkstraNode>> graph = new ArrayList<>();

        // 정점 수만큼 반복, graph ArrayList 초기화
        // 1 증가시키고 0은 쓰지 않는 형태(편의)
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());   // ArrayList 생성
        }

        // 데이터 삽입
        for (int i = 0; i < data.length; i++) {
            // data[i][0]: 출발 정점
            // data[i][1]: 도착 정점
            // data[i][2]: 가중치
            graph.get(data[i][0]).add(new DijkstraNode(data[i][1], data[i][2]));
        }

        // DP 용 메모리 선언
        int[] dist = new int[v + 1];

        // DP 용 메모리 MAX_VALUE로 초기화
        Arrays.fill(dist, Integer.MAX_VALUE);

        // 시작 노드 dist는 0임
        dist[start] = 0;

        // 방문 배열 선언
        boolean[] visited = new boolean[v + 1];

        // 모든 노드를 돌면서 거리 정보 업데이트
        for (int i = 0; i < v; i++) {           // v 번
            int minDist = Integer.MAX_VALUE;    // 그 다음 노드를 선택하기 위해 선언
            int curIdx = 0;

            // 다음 노드 탐색: 방문 이력이 없고, 가장 거리가 짧은 노드 선택
            for (int j = 1; j < v + 1; j++) {
                if (!visited[j] && dist[j] < minDist) {
                    minDist = dist[j];
                    curIdx = j;
                }
            }

            // curIdx(선택된 다음 노드) 방문 처리
            visited[curIdx] = true;

            // 선택된 노드의 인접노드를 순회하면서 거리 갱신
            for (int j = 0; j < graph.get(curIdx).size(); j++) {
                DijkstraNode node = graph.get(curIdx).get(j);   // 인접 노드를 하나씩 가져옴

                // 거리 정보 업데이트
                // : 원래 거리 정보 배열에 들어있던 값보다(이전 값)
                //   현재까지의 거리 + 인접한 노드와의 거리(새 값)를 비교
                //   새 값이 더 작으면 업데이트
                if (dist[node.to] > dist[curIdx] + node.weight) {
                    dist[node.to] = dist[curIdx] + node.weight;
                }
            }
        }

        // 출력
        for (int i = 1; i < v + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("연결되어 있지 않음");
            } else {
                System.out.print(dist[i] + " ");
            }
        }
    }
}

class DijkstraNode {
    int to;
    int weight;

    public DijkstraNode(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}