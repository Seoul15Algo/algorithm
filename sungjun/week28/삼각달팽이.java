package week28;

class 삼각달팽이 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;

    public int[] solution(int n) {
        int len = n*(n+1)/2;
        int[] answer = new int[len];
        map = new int[n][n];

        solve(n, len);

        int idx = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(map[i][j] == 0) break;
                answer[idx++] = map[i][j];
            }
        }

        return answer;
    }

    public void solve(int n, int len) {
        int num = 1;
        int r = 0;
        int c = 0;
        int type = 0;

        while(num <= len) {
            map[r][c] = num++;

            if(type == 0) {
                if(r+1 < n && map[r+1][c] == 0) {
                    r++;
                    continue;
                }

                if(c+1 < n && map[r][c+1] == 0) {
                    c++;
                    type = 1;
                    continue;
                }
            }

            if(type == 1) {
                if(c+1 < n && map[r][c+1] == 0) {
                    c++;
                    continue;
                }

                if(map[r-1][c-1] == 0) {
                    r--;
                    c--;
                    type = 2;
                    continue;
                }
            }

            if(type == 2) {
                if(map[r-1][c-1] == 0) {
                    r--;
                    c--;
                    continue;
                }

                if(r+1 < n && map[r+1][c] == 0) {
                    r++;
                    type = 0;
                    continue;
                }
            }
        }
    }
}