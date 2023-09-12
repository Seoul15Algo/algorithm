package week26;

import java.util.*;

class n진수게임 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();

        char[] nums = makeTotalNums(n, t, m).toCharArray();

        int cnt = 0;
        int idx = 0;

        for(int i=0; i<nums.length; i+=m) {
            if(cnt == t) {
                break;
            }

            idx = i + p - 1;
            answer.append(Character.toUpperCase(nums[idx]));
            cnt++;
        }

        return answer.toString();
    }

    private static String makeTotalNums(int n, int t, int m) {
        StringBuilder totalNums = new StringBuilder();

        for(int i = 0; i <= m*t; i++) {
            totalNums.append(Integer.toString(i, n));
        }

        return totalNums.toString();
    }
}