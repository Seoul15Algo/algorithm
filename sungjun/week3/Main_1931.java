package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main_1931 {
	static int N;
	static int[][] time;
	static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		time = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			time[i][0] = Integer.parseInt(s.split(" ")[0]);
			time[i][1] = Integer.parseInt(s.split(" ")[1]);
		}
		
		// 끝나는 시간이 빠른 순으로 정렬
		Arrays.sort(time, new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0]-o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		// 시작 시간과 관계없이 더 일찍 끝나는 회의를 고르면 매번 최선의 선택을 보장
		search(0);
	}
	
	private static void search(int start) {
		count++;
		if(start == N-1) {
			System.out.println(count);
			System.exit(0);
		}
		
		// 이전 회의가 끝나는 시간보다 시작 시간이 같거나 느린 회의를 선택
		for (int i = start+1; i < N; i++) {
			if(time[i][0] >= time[start][1]) {
				search(i);
			}
		}
		
		System.out.println(count);
		System.exit(0);
	}

}
