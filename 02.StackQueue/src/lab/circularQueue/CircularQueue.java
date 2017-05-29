package lab.circularQueue;

public class CircularQueue<E> {
    private static int INITIAL_CAPACITY = 16;
    private int size;
    E[] elements;
    int startIndex;
    int endIndex;

    public CircularQueue() {
        this.elements = (E[]) new Object[INITIAL_CAPACITY];
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (size >= elements.length){
            grow();
        }
        elements[endIndex] = element;
        endIndex = (endIndex+1) % this.elements.length;
        size++;
    }

    public E dequeue() {
        if(this.size == 0){
            throw new IllegalArgumentException();
        }

        E element = elements[startIndex];
        startIndex = (startIndex+1) % this.elements.length;
        size--;
        return element;
    }

    public E[] toArray() {
        E[] elementsArray = (E[]) new Object[this.size];

        int sourceInd = this.startIndex;
        int destInd = 0;
        for (int a = 0; a < size(); a++) {
            elementsArray[destInd++] = elements[sourceInd];
            sourceInd = (sourceInd+1) % this.elements.length;
        }

        return elementsArray;
    }

    private void grow() {
       E[] newElementsArr = (E[]) new Object[this.size()*2];

        int sourceInd = this.startIndex;
        int destInd = 0;
        for (int a = 0; a < size(); a++) {
            newElementsArr[destInd++] = elements[sourceInd];
            sourceInd = (sourceInd+1) % this.elements.length;
        }

        elements = newElementsArr;
        startIndex = 0;
        endIndex = this.size;
    }
}
