import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14719 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int[] arr = new int[W];
        int answer = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < W; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < H; i++){
            int last = -1;
            int tmp = 0;

            for(int j = 0; j < W; j++){
                if(arr[j] >= i + 1){
                    last = j;
                    answer += tmp;
                    tmp = 0;

                }
                else{
                    if(last != -1){
                        tmp++;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
