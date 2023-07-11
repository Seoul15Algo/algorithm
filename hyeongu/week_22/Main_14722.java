import java.io.*;
import java.util.*;

public class Main_14722 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // dp 배열 : 지금까지 먹은 우유의 수
        // 0 : x, 1 : 딸, 2 : 딸초, 3 : 딸초바, 4 : 딸초바딸, ...
        int[][] dp = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++){
                // 현재 경로까지 올 때 먹은 우유의 수
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                // 이번 칸의 우유
                int now = Integer.parseInt(st.nextToken());

                // 먹어야하는 우유와 이번 칸의 우유가 같은경우 dp++
                if(dp[i][j] % 3 == now % 3){
                    dp[i][j]++;
                }
            }
        }
        System.out.println(dp[N][N]);
    }
}