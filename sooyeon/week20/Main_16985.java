import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16985 {
    static int[][][][] miro; //미로(몇번째, 회전수, 행,열)
    static boolean[][][][] visited; //미로 방문 여부
    static int[] seq; //판 순서
    static boolean[] seqVisited;
    static int[] rotate; //회전 수
    static Queue<int[]> q;
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        miro = new int[5][4][5][5];
        seq = new int[5];
        seqVisited = new boolean[5];
        rotate = new int[5];

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    miro[i][0][j][k] = Integer.parseInt(st.nextToken());
                }
            }

            //회전 결과들 저장해둔다.
            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    for (int l = 0; l < 5; l++) {
                        miro[i][j][k][l] = miro[i][j - 1][4 - l][k];
                    }
                }
            }
        }

        perm(0);

        if(result == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(result);
        }

    }

    private static void perm(int count) { //1. 순서 정하기
        if(count == 5) {
            perm2(0);
            return;
        }
        for (int i = 0; i < 5; i++) {
            if(seqVisited[i]) continue;
            seqVisited[i] = true;
            seq[count] = i;
            perm(count+1);
            seq[count] = 0;
            seqVisited[i] = false;
        }
    }

    private static void perm2(int count) { //2. 회전 수 정하기
        if(count == 5) {
            startpoint();
            return;
        }
        for (int i = 0; i < 4; i++) {
            rotate[count] = i;
            perm2(count+1);
            rotate[count] = 0;
        }
        
    }

    private static void startpoint() { //3. 시작점 정하기
       
        if(miro[seq[0]][rotate[0]][0][0] == 1) {

            if(miro[seq[4]][rotate[4]][4][4] == 1) { //출구에 길이 있으면 bfs
                q = new LinkedList<>();
                visited = new boolean[5][4][5][5];
                visited[seq[0]][rotate[0]][0][0] = true;
                q.offer(new int[]{seq[0],rotate[0],0,0,0,0}); //miro 정보4가지 , count, depth(몇층인지)
                bfs(0);
            }

        }
    }

    private static void bfs(int end) {

        while (!q.isEmpty()) {
            int cur[] = q.poll();

            // 종료조건 : 시작 지점과 반대끝 지점
            if(end == 0 && cur[0] == seq[4] && cur[2] == 4 && cur[3] == 4) {
                result = Math.min(result, cur[4]);
                return;
            }

            for (int d = 0; d < 4; d++) { //같은층 4방 검색
                int nr = cur[2] + dr[d];
                int nc = cur[3] + dc[d];
                if(!check(nr,nc)) continue;
                if(visited[cur[0]][cur[1]][nr][nc]) continue; // 방문했던거 거르고
                if(miro[cur[0]][cur[1]][nr][nc] == 1) {
                    visited[cur[0]][cur[1]][nr][nc] = true;
                    q.offer(new int[]{cur[0],cur[1],nr,nc,cur[4]+1,cur[5]});
                }

            }
            
            //맨밑층이 아니면 밑층 검사
            if(cur[5]!= 4) {
                if(!visited[seq[cur[5]+1]][rotate[cur[5]+1]][cur[2]][cur[3]]){
                    if(miro[seq[cur[5]+1]][rotate[cur[5]+1]][cur[2]][cur[3]] == 1) {
                        visited[seq[cur[5]+1]][rotate[cur[5]+1]][cur[2]][cur[3]] = true;
                        q.offer(new int[]{seq[cur[5]+1],rotate[cur[5]+1],cur[2],cur[3],cur[4]+1,cur[5]+1});
                    }
                }

            }

            //맨윗층이 아니면 윗층 검사
            if(cur[5]!= 0) {
                if(!visited[seq[cur[5]-1]][rotate[cur[5]-1]][cur[2]][cur[3]]){
                    if(miro[seq[cur[5]-1]][rotate[cur[5]-1]][cur[2]][cur[3]] == 1) {
                        visited[seq[cur[5]-1]][rotate[cur[5]-1]][cur[2]][cur[3]] = true;
                        q.offer(new int[]{seq[cur[5]-1],rotate[cur[5]-1],cur[2],cur[3],cur[4]+1,cur[5]-1});
                    }
                }

            }

        }


    }
    private static boolean check(int nr, int nc){
        return nr>=0 && nc>=0 && nr<5 && nc<5;
    }
}
