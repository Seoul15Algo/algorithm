import java.util.*;
import java.io.*;
public class Main_24337 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int highst = Math.max(a, b);
		
		if(a + b > N + 1) {
			System.out.println(-1);
			return;
		}
		
		Arrays.fill(arr, 1);
		
		if(a == 1) {
			arr[0] = highst;
		}
		else {
			arr[N - b] = highst;
		}
		
		for(int i = 2; i < a; i++) {
			arr[N - a - b + i] = i;
		}
		for(int i = 1; i < b; i++) {
			arr[N - i] = i;
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(arr[i]).append(" ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}