import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1507 {
	static int N;
	static int[][] dist;
	static int[][] copy;
	static int result;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dist = new int[N][N];
		copy = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				dist[i][j] = Integer.parseInt(st.nextToken());
				copy[i][j] = dist[i][j];
			}
		}
		
		//플로이드 와샬
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					
					if(i==j || j==k || i==k) {
						continue;
					}
					
					//최소거리가 갱신이 되어버린다면 -1 출력
					if(dist[i][j] > dist[i][k] + dist[k][j]) {
						System.out.println(-1);
						return;
					}
					
					//필요없는 도로 제거
					if(dist[i][j] == dist[i][k] + dist[k][j]) {
						copy[i][j] = 0;
					}
					
				}
			}
		}
		
		//거리 구하기 -> 다 더해서 반으로 나누기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += copy[i][j];
			}
		}
		
		System.out.println(result/2);
		
	}

}
