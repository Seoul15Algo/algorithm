import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987 {
	static int R,C; //행,열
	static int [][] board;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean[] visited = new boolean[26]; //알파벳 방문 배열
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j)-'A';
			}
		}
		
		dfs(0,0,0); //r,c,카운트
		System.out.println(result);

	}
	public static void dfs(int r, int c, int count) {
		if(visited[board[r][c]]) { //방문했던 알파벳이라면
			result = Math.max(result, count);
			return;
		}
		visited[board[r][c]] = true; //방문했다는 표시를 남기고
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(!check(nr,nc)) continue;
			dfs(nr,nc,count+1); //재귀
			
		}
		visited[board[r][c]] = false; //재귀를 벗어났을 때, 방문 취소
		
	}
	public static boolean check(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<R && nc<C;
	}

}
