import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2468 {
	static int N;
	static int[][] ground; //지역 높이 배열
	static int result = 1; //결과값
	static int min = 100, max = 1; //높이 최소, 최댓값
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ground = new int[N][N];
		
		//입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ground[i][j] = Integer.parseInt(st.nextToken());
				if(ground[i][j]< min) min= ground[i][j];
				if(ground[i][j]> max) max= ground[i][j];
			}
		}
		
		for (int i = min; i < max; i++) {
			Queue<int[]> q = new LinkedList<>();
			boolean [][] check = new boolean[N][N]; //방문 확인배열
			int count = 0;
			
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if(ground[j][k] <= i || check[j][k]) continue; //물높이(i) 보다 땅높이가 작거나같을때 or 배열check면 continue
					count++;
					
					check[j][k] = true;
					q.offer(new int[] {j,k}); //하나 넣고
					
					while(!q.isEmpty()) {
						int a[] = q.poll(); // 빼고
						
						for (int d = 0; d < 4; d++) {
							int r = a[0] + dr[d];
							int c = a[1] + dc[d];
							
							if(!check(r,c)) continue;
							if(check[r][c] || ground[r][c] <=i) continue;
							
							check[r][c] = true;
							q.offer(new int[] {r,c});
						}
					}
				}
			}
			if(count> result) result =count;
				
		}
		System.out.println(result);
	}
	public static boolean check(int r, int c) {
		return r>=0 && c>=0 && r<N && c<N;
	}

}
