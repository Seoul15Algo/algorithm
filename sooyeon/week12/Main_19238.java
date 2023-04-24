import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238 {
	static int N,M,F; //영역, 승객수, 연료
	static int x,y; //시작 위치
	static int[][] map;
	static boolean[][] visited;
	static boolean[] check;
	static List<E> end;
	static boolean flag; //이동 불가능(벽에 막혀서)
	static int[] dr = {-1,0,0,1};
	static int[] dc = {0,-1,1,0};
	static class E implements Comparable<E>{
		int ex;
		int ey;
		public E(int ex, int ey) {
			super();
			this.ex = ex;
			this.ey = ey;
		}
		@Override
		public int compareTo(E o) {
			if(this.ex == o.ex) {
				return Integer.compare(this.ey, o.ey);
			}
			return Integer.compare(this.ex, o.ex);
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		check = new boolean[M+1]; //승객 다 도착했는지
		end = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) { //벽을 -1로 바꿔줌
					map[i][j] = -1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		x = Integer.parseInt(st.nextToken())-1;
		y = Integer.parseInt(st.nextToken())-1;
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[a-1][b-1] = i+1; //맵에 몇 번째 사람의 시작 지점인지 표시해 줌
			end.add(new E(c-1,d-1)); // 1번째 사람 -> 0번째 인덱스
		}
		
		while(!isDone()) {
			//제일 가까운 손님찾기
			bfsSearchP();

			if(F <= 0 || flag) {
				System.out.println(-1);
				return;
			}
			
			//움직이기
			bfsMove();
			if(F < 0 || flag) {
				System.out.println(-1);
				return;
			}

		}
		System.out.println(F);
	}
	static void bfsMove() {
		//도착지 설정
		int index = map[x][y]-1;
		int fx = end.get(index).ex;
		int fy = end.get(index).ey;
		
		visited = new boolean[N][N];
		visited[x][y] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y,0,F}); //위치,이동 수,연료
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			
			if(cur[3] == 0) { //연료가 바닥 날 경우
				F = cur[3];
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(!check(nr,nc)) continue;
				if(visited[nr][nc]) continue;
				if(nr == fx && nc == fy) {
					//연료 온 만큼 추가
					F = cur[3]-1;
					F += (cur[2]+1)*2;
					check[index+1] = true;
					//현재 위치 바꿔주기
					x = nr;
					y = nc;
					return;
				}
				if(map[nr][nc] != -1) { //벽이 아닐 때 이동
					visited[nr][nc] = true;
					q.offer(new int[] {nr,nc,cur[2]+1,cur[3]-1});
				}

			}
		}
		//벽에 막혀서 이동 불가능일 때
		flag = true;
		
	}
	static void bfsSearchP() {
		visited = new boolean[N][N];
		visited[x][y] = true;
		int keepX = Integer.MAX_VALUE,keepY = Integer.MAX_VALUE;
		
		if(map[x][y] > 0 && !check[map[x][y]]) { //택시 시작위치에 바로 손님이 있는 경우
			return;
		}
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {x,y,F}); //위치,연료
		
		PriorityQueue<E> pq = new PriorityQueue<>();
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for (int i = 0; i < size; i++) { //큐의 사이즈만큼 돌려줌
				int cur[] = q.poll();
				
				if(cur[2] < 0) { //연료가 바닥 날 경우
					F = cur[2];
					return;
				}
				
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];
					if(!check(nr,nc)) continue;
					if(visited[nr][nc]) continue;
					
					if(map[nr][nc] > 0 && !check[map[nr][nc]]) { //승객이 있고 안 데려다 준 승객인 경우
						pq.offer(new E(nr,nc)); //pq에 넣는다
						F = cur[2]-1;
					}
					if(map[nr][nc] != -1) { //벽이 아닐 때 이동
						visited[nr][nc] = true;
						q.offer(new int[] {nr,nc,cur[2]-1});
					}
				}
			}
			if(!pq.isEmpty()) {
				E taxi = pq.poll();
				x = taxi.ex;
				y = taxi.ey;
				return;
			}
		}
		
		//벽에 막혀서 이동 불가능일 때
		flag = true;
		
	}
	static boolean check(int nr, int nc) {
		return nr<N && nc<N && nr>=0 && nc>=0;
	}
	static boolean isDone() { //모든 승객이 목적지에 갔는지
		for (int i = 1; i < M+1; i++) {
			if(!check[i]) {
				return false;
			}
		}
		return true;
	}

}