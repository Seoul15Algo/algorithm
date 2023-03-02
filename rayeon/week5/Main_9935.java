package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine(); // 처리할 문자열
		String target = br.readLine(); // 폭발 문자열

		Stack<Character> sStack = new Stack<Character>(); // 문자를 담는 스택
		Stack<Integer> iStack = new Stack<Integer>(); // 폭발 문자열과 얼마나 일치했는지 세는 스택
		iStack.add(0); // 아직 비교한 문자가 없으므로 0을 넣는다.
		
		/* 		
		ex) 
		sStack   m i r k o v n i z C C 4
		iStack 0 0 0 0 0 0 0 0 0 0 1 1 2 
		 */
		 
		for (int i = 0; i < input.length(); i++) {
			sStack.add(input.charAt(i));
			
			int cnt = iStack.peek(); // 이전 문자까지 폭발 문자열과 연달아 일치한 개수
			char c = sStack.peek(); // 현재 문자
			
			if (c == target.charAt(cnt)) { // 현재 문자가 연달아 폭발 문자열과 일치하는 경우
				iStack.add(cnt+1); 
				
				if ((cnt+1) == target.length()) { // 현재 문자까지 연달아 폭발 문자열과 일치한 개수와 폭발 문자열의 길이가 같은 경우
					// 폭발 문자열이 존재하는 것이므로, 폭발 문자열의 길이 만큼 제거
					
					/*
					ex) 
					sStack   m i r k o v C 4 -> sStack   m i r k o v
					iStack 0 0 0 0 0 0 0 1 2    iStack 0 0 0 0 0 0 0
					*/
					
					for (int j = 0; j < target.length(); j++) {
						sStack.pop();
						iStack.pop();
					}
				}
				
				continue;
			}
			
			if (c == target.charAt(0)) { // 현재 문자가 폭발 문자열의 첫 문자와 일치하는 경우
				iStack.add(1); // 새롭게 카운트
				
				/* 		
				ex) 
				sStack   m i r k o v n i z C C
				iStack 0 0 0 0 0 0 0 0 0 0 1 1
				 */
				
				continue;
			}
			
			// 문자가 일치하지 않는 경우
			iStack.add(0);
		}
		
		StringBuilder sb = new StringBuilder();
		
		int size = sStack.size();
		for (int i = 0; i < size; i++) {
			sb.append(sStack.get(i));
		}
		
		System.out.println(sb.length() == 0 ? "FRULA" : sb);
	}
}