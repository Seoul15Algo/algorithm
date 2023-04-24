package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_19238 {
	static int N, M, F, result, count;
	static int[][] map;
	static Passenger[][] passengerMap;
	static int[] driver;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static class Passenger implements Comparable<Passenger> {
		int sr;
		int sc;
		int dr;
		int dc;

		public Passenger(int sr, int sc, int dr, int dc) {
			super();
			this.sr = sr;
			this.sc = sc;
			this.dr = dr;
			this.dc = dc;
		}

		@Override
		public int compareTo(Passenger o) {
			if(this.sr == o.sr) {
				return Integer.compare(this.sc, o.sc);
			}
			return Integer.compare(this.sr, o.sr);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		passengerMap = new Passenger[N][N];
		driver = new int[2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		driver[0] = Integer.parseInt(st.nextToken())-1;
		driver[1] = Integer.parseInt(st.nextToken())-1;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int sr = Integer.parseInt(st.nextToken())-1;
			int sc = Integer.parseInt(st.nextToken())-1;
			int dr = Integer.parseInt(st.nextToken())-1;
			int dc = Integer.parseInt(st.nextToken())-1;
			
			passengerMap[sr][sc] = new Passenger(sr, sc, dr, dc);
			count++;
		}
		
		while(true) {
			if(!pickup()) break;	// 연료가 바닥났거나 길이 막혀있는 경우
			if(count == 0) break;	// 모든 승객 이동 완료
		}
		
		System.out.println(result == -1 ? result : F);
	}

	private static boolean pickup() {
		// 택시의 출발지와 손님의 출발지가 겹칠 경우
		if(passengerMap[driver[0]][driver[1]] != null) {
			Passenger cur = passengerMap[driver[0]][driver[1]];
						
			if(drive(cur.dr, cur.dc)) {				
				count--;
				driver[0] = cur.dr;
				driver[1] = cur.dc;
				passengerMap[cur.sr][cur.sc] = null;
				return true;
			}
			
			result = -1;
			return false;
		}
		
		Queue<int[]> q = new LinkedList<>();
		
		// 우선순위에 따라 승객을 뽑을 큐
		PriorityQueue<Passenger> pq = new PriorityQueue<>();
		q.add(new int[] {driver[0], driver[1], 0});
		boolean[][] visited = new boolean[N][N];
		
		// 가장 가까운 승객까지의 거리
		int passengerDist = Integer.MAX_VALUE/1000;
	
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			// 가장 가까운 승객까지의 거리보다 멀리 가는 경우 프루닝
			if(cur[2] >= passengerDist) break;
			
			for (int i = 0; i < 4; i++) {
				int r = cur[0]+dr[i];
				int c = cur[1]+dc[i];
				
				if(!check(r, c)) continue;
				if(visited[r][c]) continue;
				
				// 같은 거리에 위치한 손님들을 모두 pq에 집어넣음
				if(passengerMap[r][c] != null) {
					pq.add(passengerMap[r][c]);
					passengerDist = cur[2]+1;
					visited[r][c] = true;
					continue;
				}
				
				visited[r][c] = true;
				q.add(new int[] {r, c, cur[2]+1});
			}
		}
		
		// 태울 수 있는 손님이 없다면 (길이 막혀있다면)
		if(pq.isEmpty()) {
			result = -1;
			return false;
		}
		
		Passenger cur = pq.poll();
		count--;
		
		// 손님을 태우러 갈 연료가 부족하다면
		if(passengerDist > F) {
			result = -1;
			return false;
		}
		
		F -= passengerDist;
		driver[0] = cur.sr;
		driver[1] = cur.sc;
		
		// 손님을 목적지까지 이동시킬 수 있다면
		if(drive(cur.dr, cur.dc)) {
			driver[0] = cur.dr;
			driver[1] = cur.dc;
			passengerMap[cur.sr][cur.sc] = null;
			return true;
		}
		
		// 목적지까지 이동시키지 못한다면
		result = -1;
		return false;
	}
	
	private static boolean drive(int rr, int cc) {
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {driver[0], driver[1], 0});
		boolean[][] visited = new boolean[N][N];
		visited[driver[0]][driver[1]] = true;
		int dist = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int r = cur[0]+dr[i];
				int c = cur[1]+dc[i];
				
				if(!check(r, c)) continue;
				if(visited[r][c]) continue;
				if(r == rr && c == cc) {
					dist = cur[2]+1;
					break;
				}
				visited[r][c] = true;
				q.add(new int[] {r, c, cur[2]+1});
			}
			
			if(dist > 0) break;
		}
		
		// 목적지까지 갈 수 없는 경우일때
		if(dist > F || dist == 0) return false;
				
		F += dist;
		return true;
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N && map[r][c] == 0;
	}

}