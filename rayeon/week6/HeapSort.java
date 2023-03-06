package week6;

import java.util.Arrays;

/*
 * 힙 정렬 :
 * - 정렬할 n개의 요소를 완전 이진 트리 형태로 만든다.
 *   (완전 이진 트리 : 모든 노드의 자식 노드가 2개 이하인 트리 구조)
 * - 힙에 새로운 요소가 들어오면, 일단 힙의 마지막 노드에 삽입
 * - 새로운 노드와 부모 노드를 비교하면서 교환
 * - 루트 노드에 있는 요소를 배열의 맨 뒤에 저장하면서 정렬 수행
 */

public class HeapSort {

    private int[] array;

    public HeapSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public HeapSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public void sort() {
    	for (int i = 1; i < array.length; i++) {
    		int child = i;
    		
    		do {
    			int parent = (child - 1) / 2; 

    			if (array[child] > array[parent]) {
    				int tmp = array[child];
    				array[child] = array[parent];
    				array[parent] = tmp;
    			}

    			child = parent;
    		} while (child != 0);
    	}

    	for (int i = array.length - 1; i >= 0; i--) {
    		int tmp = array[0];
    		array[0] = array[i];
    		array[i] = tmp;
    		
    		int parent = 0;
    		int child = 1;
    		do {
    			if (array[child] < array[child + 1] && child < i - 1) {
    				child++;
    			}

    			if (array[parent] < array[child] && child < i) {
    				tmp = array[parent];
    				array[parent] = array[child];
    				array[child] = tmp;
    			}

    			parent = child;
    			child = 2 * parent + 1;
    		} while (child < i);
    	}
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "HeapSort [array=" + Arrays.toString(array) + "]";
    }
}