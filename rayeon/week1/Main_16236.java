import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node{
	int x;
	int y;
	int dist;
	
	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class Main_16236 {
	static int[][] g;
	static int[][] d;
	static int N;
	static int size = 2;

	public static void bfs(int x, int y) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};

		d = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(d[i], -1);
		
		
		Queue<Node> q = new LinkedList<Node>();
		q.offer(new Node(x,y,0));
		d[x][y] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = node.x + dx[i];
				int ny = node.y + dy[i];
				
				if(nx<0 || ny<0 || nx>=N || ny>=N)
					continue;
				
				if(d[nx][ny] == -1 && g[nx][ny] <= size) {
					if(g[nx][ny] == 0 || g[nx][ny] == size)
						d[nx][ny] = -2;
					else if(g[nx][ny] < size)
						d[nx][ny] = node.dist+1;
					
					q.offer(new Node(nx,ny,node.dist+1));
				}
			}
		}
	}
	
	public static Node shortestFish(int x, int y) {
		bfs(x,y);
		
		int min_distance = Integer.MAX_VALUE;
		int minX = -1;
		int minY = -1;
		
		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(d[i][j] <= 0)
					continue;
				
				if(d[i][j] < min_distance) {
					minX = i;
					minY = j;
					min_distance = d[i][j];
				}
			}
		}
		
		return new Node(minX, minY, min_distance);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int x = 0;
		int y = 0;
		
		N = Integer.parseInt(st.nextToken());
		g = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
				
				if(g[i][j] == 9) {
					x = i;
					y = j;
				}
			}	
		}
		
		int result = 0;
		int cnt = 0;
		
		while(true) {
			Node n = shortestFish(x, y);
			
			if(n.x==-1 && n.y==-1) {
				break;
			}
			
			g[x][y] = 0;
			g[n.x][n.y] = 9;
			
			x = n.x;
			y = n.y;
			result += n.dist;
			cnt++;
			
			if(cnt == size) {
				cnt = 0;
				size++;
			}
		}
		
		System.out.println(result);
		br.close();
	}
}