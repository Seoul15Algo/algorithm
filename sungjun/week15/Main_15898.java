package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_15898 {
	static int N, result;
	static int[][][] map;
	static int[][][][] ingredients;
	static int[] selected;
	static boolean[] visited;
	static int[][] order = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		Map<Character, Integer> convert = new HashMap<>();
		convert.put('R', 7);
		convert.put('B', 5);
		convert.put('G', 3);
		convert.put('Y', 2);
		convert.put('W', 0);
		
		map = new int[5][5][2];
		ingredients = new int[N][4][4][2];
		selected = new int[3];
		visited = new boolean[N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					ingredients[i][j][k][0] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					ingredients[i][j][k][1] = convert.get(st.nextToken().charAt(0));
				}
			}
		}
		
		perm(0);
		
		System.out.println(result);
	}
	
	private static void perm(int cnt) {
		if(cnt == 3) {
			map = new int[5][5][2];
			solve(0);
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			selected[cnt] = i;
			visited[i] = true;
			perm(cnt+1);
			visited[i] = false;
		}
	}
	
	private static void solve(int cnt) {
		if(cnt == 3) {
			result = Math.max(result, calc(map));
			return;
		}
		
		int[][][] now = new int[5][5][2];
		
		copy(now, map);
				
		for (int i = 0; i < 4; i++) {
			pour(order[i][0], order[i][1], ingredients[selected[cnt]]);
			solve(cnt+1);
			
			copy(map, now);
		}
		
		for (int i = 1; i < 4; i++) {
			ingredients[selected[cnt]] = rotate(ingredients[selected[cnt]]);
			
			for (int j = 0; j < 4; j++) {
				pour(order[j][0], order[j][1], ingredients[selected[cnt]]);
				solve(cnt+1);
				
				copy(map, now);
			}
		}
	}
	
	private static void copy(int[][][] to, int[][][] from) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				to[i][j] = Arrays.copyOf(from[i][j], 2);
			}
		}
	}
	
	private static int calc(int[][][] arr) {
		int sum = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				sum += (arr[i][j][0]*arr[i][j][1]);
			}
		}
		
		return sum;
	}

	private static int[][][] rotate(int[][][] arr) {
		int n = arr.length;
		
		int[][][] ret = new int[n][n][2];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ret[j][n-i-1] = Arrays.copyOf(arr[i][j], 2);
			}
		}
		
		return ret;
	}
	
	private static void pour(int r, int c, int[][][] arr) {		
		for (int i = r; i < r+4; i++) {
			for (int j = c; j < c+4; j++) {
				int val = map[i][j][0] + arr[i-r][j-c][0];
				if(val < 0) val = 0;
				if(val > 9) val = 9;
				
				map[i][j][0] = val;
				
				if(arr[i-r][j-c][1] == 0) continue;
				
				map[i][j][1] = arr[i-r][j-c][1];
			}
		}
	}

}