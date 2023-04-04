import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9466 {
	static int T;
	static int N;
	static int[] choice;
	static boolean[] isTeam;
	static boolean[] visited;
	static int count;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			choice = new int[N]; //누구를 선택했는지(입력)
			isTeam = new boolean[N]; //팀인지 확인하는 배열 (false:팀x , true:팀)
			visited = new boolean[N]; //사람 방문 여부
			count = 0;//팀 편성완료 사람 수
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				choice[i] = Integer.parseInt(st.nextToken())-1;
				if(i == choice[i]) { //입력값과 선택한 값이 같으면 true
					isTeam[i] = true;
					count++; 
				}
			}
			
			for (int i = 0; i < N; i++) {
				if(!isTeam[i]) { //팀이 아닐때 , 이전에 방문한적이 없을 때 dfs
					dfs(i);
				}
			}

			//팀이 아닌 경우를 세줌
			sb.append(N - count).append("\n");
			
		}
		System.out.println(sb.toString());

	}
	static void dfs(int index) {
		if(visited[index]) { //이미 방문했다면 팀편성완료 & count 증가
			isTeam[index] = true;
			count++;
		}else {//방문 안했을 때는 방문처리
			visited[index] = true;
		}
		
		if(!isTeam[choice[index]]) { //선택한 사람이 팀이 아니라면
			dfs(choice[index]);
		}
		visited[index] = false; //방문처리 다시 돌려놓기
		isTeam[index] = true; //방문했다는 처리 -> true 처리를 해 줘야 여기 올때 똑같은 경우를 안봄
	}

}