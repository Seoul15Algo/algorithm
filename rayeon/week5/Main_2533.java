package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2533 {
	static int N; // 정점 개수
	static boolean[] v; // 정점 방문 처리 배열
	static ArrayList<ArrayList<Integer>> g; // 그래프
	static int[][] DP; // 해당 정점이 얼리어답터인 경우와 아닌 경우의 최소 얼리어답터 개수를 담는 DP 배열 
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		DP = new int[N][2]; // 해당 정점이 얼리어답터인 경우와 아닌 경우를 담을 수 있도록 2차원 배열 생성
		v = new boolean[N];
		g = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			g.add(new ArrayList<>());
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken()) - 1;
			int e = Integer.parseInt(st.nextToken()) - 1;
			
			g.get(s).add(e);
			g.get(e).add(s);
		}
		
		dfs(0); 
		System.out.println(Math.min(DP[0][0], DP[0][1]));
	}

	static void dfs(int now) {
		v[now] = true;
		
		// now 정점의 DP 배열 초기화
		DP[now][0] = 0; // 자기 자신이 얼리어답터가 아니면 얼리어답터의 최소 개수는 0
		DP[now][1] = 1; // 자기 자신이 얼리어답터라면 얼리어답터의 최소 개수는 1
		
		// 부모 노드는 방문 처리 되므로 자식 노드부터 탐색된다.
		for (int next : g.get(now)) {
			if (v[next])
				continue;
			
			dfs(next);
			
			// 자신이 얼리어답터가 아니라면 모든 인접 노드가 얼리어답터가 돼야 한다. 
			// 따라서 인접 노드가 얼리어답터일 때의 얼리어답터 최소 개수 받아오기
			DP[now][0] += DP[next][1];
			
			// 자신이 얼리어답터라면 인접 노드가 꼭 얼리어답터일 필요는 없다.
			// 그러므로 인접 노드가 얼리어답터일 때와 아닐 때의 얼리어답터 최소 개수를 비교한다.
			DP[now][1] += Math.min(DP[next][0], DP[next][1]);
		}
	}
}
