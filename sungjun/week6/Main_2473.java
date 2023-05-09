package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {
	static int N;
	static long[] solution;
	static long[] result;
	static long min = Long.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		solution = new long[N];
		result = new long[3];
		
		for (int i = 0; i < N; i++) {
			solution[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(solution);
		
		a:  // 용액을 총 세개 골라야 하므로 하나는 고정시켜 놓고 투포인터 사용
		for (int i = 0; i < N; i++) {
			// 투포인터의 시작점은 고정된 용액의 인덱스+1
			// 그 전의 값들은 i-1번째에서 이미 고려되었기 때문에 무시해도 됨
			int start = i+1;
			int end = N-1;
			
			while(start < end) {
				long diff = solution[start] + solution[end] + solution[i];
				
				// 합이 0이라면 탐색 종료
				if(diff == 0) {
					result[0] = solution[start];
					result[1] = solution[end];
					result[2] = solution[i];
					
					break a; 
				}
				
				// 최소값 갱신
				if(Math.abs(diff) < min) {
					min = Math.abs(diff);
					result[0] = solution[start];
					result[1] = solution[end];
					result[2] = solution[i];
				}
				
				// 용액의 합이 음수면 시작포인터 증가
				if(diff < 0) {
					start++;
					continue;
				}
				end--;	// 양수면 끝포인터 감소
			}
		}
		
		Arrays.sort(result);
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}
}
