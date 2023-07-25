package 프로그래머스_ALGO.week23;

import java.util.*;

class PR_주차요금계산 {
    
    static Map<String, List[]> parkingInfo;
    
    public int[] solution(int[] fees, String[] records) {
        
        parkingInfo = new TreeMap<>(); // key: 차량 번호
        
        for(int i=0; i<records.length; i++){
            String[] temp = records[i].split(" ");
            
            String time = temp[0];
            String carNum = temp[1];
            String type = temp[2];
            
            if(parkingInfo.get(carNum) == null){ // 해당 키 값이 없는 경우 초기화
                List<String> enterInfo = new ArrayList<>(); // 입차 시간을 넣을 리스트
                List<String> outInfo = new ArrayList<>();   // 출차 시간을 넣을 리스트
                
                List[] input = new List[2];
                
                input[0] = enterInfo;
                input[1] = outInfo;
                
                parkingInfo.put(carNum, input);
            }
            
            List[] info = parkingInfo.get(carNum);
            
            if(type.equals("IN")){ // 입차 한 경우
                info[0].add(time);
                continue;
            } 
            
            if(type.equals("OUT")){ // 출차 한 경우
                info[1].add(time);
            }
        }
        
        int[] answer = new int[parkingInfo.keySet().size()];
        int index = 0;
        
        for(Map.Entry<String, List[]> entry : parkingInfo.entrySet()){
            answer[index] = calFee(entry.getValue(), fees);
            index++;
        }
        
        return answer;
    }
    
    private static int calFee(List[] infos, int[] fees){ // 주차 요금 계산
        List<String> enterInfo = infos[0];
        List<String> outInfo = infos[1];
        
        int minCnt = Integer.min(enterInfo.size(), outInfo.size());
        
        int stay = 0; // 주차한 시간
    
        for(int i=0; i<minCnt; i++){
            stay += calTime(enterInfo.get(i), outInfo.get(i));
        }
        
        if(enterInfo.size() > outInfo.size()){ // 24시에 출차한 경우
            stay += calTime(enterInfo.get(enterInfo.size() - 1), "23:59");
        }
        
        if(stay <= fees[0]){ // 기본 요금만 내는 경우
            return fees[1];
        }
        
        int fee = fees[1];
        fee += Math.ceil((stay - fees[0]) / (double)fees[2]) * fees[3];
        
        return fee;
    }
    
    private static int calTime(String enterTime, String outTime){ // 주차 시간 계산
        String[] enter = enterTime.split(":");
        String[] out = outTime.split(":");
        
        int enterHour = Integer.parseInt(enter[0]);
        int enterMinute = Integer.parseInt(enter[1]);
        
        int outHour = Integer.parseInt(out[0]);
        int outMinute = Integer.parseInt(out[1]);
        
        return (outHour - enterHour) * 60 + (outMinute - enterMinute);
    }
}