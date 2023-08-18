package boj.silver;

import java.util.Scanner;

/**
 * @date    2023-07-25
 * @title   1(4375) S3
 * @tags    수학, BF(브루트포스), 정수론
 * @input
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * 각 테스트 케이스는 한 줄로 이루어져 있고, n이 주어진다.
 * @output
 * 각 자릿수가 모두 1로만 이루어진 n의 배수 중 가장 작은 수의 자리수를 출력한다.
 */
public class BOJ_4375 {

    /**
     * <pre>
     *     1, 11, 111, 1111, 11111, 111111, ...
     *
     *     1    %   7   =   1
     *     11   %   7   =   4
     *     111  %   7   =   (11 * 10 + 1) % 7
     *                  =   ((11 % 7) * 10 + 1) % 7
     *                  =   (4 * 10 + 1) % 7
     *                  =   41 % 7
     *                  =   6
     *     1111 %   7   =   (111 * 10 + 1) % 7
     *                  =   ((111 % 7) * 10 + 1) % 7
     *                  =   (6 * 10 + 1) % 7
     *                  =   61 % 7
     *                  =   5
     *     ------------------------------------------
     *     따라서 1부터 순차적으로 자릿수를 증가해서 나머지 연산을 함.
     *     mod = (mod * 10 + 1) % n
     *     mod == 0 인 경우, 나누어 떨어지는 것이므로 그 시점의 자릿수를 출력.
     *
     *     나머지 연산의 법칙이 존재함.
     *     이걸 모르면 BigInteger 사용해서 문제를 해결해야 함.
     *
     *      (A + B) % p = ((A % p) + (B % p)) % p
     *      (A * B) % p = ((A % p) * (B % p)) % p
     *      (A - B) % p = ((A % p) - (B % p) + p) % p
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int mod = 0;

            for (int i = 1; ; i++) {
                mod = (mod * 10 + 1) % n;
                if (mod == 0) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }
}
