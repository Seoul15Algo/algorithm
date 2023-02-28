import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2533 {
	static int N;
	static List<Integer>[] list;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		dp = new int[N+1][2];
		for (int i = 0; i < N-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(list[a]== null) list[a] = new ArrayList<>();
			if(list[b]== null) list[b] = new ArrayList<>();
			list[a].add(b);
			list[b].add(a);
		}
		
		dp(1,-1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));

	}
	static void dp(int cur, int parent) {
		//0: 얼리어답터x, 1:얼리어답터o
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		
		for (int i = 0; i < list[cur].size(); i++) {
			if(list[cur].get(i) != parent) { //부모랑 같지 않을 때만(같은경우 : 단말노드)
				dp(list[cur].get(i), cur);
				//그전거 0 선택하면 다음거 1선택
				dp[cur][0] += dp[list[cur].get(i)][1];
				//그전거 1선택했었으면 다음거 상관없음(작은 거 선택)
				dp[cur][1] += Math.min(dp[list[cur].get(i)][0], dp[list[cur].get(i)][1]);
			}
		}
		
		
	}

}