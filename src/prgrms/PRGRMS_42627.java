package prgrms;

import java.util.*;

/**
 * @date    2023-07-17
 * @title   디스크 컨트롤러(42627), L3
 * @tags    Heap(힙)
 * @input
 * 각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어진다.
 * @output
 * 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면
 * 평균이 얼마가 되는지 return 하도록 solution 함수를 작성하라. (단, 소수점 이하의 수는 버림)
 */
public class PRGRMS_42627 {

    /**
     * <pre>
     *     제한사항
     *      - jobs의 길이는 1 이상 500 이하입니다.
     *      - jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
     *      - 각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
     *      - 각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
     *      - 하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
     *
     *     접근
     *      - 들어온 요청 시간이 빠른 것 순으로 처리
     *      - 요청 시간이 짧은 순서대로 처리
     *
     *     놓친 것
     *      - 들어온 요청 시간이 빠른 순으로 처리하되, 하나의 작업이 끝났을 때 요청이 없을 수도 있음
     *      - 그러므로 하나의 작업을 하는 시점에 들어온 작업들 중에서 다음 작업을 우선순위 큐로 sorting 처리 필요
     *          - 우선순위 큐를 이용하는 이유는 매 작업을 진행할 때마다 다음 작업의 우선순위를 결정해야 하기 떄문
     *          - 우선순위 큐를 사용하지 않는다면 매 작업 시마다 jobs 배열을 순회하면서 [0]이 조건을 만족하는 요소를 [1] 기준으로 정렬 구현 필요.
     *      - 큐가 비어있음 == 작업이 끝났음에도 다음 요청이 없음
     * </pre>
     * */
    public int solution(int[][] jobs) {

        // 작업 요청 시점이 빠른 것부터 jobs 배열 정렬(오름차순)
        Arrays.sort(jobs, Comparator.comparingInt(o -> o[0]));

        // 작업에 걸리는 시간이 적은 순으로(오름차순) 정렬하는 PQ 세팅
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        int jobIdx = 0; // 현재 진행중인 job index
        int finishedCount = 0;  // 종료된 작업 수
        int end = 0;    // 작업이 종료된 시간
        int totalTime = 0;  // 각 작업의 소요시간 누적 합계

        // 주어진 모든 작업을 모두 진행할 때까지 반복
        while (finishedCount < jobs.length) {

            // PQ에 작업을 정렬
            // end 보다 요청 시점이 이른 작업만 PQ에 삽입
            while (jobIdx < jobs.length && jobs[jobIdx][0] <= end) {
                pq.add(jobs[jobIdx]);
                jobIdx++;
            }

            // PQ가 비어있음 == end 보다 요청 시점이 이른 작업이 없음
            // 작업 종료되었으나, 이후 작업이 없으므로 현재 작업(요청 시간 오름차순) 요청 시간을 end에 더함
            // 여기서의 jobIdx는 요청 시간 오름차순 정렬된 jobs 순서
            if (pq.isEmpty()) {
                end = jobs[jobIdx][0];
            } else {
                // PQ가 비어있지 않음 == 작업이 끝나기 전 다른 작업 요청이 있음
                // PQ는 작업이 끝나기 전에 요청된 작업을 소요시간 오름차순 정렬하므로, 짧은 것 먼저 poll()
                // totalTime: 대기 시간 + 실제 작업 소요 시간 -> ( 대기 시간: 이전 작업 종료 시간 - 현재 작업 요청 시간 )
                // end 시간 변경: + 현재 작업 소요 시간
                // 하나의 작업이 종료되었으므로 finishedCount++
                int[] task = pq.poll();
                totalTime += end - task[0] + task[1];
                end += task[1];
                finishedCount++;
            }
        }

        return (int) Math.floor((double) totalTime / finishedCount);
    }
}
