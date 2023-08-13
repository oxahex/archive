package boj;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @date    2023-08-13
 * @title   오큰수(17298) G4
 * @tags    Stack, Monotone Stack
 * @input
 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
 * 둘째 줄에 수열 A의 원소 A1, A2, ..., AN (1 ≤ Ai ≤ 1,000,000)이 주어진다.
 * @output
 * 총 N개의 수 NGE(1), NGE(2), ..., NGE(N)을 공백으로 구분해 출력한다.
 */
public class BOJ_17298 {

    /**
     * <pre>
     *     이중 반복문으로 하면 입력값이 1,000,000 이므로 시간초과.
     *     모노톤 스택을 이용하면 FILO 특성 덕분에 정답을 내야 하는 순서를 유예할 수 있다.
     *     바로 다음 큰 값이나 작은 값을 구할 때 사용.
     *
     *     스택에 주어진 수열을 순차적으로 넣을 때,
     *     stack.peek() 값이 현재 비교하고자 하는 값보다 크면 -> 오큰수
     *     stack에 수열 값을 넣으면 index를 알기 어려우니까 stack에 index를 넣음.
     *
     *     수열 Array = S, 정답 Array = A 일 때,
     *      - !stack.isEmpty() && S[stack.peek()] < S[i] -> 반복문 돌며 A[stack.pop()] = S[i] (오큰수를 정답 배열에 저장)
     *      - stack.isEmpty() 인 경우 stack.push(i); -> 최초 1회 하게 된다.
     *      - 전체 반복문을 돌고, !stack.isEmpty 인 경우는 오큰수가 없는 경우 -> -1 저장
     *
     *     주의할 점
     *      - 처음에 Scanner로 입력을 받고, System.out.println() 으로 출력했는데, 시간 초과였다.
     *      - 1,000,000 정도 되는 다량의 입력과 출력을 수행해야 한다면 BufferedReader, BufferedWriter를 쓰는 것이 낫겠다. 반복분을 더 쓰더라도.
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && S[stack.peek()] < S[i]) {
                A[stack.pop()] = S[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            A[stack.pop()] = -1;
        }

        for (int i = 0; i < N; i++) {
            bw.write(A[i] + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
