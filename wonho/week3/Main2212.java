package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int[] sensors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] subs = new int[n - 1];

        Arrays.sort(sensors);
        for (int i = 0; i < n-1; i++) {
            subs[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(subs);

        int answer = 0;
        for (int i = 0; i < n-k; i++) {
            answer += subs[i];
        }

        System.out.println(answer);
    }
}
