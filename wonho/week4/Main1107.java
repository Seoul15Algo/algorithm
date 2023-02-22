package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1107 {
    private static boolean[] brokeButtons;
    private static int channel;
    private static int length;
    private static int minCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        channel = Integer.parseInt(input);
        length = input.length();
        int m = Integer.parseInt(br.readLine());
        brokeButtons = new boolean[10];

        if (m != 0) {
            String[] buttons = br.readLine().split(" ");
            for (String button : buttons) {
                brokeButtons[Integer.parseInt(button)] = true;
            }
        }

        minCount = Math.abs(channel - 100);

        if (minCount == 0) {
            System.out.println(minCount);
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (brokeButtons[i]) {
                continue;
            }
            search( 1, i);
        }

        System.out.println(minCount);
    }

    public static void search(int count, int number) {
        if (count == 7) {
            return;
        }

        minCount = Math.min(minCount, count + Math.abs(channel - number));
        for (int i = 0; i < 10; i++) {
            if (brokeButtons[i]) {
                continue;
            }
            search(count + 1, number + (int) (Math.pow(10, count) * i));
        }
    }
}
