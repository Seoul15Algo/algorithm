import java.util.*;
import java.io.*;

public class Main_1208 {
    static int N, S;
    static int[] arr;
    static List<Integer> left, right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];
        left = new ArrayList<>();
        right = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 전체 배열의 길이가 40이므로 2^40은 subset 불가능
        // 따라서 subset이 가능한 2^20 배열 2개로 나눈 뒤 진행
        // 각 배열 subset 후 해당 list를 정렬
        subset(0, N/2, 0, left);
        subset(N/2, N, 0, right);
        Collections.sort(left);
        Collections.sort(right);

        long answer = 0;
        if(S == 0){
            answer--;
        }

        // left의 값을 기준으로 더해서 S가 되는 값의 개수를 구함
        // 값이 중복되는 경우가 존재하기 때문에 left * right를 answer에 더해줌
        // 중복되는 값을 확인한 경우 index를 개수만큼 jump
        for(int i = 0; i < left.size(); i++){
            int now = left.get(i);
            int right_cnt = binarySearch(S - now, right);

            if(right_cnt > 0){
                int left_cnt = sameValue(i, now, left);
                answer += (long)left_cnt * right_cnt;
                i += left_cnt - 1;
            }
        }
        System.out.println(answer);
    }

    // 이분탐색
    static int binarySearch(int target, List<Integer>list){
        int start = 0;
        int end = list.size() - 1;

        while(start <= end){
            int mid = (start + end)/2;

            if(list.get(mid) == target){
                return sameValue(mid, target, list);
            }
            if(list.get(mid) > target){
                end = mid - 1;
                continue;
            }
            start = mid + 1;
        }
        return 0;
    }


    // 부분수열
    static void subset(int now, int end, int sum, List<Integer> list){
        if(now == end){
            list.add(sum);
            return;
        }

        subset(now + 1, end, sum + arr[now], list);
        subset(now + 1, end, sum, list);
    }

    // 정렬된 list에서 해당하는 값의 개수를 찾는 메소드
    // 앞, 뒤로 같은 값의 개수를 return
    static int sameValue(int index, int value, List<Integer>list){
        int result = 1;
        int tmp = index + 1;
        while(tmp < list.size() && list.get(tmp) == value){
            tmp++;
            result++;
        }

        tmp = index - 1;
        while(tmp >= 0 && list.get(tmp) == value){
            tmp--;
            result++;
        }

        return result;

    }
}
