import java.util.*;
import java.io.*;
public class Main_16946 {
    static int N, M;
    static int[][] arr, sector;
    static boolean[][] visit;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static List<Integer> list, sumVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        sector = new int[N][M];
        list = new ArrayList<>();
        sumVisit = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < M; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        int n = 1;
        list.add(0);
        visit = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 연결된 0 탐색
                // sector 배열에 해당 구간의 인덱스를 저장
                // 배열의 값이 같으면 연결된 구간
                // 리스트에 해당 구간의 크기를 저장
                if(arr[i][j] == 0 && !visit[i][j]) {
                    visit[i][j] = true;
                    sector[i][j] = n;
                    list.add(makeSector(i, j, n++));
                }
            }
        }

        // 벽에서 4방향을 탐색 후 만나는 구간의 크기를 더해줌
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] > 0) {
                    arr[i][j] = calcSum(i, j);
                }
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                sb.append(arr[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int calcSum(int r, int c) {
        int sum = 1;

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 이미 방문한 구간을 리스트에 저장하여 contains를 통해 중복 검사
            if(isRange(nr, nc) && !sumVisit.contains(sector[nr][nc])) {
                sum += list.get(sector[nr][nc]);
                sumVisit.add(sector[nr][nc]);
            }
        }
        // 리스트 초기화
        sumVisit.clear();
        return sum % 10;
    }

    // 플러드필
    // 구역의 크기 return
    static int makeSector(int r, int c, int n) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {r, c});
        int cnt = 1;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];

                if(!isRange(nr, nc) || visit[nr][nc] || arr[nr][nc] > 0) {
                    continue;
                }

                cnt++;
                visit[nr][nc] = true;
                sector[nr][nc] = n;
                q.offer(new int[] {nr, nc});
            }
        }
        return cnt;
    }

    static boolean isRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}