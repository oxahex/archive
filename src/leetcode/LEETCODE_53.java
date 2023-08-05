package leetcode;

import java.util.Arrays;

/**
 * @date    2023-08-05
 * @title   Maximum Subarray(53), Medium
 * @tags    Divide and Conquer(분할정복), Array, Dynamic Programming
 * @input
 * Given an integer array nums,
 * @output
 * find the subarray with the largest sum, and return its sum.
 */
public class LEETCODE_53 {

    /**
     * <pre>
     *     전체 배열에서 최대 부분합을 구하는 문제.
     *     Two Pointers 사용하기에는 배열을 오름차순 정렬할 수 없으므로 오히려 포인터 이동의 조건이 더 까다로워짐.
     *
     *     부분합이라는 것은 이전의 결과가 다음 결과에 영향을 미친다.
     *     int[] nums = {1, -2, 3, 5, -4, 2, 5};
     *     부분합 배열을 만들면 {1, -1, 2, 7, 3, 5, 10} 이다.
     *     이전 부분배열의 합에 그 다음 배열을 더한 것임.
     *
     *     그러므로 이전 부분 배열 합의 최댓값을 구하면, 그 다음 영역을 탐색할 때 두 가지 선택만 하면 된다.
     *      - 이전 영역의 부분합 + 현재 영역의 값 > 현재 영역의 값 -> 현재 영역의 값 포함
     *      - 이전 영역의 부분합 + 현재 영역의 값 <= 현재 영역의 값 -> 현재 영역의 값으로 초기화
     * </pre>
     * */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 이전 배열의 최댓값(연속된 값)과 이전 배열 최댓값 + 현재 값 중 큰 것이 max
            max = Math.max(nums[i], max + nums[i]);
            // 합을 구해야 하므로, 갱신된 배열의 최댓값과 이전 최댓값을 비교
            sum = Math.max(sum, max);
        }
        return sum;
    }


    /**
     * Memory Limit Exceeded
     * */
    public int fail(int[] nums) {
        int len = nums.length;
        int[][] table = new int[len][len];

        int answer = nums[0];
        table[0][0] = nums[0];

        // 구간합
        for (int i = 1; i < len; i++) {
            table[0][i] = table[0][i - 1] + nums[i];
            answer = Math.max(answer, table[0][i]);
        }

        // 나머지 부분 연산하며 최댓값 저장
        for (int i = 1; i < len; i++) {
            for (int j = i; j < len; j++) {
                table[i][j] = table[i - 1][j] - table[i - 1][i - 1];
                answer = Math.max(answer, table[i][j]);
            }
        }

        return answer;
    }
}
