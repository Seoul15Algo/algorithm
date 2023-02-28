package wonho.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main9935 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String deleteStr = br.readLine();

        int strLength = str.length();
        int deleteStrLength = deleteStr.length();
        StringBuilder sb = new StringBuilder(str.substring(0, deleteStrLength));
        for (int i = deleteStrLength; i <= strLength; i++) {
            while (sb.length() >= deleteStrLength &&
                    sb.substring(sb.length() - deleteStrLength).equals(deleteStr)) {
                sb.delete(sb.length() - deleteStrLength, sb.length());
            }

            if (i == strLength) {
                break;
            }

            sb.append(str.charAt(i));
        }

        if (sb.length() == 0) {
            System.out.println("FRULA");
            return;
        }
        System.out.println(sb);
    }
}
