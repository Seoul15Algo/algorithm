package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// BJ 1949 : 우수 마을
public class Main_1949 {
    static int N;
    static Village[] villages;
    static boolean[] visited;
    static int[][] dp;
	
    static class Village {
        int citizen;
        List<Integer> neighbors;
        
        public Village(int citizen) {
            super();
            this.citizen = citizen;
            this.neighbors = new ArrayList<Integer>();
        }
    }

    static void dfs (int now) {
        visited[now] = true;
        
        for (int next : villages[now].neighbors) {
            if (visited[next]) {
                continue;
            }

            dfs(next);
            dp[now][0] += Math.max(dp[next][0], dp[next][1]);
            dp[now][1] += dp[next][0];
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        villages = new Village[N];
        visited = new boolean[N];
        dp = new int[N][2];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int citizen = Integer.parseInt(st.nextToken());
            villages[i] = new Village(citizen);
            dp[i][1] = citizen;
        }
        
        for (int i = 0; i < (N - 1); i++) {
            st = new StringTokenizer(br.readLine());
            
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            
            villages[s].neighbors.add(e);
            villages[e].neighbors.add(s);
        }

        dfs(0);
        System.out.println(Math.max(dp[0][0], dp[0][1]));
        
        br.close();
    }
}