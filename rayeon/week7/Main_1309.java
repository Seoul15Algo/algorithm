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
		
		cages[0][0][0] = 1;
		cages[1][0][0] = 1;
		cages[0][0][1] = 2;
		cages[1][0][1] = 1;
		
		for (int row = 1; row < N; row++) {
			cages[1][row][0] = (cages[1][row-1][1] + cages[0][row-1][1] - cages[1][row-1][0]) % 9901;
			cages[0][row][0] = (cages[1][row-1][1] + cages[0][row-1][1]) % 9901;
			
			cages[1][row][1] = cages[0][row-1][1];
			cages[0][row][1] = (cages[0][row][0] + cages[1][row][0]) % 9901;
		}
		
		System.out.println((cages[0][N-1][1] + cages[1][N-1][1]) % 9901);
		
		br.close();
	}
}