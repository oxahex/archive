package algorithm;

import java.util.Arrays;

public class TwoPointers {
    public static int[] forLoop(int[] arr, int target) {
        int[] result = {-1, -1};

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];   // i 번째 값으로 초기화
            for (int j = i + 1; j < arr.length; j++) {
                if (sum == target) {
                    result[0] = i;
                    result[1] = j - 1;
                    break;
                }
                sum += arr[j];
            }
            if (sum == target) break;
        }
        return result;
    }

    public static int[] twoPointers(int[] arr, int target) {
        int p1 = 0;
        int p2 = 0;
        int sum = 0;
        int[] result = {-1, -1};

        while (true) {
            if (sum > target) {
                sum -= arr[p1++];               // sum 값이 target 보다 크면 sum 에서 해당 값 빼주고 p1 증가
            } else if (p2 == arr.length) {
                break;                          // p2 와 전체 배열의 길이가 같으면
            } else {
                sum += arr[p2++];               // 그 외 상황에서는 p2 값을 더하고 포인터를 다음 값으로 옮김
            }

            if (sum == target) {
                result[0] = p1;
                result[1] = p2 - 1;
                break;
            }
        }

        return result;
    }

    /**
     * <pre>
     *     부분합이 n(target)이 되는 값을 찾는 경우(2개)
     *
     *     이중 for문 사용
     *      - O(N^2)
     *
     *     Tow Pointers 사용
     *      - O(N)
     *      - 좀 더 선형적으로 풀어낼 수 있음.
     *      - 시간복잡도 역시 유리
     * </pre>
     * */

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 2, 4, 3, 2};

        System.out.println("for loop");
        System.out.println(Arrays.toString(forLoop(arr, 9)));   // 4, 5 구간
        System.out.println(Arrays.toString(forLoop(arr, 14)));  // 없음 -1, -1 리턴

        System.out.println("two pointers");
        System.out.println(Arrays.toString(twoPointers(arr, 9)));
        System.out.println(Arrays.toString(twoPointers(arr, 14)));

    }
}
