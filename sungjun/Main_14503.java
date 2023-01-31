import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14503 {
	
	static int[][] map;
	static int cleaned = 0;
	static int N;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s1[] = br.readLine().split(" ");
		N = Integer.parseInt(s1[0]);
		M = Integer.parseInt(s1[1]);
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i][0] = -1;
			map[i][M-1] = -1;
		}
		
		for(int j = 0; j < M; j++) {
			map[0][j] = -1;
			map[N-1][j] = -1;
		}
		
		String s2[] = br.readLine().split(" ");
		int r = Integer.parseInt(s2[0]);
		int c = Integer.parseInt(s2[1]);
		int d = Integer.parseInt(s2[2]);
				
		for (int i = 0; i < N; i++) {
			String s3[] = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(s3[j]);
			}
		}
		
		clean(r, c, d);
		
		System.out.println(cleaned);
	}
	
	// 청소 진행 함수
	private static void clean(int x, int y, int d) {
		if(map[x][y] == 0) {
			map[x][y] = 2;
			cleaned++;
		}
		
		// 바라보는 방향에 따라 step2 진행
		switch(d) {
		case 0:
			step2(x, y, 0);
			break;
		case 1:
			step2(x, y, 1);
			break;
		case 2:
			step2(x, y, 2);
			break;
		case 3:
			step2(x, y, 3); 
			break;
		}
		
	}
	
	// step2 진행 함수
	private static void step2(int x, int y, int d) {
		switch(d) {
		case 0:
			if(map[x][y-1] == 0) {		// 현 위치의 왼쪽 방향이 청소가 안됐다면
				clean(x, y-1, 3);
			} else if (check_all(x, y)) {	// 만약 전후좌우가 모두 청소됐거나 벽이라면
				if(map[x+1][y] == 1) {		// 만약 후방이 벽이라면
					return;		// 종료
				}
				step2(x+1, y, 0);		// 벽이 아니라면 바라보는 방향 유지한채로 뒤로 후진
			} else if (map[x][y-1] != 0) {	// 전후좌우중 청소가 안된 방향이 있다면
				step2(x, y, 3);		// 왼쪽 방향 차례로 탐색 진행 (90도 회전하여 step2 진행)
			}
			break;
		case 1:
			if(map[x-1][y] == 0) {
				clean(x-1, y, 0);
			} else if(check_all(x, y)) {
				if(map[x][y-1] == 1) {
					return;
				}
				step2(x, y-1, 1);
			} else if(map[x-1][y] != 0) {
				step2(x, y, 0);
			}
			break;
		case 2:
			if(map[x][y+1] == 0) {
				clean(x, y+1, 1);
			} else if (check_all(x, y)) {
				if(map[x-1][y] == 1) {
					return;
				}
				step2(x-1, y, 2);
			} else if(map[x][y+1] != 0) {
				step2(x, y, 1);
			}
			break;
		case 3:
			if(map[x+1][y] == 0) {
				clean(x+1, y, 2);
			} else if (check_all(x, y)) {
				if(map[x][y+1] == 1) {
					return;
				}
				step2(x, y+1, 3);
			} else if(map[x+1][y] != 0) {
				step2(x, y, 2);
			} 
			break;
		}
	}
	
	
	// 전후좌우에 청소가 되지 않은 공간이 있는지 탐색하는 함수
	private static boolean check_all(int x, int y) {
		int[] a = {1, 0, -1, 0};
		int[] b = {0, 1, 0, -1}; 
		
		int check = 0;
		
		for (int i = 0; i < 4; i++) {
			if(map[x+a[i]][y+b[i]] == 0) {
				check++;
				break;
			}
		}
		
		if(check == 0) {
			return true;
		} else {
			return false;
		}
	}

}
