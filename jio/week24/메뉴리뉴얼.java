import java.util.*;

class Solution {

    static Map<String, Integer> orderMap;
    static int[] maxValues;

    public String[] solution(String[] orders, int[] course) {
        orderMap = new HashMap<>(); // key : 나올 수 있는 구성, value : 구성이 주문된 횟수
        maxValues = new int[11];    // maxValue[2] : 2개로 이루어진 구성 중 가장 많이 주문된 횟수

        for(String order : orders) {
            char[] arr = order.toCharArray(); // order를 먼저 정렬 시켜줌(xy, yx가 같은 구성이기 때문에)
            Arrays.sort(arr);
            String sortedOrder = new String(arr);

            subset(sortedOrder, "", 0);
        }

        for(String key : orderMap.keySet()) { // map을 돌며 구성의 개수에 따른 최대 주문 횟수를 찾음
            int index = key.length();
            maxValues[index] = Integer.max(maxValues[index], orderMap.get(key));
        }

        List<String> answerList = new ArrayList<>(); // 정답을 저장할 리스트

        for(int length : course) {
            int maxValue = maxValues[length];
            if(maxValue < 2) { // 최대 주문 횟수가 2번 이상인 구성은 넘어감
                continue;
            }

            for(String key : orderMap.keySet()) {
                int value = orderMap.get(key);

                if(value == maxValue && length == key.length()) {
                    answerList.add(key);
                }
            }
        }

        Collections.sort(answerList);

        return answerList.toArray(new String[answerList.size()]);
    }

    private static void subset(String order, String str, int cnt) { // 부분집합을 통해 모든 구성 확인
        if(cnt == order.length()) {
            if(str.length() < 2) {
                return;
            }

            orderMap.put(str, orderMap.getOrDefault(str, 0) + 1);
            return;
        }

        subset(order, str, cnt + 1);
        subset(order, str + order.substring(cnt, cnt + 1), cnt + 1);
    }
}