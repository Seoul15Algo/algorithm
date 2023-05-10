package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q15898 {
    private static final String WHITE = "W";
    private static final String YELLOW = "Y";
    private static final String GREEN = "G";
    private static final String RED = "R";
    private static final String BLUE = "B";
    private static Map<String, Integer> qualities;

    static class Ingredient {
        private int efficacy;
        private String element;

        public Ingredient() {
        }

        public Ingredient(int efficacy, String element) {
            this.efficacy = efficacy;
            this.element = element;
        }

        public Ingredient(Ingredient ingredient) {
            this.efficacy = ingredient.efficacy;
            this.element = ingredient.element;
        }

        public void setEfficacy(int efficacy) {
            this.efficacy = efficacy;
        }

        public void setElement(String element) {
            this.element = element;
        }

        public void fusion(Ingredient ingredient) {
            fusionEfficacy(ingredient.efficacy);
            fusionElement(ingredient.element);
        }

        private void fusionEfficacy(int efficacy) {
            int resultEfficacy = this.efficacy + efficacy;
            if (resultEfficacy < 0) {
                this.efficacy = 0;
                return;
            }
            this.efficacy = Math.min(resultEfficacy, 9);
        }

        private void fusionElement(String element) {
            if (element.equals(WHITE)) {
                return;
            }
            this.element = element;
        }

        public int getQuality() {
            return qualities.get(this.element) * this.efficacy;
        }
    }

    private static BufferedReader br;
    private static Ingredient[][][] ingredients;
    private static int n;
    private static boolean[] visited;
    private static long maxTotal;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        ingredients = new Ingredient[n][4][4];
        visited = new boolean[n];
        qualities = new HashMap<>();
        qualities.put(WHITE, 0);
        qualities.put(RED, 7);
        qualities.put(BLUE, 5);
        qualities.put(GREEN, 3);
        qualities.put(YELLOW, 2);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    ingredients[i][j][k] = new Ingredient();
                }
            }
        }
        for (int i = 0; i < n; i++) {
            input(i);
        }

        Ingredient[][] kiln = new Ingredient[5][5];
        for (int i = 0; i < 5; i++) {
            Arrays.fill(kiln[i], new Ingredient(0, WHITE));
        }

        search(0, kiln);
        System.out.println(maxTotal);
    }

    public static void input(int index) throws IOException {
        for (int i = 0; i < 4; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                ingredients[index][i][j].setEfficacy(Integer.parseInt(row[j]));
            }
        }

        for (int i = 0; i < 4; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < 4; j++) {
                ingredients[index][i][j].setElement(row[j]);
            }
        }
    }

    public static void search(int count, Ingredient[][] kiln) {
        if (count == 3) {
            long total = 0;
            for (Ingredient[] k : kiln) {
                for (Ingredient ingredient : k) {
                    total += ingredient.getQuality();
                }
            }
            maxTotal = Math.max(maxTotal, total);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            for (int j = 0; j <= 3; j++) {
                for (int k = 0; k <= 1; k++) {
                    for (int l = 0; l <= 1; l++) {
                        search(count + 1, fusion(kiln, i, j, k, l));
                    }
                }
            }
            visited[i] = false;
        }
    }

    public static Ingredient[][] fusion(Ingredient[][] kiln, int index, int rotate, int row, int col) {
        Ingredient[][] copyKiln = new Ingredient[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                copyKiln[i][j] = new Ingredient(kiln[i][j]);
            }
        }

        if (rotate == 0) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyKiln[row + i][col + j].fusion(ingredients[index][i][j]);
                }
            }
        } else if (rotate == 1) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyKiln[row + j][col + (3 - i)].fusion(ingredients[index][i][j]);
                }
            }

        } else if (rotate == 2) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyKiln[row + (3 - i)][col + (3 - j)].fusion(ingredients[index][i][j]);
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    copyKiln[row + (3 - j)][col + i].fusion(ingredients[index][i][j]);
                }
            }
        }

        return copyKiln;
    }
}
