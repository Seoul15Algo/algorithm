package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_16954 {
	final static int N = 8;
	static int[][] map;
	static boolean[][] visited;
	static Queue<int[]> walls;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		map = new int[N][N];
		visited = new boolean[N][N];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		walls = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			char[] cs = br.readLine().toCharArray();
			
			for (int j = 0; j < N; j++) {
				switch (cs[j]) {
				case '.':
					map[i][j] = 0;
					break;
				case '#':
					map[i][j] = 1;
					break;
				default:
					break;
				}
			}
		}
		
		System.out.println(bfs());
		
	}

	private static int bfs() {		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {N-1, 0, 0});		// r, c, 경과시간
		visited[N-1][0] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			boolean flag = false;
						
			for (int i = 0; i < 8; i++) {
				int r = cur[0]+dr[i];
				int c = cur[1]+dc[i];
				
				if(!check(r, c)) continue;
				if(visited[r][c]) continue;
				if(r-cur[2] >= 0) {		// 이동할 위치에서 경과 시간만큼 위로 이동한 칸이 벽인지 체크
					if(map[r-cur[2]][c] == 1) {		// 즉, 이동할 위치에 벽이 있는지 체크
						continue;
					}
					
					if(r-cur[2]-1 >= 0) {	// 이동할 위치의 한 칸 위가 벽인지 체크
						if(map[r-cur[2]-1][c] == 1) {
							continue;
						}
					}
				}
				if(r == 0 && c == N-1) return 1;	// 목적지 도달 시 1 반환
				
				visited[r][c] = true;
				q.add(new int[] {r, c, cur[2]+1});
				flag = true;
			}
			
			// 8방중 어떤 곳으로도 이동이 불가능할 때
			if(!flag) {
				if(cur[0]-cur[2]-1 >= 0) {	// 현위치의 한 칸 위가 벽인지 체크
					if(map[cur[0]-cur[2]-1][cur[1]] == 1) {
						continue;
					}
				}
				
				q.add(new int[] {cur[0], cur[1], cur[2]+1});
			}
		}
		
		return 0;		// 목적지까지 도착하지 못할 경우 0 반환
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}