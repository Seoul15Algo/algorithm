import java.io.*;
import java.util.Arrays;

public class Main_3687 {
    static final Long INF = Long.MAX_VALUE / 100;
    static final int N = 15;
    static final int M = 7;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dp = new long[15][7];
        long[] tail = {INF, 1, 7, 4, 2, 0, 8};

        for(int i = 0; i < N; i++){
            Arrays.fill(dp[i], INF);
        }
        dp[0][1] = 1;
        dp[0][2] = 7;
        dp[0][3] = 4;
        dp[0][4] = 2;
        dp[0][5] = 6;
        dp[0][6] = 8;

        for(int i = 1; i < N; i++){
            for(int j = 0; j < M; j++){
                for(int k = 0; k < M - j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j + k] * 10 + tail[M - k - 1]);
                }
            }
        }

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0){
            int now = Integer.parseInt(br.readLine());

            sb.append(dp[(now - 1) / M][(now - 1) % M]).append(" ");
            if(now % 2 > 0){
                sb.append(7);
                now -= 3;
            }
            while(now > 0){
                sb.append(1);
                now -= 2;
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}