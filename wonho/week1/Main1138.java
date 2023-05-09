import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main1138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] numbers = br.readLine().split(" ");

        int number = n;
        List<Integer> answer = new ArrayList<>();
        for (int i = n-1; i >= 0; i--) {
            int left = Integer.parseInt(numbers[i]);

            if (left == 0) {
                answer.add(0, number--);
                continue;
            }

            int count = 0;
            for (int j = 0; j < answer.size(); j++) {
                if (answer.get(j) > number) {
                    count++;
                }

                if (count == left) {
                    answer.add(j+1, number--);
                    break;
                }
            }
        }

        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i));
            if (i < answer.size()-1) {
                System.out.print(" ");
            }
        }
    }
}
