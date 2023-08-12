package boj;

import java.io.*;
import java.util.*;

/**
 * @date    2023-08-12
 * @title   최솟값 찾기(11003) P5
 * @tags    Deque, Priority Queue, Sliding Window
 * @input
 * 첫째 줄에 N과 L이 주어진다. (1 ≤ L ≤ N ≤ 5,000,000)
 * 둘째 줄에는 N개의 수 Ai가 주어진다. (-10^9 ≤ Ai ≤ 10^9)
 * @output
 * 첫째 줄에 Di를 공백으로 구분하여 순서대로 출력한다.
 */
public class BOJ_11003 {

    /**
     * <pre>
     *     Di = Ai-L+1 ~ Ai, N = 12, L = 3
     *     D0 = A0-3+1 ~ A0     -> A0 ~ A0
     *     D1 = A1-3+1 ~ A1     -> A0 ~ A1
     *     D2 = A2-3+1 ~ A2     -> A0 ~ A2
     *     D3 = A3-3+1 ~ A3     -> A1 ~ A3
     *     D4 = A4-3+1 ~ A4     -> A2 ~ A4
     *     D5 = A5-3+1 ~ A5     -> A3 ~ A5
     *     .
     *     .
     *     .
     *     D12 = A12-3+1 ~ A12  -> A10 ~ A12
     *
     *     결국 L 만큼의 정해진 구간 안의 최솟값을 차례로 출력하는 문제.
     *     다만 입력값 범위가 5,00,000 이므로 일반적인 정렬 알고리즘을 사용하면 시간 초과 nlog(n) 이라서.
     *     따라서 O(n)의 시간 복잡도로 해결해야 함.
     *     D0 - Dn 까지 뭔가 탐색을 할 때마다 범위를 매번 전체 정렬하지 않고, 윈도우 내부 값을 일정 규칙에 따라 지워주면 시간 복잡도가 줄어들 것으로 생각.
     *
     *     1. 어차피 L 만큼의 범위 안 숫자만 비교할 것임.
     *     2. 값이 윈도우 안으로 들어왔을 때 윈도우 내부에 있는 값을 순차 비교, 들어온 값이 더 작으면 기존 값을 지움. 크면 그냥 둔다.
     *     3. 내부 값의 개수가 L 초과시 맨 앞 값을 지워야 함.
     *
     *     Deque로 구현하면 슬라이딩 도어처럼 구현이 쉬움.
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> dq = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());

            while (!dq.isEmpty() && dq.getLast().value > cur) {
                dq.removeLast();
            }
            dq.addLast(new Node(i, cur));
            if (dq.getFirst().index <= i - L) {
                dq.removeFirst();
            }

            bw.write(dq.getFirst().value + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}

class Node {
    int index;
    int value;

    public Node(int index, int value) {
        this.index = index;
        this.value = value;
    }
}