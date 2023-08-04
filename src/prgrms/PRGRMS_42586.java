package prgrms;

import java.util.*;

/**
 * @date    2023-07-16
 * @title   기능개발(42586), L2
 * @tags    Stack, Queue
 * @input
 * 먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어진다.
 * @output
 * 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.
 */
public class PRGRMS_42586 {

    /**
     * <pre>
     *     제한사항
     *      - 작업의 개수(progresses, speeds 배열의 길이)는 100개 이하입니다.
     *      - 작업 진도는 100 미만의 자연수입니다.
     *      - 작업 속도는 100 이하의 자연수입니다.
     *      - 배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다.
     *        예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
     *
     *     solution 1
     *     최초에 내가 작성한 코드. 스택이나 큐를 사용하지 않고 그냥 생각나는대로 작성했다.
     *     최악의 경우 전체 프로세스 완료 시 100일이 걸리기 때문에 100 크기의 배열을 선언하고,
     *     각 index가 작업이 끝나는 날을 의미하도록 함.
     *     오히려 스택과 큐를 이용하면 더 어렵게 느껴지는 문제라 스택/큐를 이용해서 다시 풀어봐야겠다.
     *
     *     solution 2
     *     해야 함...
     * </pre>
     * */


    public int[] solution1(int[] progresses, int[] speeds) {
        int[] releaseDays = new int[100];

        int day = 1;
        for (int i = 0; i < progresses.length; i++) {
            while (progresses[i] + (day * speeds[i]) < 100) {
                day++;
            }
            System.out.println(progresses[i] + ": " + day);
            releaseDays[day]++;
        }

        // array -> stream -> array: 이게 좋은 방법인지?
        // 오히려 1 - 100 index 전체를 순회하는 것이 나을 수도 있을 것 같다.
        return Arrays.stream(releaseDays).filter(x -> x != 0).toArray();
    }

//    public int[] solution2(int[] progresses, int[] speeds) {}

}
