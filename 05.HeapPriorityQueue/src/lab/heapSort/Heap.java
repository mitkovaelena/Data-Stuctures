package lab.heapSort;

public class Heap {    //maxHeap

    public static <E extends Comparable<E>> void sort(E[] array) {

        int n = array.length;
        for (int i = n / 2; i >= 0; i--) {  //constructHeap
            heapifyDown(array, i, n);
        }

        for (int j = n - 1; j > 0; j--) {
            E temp = array[0];
            array[0] = array[j];
            array[j] = temp;
            heapifyDown(array, 0, j);
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] heap, int elementInd, int len) {
        while (elementInd < len / 2) {
            int childInd = 2 * elementInd + 1;
            if (len > childInd + 1 && heap[childInd + 1].compareTo(heap[childInd]) > 0) {
                childInd += 1;
            }

            if (heap[elementInd].compareTo(heap[childInd]) > 0) {
                break;
            }

            E temp = heap[childInd];
            heap[childInd] = heap[elementInd];
            heap[elementInd] = temp;
            elementInd = childInd;
        }
    }
}
