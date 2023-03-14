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
    	//전체 트리를 최대 힙 구조로 바꿈
    	for (int i = 1; i < array.length; i++) {
			int c = i;
			do {
				int root = (c-1)/2; //특정 원소의 부모를 가리키게 됨
				if(array[root] < array[c]) { //부모의 값이 자식의 값보다 더 작다면
					int temp = array[root];
					array[root] = array[c];
					array[c] = temp;
				}
				c = root;
			} while(c!=0);
		}
    	//크기를 줄여가며 힙 구성
    	for (int i = array.length-1; i >= 0; i--) {
    		//가장 큰 값을 맨뒤로 보냄
			int temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			
			//힙구조 만드는 부분
			int root = 0;
			int c = 1;
			do {
				c = 2*root +1;
				//자식 중 더 큰 값을 찾기
				if(c < i-1 && array[c] < array[c+1]) { //오른쪽이 더크면 c값 더해줌
					c++;
				}
				//루트보다 자식이 더 크면 교환
				if(c < i && array[root]< array[c] ) {
					temp = array[root];
					array[root] = array[c];
					array[c] = temp;
				}
				root = c;
			}while(c<i);
		}
    }

    public void printArray() {
        System.out.println(Arrays.toString(array));
    }

    @Override
    public String toString() {
        return "HeapSort [array=" + Arrays.toString(array) + "]";
    }
}
