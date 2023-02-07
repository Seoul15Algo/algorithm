import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N,M,direction; 
	static int r,c; //좌표
	static int[][] ground; //땅
	static int[] dr = {0,-1,0,1}; //왼 위 오 아 순서
	static int[] dc = {-1,0,1,0};
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ground = new int[N][M];
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		direction = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		move(r,c,direction);
		System.out.println(count);

	}
	public static void move(int r, int c, int direction) {
		if(ground[r][c] == 0) {
			ground[r][c] =-1;
			count++;
		}
		for (int i = direction; i > direction-4; i--) {
			int icopy =i;
			if(icopy<0) {
				icopy = icopy+4;
			}
			
			int nr = r+dr[icopy];
			int nc = c+dc[icopy];
			if(!check(nr,nc)) break;
			
			//방향이 비었을 경우
			if(ground[nr][nc] == 0) { 
				move(nr, nc, i-1<0 ? i+3 : i-1 );
				return;
			}
		}
		//바라보는 방향 반대쪽이 벽이 아닐때, 후진
		int mdirection =  direction;
		if(direction -1 <0) {
			mdirection = direction+4;
		}
		if(ground[r+dr[mdirection-1]][c+dc[mdirection-1]] != 1) {
			move(r+dr[mdirection-1], c+dc[mdirection-1], direction);
		}
		//아무것도 할 수 없을 때
		return;
		
	}
	private static boolean check(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<M;
	}

}
