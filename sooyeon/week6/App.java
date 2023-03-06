public class App {
	
	public static void main(String[] args) {
		MergeSort msort = new MergeSort();
		msort.sort();
		System.out.println(msort.toString());
		
		QuickSort qsort = new QuickSort();
		qsort.sort();
		System.out.println(qsort.toString());
		
		HeapSort hsort = new HeapSort();
		hsort.sort();
		System.out.println(hsort.toString());
	}

}
