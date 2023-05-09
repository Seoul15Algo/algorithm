import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Node{
	int x; 
	int y;
	int dist;
	boolean wall;
	
	public Node(int x, int y, int dist, boolean wall) {
		this.x = x;
		this.y = y;
		this.dist = dist;
		this.wall = wall;
	}
}

public class Main_2206 {
	public static int N, M;
	
	public static char[][] g;
	
	public static boolean[][] v1;
	public static boolean[][] v2;
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void bfs() {
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(0, 0, 1, false));
		v1[0][0] = true;
		
		while(!q.isEmpty()) {
			Node now = q.poll();
			
			if (now.x == N-1 && now.y == M-1) {
				System.out.println(now.dist);
				return;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				
				if (nx<0 || ny<0 || nx>=N || ny>=M)
					continue;
				
				if(g[nx][ny] == '0') { // 벽이 아닌 경우
					if(!now.wall) { // 벽을 부신 적이 없는 경우
						if(!v1[nx][ny]) { // 해당 노드를 방문하지 않은 경우
							v1[nx][ny] = true;
							q.offer(new Node(nx, ny, now.dist+1, false));
						}
					}else { // 벽을 이미 부신 경우
						if(!v2[nx][ny]) { // 해당 노드를 방문하지 않은 경우
							v2[nx][ny] = true;
							q.offer(new Node(nx, ny, now.dist+1, true));
						}
					}
				} else { // 벽인 경우
					if(!now.wall) { // 벽을 부신 적이 없는 경우
						if(!v2[nx][ny]) {
							v2[nx][ny] = true;
							q.offer(new Node(nx, ny, now.dist+1, true));
						}
					}
				}
			}
		}
		
		System.out.println(-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] size = br.readLine().split(" ");
		
		N = Integer.parseInt(size[0]);
		M = Integer.parseInt(size[1]);
		
		g = new char[N][M];
		v1 = new boolean[N][M];
		v2 = new boolean[N][M];
		
		for (int i = 0; i < N; i++)
			g[i] = br.readLine().toCharArray();
			
		bfs();
		
		br.close();
	}

}
