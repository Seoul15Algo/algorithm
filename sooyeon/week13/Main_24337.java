import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_24337 {
	static int N,A,B;
	static int building[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		building = new int[N];

		if(A+B >= N+2) {
			System.out.println(-1);
			return;
		} 
		
		int tempA = A;
		//최대한 뒤부터 먼저 채워줌
		if(A != 1) {
			for (int i = N-B; i > N-B-A; i--) {
				building[i] = tempA;
				tempA--;
			}
		}
		
		int tempB = B;
		if(B == 1) {
			building[N-1] = Math.max(A, B);
		}else {
			for (int i = N-B; i < N; i++) {
				if(building[i] > tempB) {
					tempB--;
					continue;
				}
				building[i] = tempB;
				tempB--;
			}
		}
		
		//B 다채우고 A가 1일 때 마지막 숫자 제거 & 첫번째에 최댓값 넣기
		if(A == 1) {
			building[N-B] = 0;
			building[0] = B;
		}
		
		
		//빈칸일 때, 1로 출력
		for (int i = 0; i < N; i++) {
			if(building[i] == 0) {
				sb.append(1).append(" ");
			}else {
				sb.append(building[i]).append(" ");						
			}
		}
		System.out.println(sb.toString());

	}

}