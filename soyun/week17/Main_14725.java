package com.ssafy.baekjoon.random;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;


// 개미굴~
public class Main_14725 {

    static class Trie {

        static class TrieNode {

            String word;    // 해당 노드에 저장되어 있는 단어
            int depth;  // 해당 단어가 개미굴의 몇 층에 있는지
            TreeMap<Character, TrieNode> children;
            boolean isWord;

            public TrieNode(String word, int depth){
                this.word = word;
                this.depth = depth;
                this.children = new TreeMap<>();
                this.isWord = false;
            }
        }

        TrieNode root;
        int depth;

        public Trie(){
            this.root = new TrieNode("", 0);
        }

        // Trie 트리에 삽입
        public void insert(String[] words){
            TrieNode node = this.root;
            depth = 0;
            for (String word : words){
                StringBuilder subWord = new StringBuilder();
                for (Character c : word.toCharArray()){

                    subWord.append(c);
                    // computeIfAbsent(): Map에 key에 대응하는 value가 존재할 때: 해당 value 반환, 존재하지 않을 때: 해당 함수 실행
                    // 자식 노드 존재 -> 자식노드로 이동, 자식 노드 없다면 새로 Node 만들어서 넣어줌
                    node = node.children.computeIfAbsent(c, child -> new TrieNode(subWord.toString(), depth));
                }
                node.isWord = true; // 해당 단어의 마지막 문자에는 true 체크
                depth++;
            }
        }

        // Trie 트리를 순회하면서 출력 문자열을 만듦
        public void print(TrieNode root){
            TrieNode node = root;
            for (Character c : node.children.keySet()){

                // 해당 단어의 마지막 문자인 경우 -> 단어 완성!
                if (node.children.get(c).isWord){
                    // 이후에 연결된 문자가 있다면 -> 다음 depth의 단어가 있다는 뜻, 다음 층으로 내려간다.
                    if (node.children.get(c).depth != 0){
                        result.append("--".repeat(node.children.get(c).depth)); // "--"을 depth 만큼 곱한다, Java11부터 사용 가능
                    }
                    result.append(node.children.get(c).word);
                    result.append("\n");
                }
                print(node.children.get(c));
            }
        }
    }

    static int N;
    static Trie trie;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        trie = new Trie();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            String[] inputs = new String[K];
            for (int j = 0; j < K; j++) {
                inputs[j] = st.nextToken();
            }
            trie.insert(inputs);    // 단어 입력
        }

        trie.print(trie.root);  // 입력받은 단어들을 바탕으로한 개미굴 출력
        System.out.println(result);
    }
}
