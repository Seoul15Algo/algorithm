package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q2629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int weightSize = Integer.parseInt(br.readLine());
        List<Integer> weights = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < weightSize; i++) {
            weights.add(Integer.parseInt(st.nextToken()));
        }

        int marbleSize = Integer.parseInt(br.readLine());
        List<Integer> marbles = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < marbleSize; i++) {
            marbles.add(Integer.parseInt(st.nextToken()));
        }

        int[] check = new int[40001];
        int limit = 0;
        for (int i = 0; i < weightSize; i++) {
            int weight = weights.get(i);
            check[weight] += i + 1;
            limit = Math.max(limit, weight);
            int max = 0;
            for (int j = 1; j <= limit; j++) {
                if (check[j] != 0 && check[j] != i + 1) {
                    check[Math.abs(j - weight)] += i + 1;
                    check[j + weight] += i + 1;
                    max = Math.max(max, j + weight);
                }
            }
            limit = Math.max(limit, max);
        }

        for (int marble : marbles) {
            if (check[marble] != 0) {
                sb.append("Y ");
                continue;
            }
            sb.append("N ");
        }

        System.out.print(sb);
    }
}
