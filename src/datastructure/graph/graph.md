# Graph

|           |      인접 행렬       |     인접 리스트     |
|-----------|:----------------:|:--------------:|
| 특정 간선 검색  |       O(1)       |  O(degree(V))  |
| 정점의 차수 계산 |       O(N)       |  O(degree(V))  |
| 전체 노드 탐색  | O(N<sup>2</sup>) |      O(E)      |
| 메모리       |      N * N       |     N + E      |