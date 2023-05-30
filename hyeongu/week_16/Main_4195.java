import java.util.*;
import java.io.*;

public class Main_4195 {
    static List<Node> list;
    static Map<String, Integer> hm;
    static class Node{
        int p, r;

        public Node(int p){
            this.p = p;
            this.r = 1;
        }

        public int find(int x){
            if(x == this.p){
                return x;
            }
            Node next = list.get(this.p);
            return this.p = next.find(this.p);
        }
    }

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int N = Integer.parseInt(br.readLine());

            hm = new HashMap<>();
            list = new ArrayList<>();
            list.add(new Node(0));

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = getIndex(st.nextToken());
                int y = getIndex(st.nextToken());

                sb.append(union(x, y)).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int getIndex(String str){
        if(!hm.containsKey(str)){
            hm.put(str, list.size());
            list.add(new Node(list.size()));
        }
        return hm.get(str);
    }

    static int union(int x, int y){
        x = list.get(x).find(x);
        y = list.get(y).find(y);

        if(x == y){
            return list.get(x).r;
        }

        list.get(x).r += list.get(y).r;
        list.get(y).p = x;

        return list.get(x).r;
    }
}