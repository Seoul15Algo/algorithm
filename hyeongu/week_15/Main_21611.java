import java.io.*;
import java.util.*;

public class Main_21611 {
    static int N, M, INF;
    static int[][] arr;
    static int[] answer;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] first_dr = {0, 1, 0, -1};
    static int[] first_dc = {-1, 0, 1, 0};
    static List<Integer> list;

    /*  2차원 배열에 각 칸의 순서를 아래와 같이 저장하고
    *   24 23 22 21 20
    *    9  8  7  6 19
    *   10  1  0  5 18
    *   11  2  3  4 17
    *   12 13 14 15 16
    *
    *   리스트에 각 칸에 해당하는 구슬의 번호를 저장한다
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        INF = N * N;

        int[] shark = {N / 2, N / 2};
        arr = new int[N][N];
        list = new ArrayList<>();
        list.add(-1);
        answer = new int[4];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int index = 1;
        int size = 1;
        int tmp = 0;
        int dir = 0;

        while(index < INF){
            if(tmp == 2){
                size++;
                tmp = 0;

                if(size == N){
                    size--;
                }
            }

            for(int i = 0; i < size; i++){
                shark[0] += first_dr[dir];
                shark[1] += first_dc[dir];

                if(arr[shark[0]][shark[1]] > 0){
                    list.add(arr[shark[0]][shark[1]]);
                }
                arr[shark[0]][shark[1]] = index++;
            }
            tmp++;
            dir = (dir + 1) % 4;
        }


        shark[0] = N / 2;
        shark[1] = N / 2;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            int r = shark[0] + dr[d];
            int c = shark[1] + dc[d];
            if(list.size() > arr[r][c]){
                blizzard(r, c, d, 1, s);
            }

            while(explosion()){
            }
            change();

        }
        System.out.println(answer[1] + 2 * answer[2] + 3 * answer[3]);

    }

    // 폭발과 같은 형태로 연속된 구슬의 갯수를 구하고
    // 새로운 리스트에 저장
    static void change(){
        List<Integer> newList = new ArrayList<>();
        newList.add(-1);

        for(int i = 1; i < list.size() && newList.size() < INF - 1; i++){
            int now = list.get(i);
            if(now == 0){
                continue;
            }
            int k = 0;
            int cnt = 1;
            while(i + k + 1 < list.size()){
                if(list.get(i + k + 1) == 0){
                    k++;
                    continue;
                }
                if(now != list.get(i + k + 1)){
                    break;
                }
                cnt++;
                k++;
            }

            newList.add(cnt);
            newList.add(now);
            i += k;
        }

        list = newList;
    }

    // 리스트를 순회하면서 4개가 넘으면 폭발
    // 폭발할 경우 값을 0으로 변경
    // 순회도중 0이 나오면 다음칸으로 이동
    static boolean explosion(){
        boolean flag = false;

        for(int i = 1; i < list.size(); i++){
            int now = list.get(i);
            if(now == 0){
                continue;
            }

            int k = 0;
            int cnt = 1;
            while(i + k + 1 < list.size()){
                if(list.get(i + k + 1) == 0){
                    k++;
                    continue;
                }
                if(now != list.get(i + k + 1)){
                    break;
                }
                cnt++;
                k++;
            }

            if(cnt >= 4){
                flag = true;
                answer[now] += cnt;
                for(int j = 0; j <= k; j++){
                   list.set(i + j, 0);
                }
            }
            i += k;
        }
        return flag;
    }

    // 블리자드에 맞은 구슬을 0으로 변경
    static void blizzard(int r, int c, int dir, int depth, int s){
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        list.set(arr[r][c], 0);
        if(isRange(nr, nc) && list.size() > arr[nr][nc] && depth < s){
            blizzard(nr, nc, dir, depth + 1, s);
        }

    }

    static boolean isRange(int r, int c){
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
