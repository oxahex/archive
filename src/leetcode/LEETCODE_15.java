package leetcode;

import java.util.*;

/**
 * @date    2023-08-02
 * @title   3Sum(15), Medium
 * @tags    Two Pointer, Sorting
 * @input
 * Given an integer array nums,
 * @output
 * return all the triplets [nums[i], nums[j], nums[k]]
 * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 */

public class LEETCODE_15 {

    /**
     * <pre>
     *     Two Pointers 문제는 보통 포인터가 2개인데, 3개까지 고려해야 하는 문제.
     *     Pointer를 3개 쓰지 않고도 풀 수 있을거라고 생각함.
     *
     *     i, j, k 중 i는 for 반복문으로 고정하고, 구해야 하는 target 값을 nums[i] * -1로 설정
     *     start = i + 1; end = nums.length - 1;
     *     j = start, k = end, Two Pointers 방식으로 풀었다.
     *
     *     nums 배열을 오름차순으로 정렬하면, 언제 start를 이동하고, end를 이동해야 하는지 정할 수 있음.
     *     start를 이동하면 더한 값이 커지고, end를 이동하면 더한 값이 작아지므로,
     *      - nums[start] + nums[end] < target 이면, start++
     *      - nums[start] + nums[end] = target 이면, start++, HashSet에 넣어 중복 제거
     *      - nums[start] + nums[end] > target 이면, end++
     *
     *     애초에 중복을 허용하지 않으므로 0, 1, 2와 0, 2, 1의 결과는 동일한 것임.
     *     나머지는 0, 1, 2와 1, 2, 3의 값 자체가 같은 경우만 남는다.
     * </pre>
     * */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));

        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int target = nums[i] * -1;
            int start = i + 1;
            int end = nums.length - 1;

            while (start < end) {
                System.out.println(start + ", " + end);
                int sum = nums[start] + nums[end];
                if (sum < target) {
                    start++;
                } else if (sum == target) {
                    System.out.println("start: " + start + ", end: " + end + " - [ " + nums[i] + ", " + nums[start] + ", " + nums[end] + "]");
                    set.add(Arrays.asList(nums[i], nums[start], nums[end]));
                    start++;
                } else {
                    end--;
                }
            }
        }

        return new ArrayList<>(set);
    }
}
