package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16985 {
	static int[][] maze;
	static int[][][][] floor;
	static int[] p, r, floorOrder, rotateOrder;
	static boolean[] permVisited;
	static int[] dr = {1, 0, -1, 0, 5, -5};
	static int[] dc = {0, 1, 0, -1, 0, 0};
	static boolean[][] visited;
	static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		maze = new int[25][5];
		floor = new int[5][4][5][5];
		p = new int[] {0, 1, 2, 3, 4};
		r = new int[] {0, 1, 2, 3};
		floorOrder = new int[5];
		rotateOrder = new int[5];
		permVisited = new boolean[5];
		
		for (int i = 0; i < 25; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 5; j++) {
				maze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 각각의 층을 분류하여 저장
		for (int i = 0; i < 25; i++) {
			for (int j = 0; j < 5; j++) {
				floor[i/5][0][i%5][j] = maze[i][j];
			}
		}
		
		// 각각의 층을 회전시킨 정보를 저장
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j < 4; j++) {
				floor[i][j] = rotate(floor[i][j-1]);
			}
		}
	
		perm(0);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	private static void perm(int cnt) {
		if(cnt == 5) {
			doublePerm(0);	// 층의 구성에 따라 매번 모든 층의 회전 횟수를 중복순열로 체크
			return;
		}
		
		for (int i = 0; i < 5; i++) {
			if(permVisited[i]) continue;
			permVisited[i] = true;
			floorOrder[cnt] = p[i];
			perm(cnt+1);
			permVisited[i] = false;
		}	
	}
	
	private static void doublePerm(int cnt) {
		if(cnt == 5) {
			// 전체 미로 지도 재구성
			for (int i = 0; i < 25; i++) {
				for (int j = 0; j < 5; j++) {
					maze[i][j] = floor[floorOrder[i/5]][rotateOrder[i/5]][i%5][j];
				}
			}
						
			// 입구나 출구가 막혀있을 경우
			if(maze[0][0] == 0 || maze[24][4] == 0) return;
			
			visited = new boolean[25][5];
			bfs();
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			rotateOrder[cnt] = r[i];
			doublePerm(cnt+1);
		}
	}

	private static int[][] rotate(int[][] map) {
		int[][] nmap = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				nmap[j][5-i-1] = map[i][j];
			}
		}
		
		return nmap;
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0, 0});
		visited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int t = cur[2];
									
			for (int i = 0; i < 6; i++) {
				// 한칸 이동하는 것으로 계층간 이동하는 경우 방지
				if(r != 0 && r % 5 == 0) { 
					if(i == 2) continue;
				}
				if(r % 5 == 4) {
					if(i == 0) continue;
				}
				
				int nr = r+dr[i];
				int nc = c+dc[i];

				if(!check(nr, nc)) continue;
				if(visited[nr][nc]) continue;
				if(nr == 24 && nc == 4) {
					result = Math.min(result, t+1);
					return;
				}
				
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc, t+1});
			}
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < 25 && c >= 0 && c < 5 && maze[r][c] == 1;
	}
}
