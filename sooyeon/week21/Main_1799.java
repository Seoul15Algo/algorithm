import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1799 {
	static int N;
	static int[][] chess;
	static int[] dr = {-1,1,-1,1};
	static int[] dc = {-1,-1,1,1};
	
	static int oCnt, eCnt;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		chess = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				chess[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] eVisited = new boolean[N][N];
		eSearch(eVisited,0,0,0);
		
		boolean[][] oVisited = new boolean[N][N];
		oSearch(oVisited,0,1,0);
		
		System.out.println(eCnt+oCnt);
		
	}
	private static void eSearch(boolean[][] visited, int r, int c, int cnt) {
		eCnt = Math.max(eCnt, cnt);
		
		if(c >= N) { //행이 홀수 일 경우 1 부터 시작, 짝수일 경우 0 부터 시작
			r+= 1;
			c = (r%2 == 0) ? 0 : 1;
		}
		
		if(r >= N) return; //행 좌표 초과
		
		if(isAble(visited,r,c)) { //놓을 수 있으면 놓고 탐색
			visited[r][c] = true;
			eSearch(visited,r,c+2,cnt+1);
			visited[r][c] = false;
		}
		
		eSearch(visited,r,c+2,cnt);
		
	}
	private static void oSearch(boolean[][] visited, int r, int c, int cnt) {
		oCnt = Math.max(oCnt, cnt);
		
		if(c >= N) { //행이 홀수 일 경우 0 부터 시작, 짝수일 경우 1 부터 시작
			r+= 1;
			c = (r%2 == 0) ? 1 : 0;
		}
		
		if(r >= N) return; //행 좌표 초과
		
		if(isAble(visited,r,c)) { //놓을 수 있으면 놓고 탐색
			visited[r][c] = true;
			oSearch(visited,r,c+2,cnt+1);
			visited[r][c] = false;
		}
		
		oSearch(visited,r,c+2,cnt);
		
	}
	private static boolean isAble(boolean[][] visited, int r, int c) {
		if(chess[r][c] == 0) return false;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			for (int i = 1; i <= N; i++) {
				if(!check(nr,nc)) break;
				
				if(visited[nr][nc]) return false;
				nr += dr[d];
				nc += dc[d];
			}
		}
		
		return true;
	}
	private static boolean check(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}

}
