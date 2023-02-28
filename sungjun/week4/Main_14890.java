package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14890 {
	static int N, L;
	static int[][] mapR;
	static int[][] mapC;
	static int count = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		mapR = new int[N][N];	// 열 체크
		mapC = new int[N][N];	// 행 체크
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				mapR[i][j] = Integer.parseInt(st.nextToken());
				mapC[j][i] = mapR[i][j];
			}
		}
		
		setSlope(mapR);
		setSlope(mapC);
		System.out.println(count);
	}
	
	private static void setSlope(int[][] map) {		
		for (int i = 0; i < N; i++) {
			boolean check = true;
			// 지금보다 높은 칸을 만나기 전까지 이어지는 칸의 개수
			int plusSpan = 1;
			
			for (int j = 1; j < N; j++) {
				// 높이가 2이상 차이나면 해당 길은 지나갈 수 없음
				if(Math.abs(map[i][j] - map[i][j-1]) >= 2) {
					check = false;
					break;
				}
				// 현재 칸과 이전 칸의 높이가 같다면 개수 카운트
				if(map[i][j] == map[i][j-1]) {
					plusSpan++;
					continue;
				}
				// 현재 칸이 이전 칸보다 1 높다면
				if(map[i][j] - map[i][j-1] == 1) {
					// 이전까지 이어진 길의 길이가 경사로의 길이보다 같거나 길다면
					if(plusSpan >= L) {
						plusSpan = 1;	// 길이 1로 초기화
						continue;
					}
					check = false;	// 경사로의 길이보다 짧다면 경사로 설치 불가
					break;
				}
				// 현재 칸이 이전 칸보다 1 낮다면
				if(map[i][j] - map[i][j-1] == -1) {
					// 경사로의 길이만큼 다음 칸을 체크
					for (int k = 1; k < L; k++) {
						// 경사로의 길이만큼 가기 전에 길이 끝난다면 경사로 설치 불가
						if(j+k >= N) {
							check = false;
							break;
						}
						// 경사로의 길이만큼 가기 전에 높이가 다른 길이 나온다면 경사로 설치 불가
						if(map[i][j] != map[i][j+k]) {
							check = false;
							break;
						}
					}
					// 경사로를 설치했다면 경사로가 끝나는 지점부터 이어서 탐색
					j += L-1;
					// 이미 경사로를 설치한 칸에는 경사로를 놓을 수 없기 때문에 길의 길이는 0으로 초기화
					plusSpan = 0;
				}
				
			}
			
			if(check) count++;
		}
	}

}
