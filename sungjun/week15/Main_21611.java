package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_21611 {
	static int N, M, countA, countB, countC;
	static int[][] map;
	// map 인덱스 방향
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {-1, 0, 1, 0};
	// 마법 방향
	static int[] md = {-1, 1, 0, 0};
	static int[] mc = {0, 0, -1, 1};
	
	static Stack<Integer> beads;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N+1][N+1];
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N+1; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		initBeads();
				
		if(beads.size() == 0) {
			System.out.println(0);
			return;
		}
		
		for (int i = 0; i < M; i++) {			
			st = new StringTokenizer(br.readLine());
			
			magic(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
			while(true) {
				if(!explode()) break;

				if(beads.size() == 0) break;
			}
			
			if(beads.size() == 0) break;
			changeBeads();
		}
		
		System.out.println((countA+(2*countB)+(3*countC)));
	}

	private static void changeBeads() {
		Stack<Integer> tmp = new Stack<>();
		
		int count = 1;
		int type = beads.get(0);
		int size = 0;
		
		for (int i = 1; i < beads.size(); i++) {
			if(beads.get(i) == type) {
				count++;
				continue;
			}
			
			if(type != 0) {
				tmp.add(count);
				tmp.add(type);
				
				type = beads.get(i);
				count = 1;
				size += 2;
				
				if(size > N*N-1) {
					break;
				}
			}
		}
		
		if(type != 0) {
			tmp.add(count);
			tmp.add(type);
		}
		
		while(tmp.size() > N*N-1) {
			tmp.pop();
		}
		
		beads = (Stack<Integer>) tmp.clone();
	}

	private static boolean explode() {
		boolean flag = false;
		Stack<Integer> tmp = new Stack<>();
		
		int count = 1;
		tmp.add(beads.get(0));
		
		for (int i = 1; i < beads.size(); i++) {
			int now = beads.get(i);
			
			if(!tmp.isEmpty()) {
				if(now == tmp.peek()) {
					count++;
					tmp.add(now);
					continue;
				}
				
				if(count > 3) {
					for (int j = 0; j < count; j++) {
						switch (tmp.pop()) {
						case 1:
							countA++;
							break;
						case 2:
							countB++;
							break;
						case 3:
							countC++;
							break;
						default:
							break;
						}
					}
					
					flag = true;
					count = 1;
					tmp.add(now);
					continue;
				}
			}
			
			tmp.add(now);
			count = 1;
		}
		
		if(count > 3) {
			for (int j = 0; j < count; j++) {
				switch (tmp.pop()) {
				case 1:
					countA++;
					break;
				case 2:
					countB++;
					break;
				case 3:
					countC++;
					break;
				default:
					break;
				}
			}
			
			flag = true;
		}
		
		if(flag) {
			beads = (Stack<Integer>) tmp.clone();
		}
		
		return flag;
	}

	private static void initBeads() {
		beads = new Stack<>();

		int r = (N+1)/2;
		int c = (N+1)/2;
		int idx = 1;
		
		int dir = 0;
		
		for (int i = 1; i < N+1; i++) {
			for (int j = 0; j < 2; j++) {
				for (int k = 0; k < i; k++) {
					if(!check(r+dr[dir], c+dc[dir])) return;
					r += dr[dir];
					c += dc[dir];
					if(map[r][c] != 0) {
						beads.add(map[r][c]);
					}
					map[r][c] = idx++;
				}
				
				dir = (dir+1)%4;
			}
		}
	}
	
	private static void magic(int d, int s) {
		Stack<Integer> tmp = new Stack<>();
		
		int r = (N+1)/2;
		int c = (N+1)/2;
		
		for (int i = 1; i <= s; i++) {
			tmp.add(map[r+md[d]*i][c+mc[d]*i]);
		}
		
		while(!tmp.isEmpty()) {
			if(beads.size() >= tmp.peek()) {
				beads.remove(tmp.pop()-1);
				continue;
			}
			
			tmp.pop();
		}
	}

	private static boolean check(int r, int c) {
		return 1 <= r && N >= r && 1 <= c && N >= c;
	}
}