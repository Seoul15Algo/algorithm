import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Node {
  int x;
  int y;
  int time;

  public Node(int x, int y, int time) {
    this.x = x;
    this.y = y;
    this.time = time;
  }
}

public class Main_3055 {
  public static char[][] g;
  public static int R, C;

  public static int[] dx = { -1, 1, 0, 0 };
  public static int[] dy = { 0, 0, -1, 1 };

  public static Queue<Node> water = new LinkedList<>();
  
  public static int result = Integer.MAX_VALUE;

  public static void spreadWater() {
	int size = water.size();
	
    for (int i = 0; i < size; i++) {
    	Node now = water.poll();
	
	    for (int j = 0; j < 4; j++) {
	      int nx = now.x + dx[j];
	      int ny = now.y + dy[j];
	
	      if (nx < 0 || ny < 0 || nx >= R || ny >= C)
	        continue;
	
	      if (g[nx][ny] == '.') {
	      	g[nx][ny] = '*';
	      	water.add(new Node(nx, ny, 0));
	      }
	   }
	}
  }

  public static void bfs(int x, int y) {
	Queue<Node> q = new LinkedList<>();
	q.offer(new Node(x,y,0));
	
	int time = 0;
    while (!q.isEmpty()) {
    	Node now = q.poll();
    	
    	if (now.time == time) {
    		spreadWater();
    		time++;
    	}
    	
    	for (int j = 0; j < 4; j++) {
    		int nx = now.x + dx[j];
          	int ny = now.y + dy[j];
          	
          	if (nx < 0 || ny < 0 || nx >= R || ny >= C)
          		continue;
	          	
  	        if (g[nx][ny] == 'D') {
	        	result = Math.min(result, now.time + 1);
	        	
	          	return;
	        }
  	          
  	        if (g[nx][ny] == '.') {
  	        	g[nx][ny] = 'S';
  	        	q.offer(new Node(nx, ny, now.time + 1));
  	        }
		}
     }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String[] s = br.readLine().split(" ");

    R = Integer.parseInt(s[0]);
    C = Integer.parseInt(s[1]);

    int x=0, y=0;
    g = new char[R][C];
    for (int i = 0; i < R; i++) {
    	g[i] = br.readLine().toCharArray();
    	
    	for (int j = 0; j < C; j++) {
    		if (g[i][j] == 'S'){
    			x = i;
    			y = j;
    		}
    		
    		if (g[i][j] == '*')
    			water.offer(new Node(i,j,0));
    	}
    }

    bfs(x,y);
    
    System.out.println((result == Integer.MAX_VALUE) ? "KAKTUS" : result);

    br.close();
  }
}