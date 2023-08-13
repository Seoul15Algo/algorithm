package week23;

import java.util.*;

class 주차_요금_계산 {
    static class Car {
        boolean status;
        int enterTime;
        int parkingTime;

        public Car(int enterTime) {
            status = true;
            this.enterTime = enterTime;
            parkingTime = 0;
        }

        public void enter(int enterTime) {
            status = true;
            this.enterTime = enterTime;
        }

        public void exit(int exitTime) {
            status = false;
            parkingTime += (exitTime - enterTime);
        }

        public int calculateFee(int[] fees) {
            int fee = fees[1];

            int extraTime = parkingTime - fees[0];
            if (extraTime > 0) {
                fee += (extraTime / fees[2]) * fees[3];

                if (extraTime % fees[2] != 0) {
                    fee += fees[3];
                }
            }

            return fee;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        StringTokenizer st;

        Map<String, Boolean> status = new HashMap<String, Boolean>();
        status.put("IN", true);
        status.put("OUT", false);

        Map<Integer, Car> parking = new TreeMap<Integer, Car>();

        for (String record : records) {
            st = new StringTokenizer(record);

            String[] timeArr = st.nextToken().split(":");
            int time = Integer.parseInt(timeArr[0]) * 60 + Integer.parseInt(timeArr[1]);
            int carNumber = Integer.parseInt(st.nextToken());
            boolean carStatus = status.get(st.nextToken());

            if (!parking.containsKey(carNumber)) {
                parking.put(carNumber, new Car(time));

                continue;
            }

            Car car = parking.get(carNumber);

            if (carStatus) { // IN
                car.enter(time);
            } else { // OUT
                car.exit(time);
            }
        }

        int[] answer = new int[parking.size()];

        int maxTime = 23 * 60 + 59;
        int i = 0;
        for (Car car : parking.values()) {
            if (car.status) {
                car.exit(maxTime);
            }

            answer[i++] = car.calculateFee(fees);
        }

        return answer;
    }
}