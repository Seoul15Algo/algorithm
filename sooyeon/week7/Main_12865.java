import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12865 {
	static int N,K,W,V; //물품수,최대무게,무게,가치
	static int[][] backpack;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		backpack = new int[N+1][K+1]; //물품수+1, 최대무게+1
		
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			
			for (int j = 1; j < K+1; j++) {
				//이전아이템 가치 저장
				backpack[i][j] = backpack[i-1][j];

				if(j - W >= 0) { //전체무게-물품무게가 0이상 이라면 (남는무게존재)
					//이전아이템 가치 , 남은무게가치(현재 아이템 넣기 전 가치(j-W)+현재 가치 중 큰 것 선태
					backpack[i][j] = Math.max(backpack[i-1][j], backpack[i-1][j-W]+V);
				}
			}
		}
		
		System.out.println(backpack[N][K]);

	}

}
