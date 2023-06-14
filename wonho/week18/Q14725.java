package week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q14725 {

    static StringBuilder sb = new StringBuilder();

    static class Node {
        private final Map<String, Node> children;
        private boolean end;

        public Node() {
            this.children = new HashMap<>();
        }

        public Map<String, Node> getChildren() {
            return children;
        }

        public boolean isEnd() {
            return end;
        }

        public void endToTrue() {
            this.end = true;
        }

        public void search(int depth) {
            List<String> list = new ArrayList<>(children.keySet());
            Collections.sort(list);
            for (String s : list) {
                for (int i = 0; i < depth; i++) {
                    sb.append("--");
                }
                sb.append(s).append("\n");
                children.get(s).search(depth + 1);
            }
        }
    }

    static class Trie {
        private final Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String[] strs) {
            Node curNode = this.root;
            for (String str : strs) {
                curNode = curNode.getChildren().computeIfAbsent(str, key -> new Node());
            }
            curNode.endToTrue();
        }

        public void search() {
            this.root.search(0);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            String[] strs = new String[k];
            for (int j = 0; j < k; j++) {
                strs[j] = st.nextToken();
            }

            trie.insert(strs);
        }

        trie.search();
        System.out.print(sb);
    }
}