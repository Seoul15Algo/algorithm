import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20061 {

    static final int MAX = 6;
    static final int GREEN = 0;
    static final int BLUE = 1;

    // 우, 상
    static final int[] DR = {0, 1};
    static final int[] DC = {1, 0};

    static int[][] greenBoard;
    static int[][] blueBoard;

    static int score;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        greenBoard = new int[MAX - 2][MAX];
        blueBoard = new int[MAX][MAX - 2];

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            put(type, row, col);
        }
        System.out.println(score);
        System.out.println(getRemainingTiles());
    }

    static void put(int type, int row, int col) {


        switch (type) {
            // 1x1
            case 1:
                drop(new int[]{row, 0}, new int[]{row, 0}, GREEN);
                drop(new int[]{0, col}, new int[]{0, col}, BLUE);
                break;
            // 1x2
            case 2:
                drop(new int[]{row, 0}, new int[]{row, 1}, GREEN);
                drop(new int[]{0, col}, new int[]{0, col + 1}, BLUE);
                break;
            // 2x1
            case 3:
                drop(new int[]{row, 0}, new int[]{row + 1, 0}, GREEN);
                drop(new int[]{0, col}, new int[]{1, col}, BLUE);
                break;
        }

        // 한 행/열이 꽉 찼는지 확인
        checkLine();
        // 희미한 구역 확인
        checkFaintArea();
    }

    static void drop(int[] start, int[] end, int color) {

        int[][] board = (color == GREEN ? greenBoard : blueBoard);
        int[] nStart = {start[0], start[1]};
        int[] nEnd = {end[0], end[1]};

        while (true) {
            int nx1 = nStart[0] + DR[color];
            int ny1 = nStart[1] + DC[color];
            int nx2 = nEnd[0] + DR[color];
            int ny2 = nEnd[1] + DC[color];
            // 끝에 도달
            if (isOutOfBound(nx1, ny1) || isOutOfBound(nx2, ny2)) {
                break;
            }
            // 다른 블럭과 충돌
            if (board[nx1][ny1] != 0 || board[nx2][ny2] != 0) {
                break;
            }
            nStart[0] = nx1;
            nStart[1] = ny1;
            nEnd[0] = nx2;
            nEnd[1] = ny2;
        }
        board[nStart[0]][nStart[1]] = 1;
        board[nEnd[0]][nEnd[1]] = 1;
    }

    static boolean isOutOfBound(int r, int c) {
        if (r >= MAX || c >= MAX) {
            return true;
        }
        return false;
    }

    static void checkLine(){

        int greenCount = 0;
        int greenIdx = -1;

        int blueCount = 0;
        int blueIdx = -1;

        for (int i = MAX - 1; i >= 2; i--) {

            int gCount = 0;
            int bCount = 0;
            for (int j = 0; j < 4; j++) {
                if (greenBoard[j][i] != 0) {
                    gCount++;
                }
                if (blueBoard[i][j] != 0) {
                    bCount++;
                }
            }
            // 한 줄이 꽉 채워진 경우
            if (gCount == 4){
                greenCount++;
                greenIdx = i;
                pop(greenIdx, GREEN);
            }

            if (bCount == 4){
                blueCount++;
                blueIdx = i;
                pop(blueIdx, BLUE);
            }
        }

        // 한 줄 이상이 채워져서 사라진 경우에만 -> 앞쪽의 블럭들을 (지운 줄의 개수)칸 만큼 옮긴다
        if (greenIdx != -1){
            pull(greenCount, greenIdx - 1, GREEN);
        }

        if (blueIdx != -1){
            pull(blueCount, blueIdx - 1, BLUE);
        }

        score = score + greenCount + blueCount;
    }

    static void checkFaintArea() {

        int greenCount = 0;
        int blueCount = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (greenBoard[j][i] != 0) {
                    greenCount++;
                    break;
                }
            }
        }
        // 맨 뒤부터 한 줄씩 지워나간다
        for (int i = 0; i < greenCount; i++) {
            pop((MAX - i) - 1, GREEN);
        }
        // 지운 줄의 앞쪽의 블럭들을 (지운 줄의 개수)칸 만큼 옮긴다
        pull(greenCount, (MAX - greenCount) - 1, GREEN);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                if (blueBoard[i][j] != 0) {
                    blueCount++;
                    break;
                }
            }
        }
        for (int i = 0; i < blueCount; i++) {
            pop((MAX - i) - 1, BLUE);
        }
        pull(blueCount, (MAX - blueCount) - 1, BLUE);
    }

    static void pop(int idx, int color){

        for (int i = 0; i < 4; i++) {
            if (color == GREEN){
                greenBoard[i][idx] = 0;
            }
            if (color == BLUE){
                blueBoard[idx][i] = 0;
            }
        }
    }

    static void pull(int count, int idx, int color) {

        for (int i = 0; i < 4; i++) {

            for (int j = idx; j >= 0; j--) {

                if (color == GREEN){
                    if (greenBoard[i][j] != 0){
                        greenBoard[i][j] = 0;
                        greenBoard[i + DR[color] * count][j + DC[color] * count] = 1;
                    }
                }

                if (color == BLUE){
                    if (blueBoard[j][i] != 0){
                        blueBoard[j][i] = 0;
                        blueBoard[j + DR[color] * count][i + DC[color] * count] = 1;
                    }
                }
            }
        }
    }

    static int getRemainingTiles() {

        int total = 0;

        for (int i = 0; i < MAX - 2; i++) {

            for (int j = 2; j < MAX; j++) {

                if (greenBoard[i][j] != 0) {
                    total++;
                }
                if (blueBoard[j][i] != 0) {
                    total++;
                }
            }
        }
        return total;
    }
}