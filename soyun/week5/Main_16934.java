package com.ssafy.algo230228_DataStructure.soyun.week5;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


/*
    트라이 알고리즘
    https://codingnojam.tistory.com/40
 */
public class Main_16934 {

    static class Node {
        Map<Character, Node> child;

        public Node() {
            child = new HashMap<>();
        }
    }

    static class Trie {
        private final Node rootNode;

        public Trie() {
            this.rootNode = new Node();
        }

        // Trie 에 문자열 저장
        public String insert(String nickname) {
            // rootNode 부터 시작
            Node node = rootNode;
            int length = nickname.length();
            boolean flag = false;

            for (int i = 0; i < nickname.length(); i++) {

                char c = nickname.charAt(i);

                // 문자열의 단어 하나하나가 자식노드에 있는지 체크
                // 없다면 -> put
                if (!node.child.containsKey(c)) {

                    node.child.put(c, new Node());

                    // 처음으로 문자가 달라지는 위치 저장
                    if (!flag) {
                        length = i + 1;
                        flag = true;
                    }
                }
                node = node.child.get(c);
            }

            // 같은 닉네임이 존재할 경우
            if (nicknames.containsKey(nickname)) {
                nicknames.put(nickname, nicknames.get(nickname) + 1);   // 같은 닉네임으로 가입한 사람의 수 + 1
                return new StringBuilder(nickname).append(nicknames.get(nickname)).toString();
            }

            nicknames.put(nickname, 1);
            return nickname.substring(0, length);
        }

    }

    static Map<String, Integer> nicknames;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();
        nicknames = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            sb.append(trie.insert(word)).append("\n");
        }
        System.out.println(sb);
    }

}