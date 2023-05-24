import java.util.*;
import java.io.*;

public class Main_15898 {
    static Map<Character, Integer> hm;
    static List<Material> list;
    static int N, answer;
    static final int SIZE = 4;
    static final int LAST_INDEX = SIZE - 1;
    static final int MAP_SIZE = SIZE + 1;
    static int[] arr;
    static boolean[] visit;
    static int[][] init_Integer_arr;
    static char[][] init_Character_arr;

    static class Material{
        int[][][] quality;
        char[][][] element;

        public Material(int[][][] quality, char[][][] element){
            this.quality = quality;
            this.element = element;
        }
    }

    // n개의 재료중에 3개를 고름 -> nP3
    // 재료를 가마에 위치시킴 -> 4가지 위치
    // 재료를 4방향으로 회전 -> 4가지 방향
    // 모든 경우를 고려해도 시간초과가 나지 않으므로 싹다 확인

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        answer = 0;
        arr = new int[3];
        visit = new boolean[N];
        list = new ArrayList<>();
        hm = new HashMap<>();
        hm.put('R', 7);
        hm.put('B', 5);
        hm.put('G', 3);
        hm.put('Y', 2);
        hm.put('W', 0);
        init_Integer_arr = new int[MAP_SIZE][MAP_SIZE];
        init_Character_arr = new char[MAP_SIZE][MAP_SIZE];
        for(int i = 0; i < MAP_SIZE; i++){
            for(int j = 0; j < MAP_SIZE; j++){
                init_Character_arr[i][j] = 'W';
            }
        }

        // 각각의 재료 클래스에 4방향으로 회전한 배열을 3차원으로 저장
        // 배열을 회전시키지 않고 배열을 회전된 형태로 초기화
        for(int t = 0; t < N; t++){
            int[][][] quality = new int[4][SIZE][SIZE];
            char[][][] element = new char[4][SIZE][SIZE];

            for(int i = 0; i < SIZE; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < SIZE; j++){
                    int now = Integer.parseInt(st.nextToken());
                    quality[0][i][j] = now;
                    quality[1][LAST_INDEX - j][i] = now;
                    quality[2][LAST_INDEX - i][LAST_INDEX - j] = now;
                    quality[3][j][LAST_INDEX - i] = now;
                }
            }

            for(int i = 0; i < SIZE; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < SIZE; j++){
                    char now = st.nextToken().charAt(0);
                    element[0][i][j] = now;
                    element[1][LAST_INDEX - j][i] = now;
                    element[2][LAST_INDEX - i][LAST_INDEX - j] = now;
                    element[3][j][LAST_INDEX - i] = now;
                }
            }

            list.add(new Material(quality, element));
        }
        perm(0);
        System.out.println(answer);
    }

    // 순열에 의해 선택된 재료를 이용해서 dfs
    // 하나의 재료에 대해서 회전(4), 시작위치(4) 총 16가지 case
    static void dfs(int depth, int[][] nowQuality, char[][] nowElement){
        if(depth == 3){
            int score = 0;
            for(int i = 0; i < MAP_SIZE; i++){
                for(int j = 0; j < MAP_SIZE; j++){
                    // 한칸의 품질을 계산 후 누적
                    // 해시맵을 이용해서 if문 제거
                    score += hm.get(nowElement[i][j]) * nowQuality[i][j];
                }
            }
            answer = Math.max(answer, score);
            return;
        }

        for(int k = 0; k < 4; k++){
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < 2; j++){
                    dfs(depth + 1, copyArr(nowQuality, list.get(arr[depth]).quality[k], i, j),
                                         copyArr(nowElement, list.get(arr[depth]).element[k], i, j));
                }
            }
        }

    }

    // 품질
    // 배열을 복사해서 기존배열에 새로운 재료를 넣은 배열을 리턴
    static int[][] copyArr(int[][] arr, int[][] quality, int r, int c){
        int[][] clone = new int[MAP_SIZE][MAP_SIZE];

        for(int i = 0; i < MAP_SIZE; i++){
            for(int j = 0; j < MAP_SIZE; j++){
                clone[i][j] = arr[i][j];
            }
        }

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                int tmp = clone[r + i][c + j] + quality[i][j];
                if(tmp < 0){
                    tmp = 0;
                }
                if(tmp > 9){
                    tmp = 9;
                }
                clone[r + i][c + j] = tmp;
            }
        }

        return clone;
    }

    // 원소
    // 배열을 복사해서 기존배열에 새로운 재료를 넣은 배열을 리턴
    static char[][] copyArr(char[][] arr, char[][] element, int r, int c){
        char[][] clone = new char[MAP_SIZE][MAP_SIZE];

        for(int i = 0; i < MAP_SIZE; i++){
            for(int j = 0; j < MAP_SIZE; j++){
                clone[i][j] = arr[i][j];
            }
        }

        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                if(element[i][j] == 'W'){
                    continue;
                }
                clone[r + i][c + j] = element[i][j];
            }
        }

        return clone;
    }

    // 순열을 이용해 3개의 재료 선택
    static void perm(int depth){
        if(depth == 3){
            dfs(0, init_Integer_arr, init_Character_arr);
            return;
        }

        for(int i = 0; i < N; i++){
            if(visit[i]){
                continue;
            }

            visit[i] = true;
            arr[depth] = i;
            perm(depth + 1);
            visit[i] = false;
        }
    }
}