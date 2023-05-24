import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main_16954 {
	static char[][] chess;
	static int N = 8;
	static int result;
	static int[] dr = {-1,-1,0,1,1,1,0,-1,0}; //위부터 시계방향
	static int[] dc = {0,1,1,1,0,-1,-1,-1,0};
	static Queue<P> wall;
	static class P{
		int x;
		int y;
		public P(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		chess = new char[N][N];
		wall = new LinkedList<>();
		Stack<P> s = new Stack<>();
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				chess[i][j] = str.charAt(j);
				if(chess[i][j] == '#') {
					//벽이라면 스택에 넣기
					s.push(new P(i,j));
				}
			}
		}
		
		//스택에서 빼서 큐에 넣기 -> 제일 밑에있는 벽들부터 넣기 위해서
		while(!s.isEmpty()) {
			wall.offer(s.pop());
		}
		
		//초깃값
		result = 0;
		bfs();
		
		System.out.println(result);

	}
	static void bfs() {
		Queue<P> q = new LinkedList<>();
		q.offer(new P(N-1,0)); //처음 위치
		
		while(!q.isEmpty()) {
			//욱제 이동
			int qsize = q.size();
			
			for (int i = 0; i < qsize; i++) {
				P cur = q.poll();

				if(cur.x == 0 && cur.y== N-1) {
					result = 1;
					return;
				} 
				
				for (int d = 0; d < 9; d++) {
					int nr = cur.x + dr[d];
					int nc = cur.y + dc[d];
					
					if(!check(nr,nc)) continue;
					if(chess[nr][nc] =='.' && !check(nr+dr[0],nc+dc[0])) { //한칸 위가 범위를 벗어나면
						q.offer(new P(nr,nc));
						continue;
					}
					if(chess[nr][nc] =='.' && chess[nr+dr[0]][nc+dc[0]] =='.') { //8방검사 + 그위가 벽이아니라면
						q.offer(new P(nr,nc));
					}
				}
			}
			

			//벽 이동 (q사이즈만큼 돌림)
			int wsize = wall.size();
			for (int i = 0; i < wsize; i++) {
				P curW = wall.poll();
				if(curW.x == N-1) { //벽이 제일 밑칸에 있으면 체스맵에서 빼주고 패스
					chess[curW.x][curW.y] = '.';
					continue;
				}
				chess[curW.x][curW.y] = '.';
				chess[curW.x+1][curW.y] = '#';
				wall.offer(new P(curW.x+1, curW.y)); //x+1 값으로 다시 넣어줌
			}
			
		}
		
	}
	static boolean check(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}

}