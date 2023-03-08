package week6;

public class App {

	public static void main(String[] args) {
		HeapSort heap = new HeapSort();
		heap.sort();
		System.out.println(heap);
		
		MergeSort merge = new MergeSort();
		merge.sort();
		System.out.println(merge);
		
		QuickSort quick = new QuickSort();
		quick.sort();
		System.out.println(quick);
	}
}
