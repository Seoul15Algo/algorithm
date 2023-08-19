package week24;

import java.util.*;

class 메뉴리뉴얼 {
    static Map<String, Integer> courseMenu = new HashMap<>();

    public ArrayList<String> solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        boolean[] isCourse = new boolean[11];
        boolean[] isMade = new boolean[11];
        int[] courseCount = new int[11];

        // 가능한 모든 메뉴 조합 생성
        for(int i = 0; i < orders.length; i++) {
            char[] menu = orders[i].toCharArray();
            makeSubset(menu, 0, "");
        }

        // 코스로 만들 요리 개수 체크
        for(int i = 0; i < course.length; i++) {
            isCourse[course[i]] = true;
        }

        // Map 정렬 (많이 주문된 순서 -> 메뉴 개수 순으로)
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(courseMenu.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o2.getValue() == o1.getValue()) {
                    return o2.getKey().length() - o1.getKey().length();
                }
                return o2.getValue() - o1.getValue();
            }
        });

        for(Map.Entry<String, Integer> entry : entryList) {
            if(entry.getValue() <= 1) break;

            // 현재 코스의 메뉴 개수
            int courseLength = entry.getKey().length();

            // 코스로 만들 요리 개수와 다르다면 패스
            if(!isCourse[courseLength]) continue;

            // 같은 메뉴 개수의 코스가 있는지 확인
            if(isMade[courseLength]) {
                // 이미 같은 메뉴 개수의 코스가 있지만 지금 코스도 메뉴 개수가 동일하다면 추가
                if(entry.getValue() == courseCount[courseLength]) {
                    answer.add(sortString(entry.getKey()));
                }
                continue;
            }

            answer.add(sortString(entry.getKey()));

            isMade[courseLength] = true;
            courseCount[courseLength] = entry.getValue();
            continue;
        }

        // 알파벳 순 정렬
        Collections.sort(answer);

        return answer;
    }

    public String sortString(String unsortedString) {
        char[] cs = unsortedString.toCharArray();
        Arrays.sort(cs);

        return new String(cs);
    }

    // 해당 주문으로 만들 수 있는 모든 조합 생성
    public void makeSubset(char[] menu, int cnt, String menus) {
        if(cnt == menu.length) {
            if(menus.length() < 2) return;

            char[] cs = menus.toCharArray();
            Arrays.sort(cs);
            menus = new String(cs);

            if(!courseMenu.containsKey(menus)) {
                courseMenu.put(menus, 1);
                return;
            }

            courseMenu.put(menus, courseMenu.get(menus)+1);

            return;
        }

        makeSubset(menu, cnt+1, menus+menu[cnt]);
        makeSubset(menu, cnt+1, menus);
    }
}