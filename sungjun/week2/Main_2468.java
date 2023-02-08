package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2468 {
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int max;
	static int result;
	static int[] rv = {0, 1, 0, -1};
	static int[] cv = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > max) {
					max = map[i][j];
				}
			}
		}
		
		result = 0;
		
		// 빗물이 1부터 가장 높은 칸이 다 잠길때까지 내리는 경우를 모두 체크
		for (int i = 1; i <= max; i++) {
			linkedArea(i);
		}
		
		System.out.println(result);
	}
	
	// 빗물의 높이를 매개변수로 받아서 dfs 진행
	private static void linkedArea(int n) {
		int count = 0;
		visited = new boolean[N][N];
		for (int i = 0; i <N; i++) {
			for (int j = 0; j < N; j++) {
				// 아직 방문하지 않은 곳이면서 빗물에 잠기지 않는 곳이라면
				if(map[i][j] - n >= 0 && !visited[i][j]) {
					count += dfs(i, j, n);	// 해당 위치와 이어진 빗물에 잠기지 않는 구역을 모두 탐색
				}
			}
		}
		
		result = Math.max(result, count);
	}

	private static int dfs(int x, int y, int rain) {
		visited[x][y] = true;
		
		// 사방탐색
		for (int i = 0; i < 4; i++) {
			int a = x + rv[i];
			int b = y + cv[i];
			
			if(a < 0 || b < 0 || a > N-1 || b > N-1) continue;	// 배열의 범위를 벗어나지 않도록 예외처리
			if(visited[a][b]) continue;		// 이미 방문한 곳은 패스
			if(map[a][b] >= rain) {		// 빗물에 잠기지 않는다면
				dfs(a, b, rain);		// 해당 지역 다시 사방탐색
			}
		}
		
		return 1;	// 이어진 전체 구역을 하나로 카운트
	}

}