package boj;

import java.util.Scanner;

/**
 * @date    2023-08-09
 * @title   N-Queen(9663) G4
 * @tags    Back Tracking(백트래킹), BF
 * @input
 * 첫째 줄에 N이 주어진다. (1 ≤ N < 15)
 * @output
 * 첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.
 */
public class BOJ_9663 {

    static int N;
    static int[] qp;
    static int cnt;

    /**
     * <pre>
     *     백트래킹으로 해결할 수 있는 대표적인 문제.
     *     N의 범위가 크지 않아서 BF 방식으로 풀어도 괜찮을 것 같은데, 백트래킹으로 시도함.
     *
     *     N = 8
     *     int qp = new int[N];
     *     N x N 배열을 생성하지 않고, qp(queen position)을 의미하는 1차원 배열을 만든다.
     *     여기에 각 행에 퀸이 몇 번째 자리에 있는지 기록함.
     *
     *     nQueen 함수는 N번 반복 시 종료.
     *     isPromising 함수는 가능한 자리가 있을 때 true 반환.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        qp = new int[N];
        cnt = 0;

        System.out.println(nQueen(0));
    }

    /**
     * N번 반복하며 qp에 현재 Queen의 위치를 기록하고,
     * 현재 탐색하는 위치의 유망성 판단
     * */
    public static int nQueen(int row) {
        if (row == N) return ++cnt;

        for (int i = 0; i < N; i++) {
            System.out.println("row: " + row + ", i: " + i);
            qp[row] = i;

            if (isPromising(row)) {
                nQueen(row + 1);
            }
        }

        return cnt;
    }

    public static boolean isPromising(int row) {
        // 현재 퀸의 위치와 같은 열에 존재하거나, 대각선에 위치하면 false
        for (int i = 0; i < row; i++) {
            if (qp[row] == qp[i] || row - i == Math.abs(qp[row] - qp[i])) {
                return false;
            }
        }

        return true;
    }

}
