package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_3055 {
	static int R, C;
	static char[][] map;
	static int minTime = Integer.MAX_VALUE;
	static int[] rv = {1, 0, -1, 0};
	static int[] cv = {0, 1, 0, -1};
	static Queue<int[]> hq = new LinkedList<>();	// 고슴도치
	static Queue<int[]> wq = new LinkedList<>();	// 물
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
				
		for (int i = 0; i < R; i++) {
			char[] tmp = br.readLine().toCharArray();
			
			for (int j = 0; j < C; j++) {
				map[i][j] = tmp[j];
				if(tmp[j] == 'S') {
					hq.add(new int[] {i, j, 0});	// 고슴도치 초기 위치와 경과시간 큐에 추가
				}
				if(tmp[j] == '*') {
					wq.add(new int[] {i, j}); 		// 물 초기 좌표값 큐에 추가
				}
			}
		}
		
		escape();
		
		// 최소 시간이 갱신되지 않았을 경우 KAKTUS
		if(minTime == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
			return;
		}
		System.out.println(minTime);
	}
	
	private static void escape() {
		while(!hq.isEmpty()) {		// 고슴도치 큐가 비기 전까지
			expandWater(); 	// 물 확장 먼저 진행
			moveHedgehog();		// 고슴도치 이동
		}
	}
	
	private static void expandWater() {
		int wq_len = wq.size();		// 물 큐의 현재 길이 저장
		
		// 물 큐의 현재 길이만큼
		for (int i = 0; i < wq_len; i++) {		
			int w_coord[] = wq.poll();			
			
			// 각각의 물 좌표를 사방탐색하여 확장
			for (int j = 0; j < 4; j++) {
				int nr = w_coord[0] + rv[j];
				int nc = w_coord[1] + cv[j];
				
				// 인덱스 벗어나지 않도록 예외처리
				if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1) continue;
				if(map[nr][nc] == '.') {
					map[nr][nc] = '*';
					
					wq.add(new int[] {nr, nc});	// 새로 확장된 물 좌표 큐에 추가
				}
			}
		}
	}
	
	private static void moveHedgehog() {
		int hq_len = hq.size();
		
		for (int i = 0; i < hq_len; i++) {
			int[] h_coord = hq.poll();
			
			for (int j = 0; j < 4; j++) {
				int nr = h_coord[0] + rv[j];
				int nc = h_coord[1] + cv[j];
				int time = h_coord[2];
				
				if(nr < 0 || nr > R-1 || nc < 0 || nc > C-1) continue;
				if(map[nr][nc] == 'D') {	// 비버 굴 도착시 경과시간과 현재 최소시간 비교
					minTime = Math.min(minTime,  time+1);
					return;
				}
				
				if(map[nr][nc] == '.') {	// 이동할 공간이 있다면 고슴도치 이동
					map[nr][nc] = 'S';
					hq.add(new int[] {nr, nc, time+1});
				}
			}
		}
	}

}