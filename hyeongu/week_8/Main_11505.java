import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 전체적인 코드는 2357(최솟값과 최댓값)과 대부분 동일
public class Main_11505 {
    static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N + 1];

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        segmentTree seg = new segmentTree(N);
        seg.init(arr, 1, 1, N);

        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
                arr[b] = c;
                seg.update(arr, 1, 1, N, b);
                continue;
            }

            long answer = seg.find(1, 1, N, b, c);
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

    static class segmentTree{
        long[] tree;

        public segmentTree(int n){
            long h = (long) Math.ceil(Math.log(n) / Math.log(2)) + 1;
            long cnt = (long) Math.pow(2, h);
            tree = new long[Math.toIntExact(cnt)];
        }

        long init(long[] arr, int node, int start, int end){
            if(start == end){
                return tree[node] = arr[start];
            }
            return tree[node] = (init(arr, node * 2, start, (start + end)/2)) *
                    (init(arr, node * 2 + 1, (start + end)/2 + 1, end)) % MOD;
        }


        long find(int node, int start, int end, int left, int right){
            if(end < left || right < start){
                return 1;
            }
            if(left <= start && end <= right){
                return tree[node];
            }
            return (find(node * 2, start, (start + end) / 2, left, right) *
                    find(node * 2 + 1, (start + end)/2 + 1, end, left, right)) % MOD;
        }

        // init과 유사한 형태로 범위 내의 숫자를 update
        long update(long[] arr, int node, int start, int end, int index){
            if(start == end){
                return tree[node] = arr[start];
            }

            if(index < start || end < index){
                return tree[node];
            }

            return tree[node] = (update(arr, node * 2, start, (start + end)/2, index)) *
                    (update(arr, node * 2 + 1, (start + end)/2 + 1, end, index)) % MOD;
        }
    }
}
