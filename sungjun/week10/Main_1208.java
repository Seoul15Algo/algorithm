package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1208 {
	static int N, S;
	static int[] nums;
	static boolean[] selected;
	static long result;
	static ArrayList<Integer> left;
	static ArrayList<Integer> right;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		left = new ArrayList<>();
		right = new ArrayList<>();
		nums = new int[N];
		selected = new boolean[N];
		result = 0;
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		// 수열을 절반으로 나누어 각 수열의 부분수열의 합 생성
		subset(0, N/2);
		selected = new boolean[N];
		subset(N/2, N);
		
		// 부분수열의 합을 저장한 수열을 오름차순으로 정렬
		Collections.sort(left);
		Collections.sort(right);
		
		int leftIdx = 0;
		int rightIdx = right.size()-1;
		
		// 투포인터 탐색 진행
		while(leftIdx < left.size() && rightIdx >= 0) {	
			int l = left.get(leftIdx);
			int r = right.get(rightIdx);
			
			if(l+r == S) {				
				long lspan = 0;
				long rspan = 0;
				
				// 중복 값을 가진 원소를 체크하기 위함
				// 예를 들어 S = 5인 경우, left의 원소가 [1, 2, 2, 3, 4] right의 원소가 [3, 3, 5, 8, 10]이라면
				// left + right = 5를 만족하는 경우는 left = 2, right = 3 이다
				// 하지만 left가 2인 경우가 2개, right가 3인 경우가 2개일 경우 2*2로 4가지 경우의 수가 발생되기 때문에 이를 고려해주어야 한다
				while(leftIdx < left.size()) {
					if(left.get(leftIdx) == l) {
						lspan++;
						leftIdx++;
						continue;
					}
					break;
				}
				
				while(rightIdx >= 0) {
					if(right.get(rightIdx) == r) {
						rspan++;
						rightIdx--;
						continue;
					}
					break;
				}
				
				result += lspan*rspan;
				continue;
			}
			
			// S보다 작다면 left의 포인터 이동
			if(l+r < S) {
				if(leftIdx < left.size()) {
					leftIdx++;
					continue;
				}
				break; 	// S보다 작은데 left 배열이 끝났다면, 더이상 합을 키울 수 없으므로 break
			}
			
			// S보다 크다면 right의 포인터 이동
			if(l+r > S) {
				if(rightIdx > 0) {
					rightIdx--;
					continue;
				}
				break;	// S보다 큰데 right 배열이 끝났다면, 더이상 합을 줄일 수 없으므로 break
			}
		}
		
		// S가 0일 때는 공집합도 카운트가 되었기 때문에 공집합끼리 더한 한 가지 경우의 수를 빼준다
		System.out.println(S == 0 ? result-1 : result);
	}
	
	private static void subset(int start, int end) {
		if(start == end) {
			int sum = 0;
			
			for (int i = 0; i < nums.length; i++) {				
				if(selected[i]) {
					sum += nums[i];
				}
			}
			
			if(end == N/2) {
				left.add(sum);
			}
			
			if(end == N) {
				right.add(sum);
			}			
			return;
		}
		
		selected[start] = true;
		subset(start+1, end);
		selected[start] = false;
		subset(start+1, end);
	}
}