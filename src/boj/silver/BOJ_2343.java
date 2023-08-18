package boj.silver;

import java.io.*;
import java.util.*;

/**
 * @date    2023-08-18
 * @title   기타 레슨(2343) S1
 * @tags    Binary Search
 * @input
 * 첫째 줄에 강의의 수 N (1 ≤ N ≤ 100,000)과 M (1 ≤ M ≤ N)이 주어진다.
 * 다음 줄에는 강토의 기타 강의의 길이가 강의 순서대로 분 단위로(자연수)로 주어진다.
 * 각 강의의 길이는 10,000분을 넘지 않는다.
 * @output
 * 첫째 줄에 가능한 블루레이 크기중 최소를 출력한다.
 */
public class BOJ_2343 {

    /**
     * <pre>
     *     이진 탐색을 이용한 문제라는 것을 파악하는 데 시간이 오래 걸렸다.
     *     블루레이의 크기가 모두 같고, 녹화 순서가 바뀌지 않아야 한다는 문제 조건 -> 이진 탐색 알고리즘
     *
     *     문제 접근
     *      - 블루레이가 1 개인 경우 -> 주어진 강의 시간의 sum()
     *      - 블루레이가 N 개인 경우 -> 블루레이 크기 = 가장 긴 강의 시간
     *
     *     N = 9, M = 3 이고,
     *     강의 시간 배열이 1, 2, 3, 4, 5, 6, 7, 8, 9 일 때,
     *     블루레이 1개에 모든 강의를 넣는 경우 블루레이의 크기는 45.
     *     블루레이에 강의 하나씩 들어간다면 총 9 크기의 블루레이 9개가 필요.
     *
     *     9 ~ 45 사이의 숫자를 이진탐색
     *     최초 중앙값(mid) = (9 + 45) / 2 = 27
     *     27 크기의 블루레이 몇 개에 강의를 모두 넣을 수 있는지 확인
     *      - 충분히 들어간다면 end = mid - 1
     *      - 들어가지 않는다면 start = mid + 1
     *
     *     탐색한 숫자(mid) 크기의 블루레이에 강의가 모두 들어가는지 확인하는 로직이 좀 어려웠음.
     *     강의 순서를 바꿀 수 없으므로 앞에서부터 순차적으로 더하고
     *     더한 값이 mid 크기를 넘어서는 경우 cnt++ (필요한 블루레이 개수)
     *     for 반복이 종료되었는데(모든 강의를 다 더했는데) sum 값이 0이 아닌 경우 블루레이가 추가로 하나 더 필요하므로 cnt++
     *
     *     cnt(블루레이 개수) 가 주어진 블루레이 개수(M) 보다 크면 블루레이의 용량을 늘려서 더 적은 양의 블루레이에 강의를 담아야 하므로 큰 영역에서 탐색
     *     cnt >= M 이면 블루레이의 크기를 줄여서 블루레이의 개수를 늘려도 무방하므로 더 작은 영역에서 탐색
     * </pre>
     * */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 강의 수
        int M = Integer.parseInt(st.nextToken());   // 블루레이 개수

        int[] lectures = new int[N];
        st = new StringTokenizer(br.readLine());

        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            maxLength = Math.max(maxLength, lectures[i]);
        }

        int start = maxLength;
        int end = Arrays.stream(lectures).sum();

        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (sum + lectures[i] > mid) {
                    cnt++;
                    sum = 0;
                }
                sum += lectures[i];
            }

            if (sum != 0) cnt++;

            if (cnt > M) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(start);
    }
}
