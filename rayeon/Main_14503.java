import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// ºÏ µ¿ ³² ¼­
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		int[][] g = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++)
				g[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		while(true) {
			if(g[r][c] == 0) {
				g[r][c] = 2;
				result++;
			}
			
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				d = (d+3)%4;
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(g[nr][nc] == 0) {
					r = nr;
					c = nc;
					break;
				}
				
				cnt++;
			}			
			
			if(cnt == 4) {
				if(g[r-dr[d]][c-dc[d]] == 1)
					break;
				else {
					r = r-dr[d];
					c = c-dc[d];
				}
			}
		}
		
		System.out.println(result);
		br.close();
	}
}