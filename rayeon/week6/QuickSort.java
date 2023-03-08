package week6;

import java.util.Arrays;

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