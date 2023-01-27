import java.io.*;

public class Main14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        String[] rcd = br.readLine().split(" ");
        int r = Integer.parseInt(rcd[0]);
        int c = Integer.parseInt(rcd[1]);
        int d = Integer.parseInt(rcd[2]);

        String[][] map = new String[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().split(" ");
        }

        int[][] direction = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 위 오 아 왼

        int answer = 0;
        while (true) {
            if (map[r][c].equals("0")) {
                map[r][c] = "-1";
                answer++;
            }

            boolean move = false;
            for (int i = 0; i < 4; i++) {
                d = Math.floorMod(d - 1, 4);
                int row = r + direction[d][0];
                int col = c + direction[d][1];

                if (row < 0 || row >= n || col < 0 || col >= m) {
                    continue;
                }

                if (map[row][col].equals("-1") || map[row][col].equals("1")) {
                    continue;
                }

                r = row;
                c = col;
                move = true;
                break;
            }

            if (!move) {
                int backD = Math.floorMod(d - 2, 4);
                int row = r + direction[backD][0];
                int col = c + direction[backD][1];

                if (row < 0 || row >= n || col < 0 || col >= m) {
                    break;
                }

                if (map[row][col].equals("1")) {
                    break;
                }

                r = row;
                c = col;
            }
        }

        System.out.println(answer);
    }
}
