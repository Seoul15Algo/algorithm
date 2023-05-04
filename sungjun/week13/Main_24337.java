package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_24337 {
	static int N, A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		if(A+B <= N+1) {
			boolean flag = false;
			

			// 1. A가 1이라면 첫번째 건물이 가희와 단비가 볼 수 있는 가장 먼 건물이다
			// 2. 이 경우 두번째부터 N-B+1번째까지의 건물의 높이는 모두 1이고, 그 후 차례로 B-1, B-2 ... 1의 높이를 가지는게 최적의 해이다
			// 3. A가 1이 아니라면 뒤에서 B번째 건물이 가희와 단비가 볼 수 있는 가장 먼 건물이다
			// 4. 이 경우 뒤에서 B+(A-1)번째 있는 건물까지는 높이가 모두 1이다
			for (int i = 0; i < N-B-A+1; i++) {
				if(A == 1 && !flag) {
					sb.append(A > B ? A : B).append(" ");
					flag = true;
					continue;
				}
				sb.append(1).append(" ");
			}
						
			int tmp = 1;
			
			// 가희와 단비가 공통으로 보는 건물로부터 A-1번째 앞 건물부터 공통 건물까지는 오름차순의 높이를 갖는다
			for (int i = 0; i < A-1; i++) {
				sb.append(tmp++).append(" ");
			}
			
			// A가 1이 아니라면 뒤에서 B번째 건물은 A와 B중에 더 큰 값이다
			if(!flag) {
				sb.append(A > B ? A : B).append(" ");
			}
			
			// A가 1이라면 두번째 건물부터 뒤에서 B번째 건물까지는 높이가 1이다
			if(flag) {
				sb.append(1).append(" ");
			}
			
			tmp = B-1;
			
			// 뒤에서 B-1 번째부터 끝까지는 내림차순의 높이를 가진다
			for (int i = 1; i < B; i++) {
				sb.append(tmp--).append(" ");
			}
			
			System.out.println(sb);
		}
		
		// 해가 존재하지 않을 때
		if(A+B > N+1) {
			System.out.println(-1);
		}
	}

}
