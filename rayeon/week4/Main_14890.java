package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890 {
	static int N, L;
	static int[][] map;
	static boolean[][] ramp;
	
	static int[][] directions = {{1, 0}, {0, 1}};
	
	static boolean makeRamp(int r, int c, int d) {
		int height = map[r][c];
		
		int nr = r;
		int nc = c;
		for (int i = 0; i < L; i++) {
			if (height != map[nr][nc])
				return false;
			
			if (ramp[nr][nc])
				return false;
			
			nr += directions[d][0];
			nc += directions[d][1];
		}
		
		nr = r;
		nc = c;
		for (int i = 0; i < L; i++) {
			ramp[nr][nc] = true;
			
			nr += directions[d][0];
			nc += directions[d][1];
		}

		return true;
	}
	
	static boolean check(int r, int c, int d) {
		int height = map[r][c];
		r += directions[d][0];
		c += directions[d][1];

		while (r < N && c < N) {
			if (height == map[r][c]) {
				r += directions[d][0];
				c += directions[d][1];

				continue;
			}
		
			if (Math.abs(height - map[r][c]) > 1)
				return false;
			

			if (height < map[r][c]) {
				if (r - L * directions[d][0] < 0 || c - L * directions[d][1] < 0)
					return false;
				
				if (!makeRamp(r - L * directions[d][0], c - L * directions[d][1], d)) 
					return false;
				
				height = map[r][c];
				r += directions[d][0];
				c += directions[d][1];
				
				continue;
			}
			
			if (r + L * directions[d][0] > N || c + L * directions[d][1] > N)
				return false;
			
			if (!makeRamp(r, c, d)) 
				return false;
			
			height = map[r][c];
			r += L * directions[d][0];
			c += L * directions[d][1];
		}
			
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		int result = 0;
		
		ramp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			if (check(i, 0, 1))
				result++;
		}
		
		ramp = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			if (check(0, i, 0))
				result++;
		}
		
		System.out.println(result);
	}

}