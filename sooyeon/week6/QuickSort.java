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
    	quickSort(0, array.length -1);
    }
    public void quickSort(int start, int end) {
        if (start >= end)
          return;

        int pivot = start;
        int lo = start + 1;
        int hi = end;
        
        while (lo <= hi) {
          while (lo <= end && array[lo] <= array[pivot]) lo++;
          while (hi > start && array[hi] >= array[pivot]) hi--;
          if (lo > hi)
            swap(hi, pivot);
          else
            swap(lo, hi);
          }
    	
        quickSort(start, hi - 1);
        quickSort(hi + 1, end);

      }

      public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
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
