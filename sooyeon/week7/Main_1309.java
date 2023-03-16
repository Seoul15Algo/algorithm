import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1309 {
	static int N;
	static int M = 9901;
	static int[][] cage;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cage = new int[N][3]; //0: 사자안넣는 경우, 1:왼쪽케이지 사자넣는경우,2:오른쪽케이지 사자넣는경우
		
		cage[0][0] = 1;
		cage[0][1] = 1;
		cage[0][2] = 1;
		
		for (int i = 1; i < N; i++) {
			cage[i][0] = (cage[i-1][0]+cage[i-1][1]+cage[i-1][2])%M;
			cage[i][1] = (cage[i-1][0]+cage[i-1][2])%M;
			cage[i][2] = (cage[i-1][0]+cage[i-1][1])%M;
		}	
		System.out.println((cage[N-1][0]+cage[N-1][1]+cage[N-1][2])%M);
	}
}