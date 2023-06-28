import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15732 {
    static int N,K,D ;
    static int[][] boxes;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        boxes = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            boxes[i][0] = Integer.parseInt(st.nextToken());
            boxes[i][1] = Integer.parseInt(st.nextToken());
            boxes[i][2] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = N;

        while(left<=right) {
            int mid = (left+ right)/2;
            long cnt = 0;
            for (int i = 0; i < K; i++) {
                if(boxes[i][1] <= mid) {  //mid 가 끝나는 지점보다 큰 경우
                    cnt += (boxes[i][1] - boxes[i][0])/boxes[i][2] +1;

                } else if(boxes[i][0] > mid) { //mid 가 시작 점보다 작은 경우
                    continue;
                } else {
                    cnt += (mid-boxes[i][0])==0 ? 1: (mid-boxes[i][0])/ boxes[i][2] +1;
                }
            }
            if(cnt >= D) {
                right = mid -1;
            }else{
                left = mid +1;
            }
        }
        System.out.println(left);
    }
}
