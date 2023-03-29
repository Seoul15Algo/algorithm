package week9;

public class Delivery {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int deliver = 0;
        int pickup = 0;
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];
            
            int count = 0;
            while (deliver > 0 || pickup > 0) {
                deliver -= cap;
                pickup -= cap;
                count++;
            }
            answer += (i + 1) * 2 * count;
        }
        
        return answer;
    }
}
