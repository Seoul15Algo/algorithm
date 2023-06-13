package week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 참고: https://imgoood.tistory.com/m/108
public class Main_3665 {
	static int T, N, M;
	static int[] team;
	static boolean[][] arr;
	static Queue<Integer> q;
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			team = new int[N+1];
			arr = new boolean[N+1][N+1];
			q = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				int cur = Integer.parseInt(st.nextToken());
				team[cur] = i;
				
				for (int j = 1; j <= N; j++) {
					if(j != cur && !arr[j][cur]) {
						arr[cur][j] = true;
					}
				}
			}
			
			M = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				swap(a, b);
			}
			
			result.append(solve()).append("\n");
		}
		
		System.out.println(result);
	}

	private static void swap(int a, int b) {
		if(!arr[a][b]) {	
			arr[a][b] = true;
			arr[b][a] = false;
			team[a]--;
			team[b]++;
			
			return;
		}
		
		arr[a][b] = false;
		arr[b][a] = true;
		team[a]++;
		team[b]--;
	}
	
	private static String solve() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= N; i++) {
			if(team[i] == 0) q.add(i);
		}
		
		for (int i = 1; i <= N; i++) {
			if(q.isEmpty()) return "IMPOSSIBLE";
			if(q.size() > 1) return "?";
			
			int cur = q.poll();
			sb.append(cur + " ");
			
			for (int j = 1; j <= N; j++) {
				if(arr[cur][j]) {
					arr[cur][j] = false;
					if(--team[j] == 0) q.add(j);
				}
			}
		}
		
		return sb.toString();
	}

}
