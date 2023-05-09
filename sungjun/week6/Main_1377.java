package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1377 {
	static int N;
	static number[] A;
	
	static class number implements Comparable<number> {
		int value;
		int index;
		
		public number(int value, int index) {
			super();
			this.value = value;
			this.index = index;
		}
		
		@Override
		public int compareTo(number o) {
			return Integer.compare(this.value, o.value);
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new number[N];

		// 입력받은 숫자의 값과 인덱스를 묶어서 저장
		for (int i = 0; i < N; i++) {
			A[i] = new number(Integer.parseInt(br.readLine()), i);
		}
		
		// 수 정렬
		Arrays.sort(A);
		int max = 0;
		
		// 기존 인덱스와 지금 인덱스의 차이가 가장 큰 값이 정렬하는데 소요된 총 회전 수
		// 버블 정렬 시 수가 뒤로는 계속해서 움직일 수 있지만 1회전당 앞으로는 최대 한번만 움직일 수 있다
		for (int i = 0; i < N; i++) {
			if(max < A[i].index - i) {
				max = A[i].index - i;
			}
		}
		
		// 더 이상 정렬이 일어나지 않는지 확인하기 위해서 회전이 한 번 더 이루어져야 한다
		System.out.println(max+1);
	}
}