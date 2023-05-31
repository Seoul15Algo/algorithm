package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 20543 : 폭탄 던지는 태영이
public class Main_20543 {
    static int N, M;
    static long[][] H;
    static long[][] bombs;
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder answer = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = new long[N][N];
        bombs = new long[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            
            for (int j = 0; j < N; j++) {
                H[i][j] = Long.parseLong(st.nextToken());
            }
        }
        
        if (N == 1) {
        	System.out.println(Math.abs(H[0][0]));
        	return;
        }
        
        long[][] sum = new long[N][N];
        for (int r = 0; r <= N - M; r++) {
        	long colSum = 0L;
        	
        	for (int c = 0; c <= N - M; c++) {
				bombs[r + M / 2][c + M / 2] = Math.abs(H[r][c]) - colSum;
				
				if (r > 0) {
					bombs[r + M / 2][c + M / 2] -= sum[r + M / 2 - 1][c + M / 2];
					
					if (c + M / 2 > M) {
						bombs[r + M / 2][c + M / 2] += sum[r + M / 2 - 1][c + M / 2 - M];
						
						if (r + M / 2 > M) {
							bombs[r + M / 2][c + M / 2] += sum[r + M / 2 - M][c + M / 2];
							bombs[r + M / 2][c + M / 2] -= sum[r + M / 2 - M][c + M / 2 - M];
						}
					} else {
						if (r + M / 2 > M) {
							bombs[r + M / 2][c + M / 2] += sum[r + M / 2 - M][c + M / 2];
						}
					}
				}
				
				colSum += bombs[r + M / 2][c + M / 2];
				if (c >= (M - 1)) {
					colSum -= bombs[r + M / 2][c + M / 2 - (M - 1)];
				}
				
				sum[r + M / 2][c + M / 2] = (sum[r + M / 2 - 1][c + M / 2] + sum[r + M / 2][c + M / 2 - 1] - sum[r + M / 2 - 1][c + M / 2 - 1] + bombs[r + M / 2][c + M / 2]);
			}
        }
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer.append(bombs[i][j]).append(" ");
			}
			
			answer.append("\n");
		}
        
        System.out.println(answer);
        
        br.close();
    }
}