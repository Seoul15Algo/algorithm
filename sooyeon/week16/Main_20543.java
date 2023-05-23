import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20543 {
	static int N,M;
	static long[][] map;
	static long[][] bomb; //결과 : 폭탄 개수
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new long[N][N];
		bomb = new long[N][N];
		
		int point = M/2;
		
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = -Long.parseLong(st.nextToken()); //양수로 저장
			}
		}
		
		for (int i = point; i < N - point; i++) {
			for (int j = point; j < N - point; j++) {
				bomb[i][j] = map[i-point][j-point];
				
				if(i-point-1 >= 0) { //B범위 안이면 
					bomb[i][j] -= map[i-point-1][j-point];
				}
				if(j-point-1 >= 0) { //C범위 안이면
					bomb[i][j] -= map[i-point][j-point-1];
				}
				if(i-point-1>=0 && j-point-1 >= 0) { //D범위 안이면
					bomb[i][j] += map[i-point-1][j-point-1];
				}
				
				if(i-M>=0) { //b3의 범위이면
					bomb[i][j] += bomb[i-M][j];
				}
				if(j-M>=0) { //b7의 범위이면
					bomb[i][j] += bomb[i][j-M];
				}
				if(i-M>=0 && j-M>=0) { //b1의 범위이면
					bomb[i][j] -= bomb[i-M][j-M];
				}
				
			}
			
		}
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(bomb[i][j]).append(" ");
			}
			sb.append("\n");
		}

		
		System.out.println(sb.toString());

	}

}