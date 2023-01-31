import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int col = Integer.parseInt(st.nextToken());
		int row = Integer.parseInt(st.nextToken());
		
		int block[] = new int[row];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < row; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
		
		int count = 0;
		for (int i = 1; i < row-1; i++) { //첫번째줄, 마지막줄 빼고 빗물 채우기
			count += (col - block[i]);
		}
		
		for (int i = 1; i < row-1; i++) {
			int left = 0; //왼쪽 중 제일 큰 블록
			int right = 0; //오른쪽 중 제일 큰 블록
			//왼쪽탐색
			for (int j = 0; j < i; j++) {
				if(block[j] > left)
					left =block[j];
			}
			//오른쪽 탐색
			for (int j = i+1; j < row; j++) {
				if(block[j] > right)
					right =block[j];
			}

			int min = Math.min(left, right); //오른쪽, 왼쪽 중 작은 숫자 저장
			if(block[i]!= col) { // 열의 숫자보다 적을시
				if(left==0 || right ==0 || min<block[i]) {// left,right 중 0이 있을 시or 둘다 내 크기보다 작을시
					count = count-(col-block[i]); //col-높이 만큼 count 감소
				}else {
					if(min<col) { 
						count = count-(col-min); //col- 작은수 만큼 count 감소
					}
				}
			}
		}
		System.out.println(count);
	}
}