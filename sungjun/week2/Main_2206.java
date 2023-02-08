package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206 {
	static int N, M;
	static int count = 0;
	static int[][] map;		// 미로
	static int[][] dist;	// 거리
	static int[] rv = {0, 1, 0, -1};
	static int[] cv = {1, 0, -1, 0};
	static Queue<int[]> q = new LinkedList<>();
	static boolean[][][] visited;		// 벽을 부순 경우와 부수지 않은 경우의 방문 체크 노드
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(N == 1 && M == 1) {
			System.out.println(1);
			System.exit(0);
		}
		
		map = new int[N][M];
		dist = new int[N][M];
		visited = new boolean[2][N][M];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Character.getNumericValue(s.charAt(j));
			}
		}
		
		// 출발 위치
		q.add(new int[] {0, 0, 0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();		// 현재 위치
			
			// 사방탐색 진행
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + rv[i];
				int nc = cur[1] + cv[i];
				
				if(nr < 0 || nr > N-1 || nc < 0 || nc > M-1) continue;
				
				// 벽이라면
				if(map[nr][nc] == 1) {
					if(cur[2] == 0 && !visited[1][nr][nc]) {	// 아직 벽을 부순 적이 없고, 해당 벽이 부숴진 적이 없다면
						visited[cur[2]][nr][nc] = true;		// 해당 벽 부수기
						dist[nr][nc] = dist[cur[0]][cur[1]] + 1;	// 현재 거리 + 1
						q.add(new int[] {nr, nc, 1}); 	// 벽 부순 내용까지 bfs에 담기
					}
					continue;
				}
				
				// 벽이 아니라면
				// 해당 칸을 방문한 적이 없다면
				if(!visited[cur[2]][nr][nc]) {
					visited[cur[2]][nr][nc] = true;		// 방문 처리
					dist[nr][nc] = dist[cur[0]][cur[1]] + 1;	// 현재 거리 + 1
					q.add(new int[] {nr, nc, cur[2]});		// 큐에 넣고 bfs
				}
				
				// 도착지점에 도달하면 출력하고 종료
				if(nr == N-1 && nc == M-1) {
					System.out.println(dist[nr][nc] + 1);
					System.exit(0);
				}
			}
		}
		
		System.out.println(-1);
	}
	
}
