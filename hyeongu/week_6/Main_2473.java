import java.util.*;
import java.io.*;

public class Main_2473 {
    static long[] arr;
    static long answer;
    static int[] ansArr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        arr = new long[N];
        answer = 3_000_000_001L;
        ansArr = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int start = 0;
        int end = 2;

        // 미리 두 값을 정해놓고 나머지 하나의 값을 이분탐색으로 찾는 방법으로 진행
        // 미리 정한 두 값 사이의 수만 탐색하며 진행
        while(end < N){
            long key = arr[start] + arr[end];
            binary(start, end, key);

            // 0이 최솟값이므로 종료
            if(answer == 0){
                break;
            }

            if(end - start == 2){
                end++;
                start = 0;
                continue;
            }
            start++;
        }

        sb.append(arr[ansArr[0]]).append(" ");
        sb.append(arr[ansArr[1]]).append(" ");
        sb.append(arr[ansArr[2]]);

        System.out.println(sb);
    }

    static void binary(int start, int end, long key){
        // 양쪽 끝의 값이 미리 정해진 값이므로 제외하고 탐색
        int left = start + 1;
        int right = end - 1;

        while(left <= right){
            int mid = (left + right) / 2;

            // 현재 위치에서의 값이 answer보다 작을경우 answer에 저장
            if(Math.abs(key + arr[mid]) < answer){
                answer = Math.abs(key + arr[mid]);
                ansArr[0] = start;
                ansArr[1] = mid;
                ansArr[2] = end;
            }

            // answer : 0 이 나온경우
            if(key + arr[mid] == 0){
                return;
            }

            // 현재 위치에서의 값이 양수이면 왼쪽으로 이분탐색
            if(key + arr[mid] > 0){
                right = mid - 1;
                continue;
            }
            // 현재 위치에서의 값이 음수이면 오른쪽으로 이분탐색
            left = mid + 1;
        }
    }
}
