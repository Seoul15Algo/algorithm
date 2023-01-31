import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1138{
	static int N;
	static int [] left; //입력 왼쪽 값 저장
	static int [] number; //경우의 수
	static boolean [] visited;
	static int[] result; //결과 값 저장
	static int[] rleft; // number에 따른 왼쪽 사람 수 계산
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		left = new int[N]; //왼쪽 사람 수 + 저장
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			left[i] = Integer.parseInt(st.nextToken());
		}
		
		visited = new boolean[N];
		number = new int[N]; //1~N 저장
		for (int i = 0; i < N; i++) {
			number[i] = i;
		}
		rleft = new int[N];
		result = new int[N];
		
		//1234~4321 방문
		perm(0); // 재귀 

	}

	private static void perm(int depth) {
		if(depth == N) {
			//여기서 num 정해짐
//			System.out.println(Arrays.toString(result));
			for (int i = N-1; i >= 0; i--) {
				int count = 0;
				for (int j = 0; j < i; j++) {
					if(result[j] > result[i])
						count++;
					rleft[i] = count;
				}
			}
			int count = 0;
			for (int i = 0; i < N; i++) {
				if(left[result[i]] == rleft[i]) {
					count++;
				}
				if(count==N) {
					for (int re : result) {
						System.out.print((re+1)+" ");
					}
				}
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			//방문한적 없다.
			visited[i] = true;
			result[depth] = number[i];
			perm(depth+1);
			result[depth] = 0;
			visited[i] = false;
			
		}
	}
}
