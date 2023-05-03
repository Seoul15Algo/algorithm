import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1949 {
	static int N;
	static int[] nums;
	static List<Integer>[] list;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nums = new int[N+1];
		list = new ArrayList[N+1];
		dp = new int[N+1][2]; //N번째 마을이 우수마을에 포함된 경우, 안 된 경우
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>(); //초기화
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			list[b].add(a);
		}
		
		dfs(1,0);
		System.out.println(Math.max(dp[1][0], dp[1][1]));

	}
	static void dfs(int cur, int parent) {
		for (int i = 0; i < list[cur].size(); i++) {
			int child = list[cur].get(i);
			if(child == parent) continue; //방문한 리스트 거르기
			dfs(child, cur);
			dp[cur][0] += Math.max(dp[child][0], dp[child][1]); //포함 안된 경우 : 이전마을 포함된경우 , 안된경우 중 큰 값
			dp[cur][1] += dp[child][0]; //포함 된 경우 : 이전마을 포함 안된 경우
		}
		dp[cur][1] += nums[cur];
		
	}

}
