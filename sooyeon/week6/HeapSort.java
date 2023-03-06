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
    	int len = array.length;
        for(int i = len/2;i>0;i--){
            MAX_HEAPIFY(i,len);
        }
        do{
            int tmp = array[0];
            array[0] = array[len-1];
            array[len-1] = tmp;
            len = len-1;
            MAX_HEAPIFY(1,len);
        }while(len > 1);

    }
    
    public void MAX_HEAPIFY(int i, int len){
        int j;
        int tmp = array[i-1];
        while(i<=len/2){ // 자식 존재 여부
            j = i*2;
            if((j<len) && (array[j-1] <array[j])){
                j++;
            }
            if(tmp >= array[j-1]){
                break;
            }
            array[i-1] = array[j-1];
            i=j;
        }
        array[i-1] = tmp;
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
