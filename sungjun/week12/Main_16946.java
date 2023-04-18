package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16946 {
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	// Set 사용을 위한 좌표 클래스
	static class Coords {
		int r;
		int c;
		
		public Coords(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + c;
			result = prime * result + r;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coords other = (Coords) obj;
			if (c != other.c)
				return false;
			if (r != other.r)
				return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = cs[j]-'0';
				if(map[i][j] > 0) visited[i][j] = true;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				bfs(i, j);	// 벽이 아닌 곳에서 bfs 시작
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j]%10);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		Set<Coords> walls = new HashSet<>();
		
		q.add(new int[] {r, c});
		visited[r][c] = true;
		
		int count = 1;
		
		// 빈칸을 전부 탐색하여 크기를 재고, 인접한 벽들을 Set에 넣어서 중복을 방지해준다
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] > 0) {
					walls.add(new Coords(nr, nc));
					continue;
				}
				if(visited[nr][nc]) continue;
				
				count++;
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});
			}
		}
		
		// 빈칸의 구역과 인접해있는 벽들에다가 빈칸의 크기만큼 더해준다
		Iterator<Coords> it = walls.iterator();
				
		while(it.hasNext()) {
			Coords cur = it.next();
			map[cur.r][cur.c] += count;
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}