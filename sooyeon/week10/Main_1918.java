import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_1918 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String str = br.readLine();
		Stack<Character> s = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			char cur = str.charAt(i);
			
			if('A' <= cur && cur<= 'Z') { //문자는 바로 출력, 연산자는 스택에 넣음
				sb.append(cur);
			}else if(cur == '(') {
				s.add(cur);
			}else if(cur == ')') {
				while(!s.isEmpty()) {
					if(s.peek() == '(') {
						s.pop();
						break;
					}
					sb.append(s.pop());
				}
			}else { //연산자
				//내 연산자 우선순위보다 높거나 같은게 있을때까지 비우기
				while(!s.isEmpty() && priority(s.peek()) >= priority(cur)) {
					sb.append(s.pop());
				}
				s.add(cur);
			}
		}
		
		//남은거
		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		System.out.println(sb.toString());

	}

	static int priority(Character op) { //연산자 우선순위 -> 괄호를 제일 낮게 줌(괄호를 출력하면 안되기 때문에)
		if(op == '(' || op == ')') {
			return 0;
		}
		if(op == '/' || op == '*') {
			return 2;
		}
		return 1;
	}

}
