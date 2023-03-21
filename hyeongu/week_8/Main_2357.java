import java.util.*;
import java.io.*;

public class Main_2357 {
    // https://codingnojam.tistory.com/49
    // 세그먼트 트리 개념

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        segmentTree seg = new segmentTree(N);

        seg.maxInit(arr, 1, 1, N);
        seg.minInit(arr, 1, 1, N);

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = seg.minFind(1, 1, N, a, b);
            int max = seg.maxFind(1, 1, N, a, b);

            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }


    //
    static class segmentTree{
        int[] Maxtree; // 최댓값을 저장하는 세그먼트 트리
        int[] MinTree; // 최솟값을 저장하는 세그먼트 트리


        public segmentTree(int n){
            long h = (long) Math.ceil(Math.log(n) / Math.log(2)) + 1; // 트리의 높이
            long cnt = (long) Math.pow(2, h); // 트리의 노드
            Maxtree = new int[Math.toIntExact(cnt)]; // Math.toIntExact
            MinTree = new int[Math.toIntExact(cnt)]; // long -> int 형 변환 시 int 범위를 벗어나는 수 일 경우 예외를 리턴하는 함수
        }

        int maxInit(int[] arr, int node, int start, int end){
            // 리프노드 인 경우
            if(start == end){
                return Maxtree[node] = arr[start];
            }
            // 리프노드가 아닌 경우 -> 자식의 최댓값
            return Maxtree[node] = Math.max(maxInit(arr, node * 2, start, (start + end)/2)
                              , maxInit(arr, node * 2 + 1, (start + end)/2 + 1, end));
        }

        int minInit(int[] arr, int node, int start, int end){
            // 리프노드 인 경우
            if(start == end){
                return MinTree[node] = arr[start];
            }
            // 리프노드가 아닌 경우 -> 자식의 최솟값
            return MinTree[node] = Math.min(minInit(arr, node * 2, start, (start + end)/2)
                    , minInit(arr, node * 2 + 1, (start + end)/2 + 1, end));
        }

        int maxFind(int node, int start, int end, int left, int right){
            // 범위를 벗어나는 경우
            if(end < left || right < start){
                return 0;
            }
            // 범위에 포함되는 경우
            if(left <= start && end <= right){
                return Maxtree[node];
            }
            return Math.max(maxFind(node * 2, start, (start + end) / 2, left, right),
                            maxFind(node * 2 + 1, (start + end)/2 + 1, end, left, right));
        }

        int minFind(int node, int start, int end, int left, int right){
            if(end < left || right < start){
                return Integer.MAX_VALUE;
            }
            if(left <= start && end <= right){
                return MinTree[node];
            }
            return Math.min(minFind(node * 2, start, (start + end) / 2, left, right),
                    minFind(node * 2 + 1, (start + end)/2 + 1, end, left, right));
        }
    }
}
