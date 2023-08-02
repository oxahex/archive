package leetcode;


/**
 * @date    2023-08-02
 * @title   Container With Most Water(11)
 * @tags    String, Math
 * @input
 * You are given an integer array height of length n.
 * There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * @output
 * Return the maximum amount of water a container can store.
 */
public class LEETCODE_11 {

    /**
     * <pre>
     *     접근 방식
     *      - start, end를 양 끝 index로 초기화: 기본적으로 start, end 간 거리가 먼 것이 유리
     *      - height[start]가 height[end] 보다 작을 때만 start++
     *      - 크거나 같을 때는 end++
     *      - 같은 경우에는 사실 start++, end++ 둘 다 상관 없을 듯...
     *      - start, end가 변하더라도, 이전 값과 비교해서 물 양이 더 많을 떄만 max를 교체
     * </pre>
     * */
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int max = 0;

        while (start < end) {
            int w = end - start;
            int h = Math.min(height[start], height[end]);
            System.out.println("start: " + start + ", end: " + end + ", 넓이: " + w * h);
            max = Math.max(max, w * h);

            if (height[start] < height[end]) start++;
            else end--;
        }

        return max;
    }
}
