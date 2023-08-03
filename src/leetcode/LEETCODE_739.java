package leetcode;

import java.util.Stack;

/**
 * @date    2023-08-02
 * @title   Daily Temperatures(739), Medium
 * @tags    Array, Stack, Monotonic Stack
 * @input
 * Given an array of integers temperatures represents the daily temperatures,
 * @output
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 */
public class LEETCODE_739 {

    /**
     * <pre>
     *     접근 방식
     *      - temperatures 배열을 순회 -> idx를 Stack에 저장.
     *      - temperatures[i]가 Stack의 top 보다 크면 Stack.pop().
     *      - result[stack.pop()]에 현재 idx와 Stack에서 방금 빼낸 idx 차이를 넣음.
     *
     *     미래의 사건을 계속 확인해야 하기 때문에 Stack을 이용해 미래가 결정될 때까지 result[i]에 값을 넣는 것을 미룸.
     *     top 보다 큰 온도값이 주어지는 경우에만 Stack.pop()을 하고, pop() 한 값이 기록하고자 하는 답임.
     * </pre>
     * */
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            System.out.println(i + ": " + stack);
            stack.push(i);
        }

        return result;
    }
}
