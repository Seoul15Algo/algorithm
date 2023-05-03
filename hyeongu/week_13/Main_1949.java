import java.util.*;
import java.io.*;

public class Main_1949 {
    static int[][] dp;
    static class Town{
        int index;
        List<Town> link;

        public Town(int index){
            this.index = index;
            this.link = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Town> list = new ArrayList<>();
        dp = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            list.add(new Town(i));
            dp[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            list.get(a).link.add(list.get(b));
            list.get(b).link.add(list.get(a));
        }

        dfs(list.get(0), -1);

        System.out.println(Math.max(dp[0][0], dp[0][1]));
    }

    static void dfs(Town now, int before){
        int n = now.index;
        for(Town next : now.link){
            int tmp = next.index;
            if(tmp != before){
                dfs(next, n);
                dp[n][0] += Math.max(dp[tmp][0], dp[tmp][1]);
                dp[n][1] += dp[tmp][0];
            }
        }
    }
}