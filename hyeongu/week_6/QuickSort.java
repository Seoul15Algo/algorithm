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
        Quick(0, this.array.length - 1);
    }
    //

    private void Quick(int start, int end){
        if(start == end) return;

        int piv = array[(start + end) / 2];
        int left = start;
        int right = end;

        while(left <= right){
            while(array[left] < piv){
                left++;
            }

            while(array[right] > piv){
                right--;
            }

            if(left <= right){
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;
                right--;
            }
        }

        if(start < left - 1){
            Quick(start, left - 1);
        }
        if(left + 1 < end){
            Quick(left, end);
        }
    }
    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "QuickSort [array=" + Arrays.toString(array) + "]";
    }
}
