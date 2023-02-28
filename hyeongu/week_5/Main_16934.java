import java.util.*;
import java.io.*;

public class Main_16934 {
    static StringBuilder sb;

    static class Trie {
        Map<Character, Trie> next;
        int cnt;

        public Trie() {
            cnt = 0;
            next = new HashMap<>();
        }

        public static void insertTrie(Trie head, String str) {
            Trie t = head;
            char c = ' ';
            boolean flag = false;
            int pos = -1;

            // Trie 자료구조에 삽입 하면서 새로운 노드를 만드는 첫 번째 위치를 기억
            // 앞에서부터 이 위치까지 잘라서 닉네임으로 설정
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (!t.next.containsKey(c)) {
                    if (!flag) {
                        flag = true;
                        pos = i;
                    }
                    t.next.put(c, new Trie());
                }
                t = t.next.get(c);
            }
            t.cnt++;

            // 앞에서 새로운 노드를 만들었을 경우 그 위치까지만 substring
            if (flag) {
                sb.append(str.substring(0, pos + 1)).append("\n");
                return;
            }

            // 새로운 노드를 만들지 않았으면 중복된 단어가 존재하므로
            // cnt를 붙여서 출력
            sb.append(str);
            if (t.cnt > 1) {
                sb.append(t.cnt);
            }
            sb.append("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Trie head = new Trie();
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            // 트라이 자료구조 사용
            head.insertTrie(head, str);
        }
        System.out.print(sb);
    }
}
