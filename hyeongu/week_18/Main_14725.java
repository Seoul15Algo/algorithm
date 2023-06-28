import java.util.*;
import java.io.*;

public class Main_14725 {
    static StringBuilder sb;

    static class Trie {
        // 자식 노드를 String Key값으로 가지고 해당 자식으로부터 시작되는 trie를 Value값으로 가짐
        Map<String, Trie> next;

        public Trie() {
            next = new HashMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Trie head = new Trie();
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Trie now = head;

            // 포인터를 내려가면서 자식을 하나씩 추가
            int K = Integer.parseInt(st.nextToken());
            for(int j = 0; j < K; j++){
                String tmp = st.nextToken();
                if(!now.next.containsKey(tmp)){
                    now.next.put(tmp, new Trie());
                }
                now = now.next.get(tmp);
            }
        }
        printTrie(head, "");
        System.out.print(sb);
    }

    // 재귀적으로 트리를 내려가면서 출력
    public static void printTrie(Trie t, String depth){
        List<String> keylist = new ArrayList<>(t.next.keySet());
        keylist.sort(String::compareTo);
        StringBuilder nextDepth = new StringBuilder();
        nextDepth.append(depth).append("--");

        for(String str : keylist){
            sb.append(depth).append(str).append("\n");
            printTrie(t.next.get(str), nextDepth.toString());
        }
    }
}