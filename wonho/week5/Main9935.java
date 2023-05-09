package wonho.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine(); // 대상 문자열
        String deleteStr = br.readLine(); // 삭제할 문자열

        int strLength = str.length();
        int deleteStrLength = deleteStr.length();

        // 초기에 삭제할 문자열 길이만큼 대상 문자열을 StringBuilder에 삽입
        StringBuilder sb = new StringBuilder(str.substring(0, deleteStrLength));
        for (int i = deleteStrLength; i <= strLength; i++) {
            // StringBuilder에 있는 문자가 삭제할 문자열 길이보다 길고, 끝부분이 삭제할 문자열과 같다면 삭제 -> 즉 스택 pop연산과 동일
            while (sb.length() >= deleteStrLength &&
                    sb.substring(sb.length() - deleteStrLength).equals(deleteStr)) {
                sb.delete(sb.length() - deleteStrLength, sb.length());
            }

            if (i == strLength) {
                break;
            }

            // 끝에 문자를 하나씩 삽입
            sb.append(str.charAt(i));
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
            return;
        }
        System.out.println(sb);
    }
}
