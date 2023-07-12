package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 1799: 비숍
public class Main_1799 {
	static int size;
	static int[][] map;
	static int[] answers;
	static boolean[] upwards; // 우상향 대각선
	static boolean[] downwards; // 우하향 대각선
	
	public static void subset(int flag, int x, int y, int count) {
		if (x >= size) {
			answers[flag] = Math.max(answers[flag], count);
			
			return;
		}
		
		int downward = (x - y > 0 ? size - Math.abs(x - y) : size + Math.abs(x - y)) - 1;
		int upward = (x + y - (size - 1) < 0 ? size - Math.abs(x + y - (size - 1)) : size + Math.abs(x + y - (size - 1))) - 1;

		int nx = (y + 2) < size ? x : x + 1;
		int ny = y + 2;
		if (ny >= size) {
			if (x % 2 == flag) {
				ny = 1;
			} else {
				ny = 0;
			}
		}
		
		if (!upwards[upward] && !downwards[downward] && map[x][y] == 1) {
			upwards[upward] = true;
			downwards[downward] = true;

			subset(flag, nx, ny, count + 1);

			upwards[upward] = false;
			downwards[downward] = false;
		}
		
		subset(flag, nx, ny, count);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];
		answers = new int[2];
		upwards = new boolean[size * 2 - 1];
		downwards = new boolean[size * 2 - 1];
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		subset(0, 0, 0, 0);
		subset(1, 0, 1, 0);
		
		System.out.println(answers[0] + answers[1]);
		br.close();
	}
}
