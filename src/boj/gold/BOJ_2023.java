package boj.gold;

import java.util.Scanner;

/**
 * @date    2023-08-16
 * @title   신기한 소수(2023) G5
 * @tags    수학, 정수론, Back Tracking(백트래킹), 소수 판정
 * @input
 * 첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.
 * @output
 * N자리 수 중에서 신기한 소수를 오름차순으로 정렬해서 한 줄에 하나씩 출력한다.
 */
public class BOJ_2023 {

    /**
     * <pre>
     *     N자리의 모든 수를 하나씩 탐색해서는 2초 안에 문제를 해결할 수 없음.
     *     8자리 수면 0 ~ 99,999,999 이므로 입력값이 충분히 크고, 입력값이 작더라도 소수 판정을 위한 계산에서 시간 소요.
     *
     *     예시 케이스에서 7331 과 같이 7, 73, 733, 7331 앞에서부터 자릿수를 하나씩 늘렸을 때 모두 소수면 조건을 만족.
     *     모든 케이스를 찾지 않고 1 ~ 10 사이 소수인 2, 3, 5, 7 로 시작하는 숫자를 주어진 자릿수만큼 탐색하면 된다고 생각.
     *
     *     2 -> 1, 3, 5, 7, 9 가 올 수 있음.
     *     21, 25, 27 은 소수가 아니므로 제외하고 2 -> 3, 2 -> 9 의 경우만 그 다음 자릿수를 똑같은 방식으로 붙여서 소수인지 확인
     *     다음 자릿수에 짝수는 제외 -> 어차피 2로 나누어지므로 소수가 아님.
     *
     *     따라서 재귀 함수 dfs 의 종료 조건은 자릿수가 N과 같을 때.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        dfs(2, 1, N);
        dfs(3, 1, N);
        dfs(5, 1, N);
        dfs(7, 1, N);
    }

    public static void dfs(int n, int digit, int N) {
        if (digit >= N) {
            System.out.println(n);
            return;
        }

        for (int i = 1; i < 10; i++) {
            int num = n * 10 + i;
            if (isPrime(num)) {
                dfs(num, digit + 1, N);
            }
        }
    }

    public static boolean isPrime(int n) {
        int div = 2;
        while (div < n / 2) {
            if (n % div == 0) return false;
            div++;
        }

        return true;
    }
}
