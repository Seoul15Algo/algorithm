package week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// BOJ 3687 : 성냥개비
public class Main_3687 {
	static int[] dpMin;
	static int[] dpMax;
	
	static int max;
	static int min;
	
	static int[] minFirstList = {2, 5, 4, 6, 3, 7};
	static int[] minList = {6, 2, 5, 4, 3, 7};
	static int[] maxList = {6, 7, 3, 5, 4, 2};
	
	static String[] dpMinStr;
	static String[] dpMaxStr;
	
	static int makeMinimumFirstNumber() {
		int digit = min % 7 == 0 ? min / 7 : min / 7 + 1;
		
		for (int num : minFirstList) {
			int next = min - num;
			
			if (next == 1) {
				continue;
			}
			
			int nextDigit = next % 7 == 0 ? next / 7 : next / 7 + 1;
			if (nextDigit == digit) {
				continue;
			}
			
			min = next;
			if (num == 6) {
				return 6;
			}
			return dpMin[num];
		}
		
		return 0;
	}
	
	static int makeMinimumNumber() {
		int digit = min % 7 == 0 ? min / 7 : min / 7 + 1;
		
		for (int num : minList) {
			int next = min - num;
			
			if (next == 1) {
				continue;
			}
			
			int nextDigit = next % 7 == 0 ? next / 7 : next / 7 + 1;
			if (nextDigit == digit) {
				continue;
			}
			
			min = next;
			return dpMin[num];
		}
		
		return 0;
	}

	static int makeMaximumNumber() {
		int digit = max / 2;
		
		for (int num : maxList) {
			int next = max - num;
			
			if (next == 1 || next < 0) {
				continue;
			}
			
			int nextDigit = next / 2;
			if (nextDigit < digit - 1) {
				continue;
			}

			max = next;
			return dpMax[num];
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();
		
		dpMin = new int[8];
		dpMin[2] = 1;
		dpMin[3] = 7;
		dpMin[4] = 4;
		dpMin[5] = 2;
		dpMin[6] = 0;
		dpMin[7] = 8;
		
		dpMax = new int[8];
		dpMax[2] = 1;
		dpMax[3] = 7;
		dpMax[4] = 4;
		dpMax[5] = 5;
		dpMax[6] = 9;
		dpMax[7] = 8;
		
		dpMinStr = new String[101];
		dpMaxStr = new String[101];
		
		for (int i = 2; i < 101; i++) {
			StringBuilder maxAnswer = new StringBuilder();
			max = i;
			while (max > 0) {
				if (dpMaxStr[max] != null) {
					maxAnswer.append(dpMaxStr[max]);
					break;
				}
				
				maxAnswer.append(makeMaximumNumber());
			}
			
			StringBuilder minAnswer = new StringBuilder();
			min = i;
			while (min > 0) {
				if (dpMinStr[min] != null) {
					minAnswer.append(dpMinStr[min]);
					break;
				}

				minAnswer.append(makeMinimumNumber());
			}
			
			dpMinStr[i] = minAnswer.toString();
			dpMaxStr[i] = maxAnswer.toString();
		}
		
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 0; testcase < T; testcase++) {
			int N = Integer.parseInt(br.readLine());
			
			StringBuilder minAnswer = new StringBuilder();
			min = N;
			minAnswer.append(makeMinimumFirstNumber());
			if (min > 1) {
				minAnswer.append(dpMinStr[min]);
			}
			
			answers.append(minAnswer).append(" ");
			answers.append(dpMaxStr[N]).append("\n");
		}
		
		System.out.println(answers.toString());
		br.close();
	}
}