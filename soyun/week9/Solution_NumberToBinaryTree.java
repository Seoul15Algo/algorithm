package com.ssafy.algo230329_Random1.soyun.week9;

import java.util.ArrayList;
import java.util.List;

public class Solution_NumberToBinaryTree {

    String binaryNumber;
    boolean[] binaryTree;
    int idx;

    public boolean isBinaryTree(int root){
        // TODO: 이진트리인지 검사

        // 리프노드는 무조건 true
        if (root >= binaryTree.length) {
            return true;
        }

        // 자식 노드는 true 인데 부모 노드는 false 일 때 -> 이진트리가 성립하지 않는다
        if (binaryTree[root] && !binaryTree[root / 2]){
            return false;
        }

        // 비트 연산, 하위 서브트리 중 하나라도 false 를 반환한다면 이진트리는 성립하지 않는다
        return (isBinaryTree(root * 2) & isBinaryTree(root * 2 + 1));
    }

    public void fill(int root){
        if (root >= binaryTree.length){
            return;
        }

        // 좌측 서브트리 -> 부모 -> 우측 서브트리 순으로 숫자 채움
        fill(root * 2);

        binaryTree[root] = binaryNumber.charAt(idx++) == '1';

        fill(root * 2 + 1);
    }

    public void makeBinaryTree(){
        // TODO: 이진트리 만들기
        binaryTree = new boolean[binaryNumber.length() + 1];
        binaryTree[0] = true;
        idx = 0;

        fill(1);

    }

    public int getMaxNodeCapacity(int len){
        // TODO: 포화이진트리의 노드 개수
        int i = 1;
        while (i - 1 < len){
            i = (i << 1);
        }
        return i - 1;
    }

    public boolean validateNumber(long number){
        // TODO: 숫자 -> 이진수 -> 포화이진트리로 변환
        binaryNumber = Long.toBinaryString(number);
        int maxNodeCapacity = getMaxNodeCapacity(binaryNumber.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxNodeCapacity - binaryNumber.length(); i++){
            sb.append("0");
        }
        binaryNumber = sb.append(binaryNumber).toString();
        //System.out.println(binaryNumber);

        makeBinaryTree();   // 이진트리로 변환한다

        // 변환한 이진트리가 이진트리인지 검사
        if (isBinaryTree(1)){
            return true;
        }

        return false;
    }

    public List<Integer> solution(long[] numbers){
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < numbers.length; i++) {
            // TODO: 입력한 숫자를 이진트리로 표현할 수 있는지 검사
            if (validateNumber(numbers[i])){
                answer.add(1);
                continue;
            }
            answer.add(0);
        }

        return answer;
    }
}