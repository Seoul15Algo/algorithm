package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// @rt3310
// 부분 수열의 합 2
public class Main_1208 {
    static int[] nums;
    static boolean[] v;
    
    static void subset(int cnt, int start, int end, Map<Long, Long> map) {
        if (cnt == (end - start + 1)) {
            long sum = 0;

            for (int i = start; i <= end; i++) {
                if (v[i]) {
                    sum += nums[i];
                }
            }

            if (map.containsKey(sum)) {
                map.put(sum, map.get(sum)+1);
            } else {
                map.put(sum, 1L);
            }
            
            return;
        }
        
        v[start + cnt] = true;
        subset(cnt+1, start, end, map);
        v[start + cnt] = false;
        subset(cnt+1, start, end, map);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        
        /*
        ex) 
                왼쪽 부분 집합 = {-7, -3, -2}
        leftMap = {{0 : 1}, {-7 : 1}, {-3 : 1}, {-2 : 1},
        		  {-10 : 1}, {-9 : 1}, {-5 : 1}, {-12 : 1}}
        
                오른쪽 부분 집합 = {5, 8}
        rightMap = {{0 : 1}, {5 : 1}, {8 : 1}, {13 : 1}}
         */
        Map<Long, Long> leftMap = new HashMap<>(); // 부분 수열의 합, 부분 수열의 개수
        Map<Long, Long> rightMap = new HashMap<>();

        v = new boolean[N];
        
        // 절반으로 나누어 부분합 구하기
        subset(0, 0, N/2, leftMap); // param2: 부분 집합의 시작점, param3: 부분 집합의 끝점
        subset(0, N/2 + 1, N - 1, rightMap); 

        long result = 0;
        /*
        ex) S = 0
        leftMap | S - leftMap | rightMap 
           0           0            0    => 1 * 1 = 1 // 하지만 크기가 양수인 부분 수열이므로 아무것도 고르지 않은 경우는 제외
          -7           7            X
          -3           3            X
                      ...
          -5           5            5    => 1 * 1 = 1        
                      ...
         */
        for (Map.Entry<Long, Long> i : leftMap.entrySet()) {
            // S에서 왼쪽 부분 집합의 부분합을 뺀 값이 오른쪽 부분집합에 존재하는 경우 
            result += rightMap.getOrDefault(S - i.getKey(), 0L) * i.getValue();
        }
        
        if (S == 0) { // S가 0인 경우 둘 다 아무것도 고르지 않은 경우의 수 제외
            result--;
        }
        
        System.out.println(result);
        br.close();
    }
}
