package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061 {
	static int N, score, count;
	static int[][] green, blue;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		green = new int[6][4];
		blue = new int[4][6];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			playGreen(a, c);
			playBlue(a, b);
			
			checkGreen();
			checkBlue();
			
			specialGreen();
			specialBlue();
		}
		
		count();
		
		System.out.println(score);
		System.out.println(count);
	}

	// 마지막에 파란색 보드와 초록색 보드에 들어있는 타일의 개수
	private static void count() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				if(blue[i][j] == 1) {
					count++;
				}
				
				if(green[j][i] == 1) {
					count++;
				}
			}
		}
	}
	
	// 초록색 보드에서 맨 위 두 칸에 타일이 있는지 검사
	private static void specialGreen() {
		int idx = 0;
		boolean flag = false;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j] == 1) {
					idx = i;
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		
		if(flag) {		
			if(idx == 0) {	// 맨 위 칸에 타일이 있다면
				moveGreen(3, 2);	// 두칸씩 아래로 밀어낸다
				return;
			}
			
			moveGreen(4, 1);	// 두번째 칸에 타일이 있다면 한칸씩 아래로 밀어낸다
		}
	}
	
	private static void specialBlue() {
		int idx = 0;
		boolean flag = false;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if(blue[j][i] == 1) {
					idx = i;
					flag = true;
					break;
				}
			}
			if(flag) break;
		}
		
		if(flag) {
			if(idx == 0) {
				moveBlue(3, 2);
				return;
			}
			
			moveBlue(4, 1);
		}
	}

	private static void checkBlue() {
		for (int i = 0; i < 6; i++) {
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if(blue[j][i] == 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
				score++;
				moveBlue(i-1, 1);
			}
		}
	}

	private static void moveBlue(int n, int m) {
		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				blue[j][i+m] = blue[j][i];
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < m; j++) {
				blue[i][j] = 0;
			}
		}
	}
	
	// 초록색 보드에 타일로 꽉 찬 행이 있는지 검사
	private static void checkGreen() {
		for (int i = 0; i < 6; i++) {
			boolean flag = true;
			for (int j = 0; j < 4; j++) {
				if(green[i][j] == 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
				score++;
				moveGreen(i-1, 1);
			}
		}
	}
	
	// 타일로 꽉 찬 행을 제거하고 위에 칸들을 당겨온다
	private static void moveGreen(int n, int m) {
		for (int i = n; i >= 0; i--) {
			for (int j = 0; j < 4; j++) {
				green[i+m][j] = green[i][j];
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < 4; j++) {
				green[i][j] = 0;
			}
		}
	}
	
	private static void playBlue(int a, int b) {
		int col = 0;
		
		if(a == 1) {
			while(col < 6 && blue[b][col] == 0) {
				col++;
			}
			
			blue[b][col-1] = 1;
			return;
		}
		
		if(a == 2) {
			while(col < 6 && blue[b][col] == 0) {
				col++;
			}
			
			blue[b][col-1] = 1;
			blue[b][col-2] = 1;
			return;
		}
		
		if(a == 3) {
			while(col < 6 && blue[b][col] == 0 && blue[b+1][col] == 0) {
				col++;
			}
			
			blue[b][col-1] = 1;
			blue[b+1][col-1] = 1;
			return;
		}
		
	}

	// 타일을 초록색 보드에 배치
	private static void playGreen(int a, int c) {
		int row = 0;
		
		if(a == 1) {
			while(row < 6 && green[row][c] == 0) {
				row++;
			}
			
			green[row-1][c] = 1;
			return;
		}
		
		if(a == 2) {
			while(row < 6 && green[row][c] == 0 && green[row][c+1] == 0) {
				row++;
			}
			
			green[row-1][c] = 1;
			green[row-1][c+1] = 1;
			return;
		}
		
		if(a == 3) {
			while(row < 6 && green[row][c] == 0) {
				row++;
			}
			
			green[row-1][c] = 1;
			green[row-2][c] = 1;
			return;
		}
	}

}
