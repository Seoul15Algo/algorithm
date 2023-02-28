import java.util.*;
import java.io.*;

public class Main_2533 {
    static int N;
    static int[][] dp;
    static boolean[] visit;
    static class Node{
        List<Node> list;
        int idx;
        public Node(int i){
            list = new ArrayList<>();
            this.idx = i;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        List<Node> nodes = new ArrayList<>();
        dp = new int[N][2];
        visit = new boolean[N];

        for(int i = 0; i < N; i++){
            nodes.add(new Node(i));
        }
        for(int i = 0; i < N - 1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            nodes.get(u).list.add(nodes.get(v));
            nodes.get(v).list.add(nodes.get(u));
        }
        visit[0] = true;
        int answer = Math.min(dfs(nodes.get(0), true), dfs(nodes.get(0), false));
        System.out.println(answer);
    }

    static int dfs(Node n, boolean flag){
        if(!flag){
            for(Node next : n.list){
                if(visit[next.idx]){
                    continue;
                }
                if(dp[next.idx][0] == 0){
                    visit[next.idx] = true;
                    dfs(next, true);
                    visit[next.idx] = false;
                }
                dp[n.idx][1] += dp[next.idx][0];
            }
            return dp[n.idx][1];
        }
        for(Node next : n.list){
            if(visit[next.idx]){
                continue;
            }

            visit[next.idx] = true;
            if(dp[next.idx][0] == 0) {
                dfs(next, true);
            }
            if(dp[next.idx][1] == 0) {
                dfs(next, false);
            }
            visit[next.idx] = false;
            dp[n.idx][0] += Math.min(dp[next.idx][0] , dp[next.idx][1]);
        }
        dp[n.idx][0]++;
        return dp[n.idx][0];
    }
}