package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// BJ 19238: 스타트 택시
public class Main_19238 {
    static int N, M, fuel;
    static int[][] map;
    static int taxiRow, taxiCol;
    static Passenger[] passengers;
    static PriorityQueue<Passenger> pq;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    	
    static class Passenger implements Comparable<Passenger>{
        int orgRow, orgCol;
        int destRow, destCol;
        int orgToDest;
        int taxiToPassenger;

        public Passenger(int orgRow, int orgCol, int destRow, int destCol, int orgToDest) {
            this.orgRow = orgRow;
            this.orgCol = orgCol;
            this.destRow = destRow;
            this.destCol = destCol;
            this.orgToDest = orgToDest;
        }

		@Override
		public int compareTo(Passenger o) {
			if (this.taxiToPassenger == o.taxiToPassenger) {
				if (this.orgRow == o.orgRow) 
					return Integer.compare(this.orgCol, o.orgCol);
				return Integer.compare(this.orgRow, o.orgRow);
			}
			return Integer.compare(this.taxiToPassenger, o.taxiToPassenger);
		}
    }
    
    static boolean check(int row, int col) {
    	if (row < 0 || col < 0 || row >= N || col >= N)
    		return false;
    	return true;
    }
    
    static int getDistanceOrgToDest(int orgRow, int orgCol, int destRow, int destCol) {
    	if (orgRow == destRow && orgCol == destCol) // 출발지와 도착지가 같은 경우
    		return 0;

    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {orgRow, orgCol, 0});
    	boolean[][] visited = new boolean[N][N];
    	visited[orgRow][orgCol] = true;

    	int distance = 0;
    	while (!q.isEmpty()) {
    		int[] now = q.poll();

    		for (int i = 0; i < 4; i++) {
				int nr = now[0] + directions[i][0];
				int nc = now[1] + directions[i][1];

				if (!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1)
					continue;
				
				if (nr == destRow && nc == destCol) {
					distance = now[2] + 1;

					return distance;
				}
				
				visited[nr][nc] = true;
				q.offer(new int[] {nr, nc, now[2] + 1});
    		}
    	}
    	
    	// 이동할 수 없는 경우
    	return -1;
    }
    
    static void bfs() {
    	Queue<int[]> q = new LinkedList<>();
    	q.offer(new int[] {taxiRow, taxiCol, 0});
    	boolean[][] visited = new boolean[N][N];
    	visited[taxiRow][taxiCol] = true;
    	
    	// 택시가 서있는 위치에 승객이 있는 경우
    	if (map[taxiRow][taxiCol] > 1) {
    		passengers[map[taxiRow][taxiCol] - 2].taxiToPassenger = 0;
			pq.offer(passengers[map[taxiRow][taxiCol]-2]);
			
			return;
    	}
    	
    	while (!q.isEmpty()) {
    		int[] now = q.poll();
    		
    		// 현재 pq에 담긴 최소 거리보다 큰 경우
    		if (!pq.isEmpty() && pq.peek().taxiToPassenger <= now[2])
    			return;
    		
    		for (int i = 0; i < 4; i++) {
				int nr = now[0] + directions[i][0];
				int nc = now[1] + directions[i][1];
			
				if (!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1)
					continue;
				
				visited[nr][nc] = true;
				
				if (map[nr][nc] != 0) {
					passengers[map[nr][nc] - 2].taxiToPassenger = now[2] + 1;
					pq.offer(passengers[map[nr][nc]-2]);
				}

				q.offer(new int[] {nr, nc, now[2] + 1});
    		}
    	}
    }

    static boolean process() {
    	bfs();
    	
    	if (pq.isEmpty()) { // 태울 수 있는 승객이 없는 경우
    		return false;
    	}
    	
    	Passenger nextPassenger = pq.poll();

    	if (nextPassenger.orgToDest + nextPassenger.taxiToPassenger > fuel) { // 승객을 데리고 이동하던 중 연료가 떨어지는 경우
    		return false;
    	}
    	
    	map[nextPassenger.orgRow][nextPassenger.orgCol] = 0;
    	fuel += (nextPassenger.orgToDest - nextPassenger.taxiToPassenger);
    	taxiRow = nextPassenger.destRow;
    	taxiCol = nextPassenger.destCol;

    	return true;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxiRow = Integer.parseInt(st.nextToken()) - 1;
        taxiCol = Integer.parseInt(st.nextToken()) - 1;

        passengers = new Passenger[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            
            int orgRow = Integer.parseInt(st.nextToken())-1;
            int orgCol = Integer.parseInt(st.nextToken())-1;
            int destRow = Integer.parseInt(st.nextToken())-1;
            int destCol = Integer.parseInt(st.nextToken())-1;
            int orgToDest = getDistanceOrgToDest(orgRow, orgCol, destRow, destCol);

            if (orgToDest == -1) { // 승객이 출발지에서 도착지로 이동할 수 없는 경우
            	System.out.println(-1);
            	return;
            }
            
            map[orgRow][orgCol] = i + 2;
            passengers[i] = new Passenger(orgRow, orgCol, destRow, destCol, orgToDest);
        }
        
        pq = new PriorityQueue<>(); 
        for (int i = 0; i < M; i++) { 
        	if (!process()) {
        		fuel = -1; 
        		break; 
        	}
        	
        	pq.clear(); 
        }

        System.out.println(fuel);
        br.close();
    }
}
