package week6;

public class App {
    public static void main(String[] args) {

        MergeSort ms = new MergeSort();
        ms.sort();
        System.out.println(ms);

        QuickSort qs = new QuickSort();
        qs.sort();
        System.out.println(qs);

        HeapSort hs = new HeapSort();
        hs.sort();
        System.out.println(hs);
    }
}
