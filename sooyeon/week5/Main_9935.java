import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_9935 {
	static Stack<Character> s;
	static String str;
	static String bomb;
	static int bombSize;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		str = br.readLine();
		bomb = br.readLine();
		bombSize = bomb.length();
		s = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			s.push(str.charAt(i));
			if(s.size()>=bombSize && s.peek()==bomb.charAt(bombSize-1)) {
				//스택의 크기가 폭발문자열의 크기보다 크거나 같고 맨 위가 폭발 문자열의 끝자리와 같으면
				Bomb(i);
			}
		}
		if(s.isEmpty()) {
			System.out.println("FRULA");
			return;
		}
		for (int i = 0; i < s.size(); i++) {
			sb.append(s.get(i));
		}
		System.out.println(sb.toString());
		
	}

	static void Bomb(int end) {
		for (int i = 0; i < bombSize; i++) {
			if(s.get(s.size()-1-i) != bomb.charAt(bombSize-1-i)){ //폭발 문자열이 아닐 때
				return;
			}
		}
		for (int i = 0; i < bombSize; i++) {
			s.pop();
		}
	}

}