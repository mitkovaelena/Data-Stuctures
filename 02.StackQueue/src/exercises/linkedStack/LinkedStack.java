package exercises.linkedStack;

public class LinkedStack<E> {

    private class Node {
        private E value;
        private Node next;

        private Node(E value) {
            this.value = value;
        }

        private E getValue() {
            return value;
        }

        private Node getNext() {
            return next;
        }

        private void setNext(Node next) {
            this.next = next;
        }
    }

    private Node firstNode;
    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        Node oldTop = this.firstNode;
        this.firstNode = new Node(element);
        this.firstNode.setNext(oldTop);
        this.size++;
    }

    public E pop() {
        if (this.size() == 0){
            throw new IllegalArgumentException();
        }
        Node oldTop = this.firstNode;

        if (this.size() == 1){
            this.firstNode = null;
        } else {
           this.firstNode = oldTop.getNext();
        }

        this.size--;
        return oldTop.getValue();
    }

    public E[] toArray() {
        E[] newElementsArr = (E[]) new Object[this.size()*2];
        Node crnt = this.firstNode;
        for (int a = 0; a < size(); a++) {
            newElementsArr[a] = crnt.getValue();
            crnt = crnt.getNext();
        }
        return newElementsArr;
    }
}