import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2138 {

    static final int INF = Integer.MAX_VALUE / 1000;
    static int N;
    static boolean[] origin;
    static boolean[] target;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        origin = new boolean[N + 2];
        target = new boolean[N + 2];

        String[] originInput = br.readLine().split("");
        String[] targetInput = br.readLine().split("");
        for (int i = 1; i <= N; i++) {
            origin[i] = originInput[i - 1].equals("1");
            target[i] = targetInput[i - 1].equals("1");
        }

        // 첫번째 버튼을 누르지 않은 경우
        boolean[] copied = copy();
        int result1 = getResult(copied);

        // 첫번째 버튼을 누른 경우
        copied = copy();
        pushSwitch(1, copied);
        int result2 = getResult(copied) + 1;

        int min = Math.min(result1, result2);
        System.out.println(min == INF ? -1 : min);
    }

    private static int getResult(boolean[] lightBulbs) {

        int result = 0;
        for (int i = 2; i <= N; i++) {

            // i-1번째 전구의 현재 상태가 목표 상태와 다르다면 -> i번째 스위치를 누른다
            if (lightBulbs[i - 1] ^ target[i - 1]){
                pushSwitch(i, lightBulbs);
                result++;
            }
        }

        // 현재 상태가 목표 상태와 같은지 체크
        if (isSame(lightBulbs)){
            return result;
        }

        return INF;
    }

    private static void pushSwitch(int idx, boolean[] lightBulbs) {

        lightBulbs[idx - 1] = !lightBulbs[idx - 1];
        lightBulbs[idx] = !lightBulbs[idx];
        lightBulbs[idx + 1] = !lightBulbs[idx + 1];
    }

    private static boolean isSame(boolean[] lightBulbs) {
        for (int i = 1; i <= N; i++){
            if (lightBulbs[i] ^ target[i]){
                return false;
            }
        }
        return true;
    }

    private static boolean[] copy() {

        return Arrays.copyOf(origin, origin.length);
    }

}