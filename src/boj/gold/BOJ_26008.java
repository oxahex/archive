package boj.gold;

import java.util.Scanner;

/**
 * @date    2023-07-14
 * @title   해시 해킹(26008) G4
 * @tags    수학, 정수론
 * @input
 * 첫째 줄에 비밀번호의 길이(M), 문자 종류의 개수(N), 정수(A)가 주어진다.(1 <= M, N, A <= 5 000 000)
 * 둘째 줄에 재현이가 알아낸 해시값 정수 H가 주어진다. (0 <= H < M)
 * @output
 * 주어진 해시값을 갖는 비밀번호의 개수를 출력한다. 출력하는 값이 너무 커질 수 있으므로,
 * 이것을 1,000,000,007 (= 10^9 + 7)로 나눈 나머지를 출력한다.
 */
public class BOJ_26008 {
    /**
     * <pre>
     *     1. 해시값이 몇 번 충돌하는가를 묻는 문제.
     *     2. mod M 이므로 가능한 해시값의 범위는 0 < 해시값 <= M-1
     *     3. 가능한 비밀번호의 가짓수는 M^N
     *     4. 나올 수 있는 해시값의 개수(해시 테이블 사이즈)는 M
     *     5. 각 해시값 당 충돌이 일어날 확률은 M^(N-1)
     *
     *     Q    해시값 충돌이 일어날 확률일 뿐 이것을 실제로 겹치는 경우의 수라고 말할 수 있는지?
     *     A    h(P) = [ P0*A0 + P1*A1 +, ..., + P(n-1)*A(n-1) ] mod M
     *          처음 P0 * A0 부분이 [0, M-1] 순서쌍이 하나씩 오는 케이스이고,
     *          나머지 영역은 나머지 M-1개의 경우의 수이다.
     *          P0가 나머지 P 집합에 영향을 주지 않음.
     *          따라서 첫 번째 부분을 제외한 나머지 영역 조합과 관계 없음.
     *          P0 * A0 == P0, A의 지수가 0이므로.
     *          P0는 0 부터 M-1 까지의 수 -> 서로 다른 M개의 경우의 수
     *          P0가 뭐건 나머지는 항상 동일한 개수와 확률의 사건이 나오게 된다.
     *          P0 * A0 부분을 제외한 나머지 케이스의 경우의 수는 M^(N-1)
     *          따라서 P0에 오는 각각의 경우의 수마다 나머지 경우의 수 M^(N-1)이 발생함.
     *          즉, 각각의 케이스가 M^(N-1)번 균일하게 충돌한다.
     * </pre>
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();
        int h = sc.nextInt();

        long answer = 1;
        for (int i = 0; i < n - 1; i++) {
            answer = (answer * m) % 1000000007;
        }

        System.out.println(answer);
    }
}


