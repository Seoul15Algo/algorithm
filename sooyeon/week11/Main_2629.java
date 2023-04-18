import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2629 {
	static int N,M; //추의개수, 구슬의 개수
	static int[] weight;
	static boolean[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		weight = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new boolean[N+1][40001];
		//초깃값 설정
		dp[1][weight[1]] = true;
			
		for (int i = 2; i < N+1; i++) {
			dp[i][weight[i]] = true;
			for (int j = 0; j < 40001; j++) {
				if(dp[i-1][j]) { //이전 값이 true일 때
					//그 값 , 그값-내값, 그값+내값 true
					dp[i][j] = true;
					dp[i][j+weight[i]] = true;
					dp[i][Math.abs(j-weight[i])] = true;

				}
			}
		}
		
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			int biz = Integer.parseInt(st.nextToken());
			if(dp[N][biz]) {
				sb.append("Y ");
			}
			if(!dp[N][biz]) {
				sb.append("N ");
			}
		}
		
		System.out.println(sb.toString());
	}
}