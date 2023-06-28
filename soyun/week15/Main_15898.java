package com.ssafy.baekjoon.random;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15898 {

    // 하, 우, 대각선 방향
    static final int[] px = {0, 1, 0, 1};
    static final int[] py = {0, 0, 1, 1};

    static int N;
    static Material[][][][] materials;
    static int max;

    static Material[][] kiln;
    static int[] pickedMaterial, pickedPosition, pickedDirection;
    static boolean[] alreadyPickedMaterial;
    

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        materials = new Material[N][4][4][4];
        kiln = new Material[5][5];
        pickedMaterial = new int[3];
        pickedPosition = new int[3];
        pickedDirection = new int[3];
        alreadyPickedMaterial = new boolean[N];
        max = 0;

        // 재료 배열 초기화
        for (int k = 0; k < N; k++){
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    materials[k][0][i][j] = new Material();
                    materials[k][0][i][j].setEffect(Integer.parseInt(st.nextToken()));
                }
            }
            for (int i = 0; i < 4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    materials[k][0][i][j].setElement(st.nextToken().charAt(0));
                }
            }

            for (int i = 1; i < 4; i++) {
                materials[k][i] = rotate(materials[k][0], i);
            }
        }

        pickMaterial(0);

        System.out.println(max);
    }

    // 재료 회전시키기
    static Material[][] rotate(Material[][] material, int times){

        Material[][] origin = new Material[4][4];
        // rotated 배열 초기화
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++) {
                origin[i][j] = new Material(material[i][j].effect, material[i][j].element);
            }
        }

        for (int k = 0; k < times; k++) {
            Material[][] rotated = new Material[4][4];
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    rotated[j][3 - i] = new Material(origin[i][j].effect, origin[i][j].element);
                }
            }
            origin = rotated;
        }
        return origin;
    }

    // 재료를 선택하는 DFS
    static void pickMaterial(int cnt){
        if (cnt == 3){
            pickPosition(0);
            return;
        }

        // 순서의 영향을 받기 때문에 순열 이용
        for (int i = 0; i < N; i++) {
            if (alreadyPickedMaterial[i]){
                continue;
            }
            alreadyPickedMaterial[i] = true;
            pickedMaterial[cnt] = i;
            pickMaterial(cnt + 1);
            alreadyPickedMaterial[i] = false;
        }
    }

    // 재료를 놓는 위치를 선택하는 DFS
    static void pickPosition(int cnt){
        if (cnt == 3){
            pickDirection(0);
            return;
        }

        for (int p = 0; p < 4; p++) {
            pickedPosition[cnt] = p;
            pickPosition(cnt + 1);
        }
    }

    // 재료를 놓는 방향을 선택하는 DFS
    static void pickDirection(int cnt){

        // 결정완료, 폭탄의 품질을 계싼한다
        if (cnt == 3){

            // 가마 초기화
            initKiln();
            for (int i = 0; i < 3; i++) {
                // 재료를 가마의 해당하는 위치에 놓는다
                put(materials[pickedMaterial[i]][pickedDirection[i]], pickedPosition[i]);
            }

            int result = getQuality();
            max = Math.max(max, result);

            return;
        }

        for (int d = 0; d < 4; d++) {
            pickedDirection[cnt] = d;
            pickDirection(cnt + 1);
        }
    }

    // 가마 초기화
    static void initKiln(){

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                kiln[i][j] = new Material(0, 'W');
            }
        }
    }

    // 재료 놓기
    static void put(Material[][] material, int pos){
        int x = px[pos];
        int y = py[pos];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 효능을 더한다 -> 가능한 범위를 초과하면 값을 다듬어줌
                kiln[i + x][j + y].effect = trim(kiln[i + x][j + y].effect + material[i][j].effect);
                // 흰색 원소가 아니라면 색이 바뀜
                if (material[i][j].element != 'W') {
                    kiln[i + x][j + y].element = material[i][j].element;
                }
            }
        }
    }

    // 효능 값 다듬기
    static int trim(int effect){
        if (effect < 0){
            return 0;
        }
        if (effect > 9) {
            return 9;
        }
        return effect;
    }

    // 품질을 계산한다
    private static int getQuality() {

        int result = 0;
        int coefficient = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {

                switch(kiln[i][j].element){
                    case 'R':
                        coefficient = 7;
                        break;
                    case 'B':
                        coefficient = 5;
                        break;
                    case 'G':
                        coefficient = 3;
                        break;
                    case 'Y':
                        coefficient = 2;
                        break;
                    case 'W':
                        coefficient = 0;
                        break;
                }

                result = result + coefficient * kiln[i][j].effect;
            }
        }
        return result;
    }

    static class Material{

        int effect;
        char element;

        public Material() {
        }

        public Material(int effect, char element) {
            this.effect = effect;
            this.element = element;
        }

        public void setEffect(int effect) {
            this.effect = effect;
        }

        public void setElement(char element) {
            this.element = element;
        }
    }
}
