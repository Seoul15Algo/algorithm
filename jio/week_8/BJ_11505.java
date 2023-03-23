import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//최종 결과값에서 나머지 연산을 수행하지 않고 세그먼트 트리를 생성, 업데이트 하는 과정과 query를 수행하는 과정 속에서
//매번 나머지 연산을 수행해주어 overflow를 방지한다.
public class BJ_11505 {
    static int N, M, K;
    static int MOD = 1000000007; //Modular 연산 값
    static int[] nums;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        tree = new long[N*4]; //일반적으로 2N <= size < 4N으로 설정
        build(1, 0, N-1); //트리의 root 노드는 1부터 시작해야 계산이 편하다.

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type == 1){ //update
                int idx = Integer.parseInt(st.nextToken())-1;
                int newValue = Integer.parseInt(st.nextToken());
                update(idx, newValue, 1, 0, N-1);
            }else{ //query(구간 곱 구하기)
                int left = Integer.parseInt(st.nextToken())-1;
                int right = Integer.parseInt(st.nextToken())-1;
                sb.append(query(left, right, 1, 0, N-1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static long build(int node, int nodeLeft, int nodeRight) { //세그먼트 트리 생성
        if(nodeLeft == nodeRight){
            return tree[node] = nums[nodeLeft];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        long leftValue = build(node*2, nodeLeft, mid);
        long rightValue = build(node*2 + 1, mid+1, nodeRight);
        return tree[node] = leftValue * rightValue % MOD;
    }

    private static long update(int index, int newValue, int node, int nodeLeft, int nodeRight){ //특정 값 업데이트
        if(nodeRight < index || nodeLeft > index){ //구간을 벗어나는 경우
            return tree[node];
        }
        if(nodeLeft == nodeRight){  //바꾸려는 위치에 도달 시
            return tree[node] = newValue;
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        long leftValue = update(index, newValue, node * 2, nodeLeft, mid);
        long rightValue = update(index, newValue, node * 2 + 1, mid + 1, nodeRight);
        return tree[node] = leftValue * rightValue  % MOD;
    }

    private static long query(int left, int right, int node, int nodeLeft, int nodeRight){ //구간 곱 계산
        if(nodeRight < left || nodeLeft > right){ //구간에서 벗어나는 경우 1을 return
            return 1;
        }
        if(left <= nodeLeft && nodeRight <= right){ //구간에 완전히 들어와 있는 경우 현재 트리에 저장된 값 return
            return tree[node];
        }
        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        long leftValue = query(left, right, node * 2, nodeLeft, mid);
        long rightValue = query(left, right, node * 2 + 1, mid + 1, nodeRight);
        return leftValue * rightValue  % MOD;
    }
}