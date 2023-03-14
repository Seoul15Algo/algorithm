import java.io.*;

public class Main_1309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // dp[i][0] -> 해당 행에 사자가 있는 경우
        // dp[i][1] -> 해당 행에 사자가 없는 경우
        long[][] dp = new long[N + 1][2];

        dp[1][0] = 2;
        dp[1][1] = 1;

        for(int i = 2; i <= N; i++){
            // 사자가 있는 경우
            // 바로 전 행에도 한마리의 사자가 있는 경우 해당 열이 아닌 열에 존재
            // 바로 전 행에 사자가 없는 경우 2 열에 모두 배치할 수 있음
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] * 2) % 9901;

            // 사자가 없는 경우
            // 바로 전 행의 경우와 관계 없이 한 가지 경우
            dp[i][1] = (dp[i-1][0] + dp[i-1][1]) % 9901;
        }
        long answer = (dp[N][0] + dp[N][1]) % 9901;
        System.out.println(answer);
    }
}