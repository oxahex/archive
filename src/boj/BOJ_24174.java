package boj;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @date    2023-07-15
 * @title   알고리즘 수업 - 힙 정렬 2(24174) S5
 * @input
 * 첫째 줄에 배열 A의 크기 N(5 ≤ N ≤ 500,000), 교환 횟수 K(1 ≤ K ≤ 10^8)가 주어진다.
 * 다음 줄에 서로 다른 배열 A의 원소 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 10^9)
 * @output
 * 배열 A에 K 번 교환이 발생한 직후의 배열 A를 한 줄에 출력한다. 교환 횟수가 K 보다 작으면 -1을 출력한다.
 */
public class BOJ_24174 {
    static int cnt = 0;
    static int k;
    static StringBuilder captured = new StringBuilder();

    /**
     * @note
     * <pre>
     *     주어진 의사 코드를 해석하는 것이 익숙하지 않아서 그런지 index가 헷갈렸고
     *     지금 구현한 것과 같이 완전히 정렬한 상태에서 교환 횟수를 비교하는 것이 아니라
     *     주어진 교환 최대 횟수 k를 만족할 때 코드를 종료,
     *     그 시점의 배열을 출력할 수 있다면 좋을 것 같아서 따로 기록해 둔다.
     *      - Exception을 이용하는 방법?
     *
     *     또 main 함수 상단에 계속해서 static으로 선언하는 것이 조금 신경 쓰이는데,
     *     왜 신경 쓰이는지, 이게 별로면 어떤 게 나은지 등 좀 더 추가적으로 확인해야 할 것 같다.
     *     리팩토링이 필요한 답안.
     * </pre>
     * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }

        for (int i = n / 2; i > 0; i--) {
            heapify(list, i, n);
        }

        for (int i = n; i > 1; i--) {
            swap(list, 1, i);
            heapify(list, 1, i - 1);
        }

        if (cnt < k) {
            System.out.println(-1);
        } else {
            System.out.println(captured);
        }
    }

    public static void heapify(ArrayList<Integer> list, int k, int n) {
        int left = k * 2;
        int right = k * 2 + 1;
        int smaller = -1;

        if (right < n + 1) {
            smaller = list.get(left) < list.get(right) ? left : right;
        } else if (left < n + 1) {
            smaller = left;
        } else {
            return;
        }

        if (list.get(smaller) < list.get(k)) {
            swap(list, k, smaller);
            heapify(list, smaller, n);
        }
    }

    public static void swap(ArrayList<Integer> list, int x, int y) {
        cnt++;
        int tmp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, tmp);

        if (cnt == k) {
            for (int i = 1; i < list.size(); i++) {
                captured.append(list.get(i)).append(" ");
            }
        }
    }
}
