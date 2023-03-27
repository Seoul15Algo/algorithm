package week9;

public class BinaryTree {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        for (long number : numbers) {
            if (search(number)) {
                answer[idx++] = 1;
                continue;
            }
            answer[idx++] = 0;
        }

        return answer;
    }

    public boolean search(long number) {
        String binary = Long.toBinaryString(number);
        long length = binary.length();
        long total = 0;
        int count = 0;
        while (length > total) {
            total += Math.pow(2, count);
            count++;
        }
        long addCount = total - length;
        binary = "0".repeat((int) addCount) + binary;

        if (binary.equals("1")) {
            return true;
        }

        return checkParent(binary, 0, binary.length() - 1);
    }

    public boolean checkParent(String binary, int left, int right) {
        if (right == left) {
            return true;
        }

        int mid = (left + right) / 2;
        boolean current = true;
        if (binary.charAt(mid) == '0') {
            current = (binary.charAt((left + mid - 1) / 2) != '1') && (binary.charAt((mid + 1 + right) / 2) != '1');
        }

        return current && checkParent(binary, left, mid - 1) && checkParent(binary, mid + 1, right);
    }
}
