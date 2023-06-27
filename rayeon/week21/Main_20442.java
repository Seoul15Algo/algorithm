package week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ 20442: ㅋㅋ루ㅋㅋ
public class Main_20442 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input = br.readLine().toCharArray();
		
		// 해당 문자까지의 R의 누적 개수를 저장한다.
		int[] rCount = new int[input.length];
		int cnt = 0;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == 'R') {
				cnt++;
			}
			
			rCount[i] = cnt;
		}
		
		int answer = rCount[input.length - 1];
		
		int L = 0;
		int R = input.length - 1;
		int kCount = 0;
		
		while (L < R) {
			while (L < input.length && input[L] == 'R') {
				L++;
			}
			
			while (R > L && input[R] == 'R') {
				R--;
			}
			
			// L과 R이 같거나, 두 K 사이에 R이 없는 경우 더 이상 조회할 필요가 없으므로 종료
			if (rCount[R] - rCount[L] == 0) {
				break;
			}
			
			if (L < R) {
				kCount++;
				
				answer = Math.max(rCount[R] - rCount[L] + kCount * 2, answer);
				
				L++;
				R--;
			}
		}
		
		System.out.println(answer);
		br.close();
	}
}