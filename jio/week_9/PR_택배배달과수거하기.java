import java.util.*;

class Solution {
    int[] deliveries;
    int[] pickups;
    int N;
    int cap;
    long totalDis;

    class Pair{
        int idx;
        int count;

        Pair(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
    }
    List<Pair> validDeliveries;
    List<Pair> validPickups;
    int maxDeliveryIdx;
    int maxPickupIdx;


    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        this.deliveries = deliveries;
        this.pickups = pickups;
        this.N = n;
        this.cap = cap;
        this.totalDis = 0; //최종 이동 거리

        validDeliveries = new ArrayList<>(); //배달해야 할 택배가 있는 집
        validPickups = new ArrayList<>(); //수거해야 할 택배가 있는 집

        for(int i=0; i<N; i++){
            if(deliveries[i] != 0){
                validDeliveries.add(new Pair(i+1, deliveries[i]));
            }
            if(pickups[i] != 0){
                validPickups.add(new Pair(i+1, pickups[i]));
            }
        }

        maxDeliveryIdx = validDeliveries.size()-1; //배달 할 택배가 남아있는 집 중 가장 먼 집의 index
        maxPickupIdx = validPickups.size()-1;      //수거 할 택배가 남아있는 집 중 가장 먼 집의 index
        while(true){
            int deliveryDis = delivery(); //배달 해야 할 최대 이동 거리
            int pickupDis = pickup();     //수거 해야 할 최대 이동 거리
            if(deliveryDis == 0 && pickupDis == 0){
                break;
            }
            totalDis += (long) Math.max(deliveryDis, pickupDis) * 2; //배달해야 할 가장 먼 집과 수거해야 할 가장 먼 집 중 더 큰 값을 선택
        }
        return totalDis;
    }

    private int delivery() {
        int maxDis = 0; //배달하기 위해 이동해야하는 최대 거리를 저장
        int curDelivery = cap; //현재 트럭에 담겨있는 택배 수, 초기 값을 cap으로 지정
        for (int i = maxDeliveryIdx; i > -1; i--) { //배달할 택배가 있는 가장 먼 집부터 가장 가까운 집으로 순회
            Pair cur = validDeliveries.get(i);
            if(maxDis == 0 && cur.count > 0){  //배달해야 할 택배가 남아있는 가장 먼 집의 거리를 저장 후 추후 return
                maxDis = cur.idx;
            }
            if(cur.count >= curDelivery){ //1. 현재 트럭에 담겨있는 택배 수 보다 집에 배달해야할 택배가 더 클 경우
                cur.count -= curDelivery;
                break;
            }
            //2. 현재 트럭에 담겨있는 택배 수가 집에 배달해야할 택배보다 더 클 경우
            maxDeliveryIdx -= 1;       //가장 먼 집 index 갱신
            curDelivery -= cur.count;  //트럭에 남아있는 택배 수 갱신
            cur.count = 0;             //집에 남아있는 택배 수 갱신
        }
        return maxDis;
    }

    private int pickup() { //delivery와 동일
        int maxDis = 0;
        int curPickup = cap;
        for (int i = maxPickupIdx; i > -1; i--) {
            Pair cur = validPickups.get(i);
            if(maxDis == 0 && cur.count > 0){
                maxDis = cur.idx;
            }
            if(cur.count >= curPickup){
                cur.count -= curPickup;
                break;
            }
            maxPickupIdx -= 1;
            curPickup -= cur.count;
            cur.count = 0;
        }
        return maxDis;
    }
}
