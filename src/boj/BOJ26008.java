package boj;

import java.util.Scanner;

/**
 * @date    2023-07-14
 * @title   해시 해킹(26008)
 */
public class BOJ26008 {
    /**
     * @input
     * 첫째 줄에 비밀번호의 길이(M), 문자 종류의 개수(N), 정수(A)가 주어진다. (1 <= M, N, A <= 5 000 000)
     * 둘째 줄에 재현이가 알아낸 해시값 정수 H가 주어진다. (0 <= H < M)
     * @output
     * 주어진 해시값을 갖는 비밀번호의 개수를 출력한다. 출력하는 값이 너무 커질 수 있으므로,
     * 이것을 1,000,000,007 (= 10^9 + 7)로 나눈 나머지를 출력한다.
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
