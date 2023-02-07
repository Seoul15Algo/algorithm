import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055 {
	static int R,C; //지도 행,열
	static char [][] map; //지도
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		Queue <int[]> waterQ = new LinkedList<>(); //물 이동 큐
		Queue <int[]> hedgeQ = new LinkedList<>(); //고슴도치 이동 큐
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'S') { //고슴도치 큐에 넣기
					hedgeQ.offer(new int[] {i,j,0});
				}
				if(map[i][j] == '*') { //물이면 큐에 넣기
					waterQ.offer(new int[] {i,j});
				}
			}
		}
		
		while(!hedgeQ.isEmpty()) { //고슴도치 큐가 빌때까지
			
			int w_len = waterQ.size(); //물 큐 사이즈만큼
			for (int i = 0; i < w_len; i++) {
				int[]a = waterQ.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = a[0]+ dr[d];
					int nc = a[1]+ dc[d];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc] == '.') {
						map[nr][nc] = '*'; // map 에 물부분 *저장
						waterQ.offer(new int[] {nr,nc});
					}
				}
			}
			
			//물 이동 후, 고슴도치 이동
			int h_len = hedgeQ.size();
			for (int i = 0; i < h_len; i++) {
				int[]a = hedgeQ.poll();
				for (int d = 0; d < 4; d++) {
					int nr = a[0]+ dr[d];
					int nc = a[1]+ dc[d];
					int time = a[2];
					
					if(!check(nr,nc)) continue;
					if(map[nr][nc] =='D') { //탈출
						System.out.println(time+1);
						return;
					}
					if(map[nr][nc] == '.') {
						map[nr][nc] ='S'; //기존위치 고슴도치로 채워줌
						hedgeQ.offer(new int[] {nr,nc,time+1});
					}
				}
			}
		}
		
		//여기 도달하면 탈출하지 못했다는 의미
		System.out.println("KAKTUS");

	}
	public static boolean check(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<R && nc<C;
	}

}
