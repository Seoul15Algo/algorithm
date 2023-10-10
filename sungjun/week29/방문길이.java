package week29;

import java.util.*;

class 방문길이 {
    public int solution(String dirs) {
        int answer = 0;
        boolean[][][][] visited = new boolean[11][11][11][11];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        Map<Character, Integer> direction = new HashMap<>();

        int r = 5;
        int c = 5;

        direction.put('U', 0);
        direction.put('R', 1);
        direction.put('D', 2);
        direction.put('L', 3);

        char[] orders = dirs.toCharArray();

        for(char order : orders) {
            int nr = r+dr[direction.get(order)];
            int nc = c+dc[direction.get(order)];

            if(!check(nr, nc)) continue;
            if(visited[r][c][nr][nc]) {
                r = nr;
                c = nc;
                continue;
            }

            visited[r][c][nr][nc] = true;
            visited[nr][nc][r][c] = true;
            answer++;

            r = nr;
            c = nc;
        }

        return answer;
    }

    public boolean check(int r, int c) {
        return r >= 0 && r < 11 && c >= 0 && c < 11;
    }
}