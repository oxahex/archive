package boj;

import java.util.Scanner;

/**
 * @date    2023-07-14
 * @title   해시 해킹(26008)
 * @input
 * 첫째 줄에 비밀번호의 길이(M), 문자 종류의 개수(N), 정수(A)가 주어진다.(1 <= M, N, A <= 5 000 000)
 * 둘째 줄에 재현이가 알아낸 해시값 정수 H가 주어진다. (0 <= H < M)
 * @output
 * 주어진 해시값을 갖는 비밀번호의 개수를 출력한다. 출력하는 값이 너무 커질 수 있으므로,
 * 이것을 1,000,000,007 (= 10^9 + 7)로 나눈 나머지를 출력한다.
 */
public class BOJ26008 {
    /**
     * @note
     * 1. 해시값이 몇 번 충돌하는가를 묻는 문제.
     * 2. mod M 이므로 가능한 해시값의 범위는 0 < 해시값 <= M-1
     * 3. 가능한 비밀번호의 가짓수는 M^N
     * 4. 나올 수 있는 해시값의 개수(해시 테이블 사이즈)는 M
     * 5. 각 해시값 당 충돌이 일어날 확률은 M^(N-1)
     * @question
     * 해시값 충돌이 일어날 확률일 뿐 이것을 실제로 겹치는 경우의 수라고 말할 수 있는지?
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int h = sc.nextInt();

        String v = "";
        long answer = 1;
        for (int i = 0; i < n - 1; i++) {
            answer = (answer * m) % 1000000007;
        }

        System.out.println(answer);
    }
}



