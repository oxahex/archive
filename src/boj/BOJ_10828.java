package boj;

import java.io.*;

/**
 * @date    2023-07-15
 * @title   스택(10828) S4
 * @tags    구현, 자료구조, Stack(스택)
 * @input
 * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
 * 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
 * @output
 * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
 */
class Stack_10828 {
    private int top;
    private int[] stack;

    public Stack_10828(int size) {
        this.stack = new int[size];
        this.top = -1;
    }

    public void push(int n) {;
        this.stack[++this.top] = n;
    }

    public int pop() {
        if (top == -1) return -1;
        return this.stack[this.top--];
    }

    public int size() {
        return this.top + 1;
    }

    public int empty() {
        if (this.top == -1) return 1;
        return 0;
    }

    public int top() {
        if (this.top == -1) return -1;
        return this.stack[this.top];
    }
}
public class BOJ_10828 {

    /**
     * <pre>
     *     스택을 구현하는 문제이고 어려운 문제는 아니었으나
     *     Scanner 사용 시 시간 초과
     *     버퍼를 열고, 닫을 때 .close() 처리 해주는 것 때문에 기록
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());

        Stack_10828 pStack = new Stack_10828(n);

        for (int i = 0; i < n; i++) {
            String op = br.readLine();

            if (op.contains("push")) {
                int v = Integer.parseInt(op.split(" ")[1]);
                pStack.push(v);
            } else if (op.contains("pop")) {
                bw.write(pStack.pop() + "\n");
            } else if (op.contains("size")) {
                bw.write(pStack.size() + "\n");
            } else if (op.contains("empty")) {
                bw.write(pStack.empty() + "\n");
            } else if (op.contains("top")) {
                bw.write(pStack.top() + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
