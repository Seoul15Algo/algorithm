package com.ssafy.algo230405_Random2.soyun.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1509 {

    static char[] sentence;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sentence = br.readLine().toCharArray();
        N = sentence.length;

        // palindrome[i][j]: i ~ j 인덱스 까지의 문자열이 팰린드롬인지
        boolean[][] palindrome = makePalindromeTable();

        // dp[i] = 문자열 인덱스가 1 ~ i 일때의 최소 팰린드롬 분할
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                // (j - 1) ~ (i - 1)까지의 부분 문자열이 팰린드롬이라면
                // (palindrome 의 시작 인덱스를 0으로 잡아서 -1 해준 것)
                if (palindrome[j - 1][i - 1]){
                    // 현재의 최소 팰린드롬 분할 개수와
                    // 체크한 팰린드롬 문자열을 하나로 셌을 때의 팰린드롬 분할 개수를 비교
                    // 더 작은 값으로 갱신
                    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }

    // palindrome 테이블 채우기
    static boolean[][] makePalindromeTable(){

        boolean[][] palindrome = new boolean[N][N];

        /*  ex) 주어진 문자열 길이 = 4
            행은 문자열 시작 인덱스, 열은 문자열 끝 인덱스
            1 5 8 10
            0 2 6 9
            0 0 3 7
            0 0 0 4
            위에 적힌 숫자 순서대로 테이블을 채운다 (가장 작은 길이의 부분 문자열부터)
            [0][0] -> [1][1] -> [2][2] -> [3][3] -> [0][1] -> [1][2] -> ... 이런 순서로
         */
        for (int i = 0; i < N; i++) {
            int row = 0;
            int col = i;
            while (check(row, col)) {
                // row 와 col 의 차이가 1보다 크다면 -> 체크하고자 하는 문자열의 길이가 3 이상 -> 양 끝과 가운데의 문자열 체크
                // ex) ABBA -> 맨 끝의 A가 일치하는지 체크, 가운데의 BB가 팰린드롬인지 체크
                if (Math.abs(row - col) > 1) {
                    palindrome[row][col] = (sentence[row] == sentence[col]) & palindrome[row + 1][col - 1];
                }
                // 체크하고자 하는 문자열의 길이가 1 또는 2 -> 가운데에 문자열이 없으므로 양 끝값만 비교해준다
                else {
                    palindrome[row][col] = (sentence[row] == sentence[col]);
                }
                row++;
                col++;
            }
        }
        return palindrome;
    }

    // 범위 체크
    static boolean check(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            return false;
        }
        return true;
    }
}