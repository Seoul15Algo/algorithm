package week32;

import java.util.*;

class Q64064 {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 1;
        boolean[] checked = new boolean[user_id.length];
        int[] possible = new int[banned_id.length];

        for(int l = 0; l < banned_id.length; l++) {
            int tmp = 0;

            for(int i = 0; i < user_id.length; i++) {
                if(user_id[i].length() != banned_id[l].length()) continue;
                if(checked[i]) continue;

                StringBuilder sb = new StringBuilder(banned_id[l]);
                boolean flag = true;

                for(int j = 0; j < user_id[i].length(); j++) {
                    if(sb.charAt(j) == '*') {
                        sb.setCharAt(j, user_id[i].charAt(j));
                        continue;
                    }
                    if(sb.charAt(j) == user_id[i].charAt(j)) continue;
                    flag = false;
                    break;
                }

                if(flag) {
                    checked[i] = true;
                    tmp++;
                }
            }

            possible[l] = tmp;
        }

        System.out.println(Arrays.toString(possible));

        for(int i = 0; i < possible.length; i++) {
            answer *= possible[i] == 0 ? 1 : possible[i];
        }

        return answer;
    }
}