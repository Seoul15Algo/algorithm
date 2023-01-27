import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14719 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int[][] world = new int[h][w];  // 블럭이 있는 칸과 빈 칸으로 이루어진 h x w 행렬

        st = new StringTokenizer(br.readLine());
        // width 크기 만큼 순회하면서 height 만큼 블럭을 쌓는다.
        // 1: 블럭이 있는 칸 | 0: 블럭이 없는 빈 칸
        for (int i = 0; i < w; i++){
            int blocks = Integer.parseInt(st.nextToken());
            for (int j = 0; j < blocks; j++) {
                world[j][i]++;
            }
        }

        int result = 0;
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < h; i++){
            // 한 행씩 탐색 -> 블럭이 있는 열의 idx 를 stack 에 push
            for (int j = 0; j < w; j++){
                if (world[i][j] == 1){
                    stk.push(j);
                }
            }
            // 블럭이 있는 열의 idx 끼리 빼면 그 사이에 빈 공간이 몇 개 있는 지 알 수 있음
            while(!stk.isEmpty()){
                int cur = stk.pop();
                if (!stk.isEmpty())
                    result += cur - stk.peek() - 1;
            }

        }
        System.out.println(result);
    }
}
