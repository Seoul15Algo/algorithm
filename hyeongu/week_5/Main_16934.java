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

            if (flag) {
                sb.append(str.substring(0, pos + 1)).append("\n");
                return;
            }
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

            head.insertTrie(head, str);
        }
        System.out.print(sb);
    }
}
