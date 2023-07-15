package datastructure;

import java.util.ArrayList;

public class PMaxHeap {
    ArrayList<Integer> heap;

    public PMaxHeap() {
        this.heap = new ArrayList<>();
        this.heap.add(0);   // dummy data: idx 기준 1번부터 시작할 수 있도록
    }

    public void insert(int data) {
        // 최소 힙의 경우 data 삽입 시 가장 끝에 우선 위치
        heap.add(data);

        // 바로 위 부모와 값 비교 후 data 크기가 더 작은 경우 switch
        int curIdx = heap.size() - 1; // 방금 추가한 데이터의 idx 위치

        // current idx 값이 0이면 dummy data or idx 오류
        // 부모의 위치(curr / 2)가 방금 추가한 데이터보다 작으면 반복 작업 실행
        // curr 시작이 0이면 cur / 2 불가, 1인 경우 1 % 2 = 1
        while (curIdx > 1 && heap.get(curIdx / 2) < heap.get(curIdx)) {
            int parentData = heap.get(curIdx / 2);
            heap.set(curIdx / 2, data);
            heap.set(curIdx, parentData);

            curIdx /= 2;   // 그 다음 부모 위치로 이동
        }
    }

    public Integer delete() {
        if (heap.size() == 1) {
            System.out.println("ERROR: EMPTY");
            return null;
        }

        int rootData = heap.get(1);   // 최상위(root)

        // root 위치와 가장 마지막 값 switch
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);   // 마지막 위치 값은 root 이동 후 제거

        // 비교
        int curIdx = 1;    // root 부터 탐색하므로 시작은 1
        while (true) {
            int leftIdx = curIdx * 2;
            int rightIdx = curIdx * 2 + 1;
            int targetIdx = -1;

            // idx 1부터 시작했음
            if (rightIdx < heap.size()) {   // 자식 노드가 왼쪽, 오른쪽 모두 있는 케이스
                // 자식 노드가 큰 쪽으로 target idx 설정
                targetIdx = heap.get(leftIdx) > heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) {  // 자식 노드가 하나 있는 케이스: 당연히 왼쪽에 있음(완전 이진트리)
                targetIdx = leftIdx;
            } else {    // 자식 노드가 없음: 부모 노드 밖에 없거나 리프 노드
                break;
            }

            if (heap.get(curIdx) > heap.get(targetIdx)) {   // 문제 없음.
                break;
            } else {
                int parentData = heap.get(curIdx);
                heap.set(curIdx, heap.get(targetIdx));
                heap.set(targetIdx, parentData);
                curIdx = targetIdx; // targetIdx 위치에서 다시 재시작하도록 current idx 위치를 target idx로 변경
            }
        }

        return rootData;
    }

    public void print() {
        // dummy data 제외하고 출력
        // 0 부터 출력하면 모든 출력에 0이 붙을 것인 최상위 노드(가장 왼 쪽)
        for (int i = 1; i < this.heap.size(); i++) {
            System.out.printf("%d ", this.heap.get(i));
        }
        System.out.println();
    }
}
