package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9328 {
	static int H, W, result;
	static char[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static boolean[] key;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			// 출입구도 bfs로 한번에 탐색하기 위해 원래 사이즈의 맵을 빈 공간으로 둘러싸준다
			map = new char[H+2][W+2];
			visited = new boolean[H+2][W+2];
			key = new boolean[26];
			result = 0;
			
			for (int i = 0; i < H+2; i++) {
				map[i][0] = '.';
				map[i][W+1] = '.';
			}
			
			for (int i = 0; i < W+2; i++) {
				map[0][i] = '.';
				map[H+1][i] = '.';
			}
			
			for (int i = 1; i < H+1; i++) {
				char[] cs = br.readLine().toCharArray();
				
				for (int j = 1; j < W+1; j++) {
					map[i][j] = cs[j-1];
				}
			}
			
			char[] givenKey = br.readLine().toCharArray();
			
			// 이미 가지고 있는 열쇠
			if(givenKey[0] != '0') {
				for (int i = 0; i < givenKey.length; i++) {
					key[givenKey[i]-'a'] = true;
				}
			}
			
			bfs();
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		boolean flag = false;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
						
			for (int i = 0; i < 4; i++) {
				int nr = cur[0]+dr[i];
				int nc = cur[1]+dc[i];
				
				if(!check(nr, nc)) continue;
				if(map[nr][nc] == '*') continue;
				if(visited[nr][nc]) continue;
				if(map[nr][nc] >= 'a' && map[nr][nc] <= 'z') {		// 새로운 열쇠 획득 시
					key[map[nr][nc]-'a'] = true;	// 열쇠 보유 갱신
					map[nr][nc] = '.';		// 열쇠가 있던 칸을 빈칸으로 바꿔준다
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc});	// 이어서 탐색 진행
					flag = true;	// 열쇠 획득 체크
					continue;
				}
				if(map[nr][nc] >= 'A' && map[nr][nc] <= 'Z') {		// 문을 만났으면
					if(key[map[nr][nc]-'A']) {	// 문에 맞는 키를 보유하고 있다면
						map[nr][nc] = '.';		// 문이 있던 칸을 빈칸으로 바꿔준다
						visited[nr][nc] = true;
						q.add(new int[] {nr, nc});		// 이어서 탐색 진행
						continue;
					}
					continue;	// 키가 없다면 큐에 추가하지 않고 continue
				}
				if(map[nr][nc] == '$') {	// 문서 획득 시
					result++;	// 결과값 갱신
					visited[nr][nc] = true;
					map[nr][nc] = '.';	// 문서가 있던 칸을 빈칸으로 바꿔준다
					q.add(new int[] {nr, nc});		// 이어서 탐색 진행
					continue;
				}
				
				visited[nr][nc] = true;
				q.add(new int[] {nr, nc});	// 빈칸이면 이어서 탐색 진행
			}
		}
		
		if(flag) {	// 열쇠를 획득했다면 이전에 못가던 길로 갈 수 있는 가능성이 열렸기 때문에
			visited = new boolean[H+2][W+2];	// 방문 배열 초기화하고 다시 bfs탐색
			bfs();
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < H+2 && c >= 0 && c < W+2;
	}

}
