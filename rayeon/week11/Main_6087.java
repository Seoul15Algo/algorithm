package Seoul15Algo.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BJ 6087 레이저 통신
public class Main_6087 {
    static int W,H;
    static char[][] map;
    static int[][][] mirrorCount;
    static int[][] cPositions;
    static int result;
    static int[][] directions = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    static class Node {
        int r;
        int c;
        int direction;
        int mirrorCount;

        public Node(int r, int c, int direction, int mirrorCount) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.mirrorCount = mirrorCount;
        }
    }

    public static boolean check(int r, int c) {
        if (r < 0 || r >= H || c < 0 || c >= W)
            return false;
        return true;
    }

    public static void bfs() {
        Queue<Node> nodes = new LinkedList<Node>();
        for (int i = 0; i < 4; i++) {
            nodes.add(new Node(cPositions[0][0], cPositions[0][1], i, 0));
        }

        while (!nodes.isEmpty()) {
            Node node = nodes.poll();

            if (node.r == cPositions[1][0] && node.c == cPositions[1][1]) {
                result = Math.min(node.mirrorCount, result);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                if (i == (node.direction + 2) % 4)
                    continue;

                int nr = node.r + directions[i][0];
                int nc = node.c + directions[i][1];

                if (!check(nr, nc) || map[nr][nc] == '*')
                    continue;

                int cnt = (i == node.direction ? node.mirrorCount : node.mirrorCount + 1);

                if (cnt >= result)
                    continue;

                if (mirrorCount[nr][nc][i] > 0 && mirrorCount[nr][nc][i] <= cnt)
                    continue;

                mirrorCount[nr][nc][i] = cnt;
                nodes.add(new Node(nr, nc, i, cnt));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        result = Integer.MAX_VALUE;
        mirrorCount = new int[H][W][4];
        cPositions = new int[2][2];

        int cNumber = 0;
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < W; j++) {
                if (map[i][j] == 'C') {
                    cPositions[cNumber][0]  = i;
                    cPositions[cNumber++][1] = j;
                }
            }
        }

        bfs();
        System.out.println(result);
        br.close();
    }
}
