package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_14002 {
	static class Number {
		int data;
		int dp;
		
		public Number(int data) {
			this.data = data;
			this.dp = 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		Number[] numbers = new Number[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = new Number(Integer.parseInt(st.nextToken()));
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (numbers[j].data < numbers[i].data) {
					numbers[i].dp = Math.max(numbers[i].dp, numbers[j].dp + 1);
				}
			}
		}
		
		Arrays.sort(numbers, new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1.dp == o2.dp) {
					return -Integer.compare(o1.data, o2.data);
				}
				
				return -Integer.compare(o1.dp, o2.dp);
			}
		});
		
		int maxCnt = numbers[0].dp;
		int[] result = new int[maxCnt];

		int idx = maxCnt - 1;
		int maxValue = 1001;
		for (int i = 0; i < N; i++) {
			if (maxCnt == 0 || idx < 0)
				break;
			
			if ((idx + 1) == numbers[i].dp) {
				if (numbers[i].data >= maxValue)
					continue;
				
				result[idx--] = numbers[i].data;
				maxValue = numbers[i].data;
			}
		}
		
		System.out.println(maxCnt);
		for (int i = 0; i < maxCnt; i++) {
			System.out.print(result[i] + " ");
		}

		br.close();
	}
}