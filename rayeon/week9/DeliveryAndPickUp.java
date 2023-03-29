package week9;

public class DeliveryAndPickUp {
	// 배달 또는 수거해야 할 택배 상자의 위치 중 제일 먼 곳 찾기
	static int getMaxDistance(int maxDistance, int[] deliveries, int[] pickups){
        int newMaxDistance = -1;
        
        for (int i = maxDistance; i >= 0; i--) {
            if (deliveries[i] > 0 || pickups[i] > 0) {
                newMaxDistance = i;
                break;
            }
        }
        
        return newMaxDistance;
    }
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int totalDelivery = 0; // 배달해야 할 택배 상자의 총 개수
        int totalPickup = 0; // 수거해야 할 택배 상자의 총 개수
        for (int i = 0; i < n; i++) {
            if (deliveries[i] > 0) {
                totalDelivery += deliveries[i];
            }
            
            if (pickups[i] > 0) {
                totalPickup += pickups[i];
            }
        }
        
        int maxDistance = getMaxDistance(n - 1, deliveries, pickups);
        while (maxDistance >= 0) {
            int deliveryCap = 0; // 현재 트럭에 실은 배달할 택배 상자의 개수
            int pickupCap = 0; // 현재 트럭에 실은 수거할 택배 상자의 개수

            for (int i = maxDistance; i >= 0; i--) {
            	// 배달 택배 상자를 트럭에 실을 수 있는 경우
                if (deliveries[i] > 0 && deliveryCap < cap) {
                	// 집에 있는 택배 상자를 모두 트럭에 실을 수 있는 경우
                    if (deliveryCap + deliveries[i] <= cap) {
                        deliveryCap += deliveries[i];
                        totalDelivery -= deliveries[i];
                        deliveries[i] = 0;
                    } else { // 집에 있는 택배 상자를 모두 트럭에 실을 수 없는 경우
                        deliveries[i] -= (cap - deliveryCap); // 실을 수 있는 정도만 싣는다.
                        totalDelivery -= (cap - deliveryCap);
                        deliveryCap = cap;
                    }
                }
                
                if (pickups[i] > 0 && pickupCap < cap) {
                    if (pickupCap + pickups[i] <= cap) {
                        pickupCap += pickups[i];
                        totalPickup -= pickups[i];
                        pickups[i] = 0;
                    } else {
                        pickups[i] -= (cap - pickupCap);
                        totalPickup -= (cap - pickupCap);
                        pickupCap = cap;
                    }
                }
                
                if (deliveryCap == cap && pickupCap == cap) {
                    break;
                }
                
                // 배달 택배 상자를 트럭에 최대로 싣고, 모두 수거한 경우
                if (deliveryCap == cap && totalPickup == 0)
                    break;
               
                // 수거 택배 상자를 트럭에 최대로 싣고,모두 배달한 경우
                if (pickupCap == cap && totalDelivery == 0)
                    break;
            }
            
            answer += ((maxDistance + 1) * 2);
            maxDistance = getMaxDistance(maxDistance, deliveries, pickups);
            
            if (totalDelivery == 0 && totalPickup == 0)
                break;
        }

        return answer;
    }
}
