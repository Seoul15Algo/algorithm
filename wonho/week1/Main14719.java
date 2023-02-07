import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] hw = br.readLine().split(" ");
        List<Integer> blocks = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int h = Integer.parseInt(hw[0]);
        int w = Integer.parseInt(hw[1]);

        int left = 0;
        int right = w-1;

        int answer = 0;
        while (left < right) {
            int leftBlockHeight = blocks.get(left);
            int rightBlockHeight = blocks.get(right);

            int water = 0;
            if (leftBlockHeight <= rightBlockHeight) {
                while (left < right && blocks.get(left) <= leftBlockHeight) {
                    left++;
                    if (leftBlockHeight - blocks.get(left) <= 0) {
                        continue;
                    }
                    water += leftBlockHeight - blocks.get(left);
                }
            }

            if (leftBlockHeight > rightBlockHeight) {
                while (left < right && blocks.get(right) <= rightBlockHeight) {
                    right--;
                    if (rightBlockHeight - blocks.get(right) <= 0) {
                        continue;
                    }
                    water += rightBlockHeight - blocks.get(right);
                }
            }
            answer += water;
        }
        System.out.println(answer);
    }
}
