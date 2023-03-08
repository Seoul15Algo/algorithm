import java.util.*;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // MergeSort 호출
        MergeSort m = new MergeSort(Arrays.copyOf(arr, arr.length));
        m.sort();
        System.out.println(m);

        // QuickSort 호출
        QuickSort q = new QuickSort(Arrays.copyOf(arr, arr.length));
        q.sort();
        System.out.println(q);

        // HeapSort 호출
        HeapSort h = new HeapSort(Arrays.copyOf(arr, arr.length));
        h.sort();
        System.out.println(h);
    }
}
