package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1918 {
	static Stack<Character> op;	// 연산자 저장 스택
	static char[] infix;	// 입력받은 중위표기식
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		op = new Stack<>();

		infix = br.readLine().toCharArray();

		for (int i = 0; i < infix.length; i++) {
			// 문자는 그대로 결과 배열에 넣음
			if(infix[i] >= 'A' && infix[i] <= 'Z') {
				sb.append(infix[i]);
				continue;
			}
			
			// 여는 괄호를 만났다면 연산자 스택에 넣음
			if(infix[i] == '(') {
				op.add(infix[i]);
				continue;
			}
			
			// 닫는 괄호를 만났다면
			if(infix[i] == ')') {
				// 여는 괄호를 만날때까지 연산자를 스택에서 꺼내서 결과 배열에 추가
				while(true) {
					char temp = op.pop();
					
					if(temp == '(') {
						break;
					}
					
					sb.append(temp);
				}
				continue;
			}
			
			// +나 -를 만난 경우, 연산자 우선순위가 낮기 때문에 여는 괄호를 만나거나 스택이 비어있을때까지
			// 모든 연산자를 스택에서 꺼내서 결과 배열에 추가하고, 현재 마주친 연산자를 스택에 넣음
			if(infix[i] == '+' || infix[i] == '-') {
				while(!op.isEmpty()) {
					if(op.peek() != '(') {
						sb.append(op.pop());
						continue;
					}
					break;
				}
				op.add(infix[i]);
				continue;
			}
			
			// *나 /를 만난 경우, 연산자 우선순위가 높기 때문에 여는 괄호를 만나거나 스택의 맨 위에 위치한 연산자가 + 또는 -가 될때까지
			// 연산자를 스택에서 꺼내서 결과 배열에 추가하고, 현재 마주친 연산자를 스택에 넣음
			if(infix[i] == '*' || infix[i] == '/') {
				while(!op.isEmpty() && (op.peek() == '*' || op.peek() == '/')) {
					sb.append(op.pop());
				}
				op.add(infix[i]);
				continue;
			}
		}
		
		// 남은 연산자를 모두 꺼내어 결과 배열에 추가
		while(!op.isEmpty()) {
			sb.append(op.pop());
		}
		
		System.out.println(sb);
	}

}