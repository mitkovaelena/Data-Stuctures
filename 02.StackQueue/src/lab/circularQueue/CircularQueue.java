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
        if (this.size() >= elements.length){
            grow();
        }
        this.elements[this.endIndex] = element;
        this.endIndex = (this.endIndex+1) % this.elements.length;
        this.setSize(this.size() + 1);

    }

    public E dequeue() {
        if(this.size == 0){
            throw new IllegalArgumentException();
        }

        E element = this.elements[startIndex];
        this.startIndex = (this.startIndex+1) % this.elements.length;
        this.setSize(this.size() - 1);
        return element;
    }

    public E[] toArray() {
        E[] elementsArray = (E[]) new Object[this.size];

        int sourceInd = this.startIndex;
        int destInd = 0;
        for (int a = 0; a < this.size(); a++) {
            elementsArray[destInd++] = this.elements[sourceInd];
            sourceInd = (sourceInd+1) % this.elements.length;
        }

        return elementsArray;
    }

    private void grow() {
       E[] newElementsArr = (E[]) new Object[this.size()*2];

        int sourceInd = this.startIndex;
        int destInd = 0;
        for (int a = 0; a < this.size(); a++) {
            newElementsArr[destInd++] = this.elements[sourceInd];
            sourceInd = (sourceInd+1) % this.elements.length;
        }

        this.elements = newElementsArr;
        this.startIndex = 0;
        this.endIndex = this.size();
    }
}
