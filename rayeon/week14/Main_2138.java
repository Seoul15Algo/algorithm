package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// BJ 2138 : 전구와 스위치
public class Main_2138 {
	static boolean isSame(int N, int[] cur, int[] target) {
		for (int i = 0; i < N; i++) {
			if (cur[i] != target[i])
				return false;
		}
		
		return true;
	}
	
	static int process(int N, int[] curBlubStatus, int[] targetBlubStatus) {
		int count = 0;
		
		for (int i = 1; i < N - 1; i++) {
			if (curBlubStatus[i-1] != targetBlubStatus[i-1]) {
				curBlubStatus[i-1] = (curBlubStatus[i-1] + 1) % 2;
				curBlubStatus[i] = (curBlubStatus[i] + 1) % 2;
				curBlubStatus[i+1] = (curBlubStatus[i+1] + 1) % 2;
				count++;
			}
		}
		if (curBlubStatus[N-2] != targetBlubStatus[N-2]) {
			curBlubStatus[N-2] = (curBlubStatus[N-2] + 1) % 2;
			curBlubStatus[N-1] = (curBlubStatus[N-1] + 1) % 2;
			count++;
		}
		
		return count;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] originBlubStatus = new int[N];
		int[] targetBlubStatus = new int[N];
		
		char[] inputOriginBlubStatus = br.readLine().toCharArray();
		char[] inputTargetBlubStatus = br.readLine().toCharArray();
		for (int i = 0; i < N; i++) {
			originBlubStatus[i] = inputOriginBlubStatus[i] - '0';
			targetBlubStatus[i] = inputTargetBlubStatus[i] - '0';
		}

		// 첫번째 스위치를 킨 경우
		int[] curBlubStatus = Arrays.copyOf(originBlubStatus, N);
		curBlubStatus[0] = (curBlubStatus[0] + 1) % 2;
		curBlubStatus[1] = (curBlubStatus[1] + 1) % 2;
		int countTurnOnFirstSwitch = process(N, curBlubStatus, targetBlubStatus) + 1;
		if (!isSame(N, curBlubStatus, targetBlubStatus)) {
			countTurnOnFirstSwitch = -1;
		}

		// 첫번째 스위치를 키지 않은 경우
		curBlubStatus = Arrays.copyOf(originBlubStatus, N);
		int countTurnOffFirstSwitch = process(N, curBlubStatus, targetBlubStatus);
		if (!isSame(N, curBlubStatus, targetBlubStatus)) {
			countTurnOffFirstSwitch = -1;
		}
		
		if (countTurnOnFirstSwitch >= 0 && countTurnOffFirstSwitch >= 0) {
			System.out.println(Math.min(countTurnOnFirstSwitch, countTurnOffFirstSwitch));
		} else if (countTurnOnFirstSwitch == -1 && countTurnOffFirstSwitch == -1){
			System.out.println(-1);
		} else {
			System.out.println(Math.max(countTurnOnFirstSwitch, countTurnOffFirstSwitch));
		}
		
		br.close();
	}
}