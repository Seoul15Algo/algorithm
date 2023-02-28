package week4;

import java.util.Scanner;

public class Main_1107 {
	static int M;
	static int N;
	static boolean[] buttons;
	static int min;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		min = Math.abs(N-100);
	
		M = sc.nextInt();
		buttons = new boolean[10];
		
		// 부서진 버튼 체크
		for (int i = 0; i < M; i++) {
			int tmp = sc.nextInt();
			buttons[tmp] = true;
		}
		
		int temp = N;
		int count = 1;
		
		// 몇자리 숫자인지 체크
		while(temp > 1) {
			temp /= 10;
			count++;
		}
		
		// 최대 탐색 범위 설정
		// 만약 4자리수라면 0~9999까지만 탐색하면 됨
		for (int i = 0; i < 10*Math.pow(10, count-1); i++) {
			String s = String.valueOf(i);
			int digit = s.length();
			
			boolean check = false;
			
			// 만약 부서진 버튼을 눌러야 한다면 해당 번호 스킵
			for (int j = 0; j < digit; j++) {
				if(buttons[s.charAt(j)-'0']) {
					check = true;
					break;
				}
			}
			
			// 부서진 버튼을 누르지 않고 만들 수 있는 조합이라면
			if(!check) {
				// 최소값 비교
				int cur = Math.abs(N-i) + digit;
				min = Math.min(min, cur);
			}
		}
		
		System.out.println(min);
		
		sc.close();
	}

}