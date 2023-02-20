package com.ssafy.algo230222_BruteForce.soyun.week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_9663_Improve {

    static int n;
    static int[] chessBoard;
    static int count;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        chessBoard = new int[n];
        count = 0;

        nQueen(0);

        System.out.println(count);
    }

    static void nQueen(int idx){

        if (idx == n){
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            chessBoard[idx] = i;

            if (validate(idx))
                nQueen(idx + 1);
        }
    }

    static boolean validate(int idx){

        for (int i = 0; i < idx; i++) {
            if (chessBoard[idx] == chessBoard[i] || idx - i == Math.abs(chessBoard[idx] - chessBoard[i]))
                return false;
        }
        return true;
    }
}
