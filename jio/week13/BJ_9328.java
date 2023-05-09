package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_9328 {
    static int H, W, T, documents;
    static char[][] grid;
    static Map<Character, List<Pair>> doors, entranceDoors, waits;
    static List<Pair> entrances;
    static class Pair{
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            solve();
        }
    }

    private static void solve() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        grid = new char[H][W];
        doors = new HashMap<>();
        entranceDoors = new HashMap<>();
        waits = new HashMap<>();
        for (Character i = 'A'; i <= 'Z'; i++) {
            doors.put(i, new ArrayList<>());
            entranceDoors.put(i, new ArrayList<>());
            waits.put(i, new ArrayList<>());
        }

        for (int i = 0; i < H; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                char doorType = chars[j];
                grid[i][j] = doorType;
                if (doorType >= 'A' && doorType <= 'Z') {
                    List<Pair> door = doors.get(doorType);
                    door.add(new Pair(i, j));
                }
            }
        }

        // 1. 입력으로 주어진 열쇠에 해당하는 문을 미리 열어줌
        char[] keys = br.readLine().toCharArray();
        if(keys[0] != '0'){ // 초기에 주어진 열쇠가 있는 경우
            for (int i = 0; i < keys.length; i++) { // 주어진 열쇠들에 해당하는 문을 열어줌
                char doorType = (char) (keys[i] - 32);
                openDoor(doorType);
            }
        }


        waits = new HashMap<>(); // 특정 문을 마주하여 대기 중인 값들을 저장할 map
        for (char i = 'A'; i <= 'Z'; i++) {
            waits.put(i, new ArrayList<>());
        }

        documents = 0;

        // 2. 가능한 출입구들을 찾는다.
        findEntrance(); // 출입구를 찾음

        // 3. 가능한 출입구들을 시작점으로 BFS로 탐색
        bfs();
        System.out.println(documents);
    }

    private static void findEntrance() { // 가장자리를 네모나게 돌며 출입구를 찾음, 길이거나 문서거나 열쇠인 경우 출입구가 될 수 있다.
        entrances = new ArrayList<>();   // 출입구를 저장할 리스트
        int x = 0;
        int y = 0;
        int d = 0;
        while(true){
            if (grid[x][y] == '.' || grid[x][y] == '$') { // 길 or 문서인 경우
                entrances.add(new Pair(x, y));
            }
            if (grid[x][y] >= 'a' && grid[x][y] <= 'z') { // 열쇠인 경우
                entrances.add(new Pair(x, y));
            }
            if (grid[x][y] >= 'A' && grid[x][y] <= 'Z') { // 문인 경우
                char doorType = grid[x][y];
                entranceDoors.get(doorType).add(new Pair(x, y)); // 출입구가 문인 경우는 따로 저장
            }
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (!inRange(nx, ny)) { // 격자에서 벗어날 경우 방향 전환
                d++;
                nx = x + dx[d];
                ny = y + dy[d];
            }
            if (nx == 0 && ny == 0) { // 다시 출발점으로 돌아온 경우
                return;
            }
            x = nx;
            y = ny;
        }

    }

    private static void bfs() {

        //1. 큐에 모든 출입구들을 넣어준다.
        Queue<Pair> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[H][W];

        for (int i = 0; i < entrances.size(); i++) {
            Pair pair = entrances.get(i);
            int x = pair.x;
            int y = pair.y;
            if (grid[x][y] == '$') { // 출입구에 문서가 있는 경우
                documents++;
            }
            if (grid[x][y] >= 'a' && grid[x][y] <= 'z') { // 출입구에 열쇠가 있는 경우 열쇠에 맞는 문을 열어준다.
                char doorType = (char) (grid[x][y] - 32);
                openDoor(doorType, que, visited, null);
            }
            que.offer(new Pair(x, y));
            visited[x][y] = true;
        }

        //2. 출입구 부터 시작해서 BFS 탐색
        while (!que.isEmpty()) {
            Pair cur = que.poll();
            int x = cur.x;
            int y = cur.y;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (!inRange(nx, ny) || grid[nx][ny] == '*' || visited[nx][ny]) { // 격자에서 벗어나거나 벽이거나 이미 방문한 경우
                    continue;
                }

                if (grid[nx][ny] >= 'A' && grid[nx][ny] <= 'Z') { // 문인 경우
                    char doorType = grid[nx][ny];
                    waits.get(doorType).add(new Pair(x, y)); // 문 앞에서 대기 중인 현재 위치를 저장
                    continue;
                }

                if (grid[nx][ny] == '$') { // 문서를 발견한 경우
                    documents++;
                }

                if (grid[nx][ny] >= 'a' && grid[nx][ny] <= 'z') { // 열쇠인 경우 해당 열쇠에 맞는 문을 열어줌
                    char doorType = (char) (grid[nx][ny] - 32);
                    openDoor(doorType, que, visited, waits);
                }

                visited[nx][ny] = true;
                que.offer(new Pair(nx, ny));
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    private static void openDoor(char doorType){ // 입력으로 주어진 열쇠에 해당하는 문을 열어줌
        List<Pair> door = doors.get(doorType);
        for (Pair pair : door) {
            grid[pair.x][pair.y] = '.';
        }
    }

    // BFS 도중 찾은 열쇠에 해당하는 문을 열어줌
    private static void openDoor(char doorType, Queue<Pair> que, boolean[][] visited, Map<Character, List<Pair>> waits){

        // 1. 찾은 열쇠에 해당하는 문을 열어 줌
        List<Pair> door = doors.get(doorType);
        for (Pair pair : door) {
            int x = pair.x;
            int y = pair.y;
            grid[x][y] = '.';
        }

        // 2. 출입구에 있던 문 또한 열어주고 그 위치를 큐에 넣어준다.(출입구에 있던 문에서도 탐색을 하기 위해)
        List<Pair> entranceDoor = entranceDoors.get(doorType);
        for (Pair pair : entranceDoor) {
            int x = pair.x;
            int y = pair.y;
            visited[x][y] = true;
            que.offer(new Pair(x, y));
        }

        // 3. 문 앞에서 기다리고 있던 값들을 큐에 넣어준다.(기다리던 위치에서 문을 연 후 다시 탐색을 하기 위해)
        if (waits != null) {
            List<Pair> wait = waits.get(doorType);
            for (Pair pair : wait) {
                int x = pair.x;
                int y = pair.y;
                que.offer(new Pair(x, y));
            }
        }
    }
}
