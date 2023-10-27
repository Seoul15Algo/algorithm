import java.util.*;
class 프렌즈4블록 {
    public int solution(int m, int n, String[] board) {
        boolean[][] check = new boolean[n][m];
        int answer = 0;
        boolean isBreak = true;
        ArrayList<Character>[] arr = new ArrayList[n];

        for(int i = 0; i < n; i++) {
            arr[i] = new ArrayList<Character>();
        }
        for(int i = m; i > 0; i--) {
            for(int j = 0; j < n; j++) {
                arr[j].add(board[i - 1].charAt(j));
            }
        }

        // 아래에서부터 시작하는 ArrayList를 이용해 remove하면 자동으로 내려오게 구현
        while(isBreak){
            //삭제하는 블럭이 있을경우 true, 없는경우 while문 종료
            isBreak = false;
            for(int i = 0; i < n - 1; i++) {
                for(int j = 0; j < arr[i].size() - 1; j++) {
                    // 현재 칸 만큼 옆의 칸이 높이가 올라오지 않은 경우
                    if(arr[i + 1].size() <= j + 1) {
                        break;
                    }

                    // 사각형 모양으로 다 같으면 기록
                    if(arr[i].get(j) == arr[i].get(j + 1)) {
                        if(arr[i + 1].get(j) == arr[i + 1].get(j + 1)&&arr[i].get(j) == arr[i + 1].get(j)) {
                            check[i][j] = true;
                            check[i][j + 1] = true;
                            check[i + 1][j] = true;
                            check[i + 1][j + 1] = true;
                            isBreak = true;
                        }
                    }
                }
            }

            // 기록된 블록을 위에서부터 제거
            for(int i = 0; i < n; i++) {
                for(int j = arr[i].size() - 1; j >= 0; j--) {
                    if(check[i][j]) {
                        arr[i].remove(j);
                        answer++;
                        check[i][j] = false;
                    }
                }
            }
        }
        return answer;
    }
}