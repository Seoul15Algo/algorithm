package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9935 {
	static char[] cs;
	static char[] result;
	static char[] bomb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String bs = br.readLine();
		
		int len = s.length();
		int blen = bs.length();
		cs = s.toCharArray();
		bomb = bs.toCharArray();
		result = new char[len];
		
		int index = 0;
		
		// 문자열 처음부터 탐색
		for (int i = 0; i < len; i++) {
			result[index] = cs[i];
			if(index < len){
				index++;
			}
			
			// 뒤에서 폭발 문자열의 길이만큼 체크
			if(index >= blen) {
				boolean check = true;
				for (int j = 0; j < blen; j++) {
					if(result[index-j-1] != bomb[blen-1-j]) {
						check = false;
						break;
					}
				}
				
				// 폭발 문자열이라면 폭파
				if(check) {
					index -= blen;
				}
			}
		}
		
		// 결과 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < index; i++) {
			sb.append(result[i]);
		}
		
		System.out.println(index == 0? "FRULA" : sb);
	}
}