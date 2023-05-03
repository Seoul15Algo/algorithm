import java.util.*;
import java.io.*;

public class Main_20061 {
    static int[] green, blue;
    static int score, cnt;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        score = 0;
        cnt = 0;
        
        // 비트마스킹을 이용해 하나의 열에 블록의 위치를 저장
        /*
         * 파란색
         * 
         * 0 0 0 1	-> 1
         * 0 1 1 1	-> 7	
         * 0 1 0 1	-> 5
         * 0 0 0 1	-> 1
         * 
         * => {1, 7, 5, 1}
         */
        green = new int[4];
        blue = new int[4];

        
        while(T-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            // 블럭 추가
            if(type == 1){
                addBlock(blue, r, r, 1);
                addBlock(green, c, c, 1);
            }
            if(type == 2){
                addBlock(blue, r, r, 3);
                addBlock(green, c, c + 1, 1);
            }
            if(type == 3){
                addBlock(blue, r, r + 1, 1);
                addBlock(green, c, c, 3);
            }
            
            // 블럭 삭제
            breakBlock(blue);
            breakBlock(green);
        }
        // 남은 블럭 개수 확인
        countBlock(blue);
        countBlock(green);

        sb.append(score).append("\n").append(cnt);
        System.out.println(sb);
    }
    
    // start 부터 end 까지 len만큼 크기의 비트를 추가
    // start 부터 end 까지의 비트 중에서 가장 큰 비트의 앞에 비트 추가
    static void addBlock(int[] arr, int start, int end, int len){
        int height = 0;
        
        // log2 를 이용해 비트의 길이를 구함
        for(int i = start; i <= end; i++){
            height = Math.max(height, (int)(Math.log(arr[i])/Math.log(2)));
        }
        
        // 추가해야하는 비트를 앞에서 구한 길이만큼 앞으로 옮긴 후 기존 값에 추가
        for(int i = start; i <= end; i++){
            arr[i] += (len << (height + 1));
        }
    }

    static void breakBlock(int[] arr){
    	// and연산을 해서 1이 나오는 비트는 4개의 줄이 모두 가지고있는 비트
    	// 따라서 테트리스에서 완성된 줄이므로 사라져야함
        int and = arr[0] & arr[1] & arr[2] & arr[3];

        for(int i = 1; i <= 4; i++){
            int now = 1 << i;
            
            // and 연산 비트가 1인경우
            // 아래에 있는 비트는 유지하고 위에있는 비트를 한칸 내려야하므로
            // 아래에 있는 비트(나머지 연산) + 한칸 내린 위에 비트(오른쪽으로 i + 1만큼 시프트 후 왼쪽으로 i만큼 시프트) 
            if((and & now) > 0){
                arr[0] = (arr[0] % now) + ((arr[0] >> (i + 1)) << i);
                arr[1] = (arr[1] % now) + ((arr[1] >> (i + 1)) << i);
                arr[2] = (arr[2] % now) + ((arr[2] >> (i + 1)) << i);
                arr[3] = (arr[3] % now) + ((arr[3] >> (i + 1)) << i);
                and = (and % now) + ((and >> (i + 1)) << i);		// 값이 바뀌었으므로 새로운 and값 저장
                score++;
                i--;		// 위의 비트가 한칸 내려왔으므로 해당 위치 다시 탐색
            }
        }
        
        // or연산에서 1이 나오는 비트는 하나라도 비트가 있는 경우
        // 5~6번째 칸에 비트가 있을 경우
        // 오른쪽으로 시프트연산을 하게되면 문제의 조건과 일치
        int or = arr[0] | arr[1] | arr[2] | arr[3];

        while(or >= 32){
            or >>= 1;
            arr[0] >>= 1;
            arr[1] >>= 1;
            arr[2] >>= 1;
            arr[3] >>= 1;
        }
    }
    	
    // 1인 비트의 개수 count
    static void countBlock(int[] arr){
        for(int i = 0; i < 4; i++){
            while(arr[i] > 1){
                arr[i] /= 2;
                cnt += arr[i] % 2;
            }
        }
    }
}