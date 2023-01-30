import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_14719 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		String[] sn = br.readLine().split(" ");
		
		int[] block = new int[sn.length];
		
		for (int i = 0; i < sn.length; i++) {
			block[i] = Integer.parseInt(sn[i]);
		}
		
		int H = Integer.parseInt(s[0]);
		int W = Integer.parseInt(s[1]);
		
		int[][] map = new int[H][W];
		
		// 블럭이 쌓인 공간은 -1로 저장
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < block[i]; j++) {
				map[H-j-1][i] = -1;
			}
		}
		
		
		for (int i = 0; i < H; i++) {
			// 시작과 끝 인덱스를 -1로 초기화
			int start = -1;
			int end = -1;
			
			for (int j = 0; j < W; j++) {
				if(start == -1) {
					// 시작 인덱스가 지정되지 않은 상태에서 블럭이 쌓인 칸을 만나면
					if(map[i][j] == -1) {
						start = j;  // 해당 칸을 시작 인덱스로 저장
					}
				} else {	// 시작 인덱스가 지정된 상태라면
					// 직전 칸이 블럭 칸이 아니라면
					if(map[i][j] == -1 && map[i][j-1] != -1) {
						end = j;	// 해당 칸을 끝 인덱스로 저장
						
						// 시작 인덱스부터 끝 인덱스 사이를 1로 채움
						for (int k = start+1; k < end; k++) {
							map[i][k] = 1;
						}
						
						// 현재 끝 인덱스를 새로운 시작 인덱스로 지정
						start = end;
						end = -1;  // 끝 인덱스 초기화
					} else if(map[i][j] == -1 && map[i][j-1] == -1) {  // 직전 칸이 블럭 칸이라면
						start = j;	// 시작 인덱스를 오른쪽으로 한칸 이동
					}
				}
			}
		}
		
		int count = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 1) count++;
			}
		}
				
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println(count);

	}
}