import java.util.*;

class Solution {
    static int answer = 0;
    static int[][] pan;
    static int[][] pancopy;
    static int[][] visit;
    public int solution(int m, int n, String[] board) {

        //R-1, M-2, A-3, F-4, N-5, T-6, J-7, C-8
        pan = new int[m][n];

        for(int i = 0; i < m; i++) {
            String str = board[i];
            for(int j = 0; j < n; j++) {
                char c = str.charAt(j);
                pan[i][j] = c-64;
            }
        }

        boolean isChange = true;
        while(isChange) {
            visit = new int[m][n];
            isChange = false;

            for(int i = 0; i < m-1; i++) {
                for(int j = 0; j < n-1; j++) {
                    //나기준 오른쪽, 대각선오른쪽아래, 밑이 같은거라면 bfs 탐색
                    if(pan[i][j]!= 0 && pan[i][j] == pan[i][j+1] && pan[i][j] == pan[i+1][j] && pan[i][j] == pan[i+1][j+1]) {


                        //visit바꿔줌
                        visit[i][j] += 1;
                        visit[i][j+1] += 1;
                        visit[i+1][j] += 1;
                        visit[i+1][j+1] += 1;

                        isChange = true;
                    }
                }
            }

            //visit 1 이상인 것들 pan에 0 넣어주고 사라진 갯수 카운트해서 answer에 반영
            int count = 0;
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(visit[i][j] > 0) {
                        count++;
                        pan[i][j] = 0;
                    }
                }
            }
            answer += count;

            //내리기
            pancopy = new int[m][n];
            for(int i = 0; i < n; i++) {
                int row = m-1;
                for(int j = m-1; j >= 0; j--) {
                    if(pan[j][i]!=0) {
                        pancopy[row--][i] = pan[j][i];
                    }
                }
            }
            //pancopy를 pan으로 옮김
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    pan[i][j] = pancopy[i][j];
                }
            }
        }




        return answer;
    }

}