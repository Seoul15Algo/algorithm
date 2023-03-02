package wonho.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main16934 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        Map<String, Integer> nicknameCount = new HashMap<>(); // 닉네임 접두사 기록
        Map<String, Integer> joinCount = new HashMap<>(); // 닉네임이 나온 횟수 기록
        List<String> aliases = new ArrayList<>(); // 추출된 닉네임 리스트 저장

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            int strLength = str.length();

            boolean isFirst = false; // 처음 넣은 것인지 ex) baekjoon -> b, ba, bae 에서 b를 처음 넣으면 b에서 true체크
            boolean put = false; // 넣은적이 있는지
            joinCount.put(str, joinCount.getOrDefault(str, 0) + 1); // 닉네임 횟수 저장
            for (int j = 1; j <= strLength; j++) {
                String sub = str.substring(0, j);
                // 접두사가 map에 없으면 기록하고, 그 접두사 기록이 첫번째라면 닉네임으로 저장
                if (nicknameCount.get(sub) == null) {
                    nicknameCount.put(sub, 1);
                    if (!isFirst) {
                        aliases.add(sub);
                        isFirst = true;
                    }
                    put = true;
                }
            }

            // 넣은 적이 없다면 닉네임 나온 횟수에서 값을 가져와 닉네임에 적용 후 카운트 증가
            if (!put) {
                int count = joinCount.get(str);
                String nextAlias;
                if (count == 1) {
                    nextAlias = str;
                } else {
                    nextAlias = str + count;
                }
                nicknameCount.put(nextAlias, 1);
                aliases.add(nextAlias);
            }
        }

        for (String alias : aliases) {
            sb.append(alias).append("\n");
        }
        System.out.print(sb);
    }
}
