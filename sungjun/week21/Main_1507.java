package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1507 {
	static int N;
	static int[][] city;	// 도시 간 최소 거리
	static boolean[][] visited;		// 도로가 존재하는지 여부
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		city = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean possible = true;
		
		for (int i = 0; i < N; i++) {			
			for (int j = i; j < N; j++) {
				boolean flag = false;

				for (int k = 0; k < N; k++) {
					if(i == k || j == k) continue;
					if(city[i][j] > city[i][k] + city[k][j]) {
						possible = false;	// 주어진 input이 최소 거리가 아닐 때
						break;
					}
					if(city[i][j] == city[i][k] + city[k][j]) {
						flag = true;	// i -> j 가 i -> k -> j 와 같을 때
						break;
					}
				}
				
				if(!possible) break;
				if(!flag) visited[i][j] = true;
			}
			
			if(!possible) break;
		}
		
		System.out.println(possible ? getResult() : -1);
	}
	
	private static int getResult() {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if(visited[i][j]) result += city[i][j];
			}
		}
		
		return result;
	}
}