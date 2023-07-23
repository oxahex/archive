package boj;

import java.io.*;
import java.util.Stack;

/**
 * @date    2023-07-17
 * @title   괄호(9012) S4
 * @tags    문자열, Stack(스택), 자료구조
 * @input
 * 입력 데이터는 표준 입력을 사용한다.
 * 입력은 T개의 테스트 데이터로 주어진다.
 * 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다.
 * 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다.
 * 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.
 * @output
 * 출력은 표준 출력을 사용한다.
 * 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”,
 * 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.
 */
public class BOJ_9012 {

    /**
     * <pre>
     *     접근
     *      - '('이 주어지면 반드시 그 개수만큼의 ')'가 문자열이 끝나기 전에 나와야 한다.
     *      - '('이 주어질 때마다 값을 Stack에 넣고, ')'가 주어질 때마다 값을 Stack에서 뺀다.
     *      - 주어진 문자열의 길이만큼 반복하는 동안 ')'이 나와 Stack 접근 시, Stack이 비어있으면 VPS가 아니다.
     *      - 탐색이 끝난 이후에 Stack.size()가 0인 경우에만 VPS다.
     *
     *     주의
     *      - 문자열 전체를 순회하고, 마지막에 개수를 확인하러 하면 안된다.
     *      - 그냥 숫자로 카운트 하든 Stack.size()를 확인하든
     *        중간에 cnt가 -1이 되는 순간 내부 반복 종료 -> bw 버퍼에 'NO' 입력해야 함.
     *
     *     solution 1:
     *      Stack 없이 구현.
     *      굳이 그렇게 한 이유는 어차피 Stack에 '(' or ')'를 넣고 계속해서 빼줘야 한다면
     *      ')'가 연속적으로 나올 수 있는 최대 개수에 limit을 거는 식으로 해도 무방하지 않나, 하는 생각으로.
     *
     *     solution 2:
     *      Stack 자료구조 사용해 구현.
     *      오히려 Stack을 이용하면 최초에 dummy data를 넣어 주어야 empty 상태의 의미를 구분할 수 있어서 애매한 것 같음.
     *      Stack을 사용하되, 더 효율적으로 사용할 수 있는 방법은 없을지?
     *      solution 1 방법을 택한다고 해서 속도가 더 느리지는 않음.
     * </pre>
     */

    public void solution1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String ps = br.readLine();

            int cnt = 0;
            for (int j = 0; j < ps.length(); j++) {
                System.out.println("inner: " + ps.charAt(j));
                if (ps.charAt(j) == '(') {
                    cnt++;
                } else {
                    cnt--;
                    if (cnt < 0) break;
                }
            }

            System.out.println(cnt);
            if (cnt == 0) {
                bw.write("YES" + "\n");
            } else {
                bw.write("NO" + "\n");
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }

    public void solution2() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        stack.push('0');    // dummy data
        for (int i = 0; i < t; i++) {
            String ps = br.readLine();

            int idx = 0;
            while (idx < ps.length() && !stack.empty()) {
                if (ps.charAt(idx) == '(') {
                    stack.push(ps.charAt(idx));
                } else {
                    stack.pop();
                }
                idx++;
            }

            if (stack.size() == 1) {
                bw.write("YES" + "\n");
            } else {
                bw.write("NO" + "\n");
            }
            stack.clear();
            stack.push('0');
        }

        bw.flush();
        br.close();
        bw.close();
    }
}