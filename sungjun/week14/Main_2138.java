package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138 {
	static int N, result;
	static int[] first, second, target;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		first = new int[N];
		second = new int[N];
		target = new int[N];
		result = Integer.MAX_VALUE;
		
		char[] tmp = br.readLine().toCharArray();
		
		for (int i = 0; i < N; i++) {
			first[i] = tmp[i]-'0';
		}
		
		second = Arrays.copyOf(first, N);
		
		tmp = br.readLine().toCharArray();
		
		for (int i = 0; i < N; i++) {
			target[i] = tmp[i]-'0';
		}
		
		int ans = 0;
		
		for (int i = 1; i < N; i++) {
			if(first[i-1] == target[i-1]) continue;
			ans++;
			change(first, i);
		}		
		
		if(first[N-1] == target[N-1]) {
			result = ans;
		}
		
		ans = 1;
		
		second[0] = (second[0]+1)%2;
		second[1] = (second[1]+1)%2;
		
		for (int i = 1; i < N; i++) {
			if(second[i-1] == target[i-1]) continue;
			ans++;
			change(second, i);
		}
		
		if(second[N-1] == target[N-1]) {
			result = Math.min(result, ans);
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	
	private static void change(int[] arr, int n) {
		for (int i = n-1; i < n+2; i++) {
			if(i >= 0 && i < N) {
				arr[i] = (arr[i]+1)%2;
			}
		}
	}
}
