import java.util.ArrayDeque;
import java.util.Queue;

public class Solution_거리두기_확인하기 {

    private char[][][] rooms;

    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, -1, 0, 1};

    public int[] solution(String[][] places) {

        makeRoom(places);

        int[] result = new int[5];

        // 각 대기실마다
        for (int i = 0; i < 5; i++) {
            if (isValid(i)) {
                result[i] = 1;
            }
        }
        return result;
    }

    // String을 char 배열로 옮기기
    public void makeRoom(String[][] places) {

        rooms = new char[5][5][5];

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < 5; i++) {
                rooms[k][i] = places[k][i].toCharArray();
            }
        }
    }

    // 해당 대기실의 인원이 모두 거리두기를 준수했는지?
    public boolean isValid(int id) {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                // 응시자가 있는 칸만 거리두기 준수 여부 확인
                if (rooms[id][i][j] != 'P') {
                    continue;
                }
                // 거리두기를 준수하지 못했다면 early return
                if (isFailed(id, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    // 거리두기가 실패했는지 체크
    public boolean isFailed(int id, int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visited = new int[5][5];
        q.offer(new int[]{x, y, 0, 'P'});
        visited[x][y]++;

        while(!q.isEmpty()) {
            int[] cur = q.poll();

            // 맨해튼 거리가 2를 넘는 애들은 확인할 필요가 없음
            if (cur[2] + 1 > 2) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                if (isOutOfBounds(nx, ny)) {
                    continue;
                }
                // 응시자 기준 대각선 위치라면 -> 두 번까지 방문 가능
                if (visited[nx][ny] > (isDiagonal(x, y, nx, ny) ? 1 : 0)) {
                    continue;
                }

                // 사람이 있고, 칸막이로 막혀있지 않다면
                if (rooms[id][nx][ny] == 'P' && cur[3] != 'X'){
                    return true;
                }

                q.offer(new int[]{nx, ny, cur[2] + 1, rooms[id][nx][ny]});
                visited[nx][ny]++;
            }
        }
        return false;
    }

    public boolean isDiagonal(int x, int y, int nx, int ny) {
        if (Math.abs(x - nx) == 1 && Math.abs(y - ny) == 1) {
            return true;
        }
        return false;
    }

    public boolean isOutOfBounds(int x, int y) {
        if (x < 0 || x >= 5 || y < 0 || y >= 5) {
            return true;
        }
        return false;
    }
}
