package datastructure.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class PGraphNode {
    int id;
    PGraphNode next;

    public PGraphNode(int id, PGraphNode next) {
        this.id = id;
        this.next = next;
    }
}

public class PGraphList {
    char[] vertices;
    PGraphNode[] adjList;
    int elementCount;

    public PGraphList(int size) {
        this.vertices = new char[size];
        this.adjList = new PGraphNode[size];
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
        this.vertices[elementCount++] = data;
    }

    public void addEdge(int x, int y) {
        // 리스트 연결 시 현재 연결되어 있는 노드를 전체 순회해서 뒤에 붙이는 것이 아니라
        // 앞에 연결한 다음 next node에 기존 첫번째 노드를 연결하는 것임
        // 노드 개수만큼 순회하는 시간 save
        this.adjList[x] = new PGraphNode(y, this.adjList[x]);
        this.adjList[y] = new PGraphNode(x, this.adjList[y]);
    }

    public void addDirectedEdge(int x, int y) {
        this.adjList[x] = new PGraphNode(y, this.adjList[x]);
    }

    public void printAdjacentList() {
        for (int i = 0; i < this.elementCount; i++) {
            System.out.print(this.vertices[i] + ": ");

            PGraphNode cur = this.adjList[i];
            while (cur != null) {
                System.out.print(this.vertices[cur.id] + " ");
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public void dfs(int id) {
        boolean[] visited = new boolean[this.elementCount];
        Stack<Integer> stack = new Stack<>();

        stack.push(id);
        visited[id] = true;

        while (!stack.isEmpty()) {
            int currentId = stack.pop();
            System.out.print(this.vertices[currentId] + " ");

            PGraphNode currentNode = this.adjList[currentId];
            while (currentNode != null) {
                if (!visited[currentNode.id]) {
                    stack.push(currentNode.id);
                    visited[currentNode.id] = true;
                }
                currentNode = currentNode.next;
            }
        }
    }

    public void bfs(int id) {
        boolean[] visited = new boolean[this.elementCount];
        Queue<Integer> q = new LinkedList<>();

        q.offer(id);
        visited[id] = true;

        while (!q.isEmpty()) {
            int currentId = q.poll();
            System.out.print(this.vertices[currentId] + " ");

            PGraphNode currentNode = this.adjList[currentId];
            while (currentNode != null) {
                if (!visited[currentNode.id]) {
                    q.offer(currentNode.id);
                    visited[currentNode.id] = true;
                }
                currentNode = currentNode.next;
            }
        }
    }
}
