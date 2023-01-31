import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1138 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int front = Integer.parseInt(st.nextToken());
            int full = 0;
            for (int j = 0; j <= front + full; j++) {
                if (arr[j] > 0) {
                    full++;
                }
            }
            arr[front + full] = i;
        }

        for(int i = 0; i < N; i++){
            sb.append(arr[i] + " ");
        }

        System.out.println(sb.toString());
    }
}
