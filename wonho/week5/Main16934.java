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
        Map<String, Integer> nicknameCount = new HashMap<>();
        Map<String, Integer> joinCount = new HashMap<>();
        List<String> aliases = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            int strLength = str.length();
            boolean isFirst = false;
            boolean put = false;
            joinCount.put(str, joinCount.getOrDefault(str, 0) + 1);
            for (int j = 1; j <= strLength; j++) {
                String sub = str.substring(0, j);
                if (nicknameCount.get(sub) == null) {
                    nicknameCount.put(sub, 1);
                    if (!isFirst) {
                        aliases.add(sub);
                        isFirst = true;
                    }
                    put = true;
                }
            }

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
