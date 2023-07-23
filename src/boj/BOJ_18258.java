package boj;

import java.io.*;

/**
 * @date    2023-07-16
 * @title   큐 2(18258) S4
 * @tags    자료구조, Queue(큐)
 * @input
 * 첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 2,000,000)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다.
 * 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다.
 * 문제에 나와있지 않은 명령이 주어지는 경우는 없다.
 * @output
 * 출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
 */
class Queue_10828 {
    private int[] queue;
    private int front;
    private int back;

    public Queue_10828(int size) {
        this.queue = new int[size];
        this.front = -1;
        this.back = -1;
    }

    public void push(int n) {
        this.queue[++this.back] = n;
        if (front == -1) front++;
    }

    public int pop() {
        if (front > back || front == -1 || back == -1) return -1;
        int data = this.queue[front];
        this.front++;
        return data;
    }

    public int size() {
        if (front > back || front == -1 || back == -1) return 0;
        return back - front + 1;
    }

    public int empty() {
        if (front > back || front == -1 || back == -1) return 1;
        return 0;
    }

    public int front() {
        if (front > back || front == -1 || back == -1) return -1;
        return this.queue[front];
    }

    public int back() {
        if (front > back || front == -1 || back == -1) return -1;
        return this.queue[back];
    }
}

public class BOJ_18258 {

    /**
     * <pre>
     *     LinkedList를 이용해서 구현하는 방법도 있음.
     *     그래도 입력해야 하는 명령어의 개수가 주어지므로 필요한 공간의 최대 크기를 알 수 있어서 배열로 구현했다.
     *
     *     front, back 값을 조정하는 것이 구현의 핵심.
     *     초기값은 모두 -1
     *     push ->  arr[++back] 에 값을 추가
     *              최초에 값을 넣는 경우에만 front++ -> 0 으로 만들어 놓음.  // 여기가 조금 부정확...
     *              그 외에는 pop 명령 시에만 front++
     *     pop ->   front++
     *              back 값은 그대로 둔다.
     *     front > back 을 만족하는 경우 값이 없음     // 이걸 이용하면 front 초기값을 -1로 하지 않아도 될 것 같음.
     *     front == back 을 만족하는 경우 값이 1개
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Queue_10828 pQueue = new Queue_10828(n);

        for (int i = 0; i < n; i++) {
            String op = br.readLine();

            if (op.contains("push")) {
                int val = Integer.parseInt(op.split(" ")[1]);
                pQueue.push(val);
            } else if (op.contains("pop")) {
                bw.write(pQueue.pop() + "\n");
            } else if (op.contains("size")) {
                bw.write(pQueue.size() + "\n");
            } else if (op.contains("empty")) {
                bw.write(pQueue.empty() + "\n");
            } else if (op.contains("front")) {
                bw.write(pQueue.front() + "\n");
            } else if (op.contains("back")) {
                bw.write(pQueue.back() + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
