package boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * @date    2023-09-02
 * @title   전화번호 목록(5052) G4
 * @tags    Trie
 * @input
 * 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (1 ≤ t ≤ 50)
 * 각 테스트 케이스의 첫째 줄에는 전화번호의 수 n이 주어진다. (1 ≤ n ≤ 10000)
 * 다음 n개의 줄에는 목록에 포함되어 있는 전화번호가 하나씩 주어진다.
 * 전화번호의 길이는 길어야 10자리이며, 목록에 있는 두 전화번호가 같은 경우는 없다.
 * @output
 * 각 테스트 케이스에 대해서, 일관성 있는 목록인 경우에는 YES, 아닌 경우에는 NO를 출력한다.
 */
public class BOJ_5052 {

    /**
     * <pre>
     *     접근
     *      - 하나의 전화번호가 다른 하나의 prefix 면 일관성이 없는 목록이라고 볼 수 있다.
     *      - 하나의 전화번호를 탐색 중 마지막 번호일 때 child가 되는 HashMap size 가 0 보다 크면 그 아래로 다른 번호가 있는 것임.
     * </pre>
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            Trie_5052 trie = new Trie_5052();
            String[] S = new String[N];

            int cnt = 0;

            for (int j = 0; j < N; j++) {
                S[j] = br.readLine();
                trie.insert(S[j]);
            }

            for (int k = 0; k < N; k++) {
                String s = S[k];
                if (trie.checkConsistency(s)) cnt++;
            }

            if (cnt == N) System.out.println("YES");
            else System.out.println("NO");
        }
    }
}

class Trie_5052 {
    TNode_5052 root;

    public Trie_5052() {
        this.root = new TNode_5052();
    }

    public void insert(String str) {
        TNode_5052 cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            cur.child.putIfAbsent(c, new TNode_5052());
            cur = cur.child.get(c);
            if (i == str.length() - 1) {
                cur.isTerminal = true;
            }
        }
    }

    public boolean checkConsistency(String str) {
        TNode_5052 cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);
                if (i == str.length() - 1 && !cur.child.isEmpty()) return false;
            }
        }

        return true;
    }
}

class TNode_5052 {
    HashMap<Character, TNode_5052> child;
    boolean isTerminal;

    public TNode_5052() {
        this.child = new HashMap<>();
        this.isTerminal = false;
    }
}