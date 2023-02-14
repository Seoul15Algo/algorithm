import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1987 {
	public static int R, C;
	public static char[][] g;
	public static ArrayList<Character> l = new ArrayList<>();
	
	public static int[] dx = { -1, 1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };
	
	public static int result = 0;
	
	public static void dfs(int x, int y) {
		if (result < l.size())
			result = l.size();
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if ( nx < 0 || ny < 0 || nx >= R || ny >= C )
				continue;
			
			if ( l.contains(g[nx][ny]) )
				continue;
			
			l.add(g[nx][ny]);
			dfs(nx, ny);
			l.remove(l.size()-1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		
		R = Integer.parseInt(s[0]);
		C = Integer.parseInt(s[1]);
		
		g = new char[R][C];
		for (int i = 0; i < R; i++)
            g[i] = br.readLine().toCharArray();
        
		l.add(g[0][0]);
		dfs(0,0);

		System.out.println(result);
		
		br.close();
	}
}
