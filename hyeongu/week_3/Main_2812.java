import java.util.*;
import java.io.*;

public class Main_2812 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String number = br.readLine();
        int last = -1;

        for(int i = 0; i < N - K ; i++){
            int max = -1;
            for(int j = last + 1; j <= i + K; j++){
                int now = number.charAt(j) - '0';
                if(now > max){
                    max = now;
                    last = j;
                }
                if(max == 9) break;
            }
            sb.append(max);
        }
        System.out.println(sb);
    }
}
