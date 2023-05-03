import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9328 {
	static int T;
	static int H,W;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] key;
	static List<P> list; //출발지점 저장
	static boolean isD;
	static int result; //훔친 문서 개수
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static class P {
		int x;
		int y;
		public P(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t<= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			key = new boolean[26]; //a~z
			list = new ArrayList<>();
			
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.charAt(j);
					if(map[i][j]!= '*') {
						if(i == 0 || j == 0 || i == H-1 || j == W-1) {
							//가장자리 일 때 && 벽이 아닐 때
							list.add(new P(i,j));
						}
					}
				}
			}
			
			String keys = br.readLine();
			if(keys.charAt(0) != '0') {
				for (int i = 0; i < keys.length(); i++) {
					int index = (int)(keys.charAt(i));
					key[index-97] = true; //아스키코드 값 이용해서 열쇠를 가지고 있다면 true로 저장
				}
			}
			
			
			//bfs 시작
			isD = true; //바뀐게 있는지(열쇠먹었는지)
			result = 0;
			
			while(isD) {
				isD = false;
				visited = new boolean[H][W];
				for (int i = 0; i < list.size(); i++) {
					bfs(list.get(i).x,list.get(i).y);
				}
				
			}
			
			sb.append(result).append("\n");
			
		}
		System.out.println(sb.toString());
		

	}
	static void bfs(int x, int y) {
		Queue<P> q = new LinkedList<>();
		q.offer(new P(x,y));
		visited[x][y] = true;
		if(65<=map[x][y] && map[x][y]<=90) {
			//문 일 경우
			if(key[map[x][y]-65]) { //열쇠 있을 경우
				map[x][y] = '.';
			}else {//열쇠 없으면 return
				return;
			}
		}
		
		if(97<=map[x][y] && map[x][y]<=122) {
			//열쇠 일 경우
			key[map[x][y]-97] = true; //열쇠 true표시
			map[x][y] = '.'; // 열쇠 ->빈공간으로 바꿔줌
			isD = true;
		}
		
		if(map[x][y] == '$') {
			//문서일 경우
			result++;
			map[x][y] = '.'; // 열쇠 ->빈공간으로 바꿔줌
		}
		
		while(!q.isEmpty()) {
			P cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.x + dr[d];
				int nc = cur.y + dc[d];
				
				if(!check(nr,nc)) continue;
				
				//빈공간 일 때 -> 이동
				if(!visited[nr][nc] && map[nr][nc] == '.') {
					visited[nr][nc] = true;
					q.offer(new P(nr,nc));
				}
				
				//열쇠 만났을 때
				if(!visited[nr][nc] && 97<=map[nr][nc] && map[nr][nc] <= 122) { //소문자 -> 97~122
					isD = true; //열쇠 먹었다는 표시 -> 한번더 bfs 돌림
					key[map[nr][nc]-97] = true; //열쇠 true표시
					map[nr][nc] = '.'; // 열쇠 ->빈공간으로 바꿔줌
					visited[nr][nc] = true;
					q.offer(new P(nr,nc));
				}
				
				//문 만났을 때
				if(!visited[nr][nc] && 65<=map[nr][nc] && map[nr][nc] <= 90) { // 대문자 -> 65~90
					if(key[map[nr][nc]-65]) {
						//문에 관련된 열쇠가 있는 경우
						map[nr][nc] = '.'; // 문 ->빈공간으로 바꿔줌
						visited[nr][nc] = true;
						q.offer(new P(nr,nc));						
					}
				}
				
				//문서 만났을 때
				if(!visited[nr][nc] && map[nr][nc] == '$') {
					visited[nr][nc] = true;
					result++; //개수 증가(훔칠 수 있는 문서)
					map[nr][nc] = '.'; //문서-> 빈 공간으로 바꿔줌
					q.offer(new P(nr,nc));
				}
	
			}
		}
		
		
	}
	static boolean check(int nr, int nc) {
		return nr<H && nc<W && nr>=0 && nc>=0;
	}

}