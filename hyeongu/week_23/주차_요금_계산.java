import java.util.*;
public class 주차_요금_계산 {
    public int[] solution(int[] fees, String[] records) {
        int[] arr = new int[10000];
        Map<Integer, Integer> tm = new TreeMap<>();

        for(String str : records){
            StringTokenizer st = new StringTokenizer(str);

            String[] time = st.nextToken().split(":");
            int car = Integer.parseInt(st.nextToken());
            String command = st.nextToken();

            if(command.equals("IN")){
                arr[car] = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
                if(arr[car] == 0){
                    arr[car] = -1;
                }
            }
            else{
                if(arr[car] == -1){
                    arr[car] = 0;
                }
                int tmp = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]) - arr[car];
                arr[car] = 0;
                tm.put(car, tm.getOrDefault(car, 0) + tmp);
            }
        }
        for(int i = 0; i<10000; i++){
            if(arr[i] != 0){
                if(arr[i] == -1){
                    arr[i] = 0;
                }
                tm.put(i, tm.getOrDefault(i, 0) + (1439-arr[i]));
            }
        }

        int[] answer = new int[tm.size()];
        int i = 0;

        for(int key : tm.keySet()){
            int time = tm.get(key);
            if(fees[0] >= time){
                answer[i++] = fees[1];
            }
            else{
                time -= fees[0];
                answer[i] = fees[1];
                answer[i] += (int)Math.ceil(time / (double)fees[2]) * fees[3];
                i++;
            }
        }
        return answer;
    }
}
