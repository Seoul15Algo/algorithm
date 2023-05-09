package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_6087 {
	static int W, H;
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static ArrayList<Integer> pos;
	static int startR, startC, endR, endC;
	static int INF = Integer.MAX_VALUE/1000;
	
	static class Laser implements Comparable<Laser> {
		int r;
		int c;
		int t;
		int d;
		int rp;
		
		public Laser(int r, int c, int t, int d, int rp) {
			super();
			this.r = r;
			this.c = c;
			this.t = t;		// 거울 설치 횟수
			this.d = d;		// 진입 방향
			this.rp = rp;		// 같은 값일 때 진입을 한 적이 있는지  --> 예를 들어 이미 최소값이 3으로 갱신되어 있는 위치에 또 3의 값으로 진입하면 1, 아니면 0
								// 이렇게 하는 이유는 같은 값일 때 진입을 하지 않는다면 진입 방향에 따라 그 다음 칸부터의 최소값 갱신이 보장되지 않음
								// 같은 값일 때 진입을 허용한다면 인접한 두 칸의 값이 동일하다면 계속해서 동일 구간을 반복해서 진입하기에 무한루프 발생
								// 따라서 반복구간 진입을 판단하는 변수를 만들어서 무한루프가 발생하지 않도록 관리
		}

		@Override
		public int compareTo(Laser o) {
			return Integer.compare(this.t, o.t);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		pos = new ArrayList<>();
		
		for (int i = 0; i < H; i++) {
			Arrays.fill(map[i], INF);		// 다익스트라 적용을 위해 초기화
		}
		
		for (int i = 0; i < H; i++) {
			char[] cs = br.readLine().toCharArray();
			for (int j = 0; j < W; j++) {
				if(cs[j] == 'C') {		// 출발 위치와 도착 위치
					pos.add(i);
					pos.add(j);
				}
				
				if(cs[j] == '*') {
					map[i][j] = -1;		// 벽
				}
			}
		}
		
		startR = pos.get(0);
		startC = pos.get(1);
		endR = pos.get(2);
		endC = pos.get(3);
				
		bfs();
		
		System.out.println(map[endR][endC]);	// 도착 위치의 결과값 출력
	}

	private static void bfs() {
		PriorityQueue<Laser> pq = new PriorityQueue<>();
		
		int r = startR;
		int c = startC;
		map[r][c] = 0;
				
		for (int i = 0; i < 4; i++) {		// 출발 위치에서 사방탐색을 해서 벽이 아닌 칸들을 큐에 추가해주고 최소값 갱신
			if(check(r+dr[i], c+dc[i])) {
				map[r+dr[i]][c+dc[i]] = 0;
				pq.add(new Laser(r+dr[i], c+dc[i], 0, i, 0));
			}
		}
		
		while(!pq.isEmpty()) {
			Laser cur = pq.poll();
			int nr = cur.r;
			int nc = cur.c;
			int t = cur.t;
			int d = cur.d;
			int rp = cur.rp;
			
			for (int i = 0; i < 4; i++) {
				if(check(nr+dr[i], nc+dc[i])) {		// 벽이 아니라면
					int temp = t;		// 현재 최소값
					if(i != d) temp = t+1;		// 진입 방향과 다르다면 거울을 설치해야 하기 때문에 거울 설치 횟수 증가
					if(nr+dr[i] == endR && nc+dc[i] == endC) {	// 다음 칸이 도착 칸이라면 최소값 비교하여 갱신
						map[nr+dr[i]][nc+dc[i]] = Math.min(map[nr+dr[i]][nc+dc[i]], temp);
						break;
					}
					if(map[nr+dr[i]][nc+dc[i]] == temp && rp != 1) {	// 다음 칸의 값이 현재 최소값과 동일하면서 반복구간 진입 기록이 없다면
						pq.add(new Laser(nr+dr[i], nc+dc[i], temp, i, 1));		// 다음 칸을 큐에 추가하고 반복구간 진입 표시
					}
					if(map[nr+dr[i]][nc+dc[i]] > temp) {	// 다음 칸의 값이 현재 최소값보다 크다면
						map[nr+dr[i]][nc+dc[i]] = temp;	// 최소값 갱신
						pq.add(new Laser(nr+dr[i], nc+dc[i], temp, i, 0));		// 큐에 추가
					}
				}
			}
		}
	}

	private static boolean check(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W && map[r][c] != -1;
	}

}
