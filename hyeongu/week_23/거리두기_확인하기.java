import java.util.*;
public class 거리두기_확인하기 {

    static char[][] tmp;
    static int size = 5;
    static boolean[][] check;
    static int dir[][] = {{-1,0},{1,0},{0,-1},{0,1}};

    static int bfs(int row,int col){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row,col,0});
        while(!q.isEmpty()){
            int[] t = q.poll();
            int r = t[0];
            int c = t[1];
            int d = t[2];
            if(d > 2)
                continue;
            check[r][c] = true;

            if(tmp[r][c] == 'P' && d > 0 && d<=2){
                return 0;
            }

            if(tmp[r][c] == 'X')
                continue;

            for(int[] di : dir){
                int nr = r+di[0];
                int nc = c+di[1];
                int nd = d+1;
                if(nr>= 0 && nr<5 && nc>=0 && nc<5 && !check[nr][nc] ){
                    q.offer(new int[]{nr,nc,nd});
                }
            }
        }
        return 1;

    }
    public int[] solution(String[][] places) {
        int[] answer = new int[size];
        List<Integer> ans = new ArrayList<>();

        for(int r = 0;r<5;r++){
            tmp = new char[5][5];
            for(int c = 0;c<5;c++ ){
                for(int i=0;i<5;i++){
                    tmp[c][i] = places[r][c].charAt(i);
                }
            }
            boolean flag = false;
            check = new boolean[5][5];
            for(int i = 0;i<5;i++){
                if(flag)
                    break;
                for(int j = 0;j<5;j++){
                    if(tmp[i][j] == 'P'){
                        if(bfs(i,j) == 0){
                            flag = true;
                            break;
                        }
                    }
                }
            }
            if(flag)
                ans.add(0);
            else
                ans.add(1);

        }

        for(int i = 0; i<size; i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}
