package exercises.arrayBasedStack;

public class ArrayStack<T> {

    private static final int INITIAL_CAPACITY = 16;

    private T[] elements;
    private int size;

    public ArrayStack() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
    }

    public ArrayStack(int capacity) {
        this.elements = (T[]) new Object[capacity];
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(T element) {
        if (size >= elements.length){
            grow();
        }
        this.elements[this.size++] = element;
    }

    public T pop() {
        if(this.size == 0){
            throw new IllegalArgumentException();
        }

        T element = elements[--size];
        this.elements[size] = null;
        return element;
    }

    public T[] toArray() {
        T[] elementsArray = (T[]) new Object[this.size];

        int index = 0;
        for (int i = elementsArray.length - 1;i >= 0; i--) {  //in oreder of input!!
            elementsArray[index++] = this.elements[i];
        }

        return elementsArray;
    }

    private void grow() {
        T[] newElementsArr = (T[]) new Object[this.size()*2];

        for (int a = 0; a < size(); a++) {
            newElementsArr[a] = elements[a];
        }
        this.elements = newElementsArr;
    }

}