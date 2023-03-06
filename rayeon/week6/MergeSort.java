package week6;

import java.util.Arrays;

/*
 * 병합 정렬 :
 * - 분할 : 입력 배열을 동일한 크기의 2개의 부분 배열로 분할
 * - 정복 : 부분 배열 정렬 
 * - 결합 : 정렬된 부분 배열을 하나의 배열로 합병
 */

public class MergeSort {

    private int[] array;

    public MergeSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public MergeSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public void sort() {
    	mergeSort(Arrays.copyOf(array, array.length), 0, array.length -1);
    }
    
    public void mergeSort(int tmp[], int start, int end) {
    	if (start < end) {
    		int middle = (start + end) / 2;

    		mergeSort(tmp, start, middle);
    		mergeSort(tmp, middle + 1, end);

    		merge(tmp, start, middle, end);
    	}
    }
    
    public void merge(int tmp[], int start, int middle, int end) {
    	int i = start; 
    	int j = middle + 1; 
    	int k = start;

    	while (i <= middle && j <= end) {
    		if (tmp[i] <= tmp[j]) {
    			array[k] = tmp[i++];
    		} else {
    			array[k] = tmp[j++];
    		}
    		
    		k++;
    	}

    	if (i > middle) {
    		for (int l = j; l <= end; l++) {
				array[k++] = tmp[l];
			}
    	} else {
    		for (int l = i; l <= middle; l++) {
				array[k++] = tmp[l];
			}
    	}

    	for (int l = start; l <= end; l++) {
    		tmp[l] = array[l];
    	}
    }
    
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "MergeSort [array=" + Arrays.toString(array) + "]";
    }
}