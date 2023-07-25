package datastructure.graph;

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
}
