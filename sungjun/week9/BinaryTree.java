package week9;

public class BinaryTree {
	public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
			char[] fullBinaryTree = generateBinaryTree(numbers[i]);
						
			// 포화이진트리인지 확인
			if(isFullTree(0, fullBinaryTree.length-1, fullBinaryTree)) {
				answer[i] = 1;
				continue;
			}
			
			answer[i] = 0;
		}
        
        return answer;
    }
	
	private static char[] generateBinaryTree(long num) {		
		String sbinaryNum = Long.toBinaryString(num);	// 이진수 변환		
		int logPow = (int)(Math.log(sbinaryNum.length()) / Math.log(2))+1;
		
		int fullTreeNode = (int)(Math.pow(2, logPow) - 1);		// 포화이진트리 노드 개수
		char[] fullTree = new char[fullTreeNode];		// 포화이진트리 생성
		
		int index = 0;
		
		// 이진수를 포화이진트리로 변환
		for (int i = 0; i < fullTreeNode - sbinaryNum.length(); i++) {
			fullTree[i] = '0';
			index = i+1;
		}
		
		for (int i = 0; i < sbinaryNum.length(); i++) {
			fullTree[i+index] = sbinaryNum.charAt(i);
		}
		
		return fullTree;
	}
	
	private static boolean isFullTree(int start, int end, char[] fullTree) {
		// 기저조건 (leaf 노드 도달)
		if(end - start < 2) {
			return true;
		}
		
		// 루트 노드 확인
		int mid = (start+end) / 2;
		char root = fullTree[mid];
		
		// 루트 노드가 0이면 자식 노드는 모두 0이여야 한다
		if(root == '0') {
			if(fullTree[(start+mid-1)/2] == '1' || fullTree[(mid+end+1)/2] == '1') {
				return false;
			}
		}
		
		// 위의 조건을 통과했다면 자식 노드로 내려가면서 계속 검증
		if(isFullTree(start, mid, fullTree) && isFullTree(mid+1, end, fullTree)) {
			return true;
		}
		
		return false;
		
	}
}
