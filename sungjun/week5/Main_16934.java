package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_16934 {

    static int N;

    static class Node {
        Map<Character, Node> child = new HashMap<>();
        int numbering = 0;
    }

    static class Trie {
        Node root = new Node();

        public String insert(String s) {
            Node node = root;
            StringBuilder result = new StringBuilder();
            // 새로운 문자가 추가됐는지 체크
            boolean isEnd = false;
            
            // 입력받은 닉네임의 첫번째 문자부터 탐색
            for (int i = 0; i < s.length(); i++) {
                // 해당 문자의 자식 노드가 존재할때
                if (node.child.get(s.charAt(i)) != null) {
                    node = node.child.get(s.charAt(i));
                    result.append(s.charAt(i));
                    continue;
                }
                // 해당 문자의 자식 노드가 없을때
                Node next = new Node();
                node.child.put(s.charAt(i), next);
                // 새로운 문자를 자식 노드로 추가, 자식 노드를 가리키도록 변경
                node = next;
                
                // 별칭이 끝나는 부분 체크, 이후의 문자들은 결과로 반환하지 않고 자식 노드로만 저장
                if (!isEnd) {
                    result.append(s.charAt(i));
                    isEnd = true;
                }
            }
            // 첫번째로 입력된 별칭일 경우는 숫자 생략
            if (node.numbering == 0) {
                node.numbering = 1;
                return result.toString();
            }
            // 이미 존재하는 별칭일 경우 뒤에 몇번째인지 숫자 추가
            node.numbering += 1;
            return result.append(node.numbering).toString();
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
        
        Trie trie = new Trie();
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            String res = trie.insert(br.readLine());
            sb.append(res + "\n");
        }
        
        System.out.println(sb);
    }

}