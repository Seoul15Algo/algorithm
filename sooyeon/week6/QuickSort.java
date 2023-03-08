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
    public void sort() {
    	quickSort(0,array.length);
    }

    public void quickSort(int start, int end) {
    	if(start+1>=end) return;
		int pivot = array[start];
		int left = start+1;
		int right = end-1;
		while(true) {
			while(left<=right && array[left]<=pivot) left++;
			while(left<=right && array[right] >= pivot) right--;
			if(left>right) break;
			swap(left,right);
		}
		swap(start,right);
		quickSort(start,right);
		quickSort(right+1,end);
	}

	public void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		
	}

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "QuickSort [array=" + Arrays.toString(array) + "]";
    }
}
