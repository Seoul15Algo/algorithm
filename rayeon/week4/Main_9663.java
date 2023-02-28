package week4;

import java.util.Scanner;

public class Main_9663 {
	static int N;
	static boolean[][] map;
	static int[] q;
	static int result = 0;
	
	static void set(int cnt) {
		if (cnt == N) {
			result++;
			
			return;
		}
		
		for (int i = 0; i < N; i++) {
			boolean enable = true;
			
			for (int j = 0; j < cnt; j++) { 
				if (i == q[j]) {
					enable = false;
					break;
				}
				
				if (cnt - j == Math.abs(q[j] - i)){
					enable = false;
					break;
				}
			}
			
			if (!enable)
				continue;
			
			q[cnt] = i;
			set(cnt+1);
		}
		
		return;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			q = new int[N];
			
			q[0] = i;

			set(1);
		}
		
		System.out.println(result);
		
		sc.close();
	}

}