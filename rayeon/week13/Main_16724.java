package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// BJ 16724 : 피리 부는 사나이
public class Main_16724 {
	static int N, M;
	static Map<Character, int[]> directions;
	static char[][] map;
	static int[][] visited;
	
	static int dfs(int num, int r, int c) {
		visited[r][c] = num;
		
		int nr = r + directions.get(map[r][c])[0];
		int nc = c + directions.get(map[r][c])[1];
		
		if (visited[nr][nc] == 0)
			return dfs(num, nr, nc);
	
		return visited[nr][nc];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		directions = new HashMap<Character, int[]>();
		directions.put('U', new int[] {-1, 0});
		directions.put('R', new int[] {0, 1});
		directions.put('D', new int[] {1, 0});
		directions.put('L', new int[] {0, -1});
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int result = 0;
		int number = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] == 0) {
					number++;
					
					if (number == dfs(number, i, j)) {
						result++;
					}
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
}