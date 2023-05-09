package week9;

public class BinaryTree {
	// 포화이진트리 생성
	static String PBT(String binary) {
        StringBuilder pbt = new StringBuilder();
        
        int treeHeight = (int) Math.floor(Math.log(binary.length()) / Math.log(2));
        int treeSize = (int) Math.pow(2, treeHeight + 1) - 1;
        
        for (int i = 0; i < (treeSize - binary.length()); i++) {
            pbt.append("0");
        }
        
        pbt.append(binary);
        
        return pbt.toString();
    }
    
    static boolean check(String pbt, int start, int end, char parent) {
        if (start > end) { // 범위를 벗어난 경우
            return true;
        }
        
        int root = (start + end) / 2;
        
        // 부모 노드가 더미 노드인데 자식 노드가 더미 노드가 아닌 경우
        if (parent == '0' && pbt.charAt(root) == '1') {
            return false;
        }
        
        // 부모 노드가 더미 노드인데 자식 노드가 더미 너드인 경우
        // 부모 노드가 더미 노드가 아닌 경우
        return check(pbt, start, root - 1, pbt.charAt(root)) && check(pbt, root + 1, end, pbt.charAt(root));
    }
    
    public int[] solution(long[] numbers) {
        int size = numbers.length;
        int[] answer = new int[size];
        
        for (int i = 0; i < size; i++) {
            String pbt = PBT(Long.toBinaryString(numbers[i]));
                          // 트리, 시작 인덱스, 종료 인덱스, 부모 노드 값
            answer[i] = check(pbt, 0, pbt.length() - 1, '1') ? 1 : 0;
        }
        
        return answer;
    }
}
