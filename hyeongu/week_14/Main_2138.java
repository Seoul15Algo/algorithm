import java.io.*;

public class Main_2138 {
    static boolean[] before, after, tmp;
    static int N, answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        before = new boolean[N + 1];		// 기존 전구 배열
        after = new boolean[N + 1];			// 만들어야하는 전구 배열
        tmp = new boolean[N + 1];			// 기존 전구 배열 복사
        answer = -1;

        String str1 = br.readLine();
        String str2 = br.readLine();
        for(int i = 0; i < N; i++){
            before[i] = str1.charAt(i) == '0' ? true : false;
            after[i] = str2.charAt(i) == '0' ? true : false;
            tmp[i] = before[i];
        }

        // 1번 스위치를 누르지 않고 시작하는 경우
        find(0);

        // 배열 초기화 후 1번 스위치를 누르는 과정
        before = tmp;
        before[0] = !before[0];
        before[1] = !before[1];
        
        // 1번 스위치를 누르고 시작하는 경우
        find(1);

        System.out.println(answer);
    }

    static void find(int cnt){
    	// 앞에서부터 순차적으로 탐색을 진행
    	// index - 1의 전구를 바꿀 수 있는 마지막 스위치는 index
    	// index - 1의 전구가 맞지 않는 경우 index 스위치를 누름
        for(int i = 1; i < N; i++){
            if(before[i - 1] != after[i - 1]){
                before[i] = !before[i];
                before[i + 1] = !before[i + 1];
                cnt++;
            }
        }
        
        // 마지막 전구가 일치하지 않는 경우 -> 불가능한 경우
        if(before[N - 1] != after[N - 1]){
            return;
        }
        if(answer == -1){
            answer = cnt;
            return;
        }
        // 1번 스위치를 누른경우와 누르지 않은 경우의 최솟값을 저장
        answer = Math.min(answer, cnt);
    }
}