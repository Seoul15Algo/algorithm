import java.util.*;
import java.io.*;
public class Main_11066 {
    static int[][] dp;          // dp[i][j] : i부터 j까지 파일의 크기 합
    static int[][] dp_sum;      // dp_sum[i][j] : i부터 j까지 합칠 때 최소비용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            dp = new int[N + 1][N + 1];
            dp_sum = new int[N + 1][N + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for(int i = 1; i <= N; i++) {
                dp[i][i] = Integer.parseInt(st.nextToken());
            }

            // 탐색하는 구간의 크기를 1부터 N까지 늘려가며 앞에서부터 탐색
            for(int size = 1; size < N; size++) {
                for(int i = 1; i + size <= N; i++) {
                    findMin(i, i + size);
                }
            }
            sb.append(dp_sum[1][N]).append("\n");
        }
        System.out.println(sb.toString());
    }

    static void findMin(int start, int end) {
        int mid = start;
        int min = Integer.MAX_VALUE;
        int tmp = 0;

        //  i ~ j는 (i ~ k) + (k ~ j) 로 표현할 수 있으므로
        // i < k < j 인 k 중에서 최솟값이 되는 k를 탐색
        while(mid < end) {
            int now = dp[start][mid] + dp[mid + 1][end] + dp_sum[start][mid] + dp_sum[mid + 1][end];

            if(now < min) {
                min = now;
                tmp = dp[start][mid] + dp[mid + 1][end];
            }

            mid++;
        }
        dp[start][end] = tmp;           // 합친 뒤의 파일의 크기
        dp_sum[start][end] = min;       // 현재까지의 비용의 합
    }
}