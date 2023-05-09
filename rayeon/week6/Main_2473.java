package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		long[] nums = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(nums);

		long result = Long.MAX_VALUE;
		int[] idx = new int[3];
		
		// 가장 작은 수를 고정하고 합이 0과 가장 가까운 경우 찾기
		for (int left = 0; left < N-2; left++) {
			int mid = left + 1;
			int right = N - 1;
			
			while (left < mid && mid < right) {
				long sum = nums[left] + nums[mid] + nums[right];
				
				if (Math.abs(sum) < result) {
					result = Math.abs(sum);
					idx[0] = left;
					idx[1] = mid;
					idx[2] = right;
				}

				if (sum > 0){
					right++;
				}
				else {
					mid++;
				}
			}
		}
		
		System.out.println(nums[idx[0]] + " " + nums[idx[1]] + " " + nums[idx[2]]);
		
		br.close();
	}
}