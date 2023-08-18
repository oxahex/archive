package boj.gold;

import java.io.*;
import java.util.*;

/**
 * @date    2023-07-22
 * @title   AC(5430) G5
 * @tags    구현, 자료구조, 문자열, 파싱, Deque(덱)
 * @input
 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. T는 최대 100이다.
 * 각 테스트 케이스의 첫째 줄에는 수행할 함수 p가 주어진다. p의 길이는 1보다 크거나 같고, 100,000보다 작거나 같다.
 * 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다. (0 ≤ n ≤ 100,000)
 * 다음 줄에는 [x1,...,xn]과 같은 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
 * 전체 테스트 케이스에 주어지는 p의 길이의 합과 n의 합은 70만을 넘지 않는다.
 * @output
 * 각 테스트 케이스에 대해서, 입력으로 주어진 정수 배열에 함수를 수행한 결과를 출력한다.
 * 만약, 에러가 발생한 경우에는 error를 출력한다.
 */
public class BOJ_5430 {

    /**
     * <pre>
     *     문제를 푸는 방식 자체는 별로 어렵지 않았는데, 문자열 파싱이 오히려 어려웠다.
     *
     *     정방향, 또는 역방향으로 뭔가를 출력해야 하기 때문에 Deque 자료구조를 이용함.
     *     실제로 주어지는 R 마다 데이터를 reverse 처리하지 않음.
     *     현재 정방향인지 역방향인지만 알 수 있도록 int flag = 1; 선언.
     *     R을 수행할 때마다 flax * -1 했음. 그러면 정방향일 때 1, 역방향일 때 -1
     *     deque.size() == 0 일 때 D 수행 시도 시 flag = 0; 하고 break; 처리.
     *
     *     이후 출력 부분에서
     *     flag = -1 이면 deque.removeLast();
     *     flag = 1 이면 deque.removeFirst();
     *     flag = 0 이면 코드상 deque.removeFirst(); 이나 작동 x
     *
     *     bw.write 시
     *     flag = 0 이면 error
     *     아니면 StringBuilder에 appen 처리해서 출력 문자열을 생성하도록 함.
     *
     *     좀 더 고민이 필요한 부분
     *      - 최초에 배열을 입력 받을 때 저렇게 split 처리하면 예외 케이스가 발생하는데, 좀 더 나은 방법 없을지?
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String op = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String[] arr = br.readLine().replace("[", "").replace("]", "").split(",");

            Deque<String> d = new LinkedList<>(Arrays.asList(arr));
            int flag = 1;

            if (arr.length == 1 && Objects.equals(arr[0], "")) d.remove();

            for (int j = 0; j < op.length(); j++) {
                if (op.charAt(j) == 'R') {
                    flag *= -1;
                } else {
                    if (!d.isEmpty()) {
                        if (flag == -1) {
                            d.removeLast();
                        } else {
                            d.removeFirst();
                        }
                    } else {
                        flag = 0;
                        break;
                    }
                }
            }

            String[] answer = new String[d.size()];
            if (flag == -1) {
                for (int f = 0; f < answer.length; f++) {
                    answer[f] = d.removeLast();
                }
            } else {
                for (int f = 0; f < answer.length; f++) {
                    answer[f] = d.removeFirst();
                }
            }

            StringBuilder sb = new StringBuilder();
            System.out.println(String.join(",", answer));
            if (flag == 0) {
                bw.write("error\n");
            } else {
                if (answer.length == 0) {
                    bw.write("[]\n");
                } else {
                    sb.append("[").append(String.join(",", answer)).append("]");
                    bw.write(sb + "\n");
                }
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
