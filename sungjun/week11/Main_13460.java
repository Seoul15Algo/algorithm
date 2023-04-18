package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13460 {
	static int N, M, result;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int[] hole;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bfr.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		hole = new int[2];
		result = Integer.MAX_VALUE/1000;
		
		int rr = 0, rc = 0, br = 0, bc = 0;
		
		for (int i = 0; i < N; i++) {
			char[] cs = bfr.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				map[i][j] = cs[j];
				if(map[i][j] == 'O') {
					hole[0] = i;
					hole[1] = j;
				}
				
				if(map[i][j] == 'R') {
					rr = i;
					rc = j;
				}
				
				if(map[i][j] == 'B') {
					br = i;
					bc = j;
				}
			}
		}
		
		dfs(0, rr, rc, br, bc);
			
		System.out.println(result == Integer.MAX_VALUE/1000 ? -1 : result);
	}
	
	private static void dfs(int cnt, int rr, int rc, int br, int bc) {
		if(cnt >= 10 || cnt >= result) {	// 갱신된 최소값 이상이거나, 10번 이상 진행했다면 리턴
			return;
		}
				
		for (int i = 0; i < 4; i++) {
			// 빨간 구슬과 파란 구슬 모두 움직일 수 없는 상태라면 continue
			if(!check(rr+dr[i], rc+dc[i], 0) && !check(br+dr[i], bc+dc[i], 1)) continue;
			int[] check = tilt(i, rr, rc, br, bc);	// 기울이기 수행
			
			if(check[0] == 1) {		// 빨간 공만 구멍에 빠졌다면
				result = Math.min(result, cnt+1);	// 최소값 갱신하고 리턴
				return;
			}
			
			if(check[0] == 2) {		// 두 공 모두 구멍에 빠지지 않았다면 이어서 진행
				dfs(cnt+1, check[1], check[2], check[3], check[4]);
			}
			
			// 맵 원위치
			map[check[1]][check[2]] = '.';
			map[check[3]][check[4]] = '.';
			map[rr][rc] = 'R';
			map[br][bc] = 'B';
		}
	}
	
	// 기울이기
	private static int[] tilt(int dir, int rr, int rc, int br, int bc) {
		boolean redHole = false;	// 각 공이 구멍에 빠졌는지 확인
		boolean blueHole = false;
		
		while(true) {
			boolean flag = false;	// 공이 하나라도 움직였는지 확인
			
			if(!redHole) {		// 아직 구멍에 빠지지 않았다면 공 이동
				if(check(rr+dr[dir], rc+dc[dir], 0)) {
					map[rr][rc] = '.';		
					rr += dr[dir];
					rc += dc[dir];
					map[rr][rc] = 'R';
					
					// 이동시킨 공이 구멍에 빠졌는지 체크
					if(rr == hole[0] && rc == hole[1]) {
						redHole = true;
						map[rr][rc] = 'O';
					}
					
					flag = true;
				}
			}
			
			if(!blueHole) {
				if(check(br+dr[dir], bc+dc[dir], 1)) {
					map[br][bc] = '.';
					br += dr[dir];
					bc += dc[dir];
					map[br][bc] = 'B';
					
					if(br == hole[0] && bc == hole[1]) {
						blueHole = true;
						map[br][bc] = 'O';
					}
					
					flag = true;
				}
			}
			
			// 두 공 모두 더이상 움직일 수 없다면 break
			if(!flag) break;
		}
		
		if(blueHole) return new int[] {0, rr, rc, br, bc};	// 파란 공만 빠졌다면
		if(redHole) return new int[] {1, rr, rc, br, bc};	// 빨간 공만 빠졌다면
		return new int[] {2, rr, rc, br, bc};	// 두 공 모두 빠지지 않았다면
	}
	
	// 공을 움직일 수 있는지 확인
	private static boolean check(int r, int c, int type) {
		if(type == 0) {		// 빨간 공
			return map[r][c] != '#' && map[r][c] != 'B';
		}
		// 파란 공
		return map[r][c] != '#' && map[r][c] != 'R';
	}

}