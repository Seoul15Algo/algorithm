package com.ssafy.baekjoon.study.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11505 {

    static class SegmentTree {

        final long DIV = 1_000_000_007;

        int size;
        long[] tree;

        // 주어진 배열로 Segment Tree 를 생성
        public SegmentTree(long[] arr, int size) {
            build(arr, size);
        }

        // left partition, right partition을 합침 -> 그때의 곱셈 연산 결과
        private long merge(long left, long right) {
            // sum 을 구하고 싶은 경우
            return (left * right) % DIV;
        }

        public void build(long[] arr, int size) {
            this.size = size;
            this.tree = new long[size * 4];

            buildRec(arr, 1, 0, size - 1);
        }

        private long buildRec(long[] arr, int node, int nodeLeft, int nodeRight) {
            // 자식노드가 하나인 경우
            if (nodeLeft == nodeRight) {
                return tree[node] = arr[nodeLeft];
            }
            
            // mid를 기준으로
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

            // leftVal, rightVal 나누어서 재귀 돈다 -> 반환 값은 해당 partition에서의 곱셈 연산 결과
            long leftVal = buildRec(arr, node * 2, nodeLeft, mid);
            long rightVal = buildRec(arr, node * 2 + 1, mid + 1, nodeRight);

            // 두 파티션을 합쳤을 때의 최소/최대값을 반환
            return tree[node] = merge(leftVal, rightVal);
        }

        // left ~ right 까지의 연산 결과 구하기
        public long query(int left, int right) {
            return queryRec(left, right, 1, 0, size - 1);
        }

        private long queryRec(long left, long right, int node, int nodeLeft, int nodeRight) {

            // 내가 처리하려는 구간을 벗어나는 경우
            if (left > nodeRight || right < nodeLeft) {
                return 1;   // default value
            }

            // 처리하려는 구간에 완전히 포함되는 경우
            if (left <= nodeLeft && right >= nodeRight) {
                return tree[node];
            }

            // 처리하려는 구간에 걸치는 경우 (구간에 벗어나는 애들과 포함되는 애들을 나누어서 계산해야 하기 때문)
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;

            return merge(
                    queryRec(left, right, node * 2, nodeLeft, mid),
                    queryRec(left, right, node * 2 + 1, mid + 1, nodeRight)
            );
        }


        // index의 값을 newValue로 업데이트할 경우의 변경된 연산 결과
        public long update(int index, long newValue){
            return updateRec(index, newValue, 1, 0, size - 1);
        }

        private long updateRec(int index, long newValue, int node, int nodeLeft, int nodeRight){
            // 업데이트 위치와 관련없는 segment
            if (index < nodeLeft || index > nodeRight){
                return tree[node];  // 업데이트 하지 않으니 본인 리턴
            }
            // 업데이트 위치를 찾았을 경우 -> 갱신
            if (nodeLeft == nodeRight){
                return tree[node] = newValue;
            }

            // 파티션을 나눠가면서 Segment Tree 업데이트
            int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
            long leftVal = updateRec(index, newValue, node * 2, nodeLeft, mid);
            long rightVal = updateRec(index, newValue, node * 2 + 1, mid + 1, nodeRight);

            return tree[node] = merge(leftVal, rightVal);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        SegmentTree segmentTree = new SegmentTree(arr, N);

        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            switch(a){
                // update
                case 1:
                    segmentTree.update(b - 1, c);
                    break;
                // query
                case 2:
                    long result = segmentTree.query(b - 1, c - 1);
                    sb.append(result).append("\n");
                    break;
                default:
                    System.out.println("유효한 입력이 아닙니다.");;
            }
        }
        System.out.println(sb);
    }
}