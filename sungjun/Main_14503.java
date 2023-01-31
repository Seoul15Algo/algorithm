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
	
	// û�� ���� �Լ�
	private static void clean(int x, int y, int d) {
		if(map[x][y] == 0) {
			map[x][y] = 2;
			cleaned++;
		}
		
		// �ٶ󺸴� ���⿡ ���� step2 ����
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
	
	// step2 ���� �Լ�
	private static void step2(int x, int y, int d) {
		switch(d) {
		case 0:
			if(map[x][y-1] == 0) {		// �� ��ġ�� ���� ������ û�Ұ� �ȵƴٸ�
				clean(x, y-1, 3);
			} else if (check_all(x, y)) {	// ���� �����¿찡 ��� û�ҵưų� ���̶��
				if(map[x+1][y] == 1) {		// ���� �Ĺ��� ���̶��
					return;		// ����
				}
				step2(x+1, y, 0);		// ���� �ƴ϶�� �ٶ󺸴� ���� ������ä�� �ڷ� ����
			} else if (map[x][y-1] != 0) {	// �����¿��� û�Ұ� �ȵ� ������ �ִٸ�
				step2(x, y, 3);		// ���� ���� ���ʷ� Ž�� ���� (90�� ȸ���Ͽ� step2 ����)
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
	
	
	// �����¿쿡 û�Ұ� ���� ���� ������ �ִ��� Ž���ϴ� �Լ�
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
