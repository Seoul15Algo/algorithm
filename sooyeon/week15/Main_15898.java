
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15898 {
	static int N;
	static int result;
	static P[][][][] material; //n번째,회전수, 행,열
	static P[][] jar;
	static int[] pick;
	static boolean[] visited;
	static int[] rotate;
	static int[] start;
	static int[][] startpoint = {{0,0},{0,1},{1,0},{1,1}}; //시작위치
	static class P {
		int ef;
		char el;
		public P() {

		}
		
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		material = new P[N][4][4][4];
		jar = new P[5][5];
		//초기화
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				jar[i][j] = new P();
				jar[i][j].el= 'W';
			}
		}
		
		//입력받기
		for (int i = 0; i < N; i++) {
			//효능 입력
			for (int j = 0; j < 4; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					material[i][0][j][k] = new P();
					material[i][0][j][k].ef = Integer.parseInt(st.nextToken());
				}
			}
			//원소 입력
			for (int j = 0; j < 4; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					material[i][0][j][k].el = st.nextToken().charAt(0);
				}
			}
			
			//회전 결과들을 저장해둠
			for (int j = 1; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						material[i][j][k][l] = new P();
						material[i][j][k][l].ef = material[i][j-1][4-l-1][k].ef;
						material[i][j][k][l].el = material[i][j-1][4-l-1][k].el;
					}
				}
			}
		}
		
		
		pick = new int[3];
		visited = new boolean[N];
		rotate = new int[3];
		start = new int[3];
		perm(0); //N개중 3개를 고른다
		
		
		System.out.println(result);

	}
	static void perm(int count) { //1. N개중 3개 뽑기
		if(count==3) {
			rotate(0); //회전 경우의수
			return;
		}
		for (int i = 0; i < N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			pick[count] = i;
			perm(count+1);
			pick[count] = 0;
			visited[i] = false;
		}
		
	}
	static void rotate(int count) { //2. 회전 수 구하기
		if(count == 3) {
			startpoint(0);
			return;
		}
		for (int i = 0; i < 4; i++) {
			rotate[count] = i;
			rotate(count+1);
			rotate[count] = 0;
		}
		
	}
	static void startpoint(int count) { //3. 시작위치 고르기
		if(count == 3) {
			make();
			return;
		}
		for (int i = 0; i < 4; i++) {
			start[count] = i;
			startpoint(count+1);
			start[count] = 0;
		}
		
		
	}
	static void make() { //4. 폭탄제조
		for (int i = 0; i < 3; i++) {
			int x = startpoint[start[i]][0]; //시작위치
			int y = startpoint[start[i]][1];
			int p = pick[i]; //뽑은숫자
			int r = rotate[i]; //회전 수
			
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					//가마 효능 바꿔줌
					jar[j+x][k+y].ef = Math.max(0, Math.min(9, jar[j+x][k+y].ef + material[p][r][j][k].ef));
					
					//가마 원소 바꿔줌
					if(material[p][r][j][k].el != 'W') {
						jar[j+x][k+y].el = material[p][r][j][k].el;
					}
				}
				
			}
		}
		calc();
	}
	static void calc() { //5. 계산&초기화
		int sum = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				switch (jar[i][j].el) {
				case 'R':
					sum+= 7*jar[i][j].ef;
					break;
				case 'B':
					sum+= 5*jar[i][j].ef;
					break;
				case 'G':
					sum+= 3*jar[i][j].ef;
					break;
				case 'Y':
					sum+= 2*jar[i][j].ef;
					break;
				}
				jar[i][j].ef = 0;
				jar[i][j].el = 'W';

			}
		}
		result = Math.max(result, sum);
	}

}