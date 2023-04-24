import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16724 {
	static int N,M;
	static char map[][];
	static int visited[][];
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			map[i] = str.toCharArray();
		}
		
		int index = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(visited[i][j] == 0) { //방문한 적이 없을 시 움직임
					move(i,j,index++);
				}
			}
		}
		System.out.println(count);

	}
	static void move(int r, int c, int index) {
		visited[r][c] = index; //방문처리
		if(map[r][c] == 'U') {
			if(visited[r-1][c] == index) { //사이클 발견 -> 카운트추가
				count++;
			}
			if(visited[r-1][c] == 0) { //이동한 적이 없을 시
				move(r-1,c,index);
			}
			//다른 경우 -> 다른 사이클에 도달 (더이상의 이동도, 카운트도 안해도됨)
		}
		if(map[r][c] == 'D') {
			if(visited[r+1][c] == index) {
				count++;
			}
			if(visited[r+1][c] == 0) {
				move(r+1,c,index);
			}
		}
		if(map[r][c] == 'L') {
			if(visited[r][c-1] == index) {
				count++;
			}
			if(visited[r][c-1] == 0) {
				move(r,c-1,index);
			}
		}
		if(map[r][c] == 'R') {
			if(visited[r][c+1] == index) {
				count++;
			}
			if(visited[r][c+1] == 0) {
				move(r,c+1,index);
			}
		}
	}

}
