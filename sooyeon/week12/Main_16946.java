import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16946 {
	static int N,M;
	static int map[][];
	static int group[][];
	static boolean gCheck[];
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static List<Integer> countG;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		group = new int[N][M];
		countG = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		
		int index = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0 && group[i][j] == 0) {
					//빈칸일경우 bfs
					bfs(i,j,index);
					index++;
				}
			}
		}
		
		//출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 0) { //0이면 그대로 출력
					sb.append("0");
				}
				if(map[i][j] ==1) {
					int answer = 1; //기본 1에(그 자리에 벽이 깨지면 1)
					gCheck = new boolean[index];
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(!check(nr,nc)) continue;
						if(map[nr][nc] == 1) continue;
						//그룹인덱스가 2부터 시작해서
						if(!gCheck[group[nr][nc]]) {
							answer += countG.get(group[nr][nc]-2);
							gCheck[group[nr][nc]] = true;
						}
					}
					sb.append(answer%10);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
		
		
		
	}
	static void bfs(int r, int c, int index) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {r,c});
		group[r][c] = index;
		int count = 0; //그룹안에 빈칸이 몇 개 인지
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			count++;
			count = count%10;
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0]+ dr[d];
				int nc = cur[1]+ dc[d];
				
				if(!check(nr,nc)) continue;
				if(map[nr][nc] == 1 || group[nr][nc]!=0) continue;
				
				group[nr][nc] = index;
				q.offer(new int[] {nr,nc});
			}
		}
		//몇개인지 리스트에 저장
		countG.add(count);
		
	}
	static boolean check(int nr, int nc) {
		return nr<N && nc<M && nr>=0 && nc>=0;
	}

}