public class Solution_두_큐_합_같게_만들기 {

    public int solution(int[] queue1, int[] queue2) {
        // 핵심: 두 큐를 하나로 합쳐서 binary search 수행
        int[] totalQueue = new int[queue1.length + queue2.length];
        long queue1Sum = 0;
        long queue2Sum = 0;

        for (int i = 0; i < queue1.length; i++) {
            int val = queue1[i];
            totalQueue[i] = val;
            queue1Sum += val;
        }

        for (int i = queue1.length; i < queue1.length + queue2.length; i++) {
            int val = queue2[i - queue1.length];
            totalQueue[i] = val;
            queue2Sum += val;
        }

        // early return - 합을 같게 만들기가 불가능한 상황
        if ((queue1Sum + queue2Sum) % 2 == 1) {
            return -1;
        }

        int count = 0;
        int left = 0;
        int right = queue1.length;
        long half = (queue1Sum + queue2Sum) / 2;
        // 반복문 탈출 -> 두 큐를 같게 만들 수 없는 상황
        while (left < right && right < totalQueue.length) {
            if (queue1Sum == half) {
                return count;
            } else if (queue1Sum > half) {
                queue1Sum -= totalQueue[left++];    // 첫번째 큐의 합이 더 크다 -> left 포인터 이동
            } else {
                queue1Sum += totalQueue[right++];   // 첫번쨰 큐의 합이 더 작다 -> right 포인터 이동
            }
            count++;
        }
        return -1;
    }
}
