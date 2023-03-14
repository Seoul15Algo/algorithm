import java.io.*;

public class Main_9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int N = str1.length() + 1;
        int M = str2.length() + 1;

        int[][] dp = new int[N][M];

        for(int i = 0; i < N - 1; i++){
            for(int j = 0; j < M - 1; j++){
                // 문자가 같을 경우 이전까지의 dp + 1
                // 문자가 연속되는 경우가 있기 때문에 대각선 위의 dp에 +1
                if(str1.charAt(i) == str2.charAt(j)){
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                    continue;
                }
                // 같은 문자가 나오지 않으면 왼쪽과 위의 dp중 최대를 저장
                dp[i + 1][j + 1] = Math.max(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        System.out.println(dp[N - 1][M - 1]);
    }
}
