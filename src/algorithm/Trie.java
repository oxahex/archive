package algorithm;

import java.util.HashMap;

/**
 * <pre>
 *     Trie
 *      - 문자열을 저장하고 빠르게 탐색하기 위한 정렬된 트리 형태의 자료구조
 *      - 문자열 저장을 위한 메모리가 필요하나 탐색이 빠름
 *      - 길이가 N인 문자열 탐색 시 O(N), 생성 복잡도는 O(문자열 개수 * N)
 *
 *     Trie Node
 *      - Key 와 Value 로 이루어진 노드로 구성
 *          class TrieNode {
 *              HashMap<Character, TrieNode) child;
 *              boolean isTerminal;
 *          }
 *
 * </pre>
 */
public class Trie {
    TNode root;

    public Trie() {
        this.root = new TNode();
    }

    public void insert(String str) {
        TNode cur = this.root;

        // 문자열의 길이 만큼 반복
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // child에 현재 문자가 key로 없으면 새로 생성
            cur.child.putIfAbsent(c, new TNode());

            // 현재 TNode를 c에 해당하는 TNode로 변경
            cur = cur.child.get(c);

            // 현재 탐색 중인 문자가 마지막 문자인 경우 끝 문자임을 명시
            if (i == str.length() - 1) {
                cur.isTerminal = true;
                return;
            }
        }
    }

    public boolean search(String str) {
        TNode cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            /*
            문자 c 가 child 가 있으면 다음으로 이동,
            없으면 str 은 목록에 없으므로 false 리턴
             */
            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);
            } else {
                return false;
            }

            /*
             c 가 마지막 문자인 경우,
             c 가 끝 문자열이면 str 은 존재함
             끝 문자열이 아니라면 여기까지 동일한 더 긴 문자열이 존재함
             prefix 를 찾아야 한다면 이 부분이 없어도 될 것 같다.
             */
            if (i == str.length() - 1) {
                if (!cur.isTerminal) return false;  // 마지막 도달 시
            }
        }

        return true;
    }

    public void delete(String str) {
        boolean ret = this.delete(this.root, str, 0);
        if (ret) {
            System.out.println("삭제 성공");
        } else {
            System.out.println("삭제 실패");
        }
    }

    public boolean delete(TNode node, String str, int idx) {
        char c = str.charAt(idx);

        if (!node.child.containsKey(c)) return false;

        TNode cur = node.child.get(c);
        idx++;

        if (idx == str.length()) {
            if (!cur.isTerminal) return false;
            cur.isTerminal = false;
            if (cur.child.isEmpty()) node.child.remove(c);
        } else {
            if (this.delete(cur, str, idx)) return false;
            if (!cur.isTerminal && cur.child.isEmpty()) node.child.remove(c);
        }

        return true;
    }
}

class TNode {
    HashMap<Character, TNode> child;
    boolean isTerminal;

    public TNode() {
        this.child = new HashMap<>();
        isTerminal = false;
    }
}