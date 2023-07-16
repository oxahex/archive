package boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @date    2023-07-14
 * @title   요세푸스 문제(1158) S4
 * @input
 * 첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ K ≤ N ≤ 5,000)
 * @output
 * 요세푸스 순열을 출력한다.
 * <3, 6, 2, 7, 5, 1, 4>
 */
public class BOJ_1158 {

    /**
     * <pre>
     *     Queue 이용해서 구현하면 어려운 문제는 아니라고 생각함.
     *     k 번째 마다 큐에서 remove -> 정답 배열에 추가하거나, remove -> 다시 add 처리하면 되는...
     *     LinkedList 자체를 구현해서 풀어보고 싶다.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            q.add(i);
        }

        ArrayList<Integer> answer = new ArrayList<>();
        while (q.size() > 0) {
            for (int i = 0; i < k; i++) {
                if (i == k - 1) {
                    answer.add(q.remove());
                } else {
                    q.add(q.remove());
                }
            }
        }

        System.out.println(
                answer.toString()
                        .replace("[", "<")
                        .replace("]", ">")
        );
    }
}
