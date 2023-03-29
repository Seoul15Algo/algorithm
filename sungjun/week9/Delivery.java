package week9;

public class Delivery {
	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;
		int deliverCount = 0;
		int pickupCount = 0;
		
		// 뒤에서부터 탐색
		for (int i = n-1; i >= 0; i--) {
			deliverCount += deliveries[i];
			pickupCount += pickups[i];
			
			// 배달 또는 수거할 택배가 있다면
			while(deliverCount > 0 || pickupCount > 0) {
				deliverCount -= cap;
				pickupCount -= cap;
				
				// 왕복 이동거리
				answer += (i+1)*2;
			}
		}
		
		return answer;
    }
}
