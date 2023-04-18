import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11066 {
	static int N;
	static int K;
	static int[] nums;
	static int[] sum; //1~i까지의 합
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		for (int t = 0; t < N; t++) {
			K = Integer.parseInt(br.readLine());
			nums = new int[K+1];
			sum = new int[K+1];
			dp = new int[K+1][K+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1]+nums[i];
			}
			
			/*
		      dp 점화식
		      dp[x][y] = x부터 y까지 합치는 비용
		      
		      1. dp[x][x + 1] = sum[x] + nums[x-2]  꼴을 전부 갱신(하나 차이)
		      2. dp[x][x + 2] = sum[x] + nums[x-3]  꼴을 전부 갱신(두개 차이)
		      ...
		    */
			
			for (int i = 1; i <= K; i++) { //몇 장 묶는지
				for (int x = 1; x+i <= K; x++) { //어디부터 묶을 것인지
					int y = x+ i;
					dp[x][y] = Integer.MAX_VALUE;
					for (int mid = x; mid < y; mid++) { //mid범위 (x~(y-1)) 까지 잘라서 최솟값 비고
						dp[x][y] = Math.min(dp[x][y], dp[x][mid] + dp[mid+1][y] + sum[y] - sum[x-1]);
					}
				}
			}
			sb.append(dp[1][K]).append("\n");
			
		}
		System.out.println(sb.toString());
	}

}