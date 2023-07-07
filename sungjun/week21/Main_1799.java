package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1799 {
	static int N, result, tmp;
	static int[] dr = {-1, -1};
	static int[] dc = {1, -1};
	static int[][] map;
	static boolean[][] blocked;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		blocked = new boolean[N][N];
		result = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
		
				if(cur == 0) blocked[i][j] = true;
			}
		}
		
		tmp = 0;
		solve(0, 0, 0, 0);	// 검은칸
		result += tmp;
		
		tmp = 0;
		solve(1, 0, 1, 0);	// 흰칸
		result += tmp;
		
		System.out.println(result);
	}
	
	private static void solve(int type, int r, int c, int cnt) {
		tmp = Math.max(tmp, cnt);
		
		int nr = r;
		int nc = c;
		
		if(nc > N-1) {
			nr++;
			
			// 줄바꿈이 일어났을 때 새로운 검은 칸 시작 위치
			if(type == 0) {
				nc = nr % 2;
			}
			
			// 줄바꿈이 일어났을 때 새로운 흰 칸 시작 위치
			if(type == 1) {
				nc = (nr % 2 + 1) % 2;
			}
		}
		
		if(nr > N-1) return;
		
		// 현재 칸에 비숍을 놓을 수 있다면
		if(isPossible(nr, nc)) {
			map[nr][nc] = 1;
			solve(type, nr, nc+2, cnt+1);	// 같은 색의 칸만 검사하기 위해 두 칸씩 이동	
			map[nr][nc] = 0;
		}
		
		solve(type, nr, nc+2, cnt);
	}
	
	// 현재 칸에 비숍을 놓았을 때 공격 범위 안에 다른 비숍이 있는지 체크
	private static boolean isPossible(int r, int c) {
		if(map[r][c] == 1 || blocked[r][c]) return false;
		
		for (int i = 0; i < 2; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			while(check(nr, nc)) {
				if(map[nr][nc] == 1) return false;
				nr += dr[i];
				nc += dc[i];
			}
		}
		
		return true;
	}
	
	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
