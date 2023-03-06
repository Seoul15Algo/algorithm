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

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort(int[] arr, int start, int end) {
    	if(start >= end) return;
    	
    	int pivot = start;
    	int i = start+1;
    	int j = end-1;
    	
    	while(i <= j) {
	    	while(arr[pivot] > arr[i] && i < end-1) i++;
	    	while(arr[pivot] < arr[j] && j > start) j--;

	    	if(i < j) {
		    	int temp = arr[j];
		    	arr[j] = arr[i];
		    	arr[i] = temp;
		    	continue;
	    	}
	    	break;
    	}
    	
    	int temp = arr[j];
    	arr[j] = arr[pivot];
    	arr[pivot] = temp;
    	
    	sort(arr, 0, j);
    	sort(arr, j+1, end);
    }
    
    public int[] getArray() {
    	return this.array;
    }
    //
    
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "QuickSort [array=" + Arrays.toString(array) + "]";
    }
}
