package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20543 {
	static int N, M;
	static long[][] map, bombMap;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new long[N][N];
		bombMap = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = -1L*Long.parseLong(st.nextToken());
			}
		}
		
		int n = M/2;
		
		for (int i = n; i < N-n; i++) {
			for (int j = n; j < N-n; j++) {
				bombMap[i][j] = map[i-n][j-n];

				if (i - n - 1 >= 0 && j - n - 1 >= 0) {
					bombMap[i][j] += map[i - n - 1][j - n - 1];                	
				}
				
				if (i - n - 1 >= 0) {
					bombMap[i][j] -= map[i - n - 1][j - n];					
				}
				
                if (j - n - 1 >= 0) {
                	bombMap[i][j] -= map[i - n][j - n - 1];                	
                }
                
                if (i - M >= 0 && j - M >= 0) {
                	bombMap[i][j] -= bombMap[i - M][j - M];                	
                }
                
                if (i - M >= 0) {
                	bombMap[i][j] += bombMap[i - M][j];                	
                }
                
                if (j - M >= 0) {
                	bombMap[i][j] += bombMap[i][j - M];                	
                }
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(bombMap[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}