package boj.gold;

import java.io.*;
import java.util.*;

/**
 * @date    2023-08-19
 * @title   수 묶기(1744) G4
 * @tags    Greedy
 * @input
 * 첫째 줄에 수열의 크기 N이 주어진다. N은 50보다 작은 자연수이다.
 * 둘째 줄부터 N개의 줄에 수열의 각 수가 주어진다.
 * 수열의 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
 * @output
 * 수를 합이 최대가 나오게 묶었을 때 합을 출력한다. 정답은 항상 2^31보다 작다.
 */
public class BOJ_1744 {

    /**
     * <pre>
     *     조건 분기에 신경써야 하는 문제. 여기서 양수는 2 이상의 숫자를 의미한다.
     *     음수 * 음수, 음수 * 양수, 음수 * 0, 양수 * 1, 양수 + 1 을 했을 때 어떤 결과가 나오는지 생각해야 한다.
     *
     *      - 음수 * 음수 = 양수 이므로 수열에 음수가 2개 이상 있다면 곱해야 한다.
     *      - 음수 * 양수 = 음수 이므로 이 둘을 곱하면 안 된다.
     *      - 음수 * 0 = 0 이므로 수열에 음수가 홀수 개일 때, 마지막 남은 음수와 0 을 곱해야 한다.
     *      - 양수 * 1 < 양수 + 1 이므로 수열에 2 이상의 양수와 1이 남아 있는 경우 곱하지 않고 더해야 한다.
     *
     *     음수와 0은 PriorityQueue m 에 오름차순으로 저장 -> m.poll() 시 작은 수 부터 나오도록 한다.
     *     2 이상의 양수는 PriorityQueue p 에 내림차순으로 저장 -> p.poll() 시 큰 수 부터 나오도록 한다.
     *     1은 따로 저장하지 않고 숫자를 카운트 한다.
     *
     *     m과 p는 각각 두 개씩 곱해서 sum 에 저장했다.
     *     음수와 0을 함께 저장한 이유는 음수 * 0 = 0 이므로 굳이 0 개수를 따로 카운트 해서 남아있는 음수와 곱하지 않아도 무방하므로.
     *     값이 1개 남는 경우 sum 에 해당 값을 더한다. m, p 는 모두 비어있는 Queue 가 된다.
     *     1의 경우는 양수 * 1 보다 양수 + 1 을 했을 때 이득이므로, 따로 카운트 했다가 결과에 더했다.
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> m = new PriorityQueue<>();
        PriorityQueue<Integer> p = new PriorityQueue<>(Collections.reverseOrder());
        int one = 0;
        for (int i = N - 1; i >= 0; i--) {
            int n = Integer.parseInt(br.readLine());
            if (n <= 0) m.offer(n);
            if (n > 1) p.offer(n);
            if (n == 1) one++;
        }

        int sum = 0;
        while (m.size() > 1) {
            sum += m.poll() * m.poll();
        }

        if (!m.isEmpty()) sum += m.poll();

        while (p.size() > 1) {
            sum += p.poll() * p.poll();
        }

        if (!p.isEmpty()) sum += p.poll();

        System.out.println(sum + one);
    }
}
