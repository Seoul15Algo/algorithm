package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1309 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[][][] cages = new int[2][N][2];
		
		// 0 : 해당 칸에 사자가 없을 때 가능한 경우의 수 
		// 1 : 해당 칸에 사자가 있을 때 가능한 경우의 수
		cages[0][0][0] = 1; // F
		cages[1][0][0] = 1; // T
		cages[0][0][1] = 2; // FF TF
		cages[1][0][1] = 1; // FT
		
		for (int row = 1; row < N; row++) {
			// 왼쪽 열
			// 현재 칸에 사자가 있는 경우의 수 = 대각선에 사자가 존재하는 경우의 수 + 대각선에 사자가 존재하지 않는 경우의 수 - 바로 윗 칸에 사자가 존재하는 경우의 수
			cages[1][row][0] = (cages[1][row-1][1] + cages[0][row-1][1] - cages[1][row-1][0]) % 9901;
			// 현재 칸에 사자가 없는 경우의 수 = 대각선에 사자가 존재하는 경우의 수 + 대각선에 사자가 존재하지 않는 경우의 수
			cages[0][row][0] = (cages[1][row-1][1] + cages[0][row-1][1]) % 9901;
			
			// 오른쪽 열
			// 현재 칸에 사자가 있는 경우의 수 = 대각선에 사자가 존재하지 않는 경우의 수
			cages[1][row][1] = cages[0][row-1][1];
			// 현재 칸에 사자가 없는 경우의 수 = 옆 칸에 사자가 존재하는 경우의 수 + 옆 칸에 사자가 존재하지 않는 경우의 수
			cages[0][row][1] = (cages[0][row][0] + cages[1][row][0]) % 9901;
		}
		
		System.out.println((cages[0][N-1][1] + cages[1][N-1][1]) % 9901);
		
		br.close();
	}
}