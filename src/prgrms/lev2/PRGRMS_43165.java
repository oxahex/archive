package prgrms.lev2;

/**
 * @date    2023-07-26
 * @title   타겟 넘버(43165), L2
 * @tags    Binary Tree(이진 트리), DFS, BFS, Recursion(재귀)
 * @input
 * 사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어진다.
 * @output
 * 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는 방법의 수를 return 한다.
 */
public class PRGRMS_43165 {

    /**
     * <pre>
     *     접근
     *      - number.length를 level로 하는 완전 이진 트리를 생각함.
     *      - 탐색을 하되 leaf node 까지 탐색 후 각 level의 node를 모두 더해서 target이 되는 경우의 수를 구하는 문제.
     *      - 재귀로 푸는 것이 나음.
     *
     *     int[] number = {1, 2, 3};
     *     1    ->  +2  ->  +3
     *                  ->  -3
     *          ->  -2  ->  -3
     *                  ->  +3
     *     각 level 마다 + 또는 - 계산을 해주는 식으로 내려가게 된다.
     *     원래대로라면 Root Node가 +1인 경우와 -1인 경우를 모두 해주고 각각의 케이스에서 나온 경우의 수를 합산해야 함.
     *     그런데 Root Node를 0으로 생각하면(그림이 이상하지만 이렇게 표현할 수 있다.)
     *     ----------------------------------
     *                           0
     *                        /   \
     *                     /       \
     *                  /           \
     *               +1             -1
     *             /  \           /   \
     *          +2    -2        +2     -2
     *        /  \   /  \     /  \    /  \
     *      +3  -3 +3  -3   +3   -3 +3   -3
     *    -----------------------------------
     *    재귀 함수를 만들 때 start node = 0, idx == level.
     *    재귀 함수 탈출 조건 -> number.length == idx 마지막 값까지 모두 계산한 것.
     *    이 때 sum == target 라면 answer++, 재귀 함수 탈출 break;
     *
     *    매 level마다 +인 경우와 -인 경우 모두 대응해야 함.
     *    완전 이진 트리이므로 모든 노드에 child가 2이므로 재귀함수 내부에서 두 케이스 모두 재귀 호출 가능
     *    sum += number[idx + 1]
     *    sum -= number[idx + 1]
     * </pre>
     * */

    public int answer;

    public int solution(int[] numbers, int target) {
        rec(numbers, 0, 0, target);
        return answer;
    }

    public void rec(int[] numbers, int sum, int idx, int target) {
        if (numbers.length == idx) {
            // 주어진 깊이의 마지막까지 도달
            if (sum == target) answer++;
            return;
        }

        rec(numbers, sum + numbers[idx], idx + 1, target);
        rec(numbers, sum - numbers[idx], idx + 1, target);
    }
}
