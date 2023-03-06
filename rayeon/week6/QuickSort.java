package week6;

import java.util.Arrays;

/*
 * 퀵 정렬 :
 * - 피봇(기준 데이터)을 설정하고, 그 기준보다 큰 데이터와 작은 데이터의 위치 변경
 * 	 (일반적으로 가장 첫번째 데이터를 피봇으로 설정)
 * - 왼쪽에서부터 피봇보다 큰 데이터를 탐색, 오른쪽에서부터 피봇보다 작은 데이터 탐색
 * - 왼쪽 포인터와 오른쪽 포인터의 위치가 엇갈리는 경우, 작은 데이터와 피봇 위치 변경
 * - 피봇을 기준으로 분할 후 재정렬
 */

public class QuickSort {

    private int[] array;

    public QuickSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public QuickSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public void sort() {
    	quickSort(0, array.length - 1);
    }
    
    public void quickSort(int start, int end) {
    	if (start >= end) 
    		return;
    	
    	int pivot = start;
    	int left = start + 1;
    	int right = end;
    	
    	while (left <= right) {
    		while (left <= end && array[left] < array[pivot]) {
    			left++;
    		}

    		while (right > start && array[right] > array[pivot]) {
    			right--;
    		}
    		
    		if (left > right) { 
    			int tmp = array[right];
    			array[right] = array[pivot];
    			array[pivot] = tmp;
    		} else { 
    			int tmp = array[left];
    			array[left] = array[right];
    			array[right] = tmp;
    		}
    	}

    	quickSort(start, right - 1);
    	quickSort(right + 1, end);
    }
    
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "QuickSort [array=" + Arrays.toString(array) + "]";
    }
}