package lab.binaryHeap;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {  //max-Heap

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<T>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void insert(T element) {
        this.heap.add(element);
        heapifyUp(this.size());
        size++;
    }

    private void heapifyUp(int elementInd) {
        int parentInd = (elementInd - 1) / 2;
        while (elementInd > 0 && this.heap.get(elementInd).compareTo(this.heap.get(parentInd)) > 0) {
            T temp = this.heap.get(parentInd);
            this.heap.set(parentInd, this.heap.get(elementInd));
            this.heap.set(elementInd, temp);
            elementInd = parentInd;
            parentInd = (elementInd - 1) / 2;
        }
    }

    public T peek() {
        return this.heap.get(0);
    }

    public T pull() {
        if (this.heap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        T maxElem = this.heap.get(0);
        this.size--;
        this.heap.set(0, this.heap.get(this.size()));
        this.heap.set(this.size(), maxElem);
        this.heap.remove(this.size());
        heapifyDown(0);
        return maxElem;
    }

    private void heapifyDown(int elementInd) {
        while (elementInd < this.size() / 2) {
            int childInd = 2 * elementInd + 1;
            if (this.heap.size() > childInd + 1 && this.heap.get(childInd + 1).compareTo(this.heap.get(childInd)) > 0) {
                childInd += 1;
            }

            if (this.heap.get(elementInd).compareTo(this.heap.get(childInd)) > 0) {
                break;
            }

            T temp = this.heap.get(childInd);
            this.heap.set(childInd, this.heap.get(elementInd));
            this.heap.set(elementInd, temp);
            elementInd = childInd;
        }
    }
}
