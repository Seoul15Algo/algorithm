import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16954 {

    static char[][] map;
    static int[][] visited;
    static List<Wall> walls;

    static final int[] DX = {-1, 0, 1, 0, 1, 1, -1, -1, 0};
    static final int[] DY = {0, -1, 0, 1, 1, -1, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[8][8];
        visited = new int[8][8];
        walls = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                if (input[j] == '#'){
                    walls.add(new Wall(i, j));
                }
                map[i][j] = input[j];
            }
        }
        Collections.reverse(walls);
        System.out.println(bfs(7, 0));
    }

    static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{x, y, 0});
        visited[x][y]++;

        int prevSecond = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            // 목표 지점에 도달한 경우
            if (cur[0] == 0 && cur[1] == 7){
                return 1;
            }

            // 해당 시간 대의 욱제가 이동할 수 있는 경우를 모두 체크했다면
            // 벽을 움직이고 다음 시간대로 넘어감
            if (prevSecond < cur[2]){
                moveWall();
                prevSecond = cur[2];
            }

            // 현재 벽과 포개어져 있는 상태인지 -> 그렇다면 더 이상 진행 불가
            if (isTrapped(cur)){
                continue;
            }

            for (int d = 0; d < 9; d++){
                int nx = cur[0] + DX[d];
                int ny = cur[1] + DY[d];

                // 방문할 수 없는 경우들
                // 욱제가 제자리에 있기로 선택한 경우, 같은 곳을 한 번 더 방문할 수 있기 때문에 visited 배열을 int 형으로 선언
                // 최대 2번까지만 방문함이 보장되기 때문에, 3번 이상 방문할 경우 그냥 넘어감
                if (isOutOfBound(nx, ny) || map[nx][ny] == '#' || visited[nx][ny] > 2) {
                    continue;
                }
                q.offer(new int[]{nx, ny, cur[2] + 1});
                visited[nx][ny]++;
            }
        }

        return 0;
    }


    static boolean isTrapped(int[] cur){

        if (map[cur[0]][cur[1]] == '#'){
            return true;
        }
        return false;
    }

    static void moveWall(){

        for (Wall wall : walls) {
            wall.move();
        }
    }

    static boolean isOutOfBound(int x, int y){
        if (x < 0 || x >= 8 || y < 0 || y >= 8) {
            return true;
        }
        return false;
    }

    static class Wall implements Comparable<Wall> {

        int x;
        int y;

        public Wall(int x, int y){
            this.x = x;
            this.y = y;
        }

        public void move(){
            map[x][y] = '.';
            // 범위를 벗어나면 이동할 수 없도록
            if (isOutOfBound(x + 1, y)){
                return;
            }
            x++;
            map[x][y] = '#';
        }

        @Override
        public int compareTo(Wall other) {

            return -1 * Integer.compare(this.x, other.x);
        }
    }
}