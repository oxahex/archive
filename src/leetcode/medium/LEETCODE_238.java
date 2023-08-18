package leetcode.medium;

/**
 * @date    2023-08-03
 * @title   Product of Array Except Self(238), Medium
 * @tags    Array, Prefix Sum
 * @input
 * Given an integer array nums,
 * @output
 * return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
public class LEETCODE_238 {

    /**
     * <pre>
     *     int[] nums =     {1,         2,          3,          4};
     *     int[] left =     {1,         1 * 1,      1 * 2,      1 * 2 * 3};
     *     int[] right =    {2 * 3 * 4, 3 * 4,      4 * 1,      1}
     * </pre>
     * */
    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[right.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        int[] answer = new int[nums.length];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = left[i] * right[i];
        }

        return answer;
    }
}
