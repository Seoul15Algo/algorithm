import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*가독성 죄송^^..... 클래스 따로 빼기엔 힘이 들었습니다..*/
public class Main_13460 {
	static int N,M;
	static char[][] map;
	static boolean check[][][][];
	static int[] dr = {-1,0,1,0};//위오아왼
    static int[] dc = {0,1,0,-1};
	static int rx,ry,bx,by,hx,hy;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'R') {
					rx = i;
					ry = j;
				}
				if(map[i][j] == 'B') {
					bx = i;
					by = j;
				}
				if(map[i][j] == 'O') {
					hx = i;
					hy = j;
				}
			}
		}
		check = new boolean[11][11][11][11]; //방문배열
		int result = bfs();
		
		System.out.println(result);
	}
	static int bfs() {
		check[rx][ry][bx][by] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {rx,ry,bx,by,0}); //빨간공좌표, 파란공 좌표, count
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			if(cur[4] > 10) { //카운트 10 넘으면 종료
				return -1;
			}
			
			if(cur[0] == hx && cur[1] == hy) { //빨간색공이 홀에 들어가면
				return cur[4];
			}
			
			for (int d = 0; d < 4; d++) {
				int nrx = cur[0];
				int nry = cur[1];
				int nbx = cur[2];
				int nby = cur[3];
				while(map[nrx + dr[d]][nry + dc[d]] != '#') {
					nrx += dr[d];
					nry += dc[d];
					if(nrx == hx && nry == hy) break; //홀 만나면 멈춤
				}
				while(map[nbx + dr[d]][nby + dc[d]] != '#') {
					nbx += dr[d];
					nby += dc[d];
					if(nbx == hx && nby == hy) break; //홀 만나면 멈춤
				}
				
				if(nbx == hx && nby == hy) continue; //파란구슬이 들어갈경우 continue;
				
				if(nrx == nbx && nry == nby) {
					if(d == 0) { //위쪽
						if(cur[0] > cur[2]) {//더 큰 x 값이 뒤로
							nrx-=dr[d];
						}else {
							nbx-=dr[d];
						}
					}
					if(d == 1) { //오른쪽
						if(cur[1] < cur[3]) {//더 작은 y 값이 뒤로
							nry-=dc[d];
						}else {
							nby-=dc[d];
						}
					}
					if(d == 2) { //아래쪽
						if(cur[0] < cur[2]) {//더 작은 x 값이 뒤로
							nrx-=dr[d];
						}else {
							nbx-=dr[d];
						}
					}
					if(d == 3) { //왼쪽
						if(cur[1] > cur[3]) {//더 큰 y 값이 뒤로
							nry-=dc[d];
						}else {
							nby-=dc[d];
						}
					}
				}
				if(!check[nrx][nry][nbx][nby]) {
					check[nrx][nry][nbx][nby] = true;
					q.offer(new int[] {nrx,nry,nbx,nby,cur[4]+1});
				}

			}
		}

		return -1;
	}

}
