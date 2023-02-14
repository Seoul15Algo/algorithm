import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1138 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		String[] s = br.readLine().split(" ");
		int[] leftCount = new int[N];
		int[] position = new int[N];
		
		for (int i = 0; i < N; i++) {
			leftCount[i] = Integer.parseInt(s[i]);
		}
		
		// 키가 가장 작은 사람은 왼쪽에 키가 큰 사람이 있는 숫자번째만큼 오른쪽에 있음
		position[leftCount[0]] = 1;
		
		for (int i = 1; i < N; i++) {
			int bigCount = leftCount[i];  // 왼쪽에 몇명이나 있는지 체크
			int count = 0;	// 비어있는 공간
			int occupy = 0;  // 배정된 공간
			
			for (int j = 0; j < N; j++) {
				if(position[j] == 0) {		// 비어있는 공간일때
					if(count == bigCount) break;	// 비어있는 공간의 개수가 왼쪽에 있는 사람의 수보다 작으면
					count++;	// 카운트 증가
				} else {
					occupy++;	// 비어있지 않으면 배정된 공간 카운트 증가
				}
			}
			
			position[count+occupy] = i+1;	// 비어있는 공간(왼쪽에 있는 사람의 수) + 배정된 공간만큼 오른쪽으로 이동된 자리 배치 
		}
		
		for (int i = 0; i < N; i++) {
			System.out.print(position[i] + " ");
		}
		
	}

}