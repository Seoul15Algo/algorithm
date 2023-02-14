import java.io.*;
import java.util.*;

public class Main_2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if(K >= N) {
            System.out.println(0);
            return;
        }

        int answer = 0;
        int[] arr = new int[N];
        int[] gap = new int[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        for(int i = 0; i < N - 1; i++){
            gap[i] = arr[i + 1] - arr[i];
        }
        Arrays.sort(gap);

        for(int i = 0; i < N - K; i++){
            answer += gap[i];
        }

        System.out.println(answer);
    }
}