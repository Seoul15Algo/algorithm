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
    public void sort() {
    	mergeSort(Arrays.copyOf(array, array.length),0,array.length);
    }
    public void mergeSort(int[] tmp, int start, int end) {
		if(start+1== end) return;
		int mid = (start+end)/2;
		mergeSort(tmp,start,mid);
		mergeSort(tmp,mid,end);
		merge(tmp,start,end);
	}

	public void merge(int[] tmp, int start, int end) {
		int mid = (start+end)/2;
		int left = start;
		int right = mid;
		for (int i = start; i < end; i++) {
			if(left == mid) tmp[i] = array[right++];
			else if(right == end) tmp[i] = array[left++];
			else if(array[left] <= array[right]) tmp[i] = array[left++];
			else tmp[i] = array[right++];
		}
		for (int i = start; i < end; i++) {
			array[i] = tmp[i];
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
