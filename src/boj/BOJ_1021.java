package boj;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @date    2023-07-13
 * @title   회전하는 큐(1021) S3
 * @input
 * 첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다.
 * N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다.
 * 둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다.
 * 위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
 * @output
 * 첫째 줄에 문제의 정답을 출력한다.
 */
public class BOJ_1021 {

    /**
     * <pre>
     *     찾고자 하는 데이터 마다 Deque 앞에서 뺄 것인지 뒤에서 뺄 것인지를 판단하는 것이 문제 핵심.
     *     2번 또는 3번 연산 중 하나를 수행하기 전에 어떤 연산이 나을지 판단하고 최적의 연산만 수행하도록 하는 것이 고민되는 부분.
     *
     *     내가 푼 방식
     *     1. 2번 연산을 무조건 수행
     *     2. 2번 연산의 결과에 따라 3번 연산의 횟수 도출(3번 연산은 어떤 경우에도 실행하지 않음)
     *
     *     Q.
     *     1 또는 2 연산 전에 최적의 연산을 선택할 수 있도록 하는 방법은 결국
     *     front or rear 부터 찾고자 하는 index 까지 순차조회 하는 방법 밖에 없나?
     *     A.
     *     LinkedList의 경우 조회 자체가 큰 연산을 요구하지 않으므로 front, 또는 rear 부터 조회해도 무방.
     *     값을 찾는 것은 결국 선형 탐색을 해야 하기 때문에, 어느 한 쪽에서 시작하는 수 밖에 없겠다.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        Deque<Integer> deque = new LinkedList<>();
        for (int i =0; i < n; i++) {
            deque.add(i + 1);
        }

        int opCount = 0;
        int dequeMinSize = n - m;

        for (int target : targets) {
            int op = 0;

            while (deque.size() > dequeMinSize) {
                if (deque.element() == target) {
                    deque.poll();
                    break;
                } else {
                    deque.addLast(deque.getFirst());
                    deque.removeFirst();
                    op++;
                }
            }
            opCount += Math.min(op, deque.size() + 1 - op);
        }

        System.out.println(opCount);
    }
}
