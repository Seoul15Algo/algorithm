package week32;

import java.util.*;

class Q68936 {
    static int zeroCount, oneCount;

    public int[] solution(int[][] arr) {
        solve(0, 0, arr.length, arr);

        return new int[] {zeroCount, oneCount};
    }

    static void solve(int r, int c, int n, int[][] arr) {
        int tmp = arr[r][c];
        boolean flag = true;

        for(int i = r; i < r+n; i++) {
            for(int j = c; j < c+n; j++) {
                if(arr[i][j] != tmp) {
                    flag = false;
                    break;
                }
            }
            if(!flag) break;
        }

        if(flag) {
            if(tmp == 0) zeroCount++;
            if(tmp == 1) oneCount++;
            return;
        }

        solve(r, c, n/2, arr);
        solve(r+n/2, c, n/2, arr);
        solve(r, c+n/2, n/2, arr);
        solve(r+n/2, c+n/2, n/2, arr);
    }
}