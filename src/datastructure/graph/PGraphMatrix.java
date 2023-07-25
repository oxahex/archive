package datastructure.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class PGraphMatrix {
    char[] vertices;
    int[][] adjMatrix;
    int elementCount;

    public PGraphMatrix(int size) {
        this.vertices = new char[size];
        this.adjMatrix = new int[size][size];
        this.elementCount = 0;
    }

    public boolean isFull() {
        return this.elementCount == this.vertices.length;
    }

    public void addVertex(char data) {
        if (isFull()) {
            System.out.println("ERROR: Graph Is Full");
            return;
        }
        this.vertices[this.elementCount++] = data;
    }

    public void addEdge(int x, int y) {
        this.adjMatrix[x][y] = 1;
        this.adjMatrix[y][x] = 1;   // 무방향이므로 양쪽 edge 모두 1
    }

    public void addDirectedEdge(int x, int y) {
        this.adjMatrix[x][y] = 1;   // 방향이라면 하나만 업데이트, 한 쪽 edge 만 1
    }

    public void deleteEdge(int x, int y) {
        this.adjMatrix[x][y] = 0;
        this.adjMatrix[y][x] = 0;   // 무방향이므로 양쪽 edge 모두 0
    }

    public void deleteDirectedEdge(int x, int y) {
        this.adjMatrix[x][y] = 0;   // 방향이라면 하나만 업데이트, 한 쪽 edge 만 0
    }

    public void printAdjacentMatrix() {
        System.out.print("  ");
        for (char item: this.vertices) {
            System.out.print(item + " ");
        }
        System.out.println();
        for (int i = 0; i < this.elementCount; i++) {
            System.out.print(this.vertices[i] + " ");
            for (int j = 0; j < this.elementCount; j++) {
                System.out.print(this.adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void dfs(int id) {
        boolean[] visited = new boolean[this.elementCount];
        Stack<Integer> stack = new Stack<>();

        // 순회 시작 정점부터 stack.push()
        stack.push(id);
        visited[id] = true;

        // 그래프를 돌면서 방문한 이력이 없으면 스택에 순차적으로 넣고 빼고를 반복(stack.isEmpty() == false 인 동안)
        while (!stack.isEmpty()) {
            // 일단 하나 꺼냄
            int currentId = stack.pop();
            System.out.print(this.vertices[currentId] + " ");

            // 인접 정점 정보를 확인: 인접 행렬이므로 반복문 돌며 확인
            for (int i = this.elementCount - 1; i >= 0; i--) {
                // 1이면 간선 있음 && 방문 이력 없음
                if (this.adjMatrix[currentId][i] == 1 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }

    public void bfs(int id) {
        boolean[] visited = new boolean[this.elementCount];
        Queue<Integer> q = new LinkedList<>();

        // 순회 시작 정점부터 queue 에 데이터 삽입
        q.offer(id);
        visited[id] = true;

        // 그래프를 돌면서 방문한 이력이 없으면 큐에 순차적으로 넣고 빼고를 반복(q.isEmpty() == false 인 동안)
        while (!q.isEmpty()) {
            int currentId = q.poll();
            System.out.print(this.vertices[currentId] + " ");

            for (int i = this.elementCount - 1; i >= 0; i--) {
                if (this.adjMatrix[currentId][i] == 1 && !visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        System.out.println();
    }
}
