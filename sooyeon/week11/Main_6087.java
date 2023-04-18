import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_6087 {
	static int W,H;
	static int x1,y1,x2,y2;
	static char[][] map;
	static int[][][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new char[H][W];
		visited = new int[H][W][4]; //행, 열, 레이저의 방향
		
		for (int i = 0; i < H; i++) { //visited 배열 큰 값으로 초기화
			for (int j = 0; j < W; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		
		boolean isE = false ;
		for (int i = 0; i < H; i++) {
			String str = br.readLine();
			for (int j = 0; j < W; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'C') {
					if(!isE) {
						x1 = i;
						y1 = j;
						isE = true;
					}
					if(isE) {
						x2 = i;
						y2 = j;
					}
				}
			}
		}
		bfs(-1,0); //이전방향, 방향 바꾼 카운트
		
		//처음에 무조건 count 하나 주고 시작했기 때문에 (방향 : -1) result값에 -1 해줌
		int answer1 = Math.min(visited[x2][y2][0], visited[x2][y2][1]);
		int answer2 = Math.min(visited[x2][y2][2], visited[x2][y2][3]);
		System.out.println(Math.min(answer1, answer2)-1);
	}
	static void bfs(int direction, int count) {
		Queue<int[]> q = new LinkedList<>();
		for (int i = 0; i < 4; i++) {
			visited[x1][y1][i] = -1;		
		}
		q.offer(new int[] {x1,y1,direction,count});
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				int cnt = cur[3];
				if(cur[2]!=d) { //방향 다르면 count 증가
					cnt+=1;
				}
				if(!check(nr,nc)) continue;
				if((map[nr][nc] =='.' || map[nr][nc] =='C')&&(visited[nr][nc][d]> cnt)) { //거울 사용 횟수가 더 적으면 q에 넣어줌
					visited[nr][nc][d] = cnt; //방문 표시
					q.offer(new int[] {nr,nc,d,cnt});
				}
			}
		}
		
	}
	static boolean check(int nr, int nc) {
		return nr< H && nc<W && nr>=0 && nc>=0;
	}

}