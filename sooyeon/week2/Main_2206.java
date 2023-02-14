import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206 {
	static int N,M; //행열
	static int[][] map;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static boolean visit[][][]; //방문여부체크(r,c,벽뿌숨여부 0:안뿌숨, 1:뿌숨)
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new boolean[N][M][2];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j)-'0';
			}

		}
		
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0,0,0,1}); //n,r,벽뿌수기가능여부(0: 가능, 1:불가능), 이동 수
		visit[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			if(cur[0] == N-1 && cur[1] == M-1 ) { //탈출
				System.out.println(cur[3]);
				return;
			}
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0]+dr[d];
				int nc = cur[1]+dc[d];
				
				if(!check(nr,nc))continue;
				if(visit[nr][nc][cur[2]]) continue; //방문 안했을시
				
				if(map[nr][nc] == 0 && !visit[nr][nc][cur[2]]) { //갈수있는 공간 & 방문하지 않았으면
					q.offer(new int[] {nr,nc,cur[2],cur[3]+1});
					visit[nr][nc][cur[2]] = true;
				}
				if(map[nr][nc] == 1 && !visit[nr][nc][cur[2]]) { //벽 && 방문하지 않았으면
					if(cur[2]==0) { //이전에 벽을 뿌순적이 없는 경우
						q.offer(new int[] {nr,nc,1,cur[3]+1});
						visit[nr][nc][1] = true;
					}
				}
				
			}
		}
		//여기 도착하면 탈출 x
		System.out.println(-1);
		
	}

	public static boolean check(int nr, int nc) {
		return nr>=0 && nc>=0 && nr <N && nc<M;
	}

}
