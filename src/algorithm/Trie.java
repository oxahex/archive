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

            cur.child.putIfAbsent(c, new TNode());
            cur = cur.child.get(c);

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

            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);     // 있으면 그 다음 자식 노드로 이동
            } else {
                return false;
            }

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