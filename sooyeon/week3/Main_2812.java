import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2812 {
	static int N,K;
	static int[] number;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		number = new int[N];
		String s = br.readLine();
		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(s.charAt(i)+"");
		}
		
		Stack<Integer> stack = new Stack<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			int curnum = number[i];
			
			while(!stack.isEmpty() && count<K && stack.peek()<curnum) { 
				//숫자를 더 지울 수 있고, 스택에 들어있는 값(top)보다 클때 제거
				stack.pop();
				count++;
			}
			stack.push(curnum);
		}
		
		//N-K개까지만 출력
		for (int i = 0; i < N-K; i++) {
			sb.append(stack.get(i));
		}
		System.out.println(sb.toString());
	}

}
