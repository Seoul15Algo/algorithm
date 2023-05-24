import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_21611 {
	static int N,M,D,S;
	static int[][] map;
	static int[] dr = {-1,1,0,0}; //위 아 왼 오 순
	static int[] dc = {0,0,-1,1};
	static int[] mr = {0,1,0,-1}; //왼 아 오 위 순
	static int[] mc = {-1,0,1,0};
	static Deque<E> d ;
	static int beforeBiz;
	static int result;
	static int keepNum;
	static boolean isEmpty;
	static boolean isBoom;
	static class E {
		int biz;
		int count;
		public E(int biz, int count) {
			super();
			this.biz = biz;
			this.count = count;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < M; i++) {
			//방향 (0 : 위, 1 : 아래 , 2 : 왼쪽, 3 : 오른쪽), 거리
			st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken())-1;
			S = Integer.parseInt(st.nextToken());
			
			int r = N/2, c= N/2; //상어 위치
			
			//구슬 파괴
			for (int s = 1; s <= S; s++) {
				int nr = r + dr[D]*s;
				int nc = r + dc[D]*s;
				map[nr][nc] = 0;
			}
			
			//구슬 이동 & 폭발
			
			d = new ArrayDeque<>();
			int curR = r;
			int curC = c;
			boolean isEnd = false;
			
			int num = 1; //왼1,아1 -> 오2,위2 -> 왼3,아3 -> 오4,위4 -> ... 순으로 이동함 이때의 숫자 번호
			beforeBiz = -1; //이전 구슬번호
			while(true) {
				if(num%2 == 1) {
					//왼쪽으로 num 번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[0];
						curC += mc[0];
						bizmove(curR,curC);
						if(curR == 0 && curC==0) {
							isEnd = true;
							break;
						}
					}
					if(isEnd) break;
					//아래로 num번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[1];
						curC += mc[1];
						bizmove(curR,curC);
					}
					
				}else {
					//오른쪽으로 num 번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[2];
						curC += mc[2];
						bizmove(curR,curC);
					}
					//위로 num번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[3];
						curC += mc[3];
						bizmove(curR,curC);
					}
				}
				if(isEnd) break;
				num++;
			}
			
			//구슬폭발이 플래그 없을 때 까지 돌게
			isBoom = true;
			while(isBoom) {
				checkbiz();				
			}
			

			//구슬 변화
			map = new int[N][N]; //맵 초기화
			num = 1;
			curR = r;
			curC = c;
			keepNum = 0;
			isEmpty = false;
			
			while(true) {
				if(num%2 == 1) {
					//왼쪽으로 num 번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[0];
						curC += mc[0];
						putinMap(curR,curC);
						if(curR == 0 && curC==0) {
							isEmpty = true;
							break;
						}
					}
					if(isEmpty) break;
					//아래로 num번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[1];
						curC += mc[1];
						putinMap(curR,curC);
					}
					
				}else {
					//오른쪽으로 num 번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[2];
						curC += mc[2];
						putinMap(curR,curC);

					}
					//위로 num번 이동
					for (int k = 0; k < num; k++) {
						curR += mr[3];
						curC += mc[3];
						putinMap(curR,curC);

					}
				}
				num++;
				if(isEmpty) break;

			}
			
		}
		System.out.println(result);

	}
	private static void checkbiz() {
		isBoom = false;
		//덱 순회하면서 검사
		beforeBiz = -1; //이전 구슬번호
		int size = d.size();
		for (int i = 0; i < size; i++) {
			E cur = d.pollFirst();
			
			if(cur.biz != beforeBiz) {
				//이전 구슬과 다를 때
				if(d.size()!=0 && d.peekLast().count >= 4) {
					isBoom = true;
					int count = d.peekLast().count;
					result += d.peekLast().biz * count;
					for (int l = 0; l < count; l++) {
						d.pollLast();
					}
				}
			}
			
			if(cur.biz == beforeBiz) {
				d.offerLast(new E(cur.biz, d.peekLast().count+1));
			}else {
				d.offerLast(new E(cur.biz,1));
				beforeBiz = cur.biz;
			}
			
			//덱에 들어있는 구슬이 전부 같은 구슬일 경우 && 4이상 인 경우
			if(i == size-1 && d.peekLast().count>=4) {
				int count = d.peekLast().count;
				result += d.peekLast().biz * count;
				for (int l = 0; l < count; l++) {
					d.pollLast();
				}
			}
		}
		
		
	}
	static void putinMap(int curR, int curC) {
		if(d.size()==0 && keepNum==0) {
			isEmpty = true;
			return;
		}
		if(keepNum == 0) {
			E cur = d.pollFirst();
			while(!d.isEmpty() && cur.biz == d.peekFirst().biz) {
				cur = d.pollFirst();
			}
			map[curR][curC] = cur.count;
			keepNum = cur.biz;
		}else {
			map[curR][curC] = keepNum;
			keepNum = 0;
		}

		
	}
	private static void bizmove(int curR, int curC) {
		if(map[curR][curC] == 0) return; //0이면 건너 뜀

		//이전 구슬번호와 일치하면 count를 그에 맞게 입력
		if(map[curR][curC] == beforeBiz) {
			d.offerLast(new E(map[curR][curC], d.peekLast().count+1));
		}else {
			d.offerLast(new E(map[curR][curC],1));
			beforeBiz = map[curR][curC];
		}
		
	}
}