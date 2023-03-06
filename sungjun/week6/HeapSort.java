package week6;

import java.util.Arrays;

public class HeapSort {

    private int[] array;

    public HeapSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public HeapSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort() {
    	int size = array.length;
    	
    	for (int i = size/2-1; i >= 0; i--) {
			heapify(array, size, i);
		}
    	
    	for (int i = size-1; i > 0; i--) {
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			
			heapify(array, i, 0);
		}
    }
    
    private void heapify(int[] arr, int n, int m) {
    	int parent = m;
    	int left = parent * 2 + 1;
    	int right = parent * 2 + 2;
    	
    	if(left < n && arr[parent] < arr[left]) parent = left;
    	if(right < n && arr[parent] < arr[right]) parent = right;
    	
    	if(m != parent) {
    		int temp = arr[parent];
    		arr[parent] = arr[m];
    		arr[m] = temp;
    		
    		heapify(arr, n, parent);
    	}
	}
	//
    
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "HeapSort [array=" + Arrays.toString(array) + "]";
    }
}
