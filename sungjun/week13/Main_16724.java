package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16724 {
	static int N, M, result, count;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] cs = br.readLine().toCharArray();
			
			for (int j = 0; j < M; j++) {
				switch (cs[j]) {
				case 'U':
					map[i][j] = 0;
					break;
				case 'R':
					map[i][j] = 1;
					break;
				case 'D':
					map[i][j] = 2;
					break;
				case 'L':
					map[i][j] = 3;
					break;
				default:
					break;
				}
			}
		}
		
		boolean flag = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == -1) continue;
				if(count == M*N) {	// 모든 칸 탐색 완료시 for문 종료
					flag = true;
					break;
				}
				findLoop(i, j, map[i][j]);	// 싸이클 체크
			}
			if(flag) break;
		}
		
		System.out.println(result);
	}

	private static int findLoop(int r, int c, int d) {		
		if(map[r][c] == -1) {	// 이미 싸이클이 생성된 구간이랑 이어진다면
			return -1;		// 새 안전구역 생성할 필요 없이 기존 싸이클에 포함
		}
		
		if(map[r][c] == -2) {	// 이번 탐색에서 지나온 칸과 이어진다면
			result++;		// 즉, 새 싸이클이 생성된다면 안전구역 하나 생성
			return -1;
		}
		
		map[r][c] = -2;		// 이번 탐색에서 방문했다는 표시
		int nr = r+dr[d];
		int nc = c+dc[d];
		count++;	// 지금까지 탐색 완료한 칸의 개수
		
		return map[r][c] = findLoop(nr, nc, map[nr][nc]);	// 이번 칸이 가르키는 방향으로 이어서 탐색 진행
	}

}
