package boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @date    2023-09-01
 * @title   접두사 찾기(14426) S1
 * @tags    Trie
 * @input
 * 첫째 줄에 문자열의 개수 N과 M (1 ≤ N ≤ 10,000, 1 ≤ M ≤ 10,000)이 주어진다.
 * 다음 N개의 줄에는 집합 S에 포함되어 있는 문자열이 주어진다.
 * 다음 M개의 줄에는 검사해야 하는 문자열이 주어진다.
 * 입력으로 주어지는 문자열은 알파벳 소문자로만 이루어져 있으며, 길이는 500을 넘지 않는다. 집합 S에 같은 문자열이 여러 번 주어지는 경우는 없다.
 * @output
 * 첫째 줄에 M개의 문자열 중에 총 몇 개가 포함되어 있는 문자열 중 적어도 하나의 접두사인지 출력한다.
 */
public class BOJ_14426 {

    /**
     * <pre>
     *     이 문제의 트라이 구현 핵심은 접두사 찾기이므로,
     *     Trie_14426 클래스의 search 메서드에서 isTerminate 를 체크하지 않는 것임
     * </pre>
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 집합 S에 포함되어 있는 문자열의 수
        int M = Integer.parseInt(st.nextToken());   // 검사해야 하는 문자열의 수

        String[] S = new String[N];

        Trie_14426 trie = new Trie_14426();
        for (int i = 0; i < N; i++) {
            trie.insert(br.readLine());
        }

        int answer = 0;
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (trie.search(s)) answer++;
        }

        System.out.println(answer);
    }
}

class Trie_14426 {
    TNode_14426 root = new TNode_14426();

    public void insert(String str) {
        TNode_14426 cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            cur.child.putIfAbsent(c, new TNode_14426());
            cur = cur.child.get(c);
        }
    }

    public boolean search(String str) {
        TNode_14426 cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);
            } else {
                return false;
            }
        }

        return true;
    }
}

class TNode_14426 {
    HashMap<Character, TNode_14426> child;

    public TNode_14426() {
        this.child = new HashMap<>();
    }
}