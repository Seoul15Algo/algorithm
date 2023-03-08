import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MergeSort {

    private int[] array;
    private int[] temp;

    public MergeSort() {
        this(new int[] { 5, 7, 3, 1, 2, 6, 8, 4 });
    }

    public MergeSort(int[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.temp = Arrays.copyOf(array, array.length);
    }

    // 구현 - 필요하면 매개변수와 메서드를 추가해도 됩니다.
    public void sort() {
        Divide(0, this.array.length - 1);
    }
    //

    private void Divide(int start, int end){
        if(start == end){
            return;
        }
        int mid = (start + end) / 2;

        Divide(start, mid);
        Divide(mid + 1, end);
        Merge(start, mid, end);
    }
    private void Merge(int start, int mid, int end){
        int left = start;
        int right = mid + 1;
        int now = start;

        while(left <= mid && right <= end){
            if(array[left] < array[right]){
                temp[now++] = array[left++];
                continue;
            }
            temp[now++] = array[right++];
        }

        while(left <= mid){
            temp[now++] = array[left++];
        }
        while(right <= end){
            temp[now++] = array[right++];
        }

        for(int i = start; i <= end; i++){
            array[i] = temp[i];
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
