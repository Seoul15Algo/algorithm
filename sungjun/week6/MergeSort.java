package week6;

import java.util.Arrays;

public class MergeSort {

    private int[] array;

    public MergeSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public MergeSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort(int[] arr, int start, int end) {
    	if(end - start < 2) return;
    	
    	int mid = (start + end) / 2;
    	sort(arr, 0, mid);
    	sort(arr, mid, end);
    	merge(arr, start, mid, end);
    }
    
    private void merge(int[] arr, int start, int mid, int end) {
    	int[] temp = new int[end-start];
    	int t = 0;
    	int i = start;
    	int j = mid;
    	
    	while(i < mid && j < end) {
    		if(arr[i] < arr[j]) {
    			temp[t++] = arr[i++];
    			continue;
    		}
    		temp[t++] = arr[j++];
    	}
    	
    	while(i < mid) temp[t++] = arr[i++];
    	while(j < end) temp[t++] = arr[j++];
    	
    	for (int k = start; k < end; k++) {
			arr[k] = temp[k-start];
		}
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
        return "MergeSort [array=" + Arrays.toString(array) + "]";
    }
}
