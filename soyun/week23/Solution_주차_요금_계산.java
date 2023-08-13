import java.util.Map;
import java.util.TreeMap;

public class Solution_주차_요금_계산 {

    class Record {

        private int entrance;
        private int time;
        private boolean out;

        // 주차장 입장
        public void enter(int enterTime) {
            this.entrance = enterTime;
            this.out = false;
        }

        // 주차장 퇴장
        public void exit(int exitTime) {
            this.time += exitTime - entrance;   // 총 이용 시간은 쌓인다.
            this.out = true;
        }

        // 총 주차 요금 계산
        public int calculateFee() {

            int fee = basicFee;

            // 기본 시간보다 적게 사용했으면 -> 기본요금
            if (time <= basicTime) {
                return fee;
            }

            time -= basicTime;

            // 기본 시간 초과 -> 단위 요금 부과
            fee += unitFee * Math.ceil((double)time / unitTime);

            return fee;
        }
    }

    private Map<Integer, Record> parkingTimes = new TreeMap<>();
    private int basicTime, basicFee;
    private int unitTime, unitFee;

    private int limitTime;

    public int[] solution(int[] fees, String[] records) {

        basicTime = fees[0];
        basicFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        limitTime = timeToMinute("23:59");

        calculate(records);

        int[] result = new int[parkingTimes.size()];
        int idx = 0;
        for (int id : parkingTimes.keySet()) {
            result[idx++] = parkingTimes.get(id).calculateFee();
        }

        return result;
    }

    public void calculate(String[] records) {
        for (String record : records) {
            String[] attr = record.split(" ");
            int id = Integer.parseInt(attr[1]);
            int time = timeToMinute(attr[0]);

            parkingTimes.putIfAbsent(id, new Record());

            // 주차장 입장
            if (attr[2].equals("IN")) {
                parkingTimes.get(id).enter(time);
                continue;
            }

            // 주차장 퇴장
            parkingTimes.get(id).exit(time);
        }

        // 자정 전까지 퇴장하지 않은 차량들 계산
        calculateRemained();
    }


    public void calculateRemained() {

        for (int id : parkingTimes.keySet()) {

            // 출차 시간 불분명 -> 23:59분 출차로 간주
            if (!parkingTimes.get(id).out) {
                parkingTimes.get(id).exit(limitTime);
            }
        }
    }

    public int timeToMinute(String time) {
        String[] splitted = time.split(":");
        int hour = Integer.parseInt(splitted[0]) * 60;
        int minute = Integer.parseInt(splitted[1]);

        return hour + minute;
    }
}
