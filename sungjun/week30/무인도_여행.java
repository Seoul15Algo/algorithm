package week30;

import java.util.*;

class 무인도_여행 {
    static ArrayList<Integer> answer;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public ArrayList<Integer> solution(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        answer = new ArrayList<>();

        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(visited[i][j]) continue;
                if(maps[i].charAt(j) == 'X') continue;
                bfs(i, j, maps);
            }
        }

        Collections.sort(answer);
        if(answer.size() == 0) answer.add(-1);

        return answer;
    }

    public void bfs(int r, int c, String[] maps) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;
        int food = maps[r].charAt(c)-'0';

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            for(int i = 0; i < 4; i++) {
                int nr = cur[0]+dr[i];
                int nc = cur[1]+dc[i];

                if(!check(nr, nc)) continue;
                if(visited[nr][nc]) continue;
                if(maps[nr].charAt(nc) == 'X') continue;

                q.add(new int[] {nr, nc});
                visited[nr][nc] = true;
                food += maps[nr].charAt(nc)-'0';
            }
        }

        answer.add(food);
    }

    public boolean check(int r, int c) {
        return r >= 0 && r < visited.length && c >= 0 && c < visited[0].length;
    }
}