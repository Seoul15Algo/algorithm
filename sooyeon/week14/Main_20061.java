import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_20061 {
	static int N;
	static int score; //점수
	static int tileCount;
	static boolean[][] green; //초록칸
	static boolean[][] blue; //파란칸
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		green = new boolean[6][4];
		blue = new boolean[4][6];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			//블록이 배치된 위치 저장
			List<Integer> pointG = new ArrayList<>();
			List<Integer> pointB = new ArrayList<>();
			
			
			switch (t) { //구역에 배치
			case 1:
				//초록색 구역에 배치 -> y 값만 고려
				for (int j = 0; j < 6; j++) {
					if(green[j][y]) { //블록이 있는 경우
						green[j-1][y] = true;
						pointG.add(j-1);
						break;
					}
					if(j == 5) { //끝인경우(벽)
						green[j][y] = true;
						pointG.add(j);
						break;
					}
				}
				
				//파란색 구역에 배치 -> x 값만 고려
				for (int j = 0; j < 6; j++) {
					if(blue[x][j]) {
						blue[x][j-1] = true;
						pointB.add(j-1);
						break;
					}
					if(j == 5) {
						blue[x][j] = true;
						pointB.add(j);
						break;
					}
				}
				break;
			case 2:
				//초록색 구역에 배치 -> y 값만 고려
				for (int j = 0; j < 6; j++) {
					if(green[j][y] || green[j][y+1]) {
						green[j-1][y] = true;
						green[j-1][y+1] = true;
						pointG.add(j-1);
						break;
					}
					if(j == 5) { //끝인경우(벽)
						green[j][y] = true;
						green[j][y+1] = true;
						pointG.add(j);
						break;
					}
				}
				//파란색 구역에 배치 -> x 값만 고려				
				for (int j = 0; j < 6; j++) {
					if(blue[x][j]) {
						blue[x][j-1] = true;
						blue[x][j-2] = true;
						pointB.add(j-1);
						pointB.add(j-2);
						break;
					}
					if(j == 5) {
						blue[x][j] = true;
						blue[x][j-1] = true;
						pointB.add(j);
						pointB.add(j-1);
						break;
					}
				}

				break;
			case 3:
				//초록색 구역에 배치 -> y 값만 고려
				for (int j = 0; j < 6; j++) {
					if(green[j][y]) {
						green[j-1][y] = true;
						green[j-2][y] = true;
						pointG.add(j-1);
						pointG.add(j-2);
						break;
					}
					if(j == 5) { //끝인경우(벽)
						green[j][y] = true;
						green[j-1][y] = true;
						pointG.add(j);
						pointG.add(j-1);
						break;
					}
				}
				
				//파란색 구역에 배치 -> x 값만 고려				
				for (int j = 0; j < 6; j++) {
					if(blue[x][j] || blue[x+1][j]) {
						blue[x][j-1] = true;
						blue[x+1][j-1] = true;
						pointB.add(j-1);
						break;
					}
					if(j == 5) {
						blue[x][j] = true;
						blue[x+1][j] = true;
						pointB.add(j);
						break;
					}
				}
				
				break;
			}
			
			//꽉찬 열 제거 - 방금 블록이 들어온 공간만 검사. . 해줘도 되나?
			int removeCntG = 0; //블록이 몇행 사라졌는지
			int removeCntB = 0;
			int pG = -1; //삭제 된 블록 중 제일 윗블록 열
			int pB = -1;
			
			for (int j = 0; j < pointG.size(); j++) {
				int cur = pointG.get(j);
				if(checkG(cur)) {
					//false로 바꿔주기
					removeCntG++;
					score++; //점수증가
					pG = cur-1;
					for (int row = 0; row < 4; row++) {
						green[cur][row] = false;
					}
				}
			}
			
			for (int j = 0; j < pointB.size(); j++) {
				int cur = pointB.get(j);
				if(checkB(cur)) {
					//false로 바꿔주기
					removeCntB++;
					score++; //점수증가
					pB = cur-1;
					for (int col = 0; col < 4; col++) {
						blue[col][cur] = false;
					}
				}
			}

			//옮기기 - 삭제된 횟수만큼
			while(pG != -1) {
				for (int j = 0; j < 4; j++) {
					green[pG+removeCntG][j]= green[pG][j];
					green[pG][j] = false;
				}
				pG--;
			}
			
			while(pB != -1) {
				for (int j = 0; j < 4; j++) {
					blue[j][pB+removeCntB]= blue[j][pB];
					blue[j][pB] = false;
				}
				pB--;
			}
			
			
			//연한부분 처리 - 초록색
			int softCntG = isInG();
			if(softCntG!=0) {
				for (int j = 5-softCntG; j >= 0; j--) {
					for (int col = 0; col < 4; col++) {
						green[j+softCntG][col] = green[j][col];
						green[j][col] = false;
					}
				}				
			}
			
			//연한부분 처리 - 파란색
			int softCntB = isInB();
			if(softCntB!=0) {
				for (int j = 5-softCntB; j >= 0; j--) {
					for (int row = 0; row < 4; row++) {
						blue[row][j+softCntB] = blue[row][j];
						blue[row][j] = false;
					}
				}				
			}
			
		}
		System.out.println(score);
		tileCount+= countTileG();
		tileCount+= countTileB();
		System.out.println(tileCount);

	}
	static int countTileB() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 2; j < 6; j++) {
				if(blue[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	static int countTileG() {
		int count = 0;
		for (int i = 2; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if(green[i][j]) {
					count++;
				}
			}
		}
		return count;
	}
	static int isInB() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if(blue[i][0]) {
				count++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if(blue[i][1]) {
				count++;
				break;
			}
		}
		return count;
	}
	static int isInG() {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			if(green[0][i]) {
				count++;
				break;
			}
		}
		for (int i = 0; i < 4; i++) {
			if(green[1][i]) {
				count++;
				break;
			}
		}
		return count;
	}
	static boolean checkB(int row) {
		for (int i = 0; i < 4; i++) {
			if(!blue[i][row]) {
				return false;				
			}
		} 
		return true;
	}
	static boolean checkG(int col) {
		for (int i = 0; i < 4; i++) {
			if(!green[col][i]) {
				return false;				
			}
		} 
		return true;
	}

}