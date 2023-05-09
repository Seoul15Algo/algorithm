import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2468 {
	public static int N;
	public static int[][] g;
	public static boolean[][] v;

	public static Set<Integer> nums = new HashSet<Integer>();
	
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	
	public static void dfs(int x, int y, int num) {
		v[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx<0 || ny<0 || nx>=N || ny>=N)
				continue;
			
			if (v[nx][ny])
				continue;
			
			if (g[nx][ny] > num)
				dfs(nx, ny, num);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int result = 0;
		
		N = Integer.valueOf(st.nextToken());
		g = new int[N][N];
		
		nums.add(0);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				g[i][j] = Integer.valueOf(st.nextToken());
				nums.add(g[i][j]);				
			}
		}
		
		Iterator<Integer> it = nums.iterator();
		while(it.hasNext()) {
			int num = it.next();
			int cnt = 0;
			v = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (g[i][j] > num && !v[i][j]) {
						dfs(i,j,num);
						cnt++;
					}
				}
			}
			
			result = Math.max(result, cnt);
		}
		
		System.out.println(result);
		
		br.close();
	}
}
