package week23;

import java.util.*;

class 주차_요금_계산 {
    static class Car implements Comparable<Car> {
        int carNum;
        int fee;

        public Car(int carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }

        @Override
        public int compareTo(Car o) {
            return Integer.compare(this.carNum, o.carNum);
        }
    }

    public int[] solution(int[] fees, String[] records) {
        ArrayList<Car> ans = new ArrayList<>();

        // 기본 정보 추출
        int basicTime = fees[0];
        int basicFee = fees[1];
        int addedTime = fees[2];
        int addedFee = fees[3];

        // 입출차 기록용
        Map<Integer, Integer> record = new HashMap<>();

        // 차량별 총 주차시간
        Map<Integer, Integer> fee = new HashMap<>();

        for (int i = 0; i < records.length; i++) {
            String status = records[i].split(" ")[2];
            int carNumber = Integer.parseInt(records[i].split(" ")[1]);
            int time = Integer.parseInt(records[i].split(" ")[0].split(":")[0]) * 60
                    + Integer.parseInt(records[i].split(" ")[0].split(":")[1]);

            // 입차
            if(status.equals("IN")) {
                record.put(carNumber, time);
                continue;
            }

            // 출차
            int startTime = record.get(carNumber);
            record.remove(carNumber);

            // 차량별 주차시간 합산
            if(fee.containsKey(carNumber)) {
                fee.replace(carNumber, fee.get(carNumber) + time-startTime);
                continue;
            }

            fee.put(carNumber, time-startTime);
        }

        // 입차 후 출차 기록이 없는 차들은 23시 59분에 출차 처리
        if(record.size() > 0) {
            record.forEach((key, value) -> {
                int carNumber = key;
                int time = 23*60 + 59 - value;

                if(fee.containsKey(carNumber)) {
                    fee.replace(carNumber, fee.get(carNumber) + time);
                    return;
                }

                fee.put(carNumber, time);
            });
        }

        fee.forEach((key, value) -> {
            int carNumber = key;
            int time = value;

            // 총 주차시간이 기본 주차 시간 이하라면 기본요금
            if(time <= basicTime) {
                ans.add(new Car(carNumber, basicFee));
                return;
            }

            // 기본 주차 시간을 초과했을 경우 주차요금 계산하여 부과
            ans.add(new Car(carNumber, basicFee + (int)Math.ceil((double)(time-basicTime) / addedTime) * addedFee));
        });

        // 차량번호 오름차순 정렬
        Collections.sort(ans);

        int[] answer = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i).fee;
        }

        return answer;
    }
}