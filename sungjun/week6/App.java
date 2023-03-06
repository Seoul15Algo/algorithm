package week6;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSort ms = new MergeSort();
		ms.sort(ms.getArray(), 0, ms.getArray().length);
		ms.printArray();
		
		QuickSort qs = new QuickSort();
		qs.sort(qs.getArray(), 0, qs.getArray().length);
		qs.printArray();
		
		HeapSort hs = new HeapSort();
		hs.sort();
		hs.printArray();
	}

}


