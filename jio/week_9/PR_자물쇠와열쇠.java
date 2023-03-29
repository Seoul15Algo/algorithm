public class PR_자물쇠와열쇠 {
    int[][] key;
    int[][] lock;
    int n;
    int m;

    // 상하좌우 + 대각선 8방으로 이동 가능하므로 [3*n][3*n] 사이즈의 배열 생성 후 (0,0)~(2n-1,2n-1)까지 순회하며
    // key를 생성한 배열에 그려보고 적합한지 확인하고 부적합이라면 이동하는 과정을 반복
    public boolean solution(int[][] keyI, int[][] lockI) {
        key = keyI;
        lock = lockI;
        n = lockI.length;
        m = keyI.length;

        boolean answer = false;
        int round = 0; //회전 수
        a: while(round < 4){
            round++;
            int[][] newLock = new int[3*n][3*n];
            for (int i = n; i < 2*n; i++) {  // newLock의 (n,n)~(2n, 2n) 위치에 기존 lock을 복사(==정 가운데에 lock을 그림)
                for (int j = n; j < 2*n; j++) {
                    newLock[i][j] = lock[i-n][j-n];
                }
            }

            for (int i = 0; i < 2 * n; i++) { // 가능한 열쇠의 이동 경우를 고려하여 정답이 되는지 확인
                for (int j = 0; j < 2 * n; j++) {
                    if(drawKey(i, j, newLock, m)){
                        answer = true;
                        break a;
                    }
                }
            }
            key = rotateRight(); //배열 시계방향으로 회전
        }
        return answer;
    }

    private int[][] rotateRight() {
        int[][] rotate = new int[m][m];
        int row = m;
        int col = m;
        for (int i = 0; i < row; i++) { //rotate 배열의 행: "기존 배열의 열" & rotate 배열의 열: "기존 행의 개수 - 1 - 현재 행의 index"
            for (int j = 0; j < col; j++) {
                rotate[j][row-1-i] = key[i][j];
            }
        }
        return rotate;
    }

    private boolean drawKey(int startX, int startY, int[][] newLock, int m) { //인자로 입력받은 위치 부터 시작하여 key를 newLock에 그려준다.
        for (int i = 0; i < m; i++) { //key를 newLock 배열에 그려줌
            for (int j = 0; j < m; j++) {
                newLock[startX+i][startY+j] += key[i][j];
            }
        }

        if(isPossible(newLock)){ //정답이 되는지 확인
            return true;
        }

        for (int i = 0; i < m; i++) { //그렸던 key를 newLock 배열에서 다시 지워줌
            for (int j = 0; j < m; j++) {
                newLock[startX+i][startY+j] -= key[i][j];
            }
        }
        return false;
    }

    private boolean isPossible(int[][] newLock) {
        for (int i = n; i < 2*n; i++) {  //기존 lock 배열이 위치한 (n,n)부터 (2n-1, 2n-1)까지 확인
            for (int j = n; j < 2 * n; j++) {
                if(newLock[i][j] == 0 || newLock[i][j] == 2) {  //구멍이 존재하거나 열쇠 돌기와 자물쇠 돌기가 충돌한 경우
                    return false;
                }
            }
        }
        return true;
    }
}

