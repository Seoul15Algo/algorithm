package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15898 {
    static int N, maxQuality;
    static int[][] numberGrid = new int[5][5];
    static String[][] colorGrid = new String[5][5];
    static List<int[][]> numbers;
    static List<String[][]> colors;

    static int[] ingredients;
    static boolean[] visited;

    static int[] dx = {0, 0, 1, 1};
    static int[] dy = {0, 1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maxQuality = Integer.MIN_VALUE;

        for (int i = 0; i < 5; i++) { // String 배열은 null로 초기화 되므로 colorGrid를 흰색으로 초기화
            for (int j = 0; j < 5; j++) {
                colorGrid[i][j] = "W";
            }
        }

        numbers = new ArrayList<>(); // 재료들의 효능(숫자 값) 정보를 담는 리스트
        colors = new ArrayList<>();  // 재료들의 원소(색깔) 정보를 담는 리스트

        for (int t = 0; t < N; t++) {
            int[][] number = new int[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    number[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            numbers.add(number);

            String[][] color = new String[4][4];
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    color[i][j] = st.nextToken();
                }
            }
            colors.add(color);
        }

        ingredients = new int[N]; // 선택한 재료들
        visited = new boolean[N];
        perm(0);

        System.out.println(maxQuality);
    }

    private static void perm(int cnt) {  // 순열을 통해 재료를 순서를 고려하여 선택
        if (cnt == 3) {  // 재료를 3개 골라준 경우
            simulation(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            ingredients[cnt] = i;

            perm(cnt + 1);

            ingredients[cnt] = 0;
            visited[i] = false;
        }
    }

    private static void simulation(int cnt) { // 선택한 재료들을 가마에 집어 넣었을 때 품질 계산

        if (cnt == 3) { // 재료를 세개 다 고르고 가마에 넣은 경우
            maxQuality = Integer.max(maxQuality, calQuality());
            return;
        }

        int ingredientIdx = ingredients[cnt]; // 선택한 재료 번호

        for (int d = 0; d < 4; d++) { // 재료는 가장 왼쪽 위에 칸이 (0,0), (0,1), (1,0), (1,1)인 4가지 경우에만 넣을 수 있다.
            int x = dx[d];
            int y = dy[d];

            for (int i = 0; i < 4; i++) { // 시계 방향으로 네 번 돌려 준다.

                String[][] originColorGrid = new String[5][5]; // 현재 원소(색)의 상태를 저장
                for (int j = 0; j < 5; j++) {
                    originColorGrid[j] = Arrays.copyOf(colorGrid[j], colorGrid[j].length);
                }

                int[][] originNumberGrid = new int[5][5]; // 현재 효능의 상태를 저장
                for (int j = 0; j < 5; j++) {
                    originNumberGrid[j] = Arrays.copyOf(numberGrid[j], numberGrid.length);
                }

                rotateRightNumber(ingredientIdx); // 1. 재료의 효능을 시계 방향으로 회전

                rotateRightColor(ingredientIdx); // 2. 재료의 원소를 시계 방향으로 회전

                putIngredient(x, y, ingredientIdx); // 3. 재료를 가마에 넣어줌

                simulation(cnt + 1);

                takeOutIngredient(originNumberGrid, originColorGrid); // 4. 재귀가 끝난 뒤 넣었던 재료를 다시 빼줌(원상 복귀)
            }

        }
    }

    private static int calQuality() { // 재료를 다 넣은 후 격자의 상태를 보고 품질이 얼마인지 계산
        int quality = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                switch(colorGrid[i][j]) {
                    case "R" :
                        quality += 7 * numberGrid[i][j];
                        break;
                    case "B" :
                        quality += 5 * numberGrid[i][j];
                        break;
                    case "G" :
                        quality += 3 * numberGrid[i][j];
                        break;
                    case "Y" :
                        quality += 2 * numberGrid[i][j];
                        break;
                }
            }
        }

        return quality;
    }

    private static void putIngredient(int x, int y, int ingredientIdx) {

        // 1. 가마의 효능 갱신
        int[][] number = numbers.get(ingredientIdx);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                numberGrid[x + i][y + j] += number[i][j];

                if (numberGrid[x + i][y + j] < 0) { // 효능을 더한 값이 음수일 경우
                    numberGrid[x + i][y + j] = 0;
                }

                if (numberGrid[x + i][y + j] > 9) { // 효능을 더한 값이 9 초과일 경우
                    numberGrid[x + i][y + j] = 9;
                }
            }
        }

        // 2. 가마의 원소(색) 갱신
        String[][] color = colors.get(ingredientIdx);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!color[i][j].equals("W")) { // 재료의 원소가 흰색이 아닌 경우 색 변경
                    colorGrid[x + i][y + j] = color[i][j];
                }
            }
        }
    }

    private static void takeOutIngredient(int[][] originNumberGrid, String[][] originColorGrid) {
        // 1. 효능 정보 이전으로 복귀
        numberGrid = originNumberGrid;

        // 2. 원소 정보(색깔) 이전으로 복귀
        colorGrid = originColorGrid;
    }

    private static void rotateRightNumber(int ingredientIdx) {
        int[][] rotate = new int[4][4];
        int[][] ingredient = numbers.get(ingredientIdx);

        // 1. 재료를 시계 방향으로 회전한 결과를 rotate 배열에 저장
        int row = 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rotate[j][row - 1 - i] = ingredient[i][j];
            }
        }

        // 2. rotate 배열로 기존 재료 배열을 갱신
        numbers.set(ingredientIdx, rotate);
    }

    private static void rotateRightColor(int ingredientIdx) {
        String[][] ingredient = colors.get(ingredientIdx);
        String[][] rotate = new String[4][4];

        // 1. 재료를 시계 방향으로 회전한 결과를 rotate 배열에 저장
        int row = 4;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                rotate[j][row - 1 - i] = ingredient[i][j];
            }
        }

        // 2. rotate 배열로 기존 재료 배열을 갱신
        colors.set(ingredientIdx, rotate);
    }
}