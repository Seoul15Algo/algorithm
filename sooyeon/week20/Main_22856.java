import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_22856 {
    static int N;
    static int result;
    static int endPoint;
    static List<Node>[] list;
    static class Node{
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList[N+1];
        for(int i=0; i<=N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            list[c].add(new Node(l,r));
        }

        endPoint = 1;
        dfsFin(1); //종료 지점 찾기 위한 dfs

        dfsMove(1); //이동횟수 구하는 dfs
    }

    private static void dfsMove(int cur) {

        if(list[cur].get(0).left != -1) {
            result++;
            dfsMove(list[cur].get(0).left);
        }
        if(cur == endPoint) {
            System.out.println(result);
            return;
        }
        if(list[cur].get(0).right != -1) {
            result++;
            dfsMove(list[cur].get(0).right);
        }
        result++;
    }

    private static void dfsFin(int cur) {
        if(list[cur].get(0).left != -1) {
            dfsFin(list[cur].get(0).left);
        }
        endPoint = cur;
        if(list[cur].get(0).right != -1) {
            dfsFin(list[cur].get(0).right);
        }
    }
}
