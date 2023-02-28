package baekjoon.algo03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2212 {

    static int n;
    static int k;
    static int[] sensors;
    static int[] distances;
    //static boolean[] towers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        sensors = new int[n];
        distances = new int[n - 1];
        //towers = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());


        for (int i = 0; i < n; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sensors);

        for (int i = 0; i < n - 1; i++) {
            distances[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(distances);

        // 첫번째 센서가 있는 곳에는 항상 집중국을 세운다.
        //towers[0] = true;

        int sum = 0;
        for (int i = 0; i < (n - 1) - (k - 1); i++){
            sum += distances[i];
        }

        System.out.println(sum);
    }
}