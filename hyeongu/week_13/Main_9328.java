import java.util.*;
import java.io.*;
public class Main_9328 {
	static int N, M, answer, key;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			answer = 0;
			char[][] arr = new char[N + 2][M + 2];
			int[][] visit = new int[N + 2][M + 2];
			
			for(int i = 0; i < N; i++) {
				String str = br.readLine();
				for(int j = 0; j < M; j++) {
					arr[i + 1][j + 1] = str.charAt(j);
				}
			}
			
			String key_str = br.readLine();
			key = 1;
			if(!key_str.equals("0")) {
				for(int i = 0; i < key_str.length(); i++) {
					int tmp = key_str.charAt(i) - 'a';
					key += 2 << tmp;
				}
			}
			visit[0][0] = key;
			findMap(0, 0, arr, visit);
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	static void findMap(int r, int c, char[][] arr, int[][] visit) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r, c});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			r = now[0];
			c = now[1];
			
			if(arr[r][c] == '$') {
				answer++;
				arr[r][c] = '.';
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isVisitable(nr, nc, arr, visit)) {
					continue;
				}
				
				visit[nr][nc] = (visit[nr][nc] | key);
				q.offer(new int[] {nr, nc});
			}
		}
	}
	
	static boolean isVisitable(int r, int c, char[][] arr, int[][] visit) {
		if(r < 0 || c < 0 || r >= N + 2 || c >= M + 2 || arr[r][c] == '*') {
			return false;
		}
		if((visit[r][c] & key) == key) {
			return false;
		}
		if(arr[r][c] == '.' || arr[r][c] == '$' || arr[r][c] == '\0') {
			return true;
		}
		if(arr[r][c] >= 'a' && arr[r][c] <= 'z') {
			key = key | (2 << (arr[r][c] - 'a'));
			return true;
		}
		if(hasKey(key, arr[r][c])) {
			return true;
		}
		return false;
	}
	
	static boolean hasKey(int now, char door) {
		int door_num = 2 << (door - 'A');
		
		return (now & door_num) > 0;
	}
}